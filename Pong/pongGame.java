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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import BrickClass.PongObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import BrickClass.Brick;
import BrickClass.PongObject;

public class pongGame extends JPanel implements KeyListener, MouseInputListener
{
   private static final long serialVersionUID = 1L;
   private static int PREF_W = 600;
   private static int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Timer timer;
   private Brick paddleL, paddleR;
   private PongObject gameObject;
   private int cornerTouchCount;
   private int mouseX, mouseY;
   private int scoreL, scoreR = 0;
   private boolean bricksCanCollide;
   private boolean gameOver, paused, start;
   private Clip collisionSound;
   private static FontMetrics metrics;

   public pongGame()
   {
      addKeyListener(this);
      addMouseListener(this);
      setFocusable(true);
      requestFocus();
      
      URL file = this.getClass().getResource("ball-hit.wav");
                  AudioInputStream audio;
                  try {
                     audio = AudioSystem.getAudioInputStream(file);
                     collisionSound = AudioSystem.getClip();
                     collisionSound.open(audio);
                     collisionSound.setFramePosition(collisionSound.getFrameLength()/4 + 1);
                  } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
                  catch (UnsupportedAudioFileException e1) {
                  }
      gameObject = new PongObject(PREF_W/2, PREF_H/2, 20, 20, Color.BLACK, 4, 4, 0, PREF_W, 0, PREF_H);

      paddleL = new Brick(30, (PREF_H/2) - 40, 20, 80, Color.ORANGE, 0, 5, 0, PREF_W, 0, PREF_H);
      paddleR = new Brick(PREF_W - (50), (PREF_H/2) - 40, 20, 80, Color.RED, 0, 5, 0, PREF_W, 0, PREF_H);
      
      paddleL.setDirectionKeys(87, 83, 0, 0);
      paddleR.setDirectionKeys(38, 40, 0, 0);
      
      timer = new Timer(10, 
      new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent e) { 
            
            if (!gameOver && !paused && start)
            gameObject.update();

            paddleL.updateKeyMovement();
            paddleR.updateKeyMovement();

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

      gameObject.draw(g2);
      paddleL.draw(g2);
      paddleR.draw(g2);

      if (gameObject.getX() > gameObject.getXMax()) 
      {
      gameOver = true;
      scoreL++;
      }
      if (gameObject.getX() < gameObject.getXMin() - gameObject.getW())
      {
      gameOver = true;
      scoreR++;
      }
      
      g2.drawString("" + scoreL, ((PREF_W/4) - (metrics.stringWidth("" + scoreL) / 2)), PREF_H/8);
      g2.drawString("" + scoreR, ((int) (PREF_W * 0.75) - (metrics.stringWidth("" + scoreR) / 2)), PREF_H/8);
      
      if (!start)
      g2.drawString("Welcome to Pong!\nPress SPACE to start!", ((PREF_W/2) - metrics.stringWidth("Welcome to Pong!\nPress SPACE to start!") / 2), PREF_H/2);
      if (gameOver)
      {
      gameObject.returnToCenter();
      gameObject.pointTowardsWinner();
      gameOver = false;
      }
      if (paused)
      {
      g2.drawString("Game is paused\nPress SPACE to resume!", ((PREF_W/2) - metrics.stringWidth("Game Over!\nPress SPACE to play again!") / 2), PREF_H/2);
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
      try
      {
      mouseX = this.getMousePosition().x;
      mouseY = this.getMousePosition().y;
      }
      catch (Exception a){}
   }

}