import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import UNIVERSAL.UI.TextBox;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class testGUI extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 400;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private static int FPSCap = 60;

    private Image img = new ImageIcon("").getImage();

    private int mouseX;
    private int mouseY;

    Timer timer;

    private static TextBox tb;


    public testGUI()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        tb = new TextBox(/*x*/ 50d, /*y*/ 50d, /*height*/ 25d, /*prompt*/ "Type here: ", /*promptcolor*/ Color.BLACK, /*promptfont*/ new Font("Quicksand", Font.PLAIN, 25), /*promptfontsize*/ 10, /*text*/ "text is written here", /*textcolor*/ Color.BLUE, /*textfont*/ new Font("Quicksand", Font.PLAIN, 25), /*textfontsize*/ 10, /*texttopromptoffset*/ 20d, /*prompttobackgroundoffset*/ 10d, /*backgroundcolor*/ Color.LIGHT_GRAY, /*edgecurve*/ 10);


        timer = new Timer(1, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) 
            { 
            }
        });
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
        tb.setGraphics(g2);
        
        tb.draw();

        //keep this for program to work
        long millis = System.currentTimeMillis();
        try
        {
        Thread.sleep((long) ((1000/FPSCap) - millis % (1000/FPSCap)));
        this.repaint();
        return;
        } catch (InterruptedException e) {System.out.println(e);}
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        testGUI gamePanel = new testGUI();
        JFrame frame = new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);

        frame.setFocusable(false);
        frame.setUndecorated(true);
        frame.setBackground(new Color(255, 255, 255, 0));
        gamePanel.setBackground(new Color(255, 255, 255, 0));

        frame.pack();
        frame.setLocationRelativeTo(null);
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
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}