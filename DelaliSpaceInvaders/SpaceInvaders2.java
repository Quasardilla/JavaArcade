package DelaliSpaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SpaceInvaders2 extends JPanel implements KeyListener {
private static final long serialVersionUID = 1L;

private static final int PREF_W = 810;
private static final int PREF_H = 600;

private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);
private Font font = new Font("Cooper Black", Font.PLAIN, 25);
private GameObject paddleR, laser;
private ArrayList<Brick> pongObject;
private Timer timer;
private ArrayList<GameObject> alien;
private Clip collisionSound;
private int scoreL, scoreR;
private boolean playing;
String message;
private boolean game0ver;
private int level, lives;
int winScore;
private boolean followed;
private int alienMoveTimer, alienMoveTimerMax;

// Constructor
public SpaceInvaders2() {
addKeyListener(this);
setFocusable(true);
requestFocus();

paddleR = new GameObject(PREF_W / 2 - 30, PREF_H - 30, 80, 20, 4, 4, 0, PREF_W, 0, PREF_H, false, Color.RED);
paddleR.setUpKey(KeyEvent.VK_UP);
paddleR.setDownKey(KeyEvent.VK_DOWN);
paddleR.setLeftKey(KeyEvent.VK_LEFT);
paddleR.setRightKey(KeyEvent.VK_RIGHT);
laser = new GameObject(200, 100, 25, 25, 4, 4, 0, PREF_W, 0, PREF_H, true, Color.CYAN);
winScore = 3;
lives = 3;
level = 1;
alienMoveTimer = 0;
alienMoveTimerMax = 50;

resetAliens();

message = "Welcome. Hit  <SPACE> to play!";

timer = new Timer(10, new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
paddleR.updateKeyMovement();

if (playing) 
{
    //   automated paddleR
    message = "";

    // move the aliens
    alienMoveTimer++;
    if (alienMoveTimer >= alienMoveTimerMax) {
        boolean aliensShouldChangeDirections = false;
    for (GameObject a : alien)
        if (a.willHitEdge())
            aliensShouldChangeDirections = true;

    // move aliens
    // moveAlien(aliensShouldChangeDirections);

    alienMoveTimer = 0;
    }

    if (followed)
        paddleR.setX(laser.getX() - 30);

    // laser.updateforBrickBreaker();

    for (GameObject b : alien)
        

    // check for collisions

    for (int i = alien.size() - 1; i >= 0; i--) {
        alien.get(i).updateAliens();
        if (laser.checkAndReactToCollisionWith(alien.get(i)))
        {
            alien.remove(i);
            scoreR++;
        }
    }

    //levels
    if (alien.size() == 0) {
    level++;
    resetAliens();
    }

    if (laser.isOffScreen()) 
    {
        playing = false;
        if (laser.getX() > PREF_W)
            laser.setX(PREF_W / 2 - laser.getW());
            laser.setY(PREF_H / 2 - laser.getH());

            message = "Hit  <SPACE> to play!";

            // Now check if game is over
            lives--;
            if (lives <= 0) {
            game0ver = true;
            message = "GAME OVER! If you want to play again press <R>";
            playing = false;
        }
    }
} 
else if (!game0ver) 
    laser.setX(paddleR.getX() + paddleR.getW() / 2 - laser.getW() / 2);

repaint();

}
});
timer.start();
}

public void changeDirectionOfAliens(boolean aliensShouldChangeDirections) {
    // set each alien dx to its opposite

    // remove each alien down

    for (int i = 0; i < alien.size(); i++) 
    {
        if (aliensShouldChangeDirections) 
        {
        alien.get(i).setDx(-alien.get(i).getDx());
        // move each alien down
        alien.get(i).setY(alien.get(i).getY() + 25);
        }
        else
        alien.get(i).updateAliens();
    }
}

public void resetAliens() {
    // Creating a list of brick objects
    alien = new ArrayList<GameObject>();
    // Creating list for rainbow colors
    Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(150, 0, 150) };
    int alienWidth = 27;
    int alienHeight = 27;
    int horizontalAlienSpacing = 50;
    int verticalAlienSpacing = 0;

    for (int j = 0; j < 5; j++) 
    {
        for (int i = 0; i < 11; i++) 
        {
            int x = 40 + i * horizontalAlienSpacing;
            int y = 100 + j * verticalAlienSpacing; // (int)(Math.random()*(PREF_H/3+100));
            Color color = colors[(int) (Math.random() * colors.length)];
            alien.add(new GameObject(x, y, alienWidth, alienHeight, 20, 0, 0, PREF_W, 0, PREF_H, false, Color.WHITE));
            //set the totalHits to that number of hits        
            alien.get(alien.size() - 1).setTotalHits(1);
        } 
    }
}

public Dimension getPreferredSize() {
return new Dimension(PREF_W, PREF_H);
}

@Override
public void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;
g2.setRenderingHints(hints);
g2.setColor(Color.BLACK);
g2.fillRect(0, 0, PREF_W, PREF_H);

/*
* Image bowl = new
* ImageIcon(this.getClass().getResource("basketball.png")).getImage();
* g2.drawImage(bowl,0, -100, PREF_W, 1000, this);
*
* Image p1 = new
* ImageIcon(this.getClass().getResource("Lebron.jpg")).getImage();
*/

g2.setFont(font);
g2.setColor(Color.RED);
g2.drawString(message, 200, 200);

paddleR.draw(g2);
// laser.draw(g2);

for (int i = 0; i < alien.size(); i++)
alien.get(i).drawAlien(g2);

//     brick3.draw(g2);
//     paddleL.draw(g2);
for (GameObject b : alien)
b.draw(g2);

// Draw the score values
g2.drawString(lives + "", 50, 50);
g2.drawString(scoreR + "", PREF_W - 100, 50);

}

public void resetGame() {
scoreR = 0;
lives = 3;
game0ver = false;
level = 1;
resetAliens();

}

@Override
public void keyPressed(KeyEvent e) {
int key = e.getKeyCode();

if (key == KeyEvent.VK_C) {
paddleR.setRandomColor();
}
paddleR.keyWasPressed(key);

if (key == KeyEvent.VK_SPACE && !game0ver) {
playing = true;
}

if (key == KeyEvent.VK_R)
if (game0ver) {
resetGame();
}

if (key == KeyEvent.VK_A)
followed = !followed;

repaint();
}

@Override
public void keyReleased(KeyEvent e) {
paddleR.keyWasReleased(e.getKeyCode());
}

@Override
public void keyTyped(KeyEvent e) {
}

private static void createAndShowGUI() {
    SpaceInvaders2 gamePanel = new SpaceInvaders2();
    JFrame frame = new JFrame("Space Invaders");
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
}
