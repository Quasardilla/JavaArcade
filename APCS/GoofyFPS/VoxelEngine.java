package GoofyFPS;

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
    double approximation;
    double CSpeed;
    double CameraXPosition;
    double CameraYPosition;
    double CameraZPosition;
    double CosineX;
    double CosineY;
    double DFC;
    double FOV;
    double lastBlockHeight;
    double lastX;
    double lastY;
    double lastZ;
    double mousePosition;
    double renderX;
    double renderY;
    double renderZ;
    double RotationX;
    double RotationY;
    double Shape;
    double Short;
    double SineX;
    double SineY;
    double SpeedX;
    double SpeedY;
    double Started;
    double ViewDistance;
    double WorldLoadingPercentage;
    double WorldSizeChunks;
    double x1;
    double x2;
    double y1;
    double y2;
    double z1;
    double z2;

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


    ArrayList<Double> BlockHeights = new ArrayList<Double>();


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
        FOV = 205;

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


        calculateCubePosition(g2);



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
        approximation = (double) (y2 - y1) / (double) (x2 - x1);

        if (x1 > this.getWidth())
        {
            if (x2 > this.getWidth()) return;
            else SetSecondaryPoint(this.getWidth(), y1 + ((this.getWidth() - x1) * approximation));
        }
        if (y1 > this.getHeight())
        {
            if (y2 > this.getHeight()) return;
            else SetSecondaryPoint(x1 + ((double) (this.getHeight() - y1) / approximation), this.getHeight());
        }

        if (x1 < 0)
        {
            if (x2 < 0) return;
            else SetSecondaryPoint(0, y1 + ((0 - x1) * approximation));
        }
        if (y1 < 0)
        {
            if (y2 < 0) return;
            else SetSecondaryPoint(x1 + ((double) (0 - y1) / approximation), 0);
        }

        if (x2 > this.getWidth()) SetSecondaryPointAgain(this.getWidth(), y1 + ((this.getWidth() - x1) * approximation));
        if (y2 > this.getHeight()) SetSecondaryPointAgain(x1 + ((double) (this.getHeight() - y1) / approximation), this.getHeight());
        if (x2 < 0) SetSecondaryPointAgain(0, y1 + ((0 - x1) * approximation));
        if (y2 < 0) SetSecondaryPointAgain(x1 + ((double) (0 - y1) / approximation), 0);
    }

    void Calculate()
    {
        SineX = Math.sin(Math.toRadians(RotationX));
        CosineX = Math.cos(Math.toRadians(RotationX));
        SineY = Math.sin(Math.toRadians(RotationY));
        CosineY = Math.cos(Math.toRadians(RotationY));
    }

    void calculateCubePosition(Graphics2D g2)
    {
        lastY = 0;
        lastX = 0;
        lastZ = 0;
        for (int i = 0; i < Math.sqrt(answer) * 3; i++)
        {
            for (int ii = 0; ii < Math.sqrt(BlockHeights.size()); ii++)
            {
                MakeCubeAt(lastX, 40*BlockHeights.get((int) (lastY / 40)), lastZ, g2);
                lastX += 40;
                lastY += 40;
            }
            lastZ += 40;
            lastX = 0;
        }
    }

    void Clip()
    {
        if (z1 < DFC || z2 < DFC)
        {
            Short = (double) (DFC - z1) / (double) (z2 - z1);

            if (z1 < DFC) SetPoint(x1 + ((x2-x1) * Short), y1 + ((y2-y1) * Short), DFC);
            else SetPoint2(x1 + ((x2 - x1) * Short), y1 + ((y2-y1) * Short), DFC);
        }
    }

    void ConLook()
    {
        if (lookRight) RotationY += (SpeedX - 1);
        if (lookLeft) RotationY += (SpeedY + 1);
        if (lookUp) RotationX += 3;
        if (lookDown) RotationX -= 3;
        if (moveUp) CameraYPosition += 4;
        if (moveDown) CameraYPosition -= 4;

        if (lookRight || lookLeft || lookUp || lookDown)
        System.out.println("look right: "+lookRight+" look left: "+lookLeft+" lookup: "+lookUp+" lookDown: "+lookDown);

        if (RotationX > 90) RotationX = 90;
        if (RotationX < -90) RotationX = -90;
    }

    void DrawLine(double x1, double y1, double z1, double x2, double y2, double z2, Graphics2D g2)
    {
        SetPoint(x1-CameraXPosition, y1-CameraYPosition, z1-CameraZPosition);
        SetPoint2(x2-CameraXPosition, y2-CameraYPosition, z2-CameraZPosition);
        SetPoint((z1 * SineY) + (x1 * CosineY), y1, (z1*CosineY) - (x1 * SineY));
        SetPoint2((z2 * SineY) + (x2 * CosineY), y2, (z2 * CosineY) - (x2 * SineY));
        SetPoint(x1, (y1 * CosineX) - (z1 * SineX), (y1 * SineX) + (z1 * CosineX));
        SetPoint2(x2, (y2 * CosineX) - (z2 * SineX), (y2 * SineX) + (z1 * CosineX));
    
        if (!(z1 < DFC && z2 < DFC))
        {
            Clip();

            SetSecondaryPoint(FOV * ((double) x1/(double) z1), FOV * ((double) y1 / (double) z1));
            SetSecondaryPointAgain(FOV * ((double) x2/(double) z2), FOV * ((double) y2 / (double) z2));
            
            approximation();
        
            if (Started == 1)
            {
                g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            }
        }
    }

    void MakeCubeAt(double x1, double y1, double z1, Graphics2D g2)
    {
        Calculate();

        renderX = x1;
        renderY = y1;
        renderZ = z1;

        DrawLine(20 + x1, 20+y1, -20+z1, 20+x1, 20+y1, 20+z1, g2);
        DrawLine(20 + x1, 20+y1, -20+z1, -20+x1, 20+y1, -20+z1, g2);
        DrawLine(-20 + x1, 20+y1, 20+z1, 20+x1, 20+y1, 20+z1, g2);
        DrawLine(-20 + x1, 20+y1, -20+z1, -20+x1, 20+y1, 20+z1, g2);
        
        DrawLine(20 + x1, -20+y1, -20+z1, 20+x1, 20+y1, -20+z1, g2);
        DrawLine(-20 + x1, -20+y1, 20+z1, -20+x1, 20+y1, 20+z1, g2);
        DrawLine(-20 + x1, -20+y1, -20+z1, -20+x1, 20+y1, -20+z1, g2);
        DrawLine(20 + x1, -20+y1, 20+z1, 20+x1, 20+y1, 20+z1, g2);
        DrawLine(20 + x1, -20+y1, -20+z1, 20+x1, -20+y1, 20+z1, g2);
        DrawLine(20 + x1, -20+y1, -20+z1, -20+x1, -20+y1, -20+z1, g2);
        DrawLine(-20 + x1, -20+y1, 20+z1, 20+x1, -20+y1, 20+z1, g2);
        DrawLine(-20 + x1, -20+y1, -20+z1, -20+x1, -20+y1, 20+z1, g2);
    }

    void SetPoint(double x1, double y1, double z1)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
    }

    void SetPoint2(double x2, double y2, double z2)
    {
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    void SetSecondaryPointAgain(double x2, double y2)
    {
        this.x2 = x2;
        this.y2 = y2;
    }

    void SetSecondaryPoint(double x1, double y1)
    {
        this.x1 = x1;
        this.y1 = y1;
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