import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BrickGame extends JPanel implements KeyListener
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 600;
   private static final int PREF_H = 400;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Quicksand", Font.PLAIN, 25);
   private Brick brick1 = new Brick(100, 50, 80, 25, Color.pink);

   public BrickGame()
   {
      addKeyListener(this);
      setFocusable(true);
      requestFocus();
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
      g2.setColor(Color.RED);
      g2.drawString("Hello", 200, 200);

      brick1.draw(g2);

   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_SPACE)
         System.out.println("Pressing Space...");

      if (key == KeyEvent.VK_C)
      {
         int r = (int) (Math.random()*256);
         int b = (int) (Math.random()*256);
         int g = (int) (Math.random()*256);
         brick1.setColor(new Color(r, g, b));
      }

      if (key == KeyEvent.VK_RIGHT)
         brick1.setX(brick1.getX() + 10);
      if (key == KeyEvent.VK_LEFT)
         brick1.setX(brick1.getX() - 10);

      if (key == KeyEvent.VK_UP)
         brick1.setY(brick1.getY() - 10);
      if (key == KeyEvent.VK_DOWN)
         brick1.setY(brick1.getY() + 10);

      repaint();
   }

   @Override
   public void keyReleased(KeyEvent e){}

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