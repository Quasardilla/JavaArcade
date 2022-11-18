import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

//Mr. Uhl's programming exercise to test your ability to use nested loops.
//Use the associated pdf of patterns to alter the loops below so that the
//stacking and order of squares matches the ones in the pdf.

public class NestedLoopGUI_template extends JPanel implements MouseListener, KeyListener
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 600;
   private static final int PREF_H = 600;
   private static final int NUM_PAGES = 8;
   private int click, loop, move;
   private int[] boxTotal;
   private boolean processDone;
   
   public NestedLoopGUI_template()
   {
      click = 0;
      loop = 0; //the total number of times the nested loop will print boxes
      move = 0;
      boxTotal = new int[NUM_PAGES];
      processDone = false;
      addMouseListener(this);
      addKeyListener(this);
      setFocusable(true);
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

      g2.setFont(new Font("Cooper Black", Font.PLAIN, 36));
      g2.setColor(Color.RED);
      if(processDone && move < NUM_PAGES) g2.drawString("Complete.", getWidth()/2 - 100, 40);
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 20));
      g2.setColor(Color.BLACK);      
      g2.drawString("clicks: " + click, getWidth()/2 - 40, 580);
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 16));
      if(move > 0) g2.drawString("[–] to page back",  20, 580);
      if(move < NUM_PAGES-1) g2.drawString("[+] to page ahead", 440, 580);
      
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 36));
      
      if(click>=0)
      {
         if(move<1) rowMajorRectangle(g2);
         else if(move<2) rowMajorReverseRectangle(g2);
         else if(move<3) columnMajorRectangle(g2);
         else if(move<4) columnMajorReverseRectangle(g2);
         else if(move<5) upperLeftTri(g2);
         else if(move<6) upperRightTri(g2);
         else if(move<7) lowerLeftTri(g2);
         else if(move<8) lowerRightTri(g2);
         else
         {
            g2.setColor(Color.RED);
            g2.drawString("FINISHED!", getWidth()/2 - 100, getHeight()/2);
         }
      }
    }
   
   public void rowMajorRectangle(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("rowMajorRectangle");
      g2.drawString("rowMajorRectangle", 120, getHeight() - 100);
      
/*1*/ for(int r=0 ; r<4; r++)   //MUST USE r HERE...just change values/conditions
      {
/*2*/    for(int c=0; c<5; c++) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void rowMajorReverseRectangle(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("rowMajorReverseRectangle");
      g2.drawString("rowMajorReverseRectangle", 50, getHeight() - 100);

/*3*/ for(int r=3; r>=0; r--)   //MUST USE r HERE...just change values/conditions
      {
/*4*/    for(int c=4; c>=0; c--) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void columnMajorRectangle(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("columnMajorRectangle");
      g2.drawString("columnMajorRectangle", 90, getHeight() - 100);
      
/*5*/ for(int c=0 ; c<5; c++)   //MUST USE c HERE...just change values/conditions
      {
/*6*/    for(int r=0; r<4; r++) //MUST USE r HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void columnMajorReverseRectangle(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("columnMajorReverseRectangle");
      g2.drawString("columnMajorReverseRectangle", 20, getHeight() - 100);
      
/*7*/ for(int c=4 ; c>=0; c--)   //MUST USE c HERE...just change values/conditions
      {
/*8*/    for(int r=3; r>=0; r--) //MUST USE r HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void upperLeftTri(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("upperLeftTri");
      g2.drawString("upperLeftTri", 300, getHeight() - 100);

/*9*/ for(int r=0 ; r<5; r++)   //MUST USE r HERE...just change values/conditions
      {
/*10*/   for(int c=0; c<5-r; c++) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void upperRightTri(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("upperRightTri");
      g2.drawString("upperRightTri", 50, getHeight() - 100);

/*11*/for(int r=0; r<5; r++)   //MUST USE r HERE...just change values/conditions
      {
/*12*/   for(int c=4; c>=r; c--) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void lowerLeftTri(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("lowerLeftTri");
      g2.drawString("lowerLeftTri", 300, 100);

/*13*/for(int r=4; r>=0; r--)   //MUST USE r HERE...just change values/conditions
      {
/*14*/   for(int c=0; c<r+1; c++) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void lowerRightTri(Graphics2D g2)
   {
      ((JFrame)this.getTopLevelAncestor()).setTitle("lowerRightTri");
      g2.drawString("lowerRightTri", 50, 100);

/*15*/for(int r=4; r>=0; r--)   //MUST USE r HERE...just change values/conditions
      {
/*16*/   for(int c=4; c>=4-r; c--) //MUST USE c HERE...just change values/conditions
         {
            if(loop < click)
               drawBox(g2, r, c);
            loop++;
         }
      }
      if(loop == click && !processDone) {processDone = true; repaint();}//the !processDone stops the unnecessary repainting
      boxTotal[move] = loop;
      loop = 0;
   }
   
   public void drawBox(Graphics2D g2, int r, int c)
   {
      g2.setFont(new Font("Arial", Font.PLAIN, 15));
      g2.setColor(new Color(10+20*r, 100+10*c, 100+20*r));
      g2.fillRect(50+c*100, 55+r*100, 90, 90);
      g2.setColor(Color.WHITE);
      g2.drawString("r="+r+", c="+c, 65+c*100, 80+r*100);
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 30));
      g2.drawString(""+(loop+1), 80+c*100, 120+r*100);
   }
   
   private static void createAndShowGUI() {
      NestedLoopGUI_template gamePanel = new NestedLoopGUI_template();

      JFrame frame = new JFrame("Nested Loop Assignment!");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(gamePanel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
   
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }

   @Override
   public void mouseClicked(MouseEvent arg0)
   {
      if(!processDone)
         click++;
      else
      {
         processDone = false;
         click = 0;
         if(move < NUM_PAGES) move++;
      }
      
      if(move >= NUM_PAGES)
      {
         click = 0;
         processDone = true;
      }
      
      repaint();
   }

   @Override
   public void mouseEntered(MouseEvent arg0){}
   @Override
   public void mouseExited(MouseEvent arg0){}
   @Override
   public void mousePressed(MouseEvent arg0){}
   @Override
   public void mouseReleased(MouseEvent arg0){}

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if(key == KeyEvent.VK_SPACE)
         if(!processDone)
            click++;
         else
         {
            processDone = false;
            click = 0;
            if(move < NUM_PAGES) move++;
         }

      if(key == KeyEvent.VK_EQUALS){
         if(move < NUM_PAGES){
            click = 0;
            move++;
            processDone = false;
         }
      }
      
      if(key == KeyEvent.VK_RIGHT)
      {
         if(processDone)
         {
            click = 0;
            if(move < NUM_PAGES)
               move++;
            processDone = false;
         }
         if(move < NUM_PAGES)
            click++;
      }

      if(key == KeyEvent.VK_LEFT)
      {
         if(click > 0)
         {
            click--;
            processDone = false; //in case it was on the last box
         }
         else if(move > 0)
         {
            move--;
            click = boxTotal[move];
         }
      }

      if(key == KeyEvent.VK_MINUS){
         if(move > 0){
            click = 0;
            move--;
            processDone = false;
         }
      }
      
      if(move >= NUM_PAGES)
      {
         click = 0;
         processDone = true;
      }
      
      repaint();
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
   }

   @Override
   public void keyTyped(KeyEvent e)
   {
   }
}