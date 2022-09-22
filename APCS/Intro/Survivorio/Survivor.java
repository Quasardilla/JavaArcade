package Intro.Survivorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import UI.FPSCounter;

import Font.FontInstaller;

public class Survivor extends JPanel implements KeyListener, MouseInputListener {

    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 400;
    protected RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    protected FPSCounter FPS = new FPSCounter();
    protected FontInstaller Font = new FontInstaller();
    
    public Survivor()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        
        //Install all fonts in the Font folder
        FontInstaller.installFont();
        
    }
    
    private static void createAndShowGUI() {
        Survivor gamePanel = new Survivor();
        JFrame frame = new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
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
    public void paintComponent(Graphics g) {
        //keep this for program to work
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        
        
        
        FPS.frame();
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}



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