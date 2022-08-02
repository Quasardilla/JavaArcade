package BrickGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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

public class BrickGame extends JPanel implements KeyListener, MouseInputListener
{
   private static final long serialVersionUID = 1L;
   private static int PREF_W = 600;
   private static int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Timer timer;
   private ArrayList<Brick> playerBricks;
   private ArrayList<Brick> deadBricks;
   private ArrayList<Brick> bricks;
   // private Brick dvd;
   // private int cornerTouchCount;
   // private Image img = new ImageIcon(this.getClass().getResource("dvdlogo.png")).getImage();
   private int mouseX, mouseY;
   private boolean bricksCanCollide;
   private Clip collisionSound;

   public BrickGame()
   {
      addKeyListener(this);
      addMouseListener(this);
      setFocusable(true);
      requestFocus();

      bricks = new ArrayList<Brick>();
      playerBricks = new ArrayList<Brick>();
      deadBricks = new ArrayList<Brick>();
      
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
      // dvd = new Brick(0, 0, 1200 / 8, 718 / 8, Color.BLACK, 4, 4, 0, PREF_W, 0, PREF_H);


      playerBricks.add(new Brick(0, 0, 25, 80, Color.ORANGE, 5, 5,  0, PREF_W, 0, PREF_H));
      playerBricks.add(new Brick(0, 0, 80, 25, Color.RED, 5, 5,  0, PREF_W, 0, PREF_H));
      
      playerBricks.get(0).setDirectionKeys(87, 83, 65, 68);
      playerBricks.get(1).setDirectionKeys(38, 40, 37, 39);
      
      // Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(200, 0, 255), Color.PINK};

      for (int i = 1; i <= 5; i++)
      {
         int x = (int) (Math.random() * (PREF_W - 25));
         int y = (int) (Math.random() * (PREF_H - 25));
         int dx = (int) (Math.random() * 3 + 1);
         int dy = (int) (Math.random() * 3 + 1);
         // Color color = colors[(int) (Math.random() * colors.length)];

         // for (int ii = 0; ii < PREF_W; ii += 5)
         // for (int iii = 0; iii < PREF_H; iii += 5)
         // bricks.add(new Brick((int) ii, (int) iii, 5, 5, color.red));

         bricks.add(new Brick(x, y, 25, 25, Brick.getRandomColor(), dx, dy, 0, PREF_W, 0, PREF_H));
         // bricks.add(new Brick(x, y, 25, 25, color, dx, dy, 0, PREF_W, 0, PREF_H));

      }
      
      timer = new Timer(10, 
      new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent e) { 

            for (Brick brick : bricks)
            {
               brick.update();
               brick.colorAdjust(PREF_W, PREF_H);
               if (brick.checkAndReactToCollisionWith(playerBricks.get(0)))
               {
               collisionSound.flush();
               collisionSound.setFramePosition(0);
               collisionSound.start();
               }
               for (Brick player : playerBricks)
               {
                  brick.checkAndReactToCollisionWith(player);
               }
               for (Brick dead : deadBricks)
               {
                  brick.checkAndReactToCollisionWith(dead);
               }
               if (bricksCanCollide)
               for (Brick brick1 : bricks)
               {
                  if (brick != brick1)
                  brick.checkAndReactToCollisionWith(brick1);
               }
               // if (brick.getX() == 0 && brick.getY() == 0 || brick.getX() + brick.getW() == PREF_W && brick.getY() + brick.getH() == PREF_H ||
               // brick.getX() + brick.getW() == PREF_W && brick.getY() == 0 || brick.getY() + brick.getH() == PREF_H && brick.getX() == 0)
               // cornerTouchCount++;
            }
            
            // dvd.update();
            for (Brick player : playerBricks)
            player.updateKeyMovement();
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
      
      PREF_W = this.getWidth();
      PREF_H = this.getHeight();

      // g2.setColor(Color.RED);
      // g2.drawString("Hello", 200, 200);

      try
      {
      mouseX = this.getMousePosition().x;
      mouseY = this.getMousePosition().y;
      }
      catch (Exception e){}

      for (Brick brick : bricks)
      {
         brick.draw(g2);
      }
      
      for (Brick player : playerBricks)
      player.draw(g2);

      // g2.drawString("Corner Touches: " + cornerTouchCount, 50, 50);
      // dvd.setXMax(PREF_W);
      // dvd.setYMax(PREF_H);


      for (Brick dead : deadBricks)
      dead.draw(g2);

      // g2.drawImage(img, dvd.getX(), dvd.getY(), dvd.getW(), dvd.getH(), null);

      //COLOR DEBUG
      // g2.setColor(Color.BLACK);
      // g2.drawString("" + brick2.getChangedColor(mouseX, mouseY, PREF_W, PREF_H), 100, 100);
      // g2.drawString("x:" + mouseX + " y:" + mouseY, 100, 80);

      // brick2.draw(g2);
      // brick3.draw(g2);
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_C)
      {
         bricksCanCollide = !bricksCanCollide;
      }

      for (Brick player : playerBricks)
      player.keyWasPressed(e.getKeyCode());

         if (key == KeyEvent.VK_H)
            {
               Brick brick1 = playerBricks.get(1);
               deadBricks.add(brick1.killBrick());
               playerBricks.set(1, new Brick(100, 50, 80, 25, Color.RED, 5, 5,  0, PREF_W, 0, PREF_H));
               playerBricks.get(1).setDirectionKeys(38, 40, 37, 39);
            }
            if (key == KeyEvent.VK_V)
            {
               Brick brick0 = playerBricks.get(0);
               deadBricks.add(brick0.killBrick());
               playerBricks.set(0, new Brick(100, 50, 25, 80, Color.ORANGE, 5, 5,  0, PREF_W, 0, PREF_H));
               playerBricks.get(0).setDirectionKeys(87, 83, 65, 68);
            }

      // if (key == KeyEvent.VK_RIGHT)
      //    right = true;
      // if (key == KeyEvent.VK_LEFT)
      //    left = true;
      // if (key == KeyEvent.VK_UP)
      //    up = true;
      // if (key == KeyEvent.VK_DOWN)
      //    down = true;
   }

   @Override
   public void keyReleased(KeyEvent e){
      
      for (Brick player : playerBricks)
      player.keyWasReleased(e.getKeyCode());

      // if (key == KeyEvent.VK_RIGHT)
      // right = false;
      // if (key == KeyEvent.VK_LEFT)
      // left = false;
      // if (key == KeyEvent.VK_UP)
      // up = false;
      // if (key == KeyEvent.VK_DOWN)
      // down = false;
   }

   @Override
   public void keyTyped(KeyEvent e){}

   private static void createAndShowGUI() {
      BrickGame gamePanel = new BrickGame();
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
      try {
      for (Brick dead: deadBricks)
         if (mouseX > dead.getX() && mouseX < dead.getX() + dead.getW() &&
         mouseY > dead.getY() && mouseY < dead.getY() + dead.getH())
         {
               deadBricks.remove(dead);
            }
         } catch (ConcurrentModificationException a) {System.out.println(":sob: console complaining about concurrent modification");}
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