package Random;


/*
this class is to be used to test things out.
please return it to how it was before you started.
*/



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import UI.TextBox;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import UI.SpriteSheet;
import javax.swing.ImageIcon;

public class testGUI extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 400;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private static int FPSCap = 60;

    private static TextBox tb;
    private SpriteSheet laser = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser1.png").getImage(), 3, 7, 4);


    public testGUI()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();


        tb = new TextBox(/*x*/ 50d, /*y*/ 50d, /*width*/ 5d, /*height*/ 25d, /*prompt*/ "Type hereeeeee: ", /*promptcolor*/ Color.BLACK, /*promptfont*/ new Font("Quicksand", Font.PLAIN, 25), /*promptfontsize*/ 10, /*text*/ "1ahflkjhasdlkjfhalsjdhflas", /*textcolor*/ Color.BLUE, /*textfont*/ new Font("Quicksand", Font.PLAIN, 25), /*textfontsize*/ 10, /*texttopromptoffset*/ 20d, /*prompttobackgroundoffset*/ 10d, /*backgroundcolor*/ Color.LIGHT_GRAY, /*edgecurve*/ 10, /*growboxwithtext*/ true);

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
        
        g2.drawImage(laser.get(1).getScaledInstance(100, 100, Image.SCALE_DEFAULT), 100, 100, null);

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