package Random;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class test2DArrays extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 500;
    private static final int PREF_H = 500;
    private static final int rows = 5;
    private static final int cols = 5;
    private static final Color colorOn = Color.WHITE;
    private static final Color colorOff = Color.LIGHT_GRAY;

    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private JButton[][] b = new JButton[rows][cols];
    
    public test2DArrays()
    {
        this.setLayout(new GridLayout(rows, cols));
        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                int row = r;
                int col = c;

                b[r][c] = new JButton();
                b[row][col].setBorderPainted(false);
                b[row][col].setOpaque(true);    

                b[row][col].setBackground((Math.random() > 0.5) ? colorOn : colorOff);   

                b[r][c].addActionListener(new ActionListener() {
        
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try { 
                            if (b[row + 1][col].getBackground().equals(colorOn))
                                b[row + 1][col].setBackground(colorOff);              
                            else
                                b[row + 1][col].setBackground(colorOn);   
                        } catch (IndexOutOfBoundsException a) {}
                             
                        try { 
                            if (b[row - 1][col].getBackground().equals(colorOn))
                                b[row - 1][col].setBackground(colorOff);              
                            else
                                b[row - 1][col].setBackground(colorOn);              
                        } catch (IndexOutOfBoundsException a) {}    

                        try {    
                            if (b[row][col].getBackground().equals(colorOn))
                                b[row][col].setBackground(colorOff);              
                            else
                                b[row][col].setBackground(colorOn);              
                        } catch (IndexOutOfBoundsException a) {}

                        try { 
                            if (b[row][col + 1].getBackground().equals(colorOn))
                                b[row][col + 1].setBackground(colorOff);              
                            else
                                b[row][col + 1].setBackground(colorOn);              
                        } catch (IndexOutOfBoundsException a) {}
                        try { 
                            if (b[row][col - 1].getBackground().equals(colorOn))
                                b[row][col - 1].setBackground(colorOff);              
                            else
                                b[row][col - 1].setBackground(colorOn);    
                        } catch (IndexOutOfBoundsException a) {}        
                    }
                    
                });
                this.add(b[r][c]);

            }
        }


        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //keep this for program to work
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        
        

        
        
        //keep this for program to work
        // long millis = System.currentTimeMillis();
        // try
        // {
        // Thread.sleep((long) ((1000/FPSCap) - millis % (1000/FPSCap)));
        // this.repaint();
        // return;
        // } catch (InterruptedException e) {System.out.println(e);}
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        test2DArrays gamePanel = new test2DArrays();
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
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}