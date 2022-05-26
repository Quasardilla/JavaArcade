package LightsOut;

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
import java.awt.BasicStroke;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class lightsOutMulti extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int rows = 5;
    private static final int cols = 5;
    private static final int PREF_W = rows * 50;
    private static final int PREF_H = cols * 50;
    private static final Color colorOn = Color.WHITE;
    private static final Color colorOff = Color.LIGHT_GRAY;

    private JPanel other;
    private JPanel game;

    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private JButton[][] b = new JButton[rows][cols];
    private JButton quit = new JButton("Quit");
    private JButton settings = new JButton("Settings");
    private JButton restart = new JButton("Restart");
    
    public lightsOutMulti()
    {
        quit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.getRuntime().exit(0);
            }
            
        });

        restart.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
            
        });

        quit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.getRuntime().exit(0);
            }
            
        });


        game = new JPanel(new GridLayout(rows, cols, 2, 2)) {
            @Override
            protected void paintComponent(Graphics g) {

                this.setBackground(Color.GRAY);

            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PREF_W, PREF_H);
            }

        };

        other = new JPanel(new GridLayout(1, 3, 2, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                this.add(quit);
                this.add(settings);
                this.add(restart);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PREF_W, 25);
            }

        };

        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                int row = r;
                int col = c;
                
                b[r][c] = new JButton();
                b[row][col].setBorderPainted(false);
                b[row][col].setOpaque(true);   
                
                b[row][col].setBackground(colorOn);
                
                b[r][c].addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        runMove(row, col);       
                    }
                    
                });
                game.add(b[r][c]);
                    
            }
        }

        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        this.add(other, BorderLayout.SOUTH);

        randomizeBoard();

        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        lightsOutMulti gamePanel = new lightsOutMulti();
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

    public void runMove(int row, int col)
    {
        try { 
            switchLight(row + 1, col); 
        } catch (IndexOutOfBoundsException a) {}
             
        try { 
            switchLight(row - 1, col);            
        } catch (IndexOutOfBoundsException a) {}    

        try {    
            switchLight(row, col);            
        } catch (IndexOutOfBoundsException a) {}

        try { 
            switchLight(row, col + 1);             
        } catch (IndexOutOfBoundsException a) {}
        try { 
            switchLight(row, col - 1);   
        } catch (IndexOutOfBoundsException a) {} 
    }

    public void switchLight(int row, int col)
    {
        if(b[row][col].getBackground().equals(colorOn))
            b[row][col].setBackground(colorOff);              
        else
            b[row][col].setBackground(colorOn);  
    }

    public void randomizeBoard()
    {
        int rand = (int) (Math.random() * 7) + 5;

        for(int i = 0; i < rand; i++)
            {
                int row = (int) (Math.random() * (rows - 1) + 1);
                int col = (int) (Math.random() * (cols - 1) + 1);

                runMove(row, col);
            }
    }

    public void resetBoard()
    {
        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                b[r][c].setOpaque(true);   
                
                b[r][c].setBackground(colorOff);
            }
        }

        randomizeBoard();
    }
}