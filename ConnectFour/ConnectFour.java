package ConnectFour;

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

public class ConnectFour extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int rows = 6;
    private static final int cols = 7;
    private static final int PREF_W = rows * 100;
    private static final int PREF_H = cols * 100;
    private static final Color emptycolor = Color.WHITE;
    private static final Color xcolor = Color.RED;
    private static final Color ocolor = Color.BLUE;
    private static boolean xTurn = true;
    private static boolean gameOver = false;

    private JPanel other;
    private JPanel game;

    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private JButton[][] b = new JButton[rows][cols];
    private JButton quit = new JButton("Quit");
    private JButton settings = new JButton("Settings");
    private JButton restart = new JButton("Restart");
    
    public ConnectFour()
    {
        quit.setBackground(Color.RED);
        quit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.getRuntime().exit(0);
            }
            
        });

        restart.setBackground(Color.BLUE);
        restart.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
            
        });

        settings.setBackground(Color.GREEN);
        settings.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
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
            protected void paintComponent(Graphics g) {}

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PREF_W, 25);
            }

        };

        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b[r].length; c++)
            {
                int row = r;
                int col = c;
                
                b[r][c] = new JButton();
                b[row][col].setBorderPainted(false);
                b[row][col].setOpaque(true);   
                
                b[row][col].setBackground(emptycolor);
                
                b[r][c].addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //if it is x's turn and the button is empty, set the color to xcolor. if it is not x's turn and the button is empty, set the color to ocolor.
                        if (!gameOver)
                        {
                            int highestrow = 0;
                            //set highestrow to the highest empty row
                            for (int i = b.length-1; i >= 0; i--)
                            {
                                if (b[i][col].getBackground() == emptycolor)
                                {
                                    highestrow = i;
                                    break;
                                }
                            }


                            if(xTurn && b[highestrow][col].getBackground() == emptycolor)
                            {
                                b[highestrow][col].setBackground(xcolor);
                                xTurn = false;
                            }
                            else if(!xTurn && b[row][col].getBackground() == emptycolor)
                            {
                                b[highestrow][col].setBackground(ocolor);
                                xTurn = true;
                            }
                        }
                    }
                    
                });
                game.add(b[r][c]);
                    
            }
        }

        other.add(quit);
        other.add(settings);
        other.add(restart);

        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        this.add(other, BorderLayout.SOUTH);

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
        ConnectFour gamePanel = new ConnectFour();
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

    // public void runMove(int row, int col)
    // {
    //     try { 
    //         switchLight(row + 1, col); 
    //     } catch (IndexOutOfBoundsException a) {}
             
    //     try { 
    //         switchLight(row - 1, col);            
    //     } catch (IndexOutOfBoundsException a) {}    

    //     try {    
    //         switchLight(row, col);            
    //     } catch (IndexOutOfBoundsException a) {}

    //     try { 
    //         switchLight(row, col + 1);             
    //     } catch (IndexOutOfBoundsException a) {}
    //     try { 
    //         switchLight(row, col - 1);   
    //     } catch (IndexOutOfBoundsException a) {} 
    // }

    // public void switchLight(int row, int col)
    // {
    //     if(b[row][col].getBackground().equals(xcolor))
    //         b[row][col].setBackground(ocolor);              
    //     else
    //         b[row][col].setBackground(xcolor);  
    // }

    // public void randomizeBoard()
    // {
    //     int rand = (int) (Math.random() * 7) + 5;

    //     for(int i = 0; i < rand; i++)
    //         {
    //             int row = (int) (Math.random() * (rows - 1) + 1);
    //             int col = (int) (Math.random() * (cols - 1) + 1);

    //             runMove(row, col);
    //         }
    // }

    public void resetBoard()
    {
        gameOver = false;
        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                b[r][c].setOpaque(true);   
                
                b[r][c].setBackground(emptycolor);
            }
        }

        // randomizeBoard();
    }
}