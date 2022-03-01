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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BrickGame extends JPanel implements KeyListener
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 600;
   private static final int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Brick brick0, brick1, brick2, brick3;
   private Timer timer;
   private ArrayList<Brick> bricks;

   public BrickGame()
   {
      addKeyListener(this);
      setFocusable(true);
      requestFocus();

      brick0 = new Brick(100, 50, 80, 25, Color.ORANGE, 5, 5,  0, PREF_W, 0, PREF_H);
      brick1 = new Brick(100, 50, 80, 25, Color.RED, 5, 5,  0, PREF_W, 0, PREF_H);
      brick2 = new Brick(125, 10, 25, 25, Color.BLUE, 3, 3, 0, PREF_W, 0, PREF_H);
      brick3 = new Brick(100, 30, 25, 25, Color.GREEN, 3.2, 3.2, 0, PREF_W, 0, PREF_H);

      brick0.setUpKey(87);
      brick0.setDownKey(83);
      brick0.setLeftKey(65);
      brick0.setRightKey(68);

      brick1.setUpKey(38);
      brick1.setDownKey(40);
      brick1.setLeftKey(37);
      brick1.setRightKey(39);

      Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(200, 0, 255), Color.PINK};

      bricks = new ArrayList<Brick>();

      for (int i = 1; i < 10; i++)
      {
         int x = (int) (Math.random() * (PREF_W - 25));
         int y = (int) (Math.random() * (PREF_H - 25));
         int dx = (int) (Math.random() * 8);
         int dy = (int) (Math.random() * 8);
         Color color = colors[(int) (Math.random() * colors.length)];
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
            }
            brick3.update(); 
            brick2.update();
            brick1.updateKeyMovement(); 
            brick0.updateKeyMovement(); 
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

      // g2.setFont(font);
      // g2.setColor(Color.RED);
      // g2.drawString("Hello", 200, 200);

      for (Brick brick : bricks)
      {
         brick.draw(g2);
      }
      
      brick0.draw(g2);
      brick1.draw(g2);
      brick2.draw(g2);
      brick3.draw(g2);
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_C)
      {
         brick1.randomColor();
      }

      brick1.keyWasPressed(e.getKeyCode());
      brick0.keyWasPressed(e.getKeyCode());

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
      
      brick1.keyWasReleased(e.getKeyCode());
      brick0.keyWasReleased(e.getKeyCode());

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

}