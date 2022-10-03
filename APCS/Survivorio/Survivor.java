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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import java.awt.Point;

import UNIVERSAL.ENTITY.Enemy;
import UNIVERSAL.ENTITY.Item;
import UNIVERSAL.ENTITY.Player;
import UNIVERSAL.Font.EasyFontInstaller;
import UNIVERSAL.UI.FPSCounter;

public class Survivor extends JPanel implements KeyListener, MouseInputListener {
    
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 400;
    private static final int PREF_H = 400;
    protected RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
    
    
    protected FPSCounter FPS = new FPSCounter(60);
    
    private int mouseX;
    private int mouseY;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    private int playerWidth = 50;
    private int playerHeight = 50;
    private Image playerIcon = new ImageIcon("APCS/Survivorio/Images/missingTexture.png").getImage().getScaledInstance(playerWidth, playerHeight, Image.SCALE_REPLICATE);
    private Player player = new Player(10, 1, 10, "", playerIcon);
    private Item temp = new Item(0, 0, "temp", new ImageIcon("APCS/Survivorio/Images/missingTexture.png").getImage().getScaledInstance(15, 15, Image.SCALE_REPLICATE), 0.01);

    private int camLimitUp = -400 + (int) player.getSpeed();
    private int camLimitDown = 400 - (int) player.getSpeed();
    private int camLimitLeft = -400 + (int) player.getSpeed();
    private int camLimitRight = 400 - (int) player.getSpeed();

    private final int centerX = ((PREF_W / 2) - (playerWidth / 2));
    private final int centerY = ((PREF_H / 2) - (playerHeight / 2));
    
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    protected int cameraOffsetX = 0;
    protected int cameraOffsetY = 0;

    private boolean start = true;

    public Survivor()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        
        //Install all fonts in the Font folder
        EasyFontInstaller.installFont();
        
        player.setX(centerX);
        player.setY(centerY);
        
        temp.setSpeed(10);

        for (int i = 0; i < 10; i++)
        {
            enemies.add(new Enemy(i * 10, i * 10, "enemy"+i, new ImageIcon("APCS/Survivorio/Images/missingTexture.png").getImage().getScaledInstance(15, 15, Image.SCALE_REPLICATE), 0.01));
        }

        upPressed = true;
        downPressed = true;
        leftPressed = true;
        rightPressed = true;
        moveCamera();
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
        temp.setGraphics(g2);
        FPS.setGraphics(g2);
        for (Enemy e: enemies) e.setGraphics(g2);

        
        moveCamera();
        
        
        try {
            mouseX = this.getMousePosition().x;
            mouseY = this.getMousePosition().y;
        } catch (Exception e) {}

        Double angle = Math.atan2(mouseY - (player.getY() + (playerHeight / 2)), mouseX - (player.getX() + (playerWidth / 2)));

        temp.draw();
        for (Enemy e: enemies) e.draw();
        player.draw();


        for (Enemy e: enemies) e.moveToward(new Point((int) player.getX(), (int) player.getY()), cameraOffsetX, cameraOffsetY);
        temp.moveToward((int) player.getX(), (int) player.getY(), cameraOffsetX, cameraOffsetY);
        
        System.out.println((upPressed || downPressed || leftPressed || rightPressed) ? "Moving" : "Not Moving");
        System.out.println("x: " + (player.getX() + cameraOffsetX) + "y: " + (player.getY() + cameraOffsetY));
        System.out.println("Centered: " + (player.getY() == centerY));
        System.out.println("Angle: " + (int) Math.toDegrees(angle));


        //Has to be on the bottom
        FPS.frame();
        FPS.FPSLimitPause();
        FPS.draw();
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
        {
            upPressed = true;
        }
        if (start) downPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downPressed = true;
        }
        if (start) upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftPressed = true;
        }
        if (start) rightPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
        }
        if (start) leftPressed = false;
        if (start) start = false;
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

    /*
     * In this use case, the camera is the opposite of everything.
     * When the camera moves right, everything but the background moves left, etc.
     */
    public void moveCamera()
    {
        if (upPressed && cameraOffsetY >= camLimitUp && player.getY() <= centerY)
        {
            cameraOffsetY -= player.getSpeed();
            temp.moveByCamera(0, cameraOffsetY);
            for (Enemy e: enemies) e.moveByCamera(0, cameraOffsetY);
        }
        
        else if (upPressed && player.getY() >= 0)
            player.setY(player.getY() - player.getSpeed());

        if (downPressed && cameraOffsetY <= camLimitDown && player.getY() >= centerY)
        {
            cameraOffsetY += player.getSpeed();
            temp.moveByCamera(0, cameraOffsetY);
            for (Enemy e: enemies) e.moveByCamera(0, cameraOffsetY);
        }
        else if (downPressed && player.getY() + playerHeight <= PREF_H)
            player.setY(player.getY() + player.getSpeed());

        if (leftPressed && cameraOffsetX >= camLimitLeft && player.getX() <= centerX)
        {
            cameraOffsetX -= player.getSpeed();
            temp.moveByCamera(cameraOffsetX, 0);
            for (Enemy e: enemies) e.moveByCamera(cameraOffsetX, 0);
        }
        else if (leftPressed && player.getX() >= 0)
            player.setX(player.getX() - player.getSpeed());

        if (rightPressed && cameraOffsetX <= camLimitRight && player.getX() >= centerX)
        {
            cameraOffsetX += player.getSpeed();
            temp.moveByCamera(cameraOffsetX, 0);
            for (Enemy e: enemies) e.moveByCamera(cameraOffsetX, 0);
        }
        else if (rightPressed && player.getX() + playerWidth <= PREF_W)
            player.setX(player.getX() + player.getSpeed());
            
    }
}