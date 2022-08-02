package TicTacToe;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults.LazyValue;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class TicTacToe extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private Font font = new Font("Quicksand", Font.PLAIN, 35);
    private static final int rows = 3;
    private static final int cols = 3;
    private static final int PREF_W = rows * 100;
    private static final int PREF_H = cols * 100;
    private static final Color emptycolor = Color.WHITE;
    private static final Color xcolor = Color.RED;
    private static final Color ocolor = Color.BLUE;
    private static int moves = 0;
    private static boolean xTurn = true;
    private static boolean gameOver = false;

    private JPanel other;
    private JPanel game;

    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private JButton[][] b = new JButton[rows][cols];
    private JButton quit = new JButton("Quit");
    private JLabel label = new JLabel("TicTacToe");
    private JButton restart = new JButton("Restart");
    
    public TicTacToe()
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
            for(int c = 0; c < b.length; c++)
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
                        
                        if(xTurn)
                        label.setText("X's Turn");
                        else
                        label.setText("O's Turn");
                        
                        if(b[row][col].getBackground() == emptycolor)
                            moves++;

                        
                        
                        //if it is x's turn and the button is empty, set the color to xcolor. if it is not x's turn and the button is empty, set the color to ocolor.
                        if (!gameOver)
                        {
                            if(xTurn && b[row][col].getBackground() == emptycolor)
                            {
                                b[row][col].setBackground(xcolor);
                                b[row][col].setFont(font);
                                b[row][col].setText("X");
                                xTurn = false;
                            }
                            else if(!xTurn && b[row][col].getBackground() == emptycolor)
                            {
                                b[row][col].setBackground(ocolor);
                                b[row][col].setFont(font);
                                b[row][col].setText("O");
                                xTurn = true;
                            }
                        }
                        
                        //most of this was done by github copilot thank god T_T
                        if (b[0][0].getBackground() == b[0][1].getBackground() && b[0][0].getBackground() == b[0][2].getBackground() && b[0][0].getBackground() != emptycolor)
                        {   
                            checkWinner(b[0][0].getBackground());
                            gameOver = true;
                        }
                        else if (b[1][0].getBackground() == b[1][1].getBackground() && b[1][0].getBackground() == b[1][2].getBackground() && b[1][0].getBackground() != emptycolor)
                        {    
                            checkWinner(b[1][0].getBackground());
                            gameOver = true;
                        }
                        else if (b[2][0].getBackground() == b[2][1].getBackground() && b[2][0].getBackground() == b[2][2].getBackground() && b[2][0].getBackground() != emptycolor)
                        {
                            checkWinner(b[2][0].getBackground());
                            gameOver = true;
                        }
                        else if (b[0][0].getBackground() == b[1][0].getBackground() && b[0][0].getBackground() == b[2][0].getBackground() && b[0][0].getBackground() != emptycolor)
                        {    
                            checkWinner(b[0][0].getBackground());
                            gameOver = true;
                        }
                        else if (b[0][1].getBackground() == b[1][1].getBackground() && b[0][1].getBackground() == b[2][1].getBackground() && b[0][1].getBackground() != emptycolor)
                        {    
                            checkWinner(b[0][1].getBackground());
                            gameOver = true;
                        }
                        else if (b[0][2].getBackground() == b[1][2].getBackground() && b[0][2].getBackground() == b[2][2].getBackground() && b[0][2].getBackground() != emptycolor)
                        {    
                            checkWinner(b[0][2].getBackground());
                            gameOver = true;
                        }
                        else if (b[0][0].getBackground() == b[1][1].getBackground() && b[0][0].getBackground() == b[2][2].getBackground() && b[0][0].getBackground() != emptycolor)
                        {    
                            checkWinner(b[0][0].getBackground());
                            gameOver = true;
                        }
                        else if (b[0][2].getBackground() == b[1][1].getBackground() && b[0][2].getBackground() == b[2][0].getBackground() && b[0][2].getBackground() != emptycolor)
                        {    
                            checkWinner(b[0][2].getBackground());
                            gameOver = true;
                        }

                        if(moves >= rows * cols)
                        {
                            gameOver = true;
                            label.setText("Tie Game!");
                        }
                    }
                    
                });
                game.add(b[r][c]);
                    
            }
        }

        other.add(quit);
        other.add(label);
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
        TicTacToe gamePanel = new TicTacToe();
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
        moves = 0;

        for(int r = 0; r < b.length; r++)
        {
            for(int c = 0; c < b.length; c++)
            {
                b[r][c].setOpaque(true);   
                b[r][c].setText("");
                b[r][c].setBackground(emptycolor);
            }
        }

        // randomizeBoard();
    }

    public void checkWinner(Color clr)
    {
        if(clr == xcolor)
            label.setText("X Wins!");
        else
            label.setText("O Wins!");

        gameOver = true;
    }
}