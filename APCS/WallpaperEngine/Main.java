package WallpaperEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.Toolkit;

public class Main extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    //frame
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PREF_H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    private static JFrame frame;

    //fps
    private static boolean showFPS = true;
    private static int FPSCap = 60;
    private static boolean unlimited = true;
    private static double totalFrames = 0;
    private static double lastFPSCheck = 0;
    private static double currentFPS = 0;
    private static Rectangle fpsRect;

    private static Rectangle exitButton;


    private static Point mouse = new Point(0, 0);


    public Main()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        // setFocusable(true);
        // requestFocus();

        exitButton = new Rectangle(0, 25, 50, 50);
        fpsRect = new Rectangle(0, 25, 50, 50);

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
        g2.setClip(new Rectangle(0, 0, getWidth(), getHeight()));

        frame.toBack();
        
        g2.setColor(Color.RED);
        g2.fillRect((int) exitButton.getX(), (int) exitButton.getY(), (int) exitButton.getWidth(), (int) exitButton.getHeight());


        //debug
        String debug = "";
        if (showFPS) debug += currentFPS;
        g2.setColor(Color.WHITE);
        g2.fillRect(mouse.x, mouse.y, g2.getFontMetrics().stringWidth(debug)+20, 25);
        g2.setColor(Color.BLACK);
        g2.drawString(debug, mouse.x+10, mouse.y+15);
        
        //keep this for program to work
        totalFrames++;
        if (System.nanoTime() > lastFPSCheck + 1000000000)
        {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }
        if (!unlimited)
        {
            long millis = System.currentTimeMillis();
            try
            {
                Thread.sleep((long) ((1000/FPSCap) - millis % (1000/FPSCap)));
                this.repaint();
                return;
            } catch (InterruptedException e) {System.out.println(e);}
        }
        else
        {
            this.repaint();
        }
    }

    private static void createAndShowGUI() {
        Main gamePanel = new Main();
        frame = new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setUndecorated(true);
        gamePanel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        frame.getContentPane().add(gamePanel);
        
        
        // frame.setFocusable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        // frame.setBackground(Color.WHITE);
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
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        mouse = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        if (exitButton.contains(e.getX(), e.getY()))
        {
            System.exit(0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}