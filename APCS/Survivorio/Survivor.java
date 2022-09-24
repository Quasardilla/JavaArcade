package Survivorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import Font.FontInstaller;
import UNIVERSAL.UI.FPSCounter;

public class Survivor extends JPanel implements KeyListener, MouseInputListener {
    
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 400;
    protected RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
    
    protected FPSCounter FPS = new FPSCounter(60);
    protected FontInstaller Font = new FontInstaller();

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private Image playerIcon = new ImageIcon("APCS/Survivorio/Images/missingTexture.png").getImage().getScaledInstance(50, 50, Image.SCALE_REPLICATE);
    private Player player = new Player(10, 1, 1, "", playerIcon);
    
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
        //Attach g2 to UIElements
        player.setGraphics(g2);
                
        updatePosition();
        
        player.drawElement();

        System.out.println("x: " + player.x + "y: " + player.y);

        FPS.frame();
        //Has to be on the bottom
        FPS.FPSLimitPause();
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            upPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
            downPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
            downPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = false;
    }

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

    public void updatePosition()
    {
        if (upPressed)
            player.setY(player.getY() - player.getSpeed());
        if (downPressed)
            player.setY(player.getY() + player.getSpeed());
        if (leftPressed)
            player.setX(player.getX() - player.getSpeed());
        if (rightPressed)
            player.setX(player.getX() + player.getSpeed());

    }
}