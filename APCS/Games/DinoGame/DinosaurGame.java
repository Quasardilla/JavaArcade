package Games.DinoGame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class DinosaurGame extends JPanel implements KeyListener
{
   private static final long serialVersionUID = 1L;
   public static final int PREF_W = 600;
   public static final int PREF_H = 300;
   
   private Dinosaur dino;
   private ArrayList<Sprite> enemies;
   
   private int timeCounter, score;
   private boolean playing, gameOver;
   private Timer timer;
      
   public DinosaurGame()
   {
      this.addKeyListener(this);
      this.setFocusable(true);
      
      dino = new Dinosaur(KeyEvent.VK_UP, KeyEvent.VK_SPACE, KeyEvent.VK_DOWN);

      enemies = new ArrayList<Sprite>();
      enemies.add(new Cactus(PREF_W));
      enemies.add(new Cactus(PREF_W + 200));
      enemies.add(new Bird());
      timeCounter = 0;
      score = 0;
      
      timer = new Timer(2, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            update();
            repaint();
         }
      });
      timer.start();
   }
   
   public void update()
   {         
      if(playing) {
         timeCounter += 1;
         score = timeCounter / 34;
         dino.update();
         for(Sprite s : enemies)
            s.update();
         // Check collisions
         for(int i = 0; i < enemies.size(); i++)
            if(dino.isCollidingWith(enemies.get(i))) {
               playing = false;
               gameOver = true;
            }
      }
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 25));
      g2.drawString(score+"", 500, 50);
      
      dino.draw(g2);
      for(Sprite s : enemies)
         s.draw(g2);
   }
   
   
   public Dimension getPreferredSize()
   {
      return new Dimension(PREF_W, PREF_H);
   }
   
   public static void createAndShowGUI()
   {
      JFrame frame = new JFrame("Dino!");
      frame.getContentPane().add(new DinosaurGame());
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
	public static void main(String[] args)
	{
	   SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run()
         {
            createAndShowGUI();
         }
	      
	   });
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	   if((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SPACE))
	      if(!gameOver && !playing)
	         playing = true;
	   
	   if(!gameOver)
	      dino.keyWasPressed(e.getKeyCode());
	   
	   if(gameOver && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_R)) {
	      gameOver = false;
	      dino.reset();
	      for(Sprite s : enemies)
	         s.reset();
	      score = 0;
	   }
	}

	public void keyReleased(KeyEvent e)
	{
      if(!gameOver)
	      dino.keyWasReleased(e.getKeyCode());
	}
	
	@Override
   public void keyTyped(KeyEvent e){}
}
