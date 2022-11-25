package Misc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import UNIVERSAL.UI.TextBox;
import UNIVERSAL.UI.Trail;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class DizzyWheel extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 600;
    private static final int PREF_H = 600;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private static int FPSCap = 60;
    private int mouseX;
    private int mouseY;

    private static FontMetrics metrics;
    private final String path = System.getProperty("user.dir");
    private final int length = 168;
    private final double initialSpeed = 1;
    private final File scoreFile = new File(path + "score.txt");
    private URL url;
    private HttpURLConnection con;
    private FileWriter writer;
    private double speed = 1;
    private double speedIncrement = 0.1;
    private double angle = 90;
    private int currentColor;
    private int hoverColor;
    private boolean gameOver = false;
    private boolean wasInColor = false;
    private int score = 0;
    private int highScore;
    private int globalHighScore = 0;
    
    private boolean debug = false;
    
    Timer timer;
    
    public DizzyWheel()
    {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        assignRandomColor();

        try {
            url = new URL("https://dizzywheel-62a78-default-rtdb.firebaseio.com/highScore");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);
        } catch (Exception e) {}


        System.out.println(path);

        if(!scoreFile.exists())
        {
            try {
                scoreFile.createNewFile();
                writer = new FileWriter(scoreFile);
                writer.write(0);
                highScore = 0;
            } catch (Exception e) {
                System.out.println("score.txt was not able to be created");
            }
        }
        else
        {
            try {
                Scanner sc = new Scanner(scoreFile);
                highScore = sc.nextInt();
            } catch (Exception e) {}
            
        }

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
        metrics = g2.getFontMetrics(new Font("", Font.PLAIN, 30));

        drawLine(g2);
        drawCircle(g2);
        hoverColor = hoveringColor();
        if (!wasInColor)
            wasInColor = (currentColor == hoverColor);
        checkIfLost();
        g2.setFont(new Font("", Font.PLAIN, 30));
        g2.setColor(Color.BLACK);
        
        if(gameOver)
        {
            scoreManager();
            drawGameOver(g2);
        }
        else
        {
            angle += speed;
            g2.drawString("" + score, (int) (PREF_W / 2) - (metrics.stringWidth("" + score) / 2), 30);
        }

        if (debug)
        {
            g2.setFont(new Font("", Font.PLAIN, 10));
            g2.setColor(Color.BLACK);
            g2.drawString("hoveringColor: " + numToString(hoverColor), 0, 15);
            g2.drawString("currentColor: " + numToString(currentColor), 0, 30);
            g2.drawString("speed: " + speed, 0, 45);
            g2.drawString("wasInColor: " + wasInColor, 0, 60);
            g2.drawString("gameOver: " + gameOver, 0, 75);

            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.RED);
            g2.fillOval((PREF_W / 2) - 2, (PREF_H / 2) - 2, 4, 4);
            g2.setColor(Color.BLACK);
            g2.drawLine((PREF_W / 2) - 2, (PREF_H / 4) - 2, (PREF_W / 2) - 2, 0);
            g2.drawLine((PREF_W / 2) - 2, (int) (PREF_H * 0.75) - 2, (PREF_W / 2) - 2, PREF_H);
            g2.drawLine((int) (PREF_W  * 0.75) - 2, (PREF_H / 2) - 2, PREF_W, (PREF_H / 2) - 2);
            g2.drawLine((int) (PREF_W / 4) - 2, (PREF_H / 2) - 2, 0, (PREF_H / 2) - 2);
        }

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
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_F3)
            debug = !debug;
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                if(gameOver)
                    resetGame();
                else if(currentColor == hoverColor)
                {
                    speed = -speed;
                    speed += (speed > 0) ? speedIncrement : -speedIncrement;
                    wasInColor = false;
                    score++;
                    assignRandomColor();
                }
                else
                    gameOver = true;
            }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        DizzyWheel gamePanel = new DizzyWheel();
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

    public void drawCircle(Graphics2D g2)
    {
        int x = (PREF_W / 2) - (int) ((PREF_W * 0.8) / 2 + 0.5);
        int y = (PREF_H / 2) - (int) ((PREF_W * 0.8) / 2 + 0.5);
        int smallX = (PREF_W / 2) - (int) ((PREF_W * 0.6) / 2 + 0.5);
        int smallY = (PREF_H / 2) - (int) ((PREF_W * 0.6) / 2 + 0.5);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.RED);
        g2.drawArc(x, y, (int) (PREF_W * 0.8), (int) (PREF_H * 0.8), 90, 90);
        g2.drawArc(smallX, smallY, (int) (PREF_W * 0.6), (int) (PREF_H * 0.6), 90, 90);
        g2.setColor(Color.GREEN);
        g2.drawArc(x, y, (int) (PREF_W * 0.8), (int) (PREF_H * 0.8), 0, 90);
        g2.drawArc(smallX, smallY, (int) (PREF_W * 0.6), (int) (PREF_H * 0.6), 0, 90);
        g2.setColor(Color.BLUE);
        g2.drawArc(x, y, (int) (PREF_W * 0.8), (int) (PREF_H * 0.8), 270, 90);
        g2.drawArc(smallX, smallY, (int) (PREF_W * 0.6), (int) (PREF_H * 0.6), 270, 90);
        g2.setColor(Color.YELLOW);
        g2.drawArc(x, y, (int) (PREF_W * 0.8), (int) (PREF_H * 0.8), 180, 90);
        g2.drawArc(smallX, smallY, (int) (PREF_W * 0.6), (int) (PREF_H * 0.6), 180, 90);

    }

    public void drawLine(Graphics2D g2)
    {
        g2.setStroke(new BasicStroke(20));
        g2.setColor(numToColor(currentColor));
        g2.drawLine(PREF_W / 2, PREF_H / 2, (PREF_W / 2) + (int) ((length - 10) * Math.sin(Math.toRadians(angle))), (PREF_H / 2) + (int) ((length - 10) * Math.cos(Math. toRadians(angle))));
        g2.fillOval((PREF_W / 2) + (int) ((length) * Math.sin(Math.toRadians(angle))) - 10, (PREF_H / 2) + (int) ((length) * Math.cos(Math.toRadians(angle))) - 10, 20, 20);
        g2.fillOval((PREF_W / 2) - 15, (PREF_H / 2) - 15, 30, 30);
    }

    public void drawGameOver(Graphics2D g2)
    {
        g2.setStroke(new BasicStroke(0));
        g2.setColor(new Color(50, 50, 50, 200));
        g2.fillRect(PREF_W / 16, PREF_H / 16, (int) (PREF_W * 0.9375) - (PREF_W / 16) , (int) (PREF_H * 0.9375) - (PREF_H / 16));
        g2.setColor(new Color(255, 255, 255));
        g2.drawString("Your Score: " + score, (PREF_W / 16) + 15, (PREF_H / 16) + 30);
        g2.drawString("Your High Score: " + highScore, (PREF_W / 16) + 15, (PREF_H / 16) + 70);
        g2.drawString("Global High Score: " + score, (PREF_W / 16) + 15, (PREF_H / 16) + 110);
    }

    public int hoveringColor()
    {
        if(angle % 360 >= 0 && angle % 360 < 90 || angle % 360 <= -270 && angle % 360 > -360)
            return 1;
        else if(angle % 360 >= 90 && angle % 360 < 180 || angle % 360 <= -180 && angle % 360 > -270)
            return 2;
        else if(angle % 360 >= 180 && angle % 360 < 270 || angle % 360 <= -90 && angle % 360 > -180)
            return 3;
        else if(angle % 360 >= 270 && angle % 360 < 360 || angle % 360 < 0 && angle % 360 > -90)
            return 4;
        else
            return -1;
    }

    public void assignRandomColor()
    {
        int rand = (int) (Math.random() * 4) + 1;
        while(rand == currentColor)
            rand = (int) (Math.random() * 4) + 1;
        currentColor = rand;
    }

    public String numToString(int num)
    {
        switch (num) {
            case 1:
                return "blue";
            case 2:
                return "green";
            case 3:
                return "red";
            case 4:
                return "yellow";                
            default:
                return "error";
        }
    }

    public Color numToColor(int num)
    {
        switch (num) {
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.RED;
            case 4:
                return Color.YELLOW;                
            default:
                return Color.BLACK;
        }
    }

    public void checkIfLost()
    {
        if(wasInColor && currentColor != hoverColor)
            gameOver = true;
    }

    public void resetGame()
    {
        gameOver = false;
        speed = initialSpeed;
        wasInColor = false;
        score = 0;
        angle = 90;
        assignRandomColor();
    }
    
    public void scoreManager()
    {
        if(score > highScore)
        try {
            highScore = score;
            writer.write(highScore);
        } catch (Exception e) {}
    }
}