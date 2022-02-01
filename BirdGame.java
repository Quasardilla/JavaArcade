//Thomas Rottinger & Victor Urumov
//Feb 1, 2022 9:14:22 AM
//Project Description:

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//Honors Computer Science - Mr. Uhl
//Program description: Building a Snowman on a Java Panel


public class BirdGame extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
   //Variables for the class
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 800;
   private static final int PREF_H = 600;
   
   //Bird
   private static boolean facingleft = false;
   private static boolean up = false;
   private static boolean down = false;
   private static boolean left = false;
   private static boolean right = false;
   private boolean canPassThrough = false;
   private static int speed = 10;
   private static int trueRectCount = 5;
   private static int rectCount = trueRectCount;
   private static int birdx = 0;
   private static int birdy = 0;
   private static int score = 0;
   private static Image bird = null;
   
   private static ArrayList<Rectangle> yBricks = new ArrayList<Rectangle>();
   private static ArrayList<Rectangle> rBricks = new ArrayList<Rectangle>();
   
   //Misc
   private static FontMetrics metrics;
   private static String message;
   private static double startTime = System.nanoTime();
   private static double currentTime = System.nanoTime();
   private static int time = 0;
   private Timer timer;
   private boolean start = false;
   private boolean pause = false;
   private boolean gameOver = false;
   
   //Class constructor
   public BirdGame()
   {
      this.setFocusable(true);
      this.setBackground(Color.WHITE);
      this.addMouseListener(this);
      this.addKeyListener(this);
      this.addMouseMotionListener(this);
      bird = new ImageIcon(this.getClass().getResource("flappybird.png")).getImage();
      timer = new Timer
            (
            10,
            new ActionListener() 
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  if (!start)
                  repaint();
                  if (!pause && !gameOver && start)
                  {
                  currentTime = System.nanoTime();
                  repaint();
                  }
               }  
            });
      timer.start();
      
      for (int i = 0; i < trueRectCount; i++)
      {
         int w = (int) (Math.random() * 101) + 50;
         int h = (int) (Math.random() * 101) + 50;
         yBricks.add(new Rectangle((int)(Math.random() * (PREF_W - w)),
         (int)(Math.random() * (PREF_H - h)), w, h));
         w = (int) (Math.random() * 101) + 50;
         h = (int) (Math.random() * 101) + 50;
         rBricks.add(new Rectangle((int)(Math.random() * (PREF_W - w)),
         (int)(Math.random() * (PREF_H - h)), w, h));
      }
   }
   
   //The method used to add graphical images to the panel
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      Rectangle birdHitBox = new Rectangle(birdx, birdy, 100, 60);
      message = "Press SPACE to play!";
      
      g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON));
      
      g2.setStroke(new BasicStroke(4));
      
      //Background
      g2.setColor(new Color(66, 161, 245));
      g2.fillRect(0, 0, PREF_W, PREF_H - 250);
      
      g2.setColor(Color.YELLOW);
      g2.fillOval(700, -100, 200, 200);
      
      g2.setColor(Color.YELLOW);
      for (Rectangle y: yBricks)
      {
         y.setRect(y.getX() + 5, y.getY(), y.getWidth(), y.getHeight());
         if (y.getX() > PREF_W + 100)
         {
          y.setRect(-90, y.getY(), y.getWidth(), y.getHeight());
         }
         
         if (y.getX() < -100)
         {
            y.setRect(PREF_W + 90, y.getY(), y.getWidth(), y.getHeight());
         }
         g2.fill(y);
      }

      g2.setColor(Color.RED);
      for (Rectangle y: rBricks)
      {
         y.setRect(y.getX() - 5, y.getY(), y.getWidth(), y.getHeight());
         if (y.getX() > PREF_W + 100)
         {
          y.setRect(-90, y.getY(), y.getWidth(), y.getHeight());
         }
         
         if (y.getX() < -100)
         {
            y.setRect(PREF_W + 90, y.getY(), y.getWidth(), y.getHeight());
         }
         g2.fill(y);
      }


      if (canPassThrough)
      {
         if(up && left)
         {
            birdy -= speed / 2;
            birdx -= speed / 2;
         }
         if(up && right)
         {
            birdy -= speed / 2;
            birdx += speed / 2;
         }
         else if (up && !left && !right)
            birdy -= speed;
         
         if (down && left)
         {
            birdy += speed / 2;
            birdx -= speed / 2;
         }
         if(down && right)
         {
            birdy += speed / 2;
            birdx += speed / 2;
         }
         else if (down && !left && !right)
            birdy += speed;
         else if (right && !up && !down)
            birdx += speed;
         else if (left && !up && !down)
            birdx -= speed;
         
         if (birdx > PREF_W + 100)
         {
            birdx = -90;
         }
         
         if (birdx < -100)
         {
            birdx = PREF_W + 90;
         }
         
         if (birdy > PREF_H + 100)
         {
            birdy = -90;
         }
         
         if (birdy < -100)
         {
            birdy = PREF_H + 90;
         }
      }
      else
      {
         if(up && left && birdy > 0 && birdx > 0)
         {
            birdy -= speed / 2;
            birdx -= speed / 2;
         }
         if(up && right && birdy > 0 && birdx < PREF_W - 100)
         {
            birdy -= speed / 2;
            birdx += speed / 2;
         }
         else if (up && !left && !right && birdy > 0)
            birdy -= speed;
         
         if (down && left && birdy < PREF_H - 60 && birdx > 0)
         {
            birdy += speed / 2;
            birdx -= speed / 2;
         }
         if(down && right && birdy < PREF_H - 60 && birdx < PREF_W - 100)
         {
            birdy += speed / 2;
            birdx += speed / 2;
         }
         else if (down && !left && !right && birdy < PREF_H - 60)
            birdy += speed;
         else if (right && !up && !down && birdx < PREF_W - 100)
            birdx += speed;
         else if (left && !up && !down && birdx > 0)
            birdx -= speed;
      }

      this.drawSnowman(g2);
      
      
      int x1[] = {624, 540, 604, 557, 613, 580, 650, 695, 671, 709, 662, 722};
      int y1[] = {438, 433, 332, 333, 254, 254, 156, 255, 256, 329, 331, 443};
      int x2[] = {609, 599, 663, 653};
      int y2[] = {435, 483, 486, 435};
      
      g2.setColor(new Color(135, 80, 43));
      g2.fillPolygon(x2, y2, x2.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(x2, y2, x2.length);
      
      g2.setColor(new Color(23, 54, 19));
      g2.fillPolygon(x1, y1, x1.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(x1, y1, x1.length);
      
      if (facingleft)
      {
      g2.setColor(new Color(0, 0, 0, 0));
      birdHitBox = new Rectangle(birdx, birdy, 100, 60);
      g2.fill(birdHitBox);
      g2.drawImage(bird, birdx + 100, birdy, -100, 60, this);
      }
      else 
      {
      g2.setColor(new Color(0, 0, 0, 0));
      birdHitBox = new Rectangle(birdx, birdy, 100, 60);
      g2.fill(birdHitBox);
      g2.drawImage(bird, birdx, birdy, 100, 60, this);
      }
      
      g2.setColor(Color.BLACK);
      g2.drawString("Thomas & Victor", 10, 580);
      
      for (int i = yBricks.size()-1; i >= 0; i--)
      {
      if (birdHitBox.intersects(yBricks.get(i)))
      {
         score += 100;
         yBricks.remove(i);
      }
      }

      if (yBricks.size() <= 0)
      {
         rectCount = rectCount + 2;
         resetRectangles(rectCount);
      }
      
      for (int i = rBricks.size()-1; i >= 0; i--)
      {
      if (birdHitBox.intersects(rBricks.get(i)))
      {
         gameOver = true;
      }
      }
      g2.setColor(Color.BLACK);
      g2.setFont(new Font("Helvetica", Font.PLAIN, 20));
      g2.drawString("Score: " + score, 10, 25);
      g2.drawString("Time: " + (currentTime - startTime), 10, 45);
      
      metrics = g2.getFontMetrics(new Font("Helvetica", Font.PLAIN, 20));
      
      g2.setFont(new Font("Helvetica", Font.PLAIN, 40));
      if (!start)
      g2.drawString(message,((PREF_W/2) - metrics.stringWidth(message)), PREF_H/2);
      if (pause)
      {
      message = "Game Paused";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message)), PREF_H/2);
      }
      if (gameOver)
      {
      message = "Game Over!";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message)), PREF_H/2);
      message = "Press SPACE to play again!";
      g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message)), PREF_H/2 + 40);
      }
      
   }
      

   
/** *******  METHODS FOR INITIALLY CREATING THE JFRAME AND JPANEL  *********/

   public Dimension getPreferredSize()
   {
      return new Dimension(PREF_W, PREF_H);
   }
   
   public static void createAndShowGUI()
   {
      JFrame frame = new JFrame("Do You Want to Build a Snowman?");
      JPanel gamePanel = new BirdGame();
      
      frame.getContentPane().add(gamePanel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
   
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }

   @Override
   public void mouseClicked(MouseEvent e) 
   {
   }
   
   @Override
   public void mousePressed(MouseEvent e)
   {
      System.out.println(e.getX() + " " + e.getY());
      
      repaint();
   }

   @Override
   public void mouseReleased(MouseEvent e)
   {
   }

   @Override
   public void mouseEntered(MouseEvent e) {}

   @Override
   public void mouseExited(MouseEvent e) {}

   @Override
   public void mouseDragged(MouseEvent e) {}

   @Override
   public void mouseMoved(MouseEvent e)
   {
//         System.out.println("Moving...@ " + e.getX() + " " + e.getY());
//         repaint();
   }

   @Override
   public void keyTyped(KeyEvent e)
   {
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_UP && !gameOver && start)
      {
         up = true;
      }
      if(e.getKeyCode() == KeyEvent.VK_DOWN && !gameOver && start)
      {
         down = true;
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT && !gameOver && start)
      {
         left = true;
         facingleft = true;
      }
      if(e.getKeyCode() == KeyEvent.VK_RIGHT && !gameOver && start)
      {
         right = true;
         facingleft = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !gameOver && start)
      {
         pause = !pause;
      }
      if(e.getKeyCode() == KeyEvent.VK_P && !gameOver && start)
      {
         canPassThrough = !canPassThrough;
      }
      if(e.getKeyCode() == KeyEvent.VK_R && start)
      {
         gameReset();
      }
      if(e.getKeyCode() == KeyEvent.VK_SPACE && gameOver)
      {
         this.gameReset();
      }
      else if (e.getKeyCode() == KeyEvent.VK_SPACE)
      {
         start = true;
      }
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_UP)
      {
         up = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_DOWN)
      {
         down = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT)
      {
         left = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      {
         right = false;
      }
   }
   
   public void resetRectangles(int count)
   {
      for (int i = 0; i < count; i++)
      {
         int w = (int) (Math.random() * 101) + 50;
         int h = (int) (Math.random() * 101) + 50;
         obstacle.add(new Rectangle((int)(Math.random()*PREF_W) - w, (int)(Math.random()*PREF_H) - h, w, h));
      }
   }
   
   public void drawSnowman(Graphics2D g2)
   {
      
      //Bottom snowball
      g2.setColor(Color.WHITE);
      g2.fillOval(275, 340, 250, 220);
      g2.setColor(Color.BLACK);
      g2.drawOval(275, 340, 250, 220);
      
      //Middle snowball
      g2.setColor(Color.WHITE);
      g2.fillOval(305, 225, 190, 175);
      g2.setColor(Color.BLACK);
      g2.drawOval(305, 225, 190, 175);
      
      //Arms
      int[] xs4 = {459, 525, 538, 530, 536, 546, 565, 570, 557, 574, 571, 553, 538, 476};
      int[] ys4 = {310, 268, 218, 197, 191, 212, 186, 196, 217, 216, 229, 229, 277, 326};
      int[] xs5 = {325, 267, 243, 244, 240, 231, 235, 215, 210, 220, 191, 197, 221, 254, 315};
      int[] ys5 = {282, 235, 203, 184, 173, 173, 195, 176, 179, 199, 204, 213, 208, 247, 299};
      g2.setColor(new Color(135, 80, 43));
      g2.fillPolygon(xs4, ys4, xs4.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(xs4, ys4, xs4.length);
      g2.setColor(new Color(135, 80, 43));
      g2.fillPolygon(xs5, ys5, xs5.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(xs5, ys5, xs5.length);
      
      //Buttons
      g2.setColor(Color.black);
      g2.fillOval(328, 340, 12, 12);
      g2.fillOval(330, 310, 12, 12);
      g2.fillOval(340, 280, 12, 12);
      
      //Scarf
      g2.setColor(Color.RED);
      g2.fillOval(335, 200, 140, 80);
      g2.setColor(Color.BLACK);
      g2.drawOval(335, 200, 140, 80);
      
      //Head
      g2.setColor(Color.WHITE);
      g2.fillOval(340, 140, 120, 120);
      g2.setColor(Color.BLACK);
      g2.drawOval(340, 140, 120, 120);
      
      //Scarf end
      g2.setColor(Color.RED);
      int[] xs3 = {405, 408, 428, 432, 437, 442, 444, 443, 436, 400, 403, 407, 409, 408, 403};
      int[] ys3 = {256, 254, 247, 248, 254, 270, 290, 316, 360, 355, 340, 320, 300, 280, 259};
      g2.fillPolygon(xs3, ys3, xs3.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(xs3, ys3, xs3.length);
      
      //Scarf bottom
      g2.setStroke(new BasicStroke(10));
      g2.setColor(Color.WHITE);
      g2.drawLine(400, 355, 436, 360);
      g2.setStroke(new BasicStroke(7));
      g2.setColor(Color.RED);
      g2.drawLine(406, 353, 431, 355);
      g2.setStroke(new BasicStroke(4));
      g2.setColor(Color.BLACK);
      g2.drawArc(401, 347, 11, 10, 160, 155);//bottom of scarf
      g2.drawArc(413, 349, 11, 10, 180, 150);//bottom of scarf
      g2.drawArc(426, 351, 11, 10, 180, 172);//bottom of scarf
      g2.drawLine(413, 354, 415, 340);
      g2.drawLine(425, 355, 427, 342);
      
      //Hat brim
      g2.setColor(new Color(60, 60, 60));
      g2.fillOval(310, 139, 180, 25);
      g2.setColor(Color.BLACK);
      g2.drawOval(310, 139, 180, 25);
      //Hat
      g2.setColor(new Color(60, 60, 60));
      int[] xs1 = {344, 356, 440, 450};
      int[] ys1 = {74, 146, 146, 74};
      g2.fillPolygon(xs1, ys1, xs1.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(xs1, ys1, xs1.length);
      //Hat bottom arc
      g2.setColor(new Color(60, 60, 60));
      g2.fillArc(356, 136, 84, 16, 180, 180);
      g2.setColor(Color.BLACK);
      g2.drawArc(356, 136, 84, 16, 180, 180);
      //Hat top
      g2.setColor(new Color(60, 60, 60));
      g2.fillOval(344, 62, 106, 16);
      g2.setColor(Color.BLACK);
      g2.drawOval(344, 62, 106, 16);
      
      //Left Eye
      g2.setColor(Color.BLACK);
      g2.fillOval(390, 175, 20, 20);
      g2.setColor(Color.WHITE);
      g2.fillOval(393, 178, 10, 10);
      
      //Right Eye
      g2.setColor(Color.BLACK);
      g2.fillOval(350, 176, 20, 20);
      g2.setColor(Color.WHITE);
      g2.fillOval(353, 179, 10, 10);
      
      //Smile
      g2.setColor(Color.BLACK);
      g2.fillOval(360, 226, 12, 12);
      g2.fillOval(374, 232, 12, 12);
      g2.fillOval(388, 231, 12, 12);
      g2.fillOval(402, 225, 12, 12);
      
      //Nose
      int[] xs2 = {375, 356, 310, 310, 335, 378};
      int[] ys2 = {198, 203, 207, 212, 216, 218};
      g2.setColor(new Color(254, 146, 0));
      g2.fillPolygon(xs2, ys2, xs2.length);
      g2.setColor(Color.BLACK);
      g2.drawPolygon(xs2, ys2, xs2.length);
      g2.fillArc(305, 206, 8, 8, 90, 180);//tip of nose
      g2.setColor(new Color(254, 146, 0));
      g2.fillOval(367, 200, 20, 17);
      g2.setColor(Color.BLACK);
      g2.drawArc(368, 198, 20, 21, -94, 188);//base of nose   
   }
   
   public void gameReset()
   {
      birdx = 0;
      birdy = 0;
      time = 0;
      rectCount = trueRectCount;
      score = 0;
      start = false;
      gameOver = false;
      pause = false;
      
      for (int i = 0; i < trueRectCount; i++)
      {
         int w = (int) (Math.random() * 101) + 50;
         int h = (int) (Math.random() * 101) + 50;
         obstacle.add(i, new Rectangle((int)(Math.random() * (PREF_W - w)),
         (int)(Math.random() * (PREF_H - h)), w, h));
      }
      if (obstacle.size() > trueRectCount);
      {
         for (int i = 0; i < (obstacle.size() - 100); i++)
         {
            obstacle.remove(obstacle.size()-1);
         }
      }
   }
}
