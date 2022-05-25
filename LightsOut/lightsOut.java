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
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class lightsOut extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int rows = 9;
    private static final int cols = 9;
    private static final int PREF_W = rows * 50;
    private static final int PREF_H = cols * 50;
    private static final Color colorOn = Color.WHITE;
    private static final Color colorOff = Color.LIGHT_GRAY;

    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private JButton[][] b = new JButton[rows][cols];
    private JButton quit;
    private JButton settings;
    private JButton restart;
    
    public lightsOut()
    {
        this.setBackground(Color.GRAY);

        this.setLayout(new GridLayout(rows, cols, 2, 2));
        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                int row = r;
                int col = c;
                
                b[r][c] = new JButton();
                b[row][col].setBorderPainted(false);
                b[row][col].setOpaque(true);   
                
                b[row][col].setBackground(colorOff);
                
                b[r][c].addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        runMove(row, col);       
                    }
                    
                });
                this.add(b[r][c]);
                
            }
        }

        initializeBoard();
        
        // this.add(quit);
        // this.add(settings);
        // this.add(restart);

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
        lightsOut gamePanel = new lightsOut();
        
        JPanel ui = new JPanel();
        ui.setFocusable(true);
        ui.requestFocus();
        ui.setLayout(new GridLayout(1, 3, 2, 2));

        gamePanel.quit = new JButton("Quit");
        gamePanel.quit.setBorderPainted(false);
        gamePanel.quit.setOpaque(true);   
        gamePanel.quit.setBackground(colorOff);
        gamePanel.quit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("Quit");
            }
            
        });
        ui.add(gamePanel.quit);

        gamePanel.settings = new JButton("Settings");
        gamePanel.settings.setBorderPainted(false);
        gamePanel.settings.setOpaque(true);   
        gamePanel.settings.setBackground(colorOff);
        gamePanel.settings.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("Settings");
            }
            
        });
        ui.add(gamePanel.settings);

        gamePanel.restart = new JButton("Restart");
        gamePanel.restart.setBorderPainted(false);
        gamePanel.restart.setOpaque(true);   
        gamePanel.restart.setBackground(colorOff);
        gamePanel.restart.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("Restart");
            }
            
        });
        ui.add(gamePanel.restart);


        JFrame frame = new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.getContentPane().add(ui, BorderLayout.SOUTH);
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

    public void initializeBoard()
    {
        int rand = (int) (Math.random() * 7) + 4;

        for(int i = 0; i < rand; i++)
            {
                int row = (int) (Math.random() * (rows - 1) + 1);
                int col = (int) (Math.random() * (cols - 1) + 1);

                runMove(row, col);
            }
    }
}