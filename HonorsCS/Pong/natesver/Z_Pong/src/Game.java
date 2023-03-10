import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//Honors Computer Science - Mr. Uhl
//Program description: A template class for creating graphical applications

public class Game extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	// Variables for the class
	private static final long serialVersionUID = "You're Mother".hashCode();
	public static final int PREF_W = 1200;
	public static final int PREF_H = 850;

	// assets
	public static Clip collide;
	public static Clip lose;
	public static Clip resume;
	public static Clip gameend;
	public static final Image hankSmiles = new ImageIcon("img/hank.jpg").getImage();
	public static final Image hankSad = new ImageIcon("img/hankmad.jpg").getImage();
	public static final Image ballImage = new ImageIcon("img/sugar.png").getImage();
	public static final Image paddle = new ImageIcon("img/walter_3.png").getImage();
	public static Image hank;

	protected static float opacity = 1f;
	protected static final float subtract = 0.05f;

	// Playing: Playing
	// Paused: Paused
	// Waiting: In between rounds
	// Complete: Someone won the game

	public static String playState = "WaitToStart";
	public static String winCondition = "3";
	public static int maxPoints = 3;
	public static String winner = "";

	public static String whoScored = "";
	public static String whoBounced = "P1";
	public static Player player1 = new Player(30, 100, 20, 100);
	public static Player player2 = new Player(PREF_W - 30 - 20, 100, 20, 100);
	public static Ball ball = new Ball(PREF_W / 2, PREF_H / 2, 45, 10, 10);
	public static boolean w, s, up, down;

	// Arrow Animation
	private Image g(int p) {
		String pp = "src/img/arr/pixil-layer-" + p;
		return new ImageIcon(pp + ".png").getImage();
	}

	private Image[] arrowFrames = { g(0), g(1), g(2), g(3), g(4) };
	private int currentFrame = 0;
	private int arrowScale = 3;
	private Image arrow = arrowFrames[currentFrame];

	private static void rst() {
		Game.resume.stop();
		Game.resume.setFramePosition(0);
		Game.resume.start();
		playState = "Complete";

		ball = new Ball(PREF_W / 2, PREF_H / 2, 45, 10, 10);
		whoScored = "";
		whoBounced = "P1";
	}

	public static void testScores(int p1, int p2) {
		if (p1 == maxPoints) {
			winner = "P1";
			rst();
		} else if (p2 == maxPoints) {
			winner = "P2";
			rst();
		}
	}

	private Timer animateTimer = new Timer(200, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (currentFrame == 4)
				currentFrame = 0;
			else
				currentFrame++;
			arrow = arrowFrames[currentFrame];
		}
	});

	private Timer physicsTimer = new Timer(1000 / 60, new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if (opacity != 0) {
				opacity -= subtract;
			}

			if (playState.equals("Playing")) {
				player1.physics(w, s);
				player2.physics(up, down);
				ball.physics();
			}
		}
	});

	private Timer repaintTimer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});

	// Class constructor
	@SuppressWarnings("resource")
	public Game() {
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);

		physicsTimer.start();
		repaintTimer.start();
		animateTimer.start();
		player1.insideColor = new Color(255, 0, 0, 100);
		player2.insideColor = new Color(0, 0, 255, 100);

		try {
			// Open an audio input stream
			final File boomfile = new File("aud/bonk.wav");
			final File losefile = new File("aud/fartxd.wav");
			final File startfile = new File("aud/lessgo.wav");
			final File endfile = new File("aud/dundun.wav");
			// set it to the audio
			final AudioInputStream boomaudio = AudioSystem.getAudioInputStream(boomfile);
			final AudioInputStream loseaudio = AudioSystem.getAudioInputStream(losefile);
			final AudioInputStream startaudio = AudioSystem.getAudioInputStream(startfile);
			final AudioInputStream endaudio = AudioSystem.getAudioInputStream(endfile);
			// get the clip
			collide = AudioSystem.getClip();
			lose = AudioSystem.getClip();
			resume = AudioSystem.getClip();
			gameend = AudioSystem.getClip();
			collide.open(boomaudio);
			lose.open(loseaudio);
			resume.open(startaudio);
			gameend.open(endaudio);

			gameend.stop();
			gameend.setFramePosition(0);
			gameend.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The method used to add graphical images to the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		player1.drawMe(g2);
		player2.drawMe(g2);
		ball.drawMe(g2);

		String text = player1.score + "                       " + player2.score;

		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Monaco", Font.PLAIN, 30));
		g2.drawString(text, PREF_W / 2 - g2.getFontMetrics().stringWidth(text) / 2, 50);

		g2.setColor(Color.ORANGE);
		FontMetrics fm = g2.getFontMetrics();
		if (playState.equals("Waiting")) {

			int ax;
			int aw;
			int ay = ball.y - (arrow.getHeight(this) * arrowScale / 2);
			int ah = arrow.getHeight(this) * arrowScale;
			if (whoScored.equals("P2")) {
				ax = PREF_W / 2 + arrow.getWidth(this);
				aw = arrow.getWidth(this) * arrowScale;
			} else {
				ax = PREF_W / 2 - arrow.getWidth(this);
				aw = arrow.getWidth(this) * -arrowScale;
			}

			g2.drawImage(arrow, ax, ay, aw, ah, this);

			text = whoScored + " scored! Press <SPACE> to resume.";
			g2.drawString(text, PREF_W / 2 - fm.stringWidth(text) / 2, 200);

		} else if (playState.equals("WaitToStart")) {

			g2.drawImage(arrow, /* x */ PREF_W / 2 + arrow.getWidth(this),
					/* y */ ball.y - (arrow.getHeight(this) * arrowScale / 2),
					/* w */ arrow.getWidth(this) * arrowScale, /* h */ arrow.getHeight(this) * arrowScale, this);

			text = "Press <SPACE> to begin game.";
			g2.drawString(text, PREF_W / 2 - fm.stringWidth(text) / 2, 200);
			text = "P1: W/S                    P2: ↑/↓";
			g2.setFont(new Font("Monaco", Font.PLAIN, 20));
			fm = g2.getFontMetrics();
			g2.drawString(text, PREF_W / 2 - fm.stringWidth(text) / 2, 150);

		} else if (playState.equals("Complete")) {

			text = "Press <SPACE> to restart game.";
			g2.drawString(text, PREF_W / 2 - fm.stringWidth(text) / 2, 200);
			text = winner + " wins!";
			g2.setFont(new Font("Monaco", Font.PLAIN, 40));
			if (winner.equals("P1"))
				g2.setColor(player1.insideColor);
			else
				g2.setColor(player2.insideColor);
			fm = g2.getFontMetrics();
			g2.drawString(text, PREF_W / 2 - fm.stringWidth(text) / 2, 150);

		} else if (playState.equals("Playing")) {
			if (opacity > 0) {
				g2.setColor(new Color(255, 200, 0, 0));
			} else {
				g2.setColor(new Color(255, 200, 0));
			}
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(PREF_W / 2, 0, PREF_W / 2, PREF_H);
		}

		if (opacity > 0) {
			AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
			g2.setComposite(alcom);
			g2.drawImage(hank, 0, 0, PREF_W, hank.getHeight(this), this);
			alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
			g2.setComposite(alcom);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W)
			w = true;
		else if (key == KeyEvent.VK_S)
			s = true;
		else if (key == KeyEvent.VK_UP)
			up = true;
		else if (key == KeyEvent.VK_DOWN)
			down = true;
		else if (key == KeyEvent.VK_SPACE) {
			if (!playState.equals("Playing") && !playState.equals("Complete")) {
				playState = "Playing";
				resume.stop();
				resume.setFramePosition(0);
				resume.start();
			} else if (playState.equals("Complete")) {
				playState = "WaitToStart";
				player1.score = 0;
				player2.score = 0;
				resume.stop();
				resume.setFramePosition(0);
				resume.start();
			}
		} else if (key == KeyEvent.VK_P) {
			maxPoints = Integer.valueOf(JOptionPane.showInputDialog(null, "Insert new Max Score"));
		}
	}

	/** ******* METHODS FOR INITIALLY CREATING THE JFRAME AND JPANEL *********/

	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Pain Pong");
		JPanel gamePanel = new Game();

		frame.getContentPane().add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			w = false;
		} else if (key == KeyEvent.VK_S) {
			s = false;
		} else if (key == KeyEvent.VK_UP) {
			up = false;
		} else if (key == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}