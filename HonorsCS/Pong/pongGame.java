package Pong;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import BrickClass.Brick;
import BrickClass.GameObject;

public class pongGame extends JPanel implements KeyListener, MouseInputListener
{
   private static final long serialVersionUID = 1L;
   private static int PREF_W = 600;
   public static int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Timer timer;
   private Brick paddleL, paddleR;
   private GameObject gameObject;
   private String message;
   private int scoreL, scoreR = 0;
   private boolean bricksCanCollide;
   private boolean gameOver, gameOverOnce, paused, start;
   private boolean entireGameOver = true;
   private Clip bounceSound, hitSound, pointSound, buzzerSound;
   private String winner = "";
   private static FontMetrics metrics;

   public pongGame()
   {
      addKeyListener(this);
      addMouseListener(this);
      setFocusable(true);
      requestFocus();
      
      URL file = this.getClass().getResource("Ping-pong-ball-bounce.wav");
                  AudioInputStream audio;
                  try {
                     audio = AudioSystem.getAudioInputStream(file);
                     bounceSound = AudioSystem.getClip();
                     bounceSound.open(audio);
                  } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
                  catch (UnsupportedAudioFileException e1) {
                  }
      file = this.getClass().getResource("Ping-pong-ball-hit.wav");
                  try {
                     audio = AudioSystem.getAudioInputStream(file);
                     hitSound = AudioSystem.getClip();
                     hitSound.open(audio);
                  } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
                  catch (UnsupportedAudioFileException e1) {
                  }
      file = this.getClass().getResource("applause.wav");
                  try {
                     audio = AudioSystem.getAudioInputStream(file);
                     pointSound = AudioSystem.getClip();
                     pointSound.open(audio);
                  } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
                  catch (UnsupportedAudioFileException e1) {
                  }
      file = this.getClass().getResource("buzzer.wav");
                  try {
                     audio = AudioSystem.getAudioInputStream(file);
                     buzzerSound = AudioSystem.getClip();
                     buzzerSound.open(audio);
                  } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
                  catch (UnsupportedAudioFileException e1) {
                  }


      Image img = new ImageIcon("Pong/racket.png").getImage();
      Image ball = new ImageIcon("Pong/ping-pong-ball.png").getImage();
 
      gameObject = new GameObject(PREF_W/2, PREF_H/2, 20, 20, Color.BLACK, 4, 4, 0, PREF_W, 30, PREF_H-30, ball);


      paddleL = new Brick(30, (PREF_H/2) - 40, 20, 80, Color.ORANGE, 0, 5, 0, PREF_W, 0, PREF_H, img);
      paddleR = new Brick(PREF_W - (50), (PREF_H/2) - 40, 20, 80, Color.RED, 0, 5, 0, PREF_W, 0, PREF_H, img);
      
      paddleL.setDirectionKeys(87, 83, 0, 0);
      paddleR.setDirectionKeys(38, 40, 0, 0);

      gameObject.combo = 0;
      
      timer = new Timer(10, 
      new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent e) { 
            if (scoreL == 3 || scoreR == 3)
            {
               entireGameOver = true;
               if (scoreL == 3) winner = "The left side won!";
               if (scoreR == 3) winner = "The right side won!";
               scoreL = 0;
               scoreR = 0;
               gameObject.combo = 0;
               gameObject.hit = 0;
               start = false;
               buzzerSound.flush();
               buzzerSound.setFramePosition(0);
               buzzerSound.start();
            }

            if (gameObject.getY() > gameObject.getYMax() - gameObject.getH() || gameObject.getY() < gameObject.getYMin())
            gameObject.setDy(-gameObject.getDy());

            if (!gameOver && !paused && start && !entireGameOver)
            {
            if (gameObject.getY() > gameObject.getYMax() - gameObject.getH() || gameObject.getY() < gameObject.getYMin())
            {
               bounceSound.flush();
               bounceSound.setFramePosition(0);
               bounceSound.start();
            }
            gameObject.update();
            }

            paddleL.updateKeyMovement();
            paddleR.updateKeyMovement();

            if (gameObject.checkAndReactToCollisionWith(paddleL) || gameObject.checkAndReactToCollisionWith(paddleR))
            {
               hitSound.flush();
               hitSound.setFramePosition(0);
               hitSound.start();

               gameObject.combo++;
               if (gameObject.combo >= 3)
                  gameObject.hit = 50;
            }   

            gameObject.checkAndReactToCollisionWith(paddleL);
            gameObject.checkAndReactToCollisionWith(paddleR);

            repaint();
         }         
      });
      timer.start();
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
   
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHints(hints);
      g2.setFont(font);
      metrics = g2.getFontMetrics(font);




      g2.drawImage(new ImageIcon("Pong/table.png").getImage(), 0, 0, PREF_W, PREF_H, null);
     
      gameObject.drawImage(g2);
      paddleL.drawImage(g2);
      paddleR.drawImage(g2);

      g2.setColor(Color.WHITE);

      if (gameObject.hit > 0)
      {
         g2.drawString("COMBO x"+gameObject.combo, (int) gameObject.getX(), (int) ((gameObject.getY()-10) + Math.sin(gameObject.getY()) * gameObject.combo));
         gameObject.hit--;
      }

      if (gameObject.getX() > gameObject.getXMax()) 
      {
      gameOver = true;
      gameOverOnce = true;
      scoreL++;
      pointSound.flush();
      pointSound.setFramePosition(0);
      pointSound.start();
   }
   if (gameObject.getX() < gameObject.getXMin() - gameObject.getW())
   {
      gameOver = true;
      gameOverOnce = true;
      scoreR++;
      pointSound.flush();
      pointSound.setFramePosition(0);
      pointSound.start();
      }
      
      g2.drawString("" + scoreL, ((PREF_W/4) - (metrics.stringWidth("" + scoreL) / 2)), PREF_H/8+10);
      g2.drawString("" + scoreR, ((int) (PREF_W * 0.75) - (metrics.stringWidth("" + scoreR) / 2)), PREF_H/8+10);
      
      if (!start || entireGameOver)
      {
      message = "Welcome to Pong!\nPress SPACE to start!";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H/2-20);
      if (winner.equals("The left side won!") || winner.equals("The right side won!"));
      {
         g2.drawString(winner, ((PREF_W/2) - metrics.stringWidth(winner) / 2), PREF_H/2-45);
      }   
      }  
      if (gameOver && !entireGameOver)
      {
         gameObject.combo = 0;
         gameObject.hit = 0;
      message = "Press SPACE to serve!";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H/2-20);
      if (gameOverOnce)
      {
      gameObject.returnToCenter();
      gameObject.pointTowardsWinner();
      gameOverOnce = false;
      }
      }
      if (paused)
      {
      message = "Game is paused\nPress SPACE to resume!";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H/2-20);
      }

   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_C)
      {
         bricksCanCollide = !bricksCanCollide;
      }
      if (key == KeyEvent.VK_SPACE && !start)
      start = true;
      if (key == KeyEvent.VK_SPACE && gameOver)
      gameOver = false;
      if (key == KeyEvent.VK_SPACE && entireGameOver)
      entireGameOver = false;
      if (key == KeyEvent.VK_R)
      {
         entireGameOver = true;
         gameObject.returnToCenter();
         winner = "";
         scoreL = 0;
         scoreR = 0;
         gameObject.combo = 0;
         gameObject.hit = 0;
         buzzerSound.flush();
         buzzerSound.setFramePosition(0);
         buzzerSound.start();
      }

      paddleL.keyWasPressed(e.getKeyCode());
      paddleR.keyWasPressed(e.getKeyCode());

   }

   @Override
   public void keyReleased(KeyEvent e){
      
      paddleL.keyWasReleased(e.getKeyCode());
      paddleR.keyWasReleased(e.getKeyCode());

   }

   @Override
   public void keyTyped(KeyEvent e){}

   private static void createAndShowGUI() {
      pongGame gamePanel = new pongGame();
      JFrame frame = new JFrame("My Frame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(gamePanel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setBackground(Color.WHITE);
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
   public void mouseMoved(MouseEvent e) 
   {
   }

}