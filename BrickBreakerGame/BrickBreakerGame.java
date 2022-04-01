package BrickBreakerGame;
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
import BrickClass.PongObject;

public class BrickBreakerGame extends JPanel implements KeyListener, MouseInputListener
{
   private static final long serialVersionUID = 1L;
   private static int PREF_W = 600;
   public static int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Timer timer;
   private static FontMetrics metrics;

   public BrickBreakerGame()
   {
      addKeyListener(this);
      addMouseListener(this);
      setFocusable(true);
      requestFocus();
      
    //   URL file = this.getClass().getResource("Ping-pong-ball-bounce.wav");
    //               AudioInputStream audio;
    //               try {
    //                  audio = AudioSystem.getAudioInputStream(file);
    //                  bounceSound = AudioSystem.getClip();
    //                  bounceSound.open(audio);
    //               } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
    //               catch (UnsupportedAudioFileException e1) {
    //               }
      
      
      timer = new Timer(10, 
      new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent e) { 
            

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
     
      

   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      
   }

   @Override
   public void keyReleased(KeyEvent e){
      


   }

   @Override
   public void keyTyped(KeyEvent e){}

   private static void createAndShowGUI() {
      BrickBreakerGame gamePanel = new BrickBreakerGame();
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