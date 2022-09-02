package APCS.GoofyFPS;

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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseListener;

public class VoxelEngine extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 400;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    //fps stuff
    private static double totalFrames = 0;
    private static double lastFPSCheck = 0;
    private static double currentFPS = 0;

    //other
    int approximation;
    int CSpeed;
    int CameraXPosition;
    int CameraYPosition;
    int CameraZPosition;
    double CosineX;
    double CosineY;
    int DFC;
    int FOV;
    int lastBlockHeight;
    int lastX;
    int lastY;
    int lastZ;
    int mousePosition;
    int renderX;
    int renderY;
    int renderZ;
    int RotationX;
    int RotationY;
    int Shape;
    int Short;
    double SineX;
    double SineY;
    int SpeedX;
    int SpeedY;
    int Started;
    int ViewDistance;
    int WorldLoadingPercentage;
    int WorldSizeChunks;
    int x1;
    int x2;
    int y1;
    int y2;
    int z1;
    int z2;

    //keys
    boolean moveLeft;
    boolean moveRight;
    boolean moveForward;
    boolean moveBackward;
    boolean moveUp;
    boolean moveDown;
    boolean lookLeft;
    boolean lookRight;
    boolean lookUp;
    boolean lookDown;

    //answer
    int answer = 9;


    ArrayList<Integer> BlockHeights = new ArrayList<Integer>();


    public VoxelEngine()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        //first flag
        Started = 0;
        lastX = 0;

        //second flag
        DFC = 20;
        CameraXPosition = 0;
        CameraYPosition = 0;
        CSpeed = 3;
        CameraZPosition = 60;
        RotationX = 0;
        RotationY = -180;
        Shape = 0;
        SpeedX = -3;
        SpeedY = 3;

        //third flag
        lastBlockHeight = 1;
        for (int i = 0; i < (Math.sqrt(answer) * 3); i++)
        {
            for (int ii = 0; ii < (Math.sqrt(answer) * 3); ii++)
            {
                BlockHeights.add(lastBlockHeight);
            }
        }

        Started = 1;

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
        
        ConLook();


        calculateCubePosition();



        //make an fps counter
		totalFrames++;
		if (System.nanoTime() > lastFPSCheck + 1000000000)
		{
			lastFPSCheck = System.nanoTime();
			currentFPS = totalFrames;
			totalFrames = 0;
		}
        g2.setColor(Color.WHITE);
        g2.fillRect(90, 80, 100, 25);
        g2.setColor(Color.BLACK);
        g2.drawString("FPS: "+currentFPS, 100, 100);
        this.repaint();
    }

    void approximation()
    {

    }

    void Calculate()
    {
        SineX = Math.sin(Math.toRadians(RotationX));
    }

    void calculateCubePosition()
    {
        lastY = 0;
        lastX = 0;
        lastZ = 0;
        for (int i = 0; i < Math.sqrt(answer) * 3; i++)
        {
            for (int ii = 0; ii < Math.sqrt(BlockHeights.size()); i++)
            {
                MakeCubeAt(lastX, 40*BlockHeights.get(lastY / 40), lastZ);
                lastX += 40;
                lastY += 40;
            }
            lastZ += 40;
            lastX = 0;
        }
    }

    void Clip()
    {

    }

    void ConLook()
    {
        if (lookRight) RotationY += (SpeedX - 1);
        if (lookLeft) RotationY += (SpeedY + 1);
        if (lookUp) RotationX += 3;
        if (lookDown) RotationX -= 3;
        if (moveUp) CameraYPosition += 4;
        if (moveDown) CameraYPosition -= 4;

        if (RotationX > 90) RotationX = 90;
        if (RotationX < -90) RotationX = -90;
    }

    void DrawLine(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z)
    {

    }

    void MakeCubeAt(int x1, int y1, int z1)
    {
        Calculate();

        renderX = x1;
        renderY = y1;
        renderZ = z1;

        DrawLine(20 + x1, 20+y1, -20+z1, 20+x1, 20+y1, 20+z1, 1, 1, 1);
        DrawLine(20 + x1, 20+y1, -20+z1, -20+x1, 20+y1, -20+z1, 1, 1, 1);
        DrawLine(-20 + x1, 20+y1, 20+z1, 20+x1, 20+y1, 20+z1, 1, 1, 1);
        DrawLine(-20 + x1, 20+y1, -20+z1, -20+x1, 20+y1, 20+z1, 1, 1, 1);
        
        DrawLine(20 + x1, -20+y1, -20+z1, 20+x1, 20+y1, -20+z1, 1, 1, 1);
        DrawLine(-20 + x1, -20+y1, 20+z1, -20+x1, 20+y1, 20+z1, 1, 1, 1);
        DrawLine(-20 + x1, -20+y1, -20+z1, -20+x1, 20+y1, -20+z1, 1, 1, 1);
        DrawLine(20 + x1, -20+y1, 20+z1, 20+x1, 20+y1, 20+z1, 1, 1, 1);
        DrawLine(20 + x1, -20+y1, -20+z1, 20+x1, -20+y1, 20+z1, 1, 1, 1);
        DrawLine(20 + x1, -20+y1, -20+z1, -20+x1, -20+y1, -20+z1, 1, 1, 1);
        DrawLine(-20 + x1, -20+y1, 20+z1, 20+x1, -20+y1, 20+z1, 1, 1, 1);
        DrawLine(-20 + x1, -20+y1, -20+z1, -20+x1, -20+y1, 20+z1, 1, 1, 1);
    }

    void SetPoint(int x1, int y1, int z1)
    {

    }

    void SetPoint2(int x2, int y2, int z2)
    {

    }

    void SetSecondaryPointAgain(int x2, int y2)
    {

    }

    void SetSecondaryPoint(int x1, int y1)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            moveForward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            moveBackward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            moveLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            moveRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_E)
        {
            moveUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            moveDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            lookUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            lookDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            lookLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            lookRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            moveForward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            moveBackward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            moveLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            moveRight = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E)
        {
            moveUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            moveDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            lookUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            lookDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            lookLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            lookRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        VoxelEngine gamePanel = new VoxelEngine();
        JFrame frame = new JFrame("Quad Render Benchmark");
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

    //get random number between a min and max
    public static int pickRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}