    package Breakout;
    import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Font;
    import java.awt.FontMetrics;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.RenderingHints;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.event.MouseEvent;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ArrayList;
    import javax.sound.sampled.AudioInputStream;
    import javax.sound.sampled.AudioSystem;
    import javax.sound.sampled.Clip;
    import javax.sound.sampled.LineUnavailableException;
    import javax.sound.sampled.UnsupportedAudioFileException;
    import java.util.ConcurrentModificationException;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import javax.swing.SwingUtilities;
    import javax.swing.Timer;
    import javax.swing.event.MouseInputListener;

    import BrickClass.Brick;
    import BrickClass.GameObject;
    import UI.Slider;
    import UI.Switch;
    import UI.Bar;
    import UI.Button;

    public class BrickBreakout extends JPanel implements KeyListener, MouseInputListener
    {
    private static final long serialVersionUID = 1L;
    private static int PREF_W = 600;
    public static int PREF_H = 400;
    private Timer timer;
    private int mouseX, mouseY;

    //Game Booleans
    private boolean ballActive, slowMode, gameOver, settings, mouseClicked, playOnce = true, autonomous;

    //Player Variables
    private int lives = 3, totalLives = 3, initialLives = 3;
    private double speed = 2, initialSpeed = 2;
    private Brick paddle = new Brick(PREF_W / 2 - 40, 325, 80, 20, Color.LIGHT_GRAY, speed * 2, speed * 2, 0, PREF_W, 0, PREF_H);

    //Non-Player Variables
    int level = 1;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private GameObject gameObject = new GameObject(paddle.getX() + (paddle.getW() / 2), paddle.getY() - 10, 10, 10, Color.white, speed, -speed, 0, PREF_W, 0, PREF_H);

    //Sound
    private Clip break1, break2, levelFinish;

    //UI
    private int score = 0;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private Font font = new Font("Quicksand", Font.PLAIN, 25);
    private static FontMetrics metrics;
    private String message;
    private Bar b = new Bar(100, 87, 100, 20, 90, 10, 5, 5, 10, 13, 10, 50, 100, 0, "Lives: ", Color.GRAY, Color.RED, Color.GREEN, Color.BLACK, new Font("Quicksand", Font.PLAIN, 10));
    private Slider speedSlider = new Slider(65, 135, 50, new BasicStroke(1), true, Color.BLACK, 10, 10, 10, Color.BLACK);
    private Button lifeUp = new Button(65, 75, 20, 20, 7, 10, 10, new Font("Quicksand", Font.PLAIN, 10), "+", Color.BLACK, Color.GRAY);
    private Button lifeDown = new Button(65, 100, 20, 20, 7, 10, 10, new Font("Quicksand", Font.PLAIN, 10), "-", Color.BLACK, Color.GRAY);
    private Switch auto = new Switch(65, 162, 50, 20, 3, Color.gray, Color.red, Color.green);

    public double angle;

    public BrickBreakout()
    {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        
            URL file = this.getClass().getResource("break1.wav");
                AudioInputStream audio;
                try {
                    audio = AudioSystem.getAudioInputStream(file);
                    break1 = AudioSystem.getClip();
                    break1.open(audio);
                } catch (IOException | LineUnavailableException e1) {} catch (UnsupportedAudioFileException e1) {}
            file = this.getClass().getResource("break2.wav");
                try {
                    audio = AudioSystem.getAudioInputStream(file);
                    break2 = AudioSystem.getClip();
                    break2.open(audio);
                } catch (IOException | LineUnavailableException e1) {} catch (UnsupportedAudioFileException e1) {}
            file = this.getClass().getResource("levelFinish.wav");
                try {
                    audio = AudioSystem.getAudioInputStream(file);
                    levelFinish = AudioSystem.getClip();
                    levelFinish.open(audio);
                } catch (IOException | LineUnavailableException e1) {} catch (UnsupportedAudioFileException e1) {}
        
        
        paddle.setDirectionKeys(0, 0, 65, 68);
        paddle.setSecondaryDirectionKeys(0, 0, 37, 39);
                
        resetGame();

        timer = new Timer(10, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) { 
                    
                //Updating & Collision Checks
                if(autonomous && !ballActive || !autonomous)
                    paddle.updateKeyMovement();

                if(ballActive && !settings)
                gameObject.update();
                else
                {
                    gameObject.setX(paddle.getX() + (paddle.getW() / 2) - 5);
                    gameObject.setY(paddle.getY() - 10);
                }

                if (gameObject.checkAndReactToCollisionWith(paddle))
                    setBallDirection();

                //Brick Removing
                try {
                    for(Brick i : bricks)
                    {   
                        if (gameObject.checkAndReactToCollisionWith(i))
                        {
                            bricks.remove(i);
                            playBreakSound();
                            if(!autonomous)
                            score += 10;
                        }
                    }
                } catch (ConcurrentModificationException a) {} 

                //Setting Slowmode Speed
                if(slowMode)
                    {
                        if (gameObject.getDx() < 0)
                        gameObject.setDx(-speed / 2);
                        else
                        gameObject.setDx(speed / 2);
                        
                        if (gameObject.getDy() < 0)
                        gameObject.setDy(-speed / 2);
                        else
                        gameObject.setDy(speed / 2);

                        if (paddle.getDx() < 0)
                        paddle.setDx((-speed * 2) / 2);
                        else
                        paddle.setDx((speed * 2) / 2);
                    }
                    else {}

                if (gameObject.getX() < gameObject.getXMin() || gameObject.getX() > (gameObject.getXMax() - gameObject.getW()))
                    gameObject.setDx(-gameObject.getDx());

                if (gameObject.getY() < gameObject.getYMin())
                    gameObject.setDy(-gameObject.getDy());
                
                if (gameObject.getY() > gameObject.getYMax() - gameObject.getH())
                {
                    ballActive = false;
                    lives--;
                }

                repaint();
            }         
        });
        timer.start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //General Drawing Configuration
        g2.setRenderingHints(hints);
        g2.setFont(font);
        metrics = g2.getFontMetrics(font);
        
        //UI Initialization
        lifeUp.setGraphics(g2);
        lifeDown.setGraphics(g2);
        speedSlider.setGraphics(g2);
        b.setGraphics(g2);
        b.setValAsFrac((double) lives / totalLives);
        auto.setGraphics(g2);

        //Mouse X and Y positions set
        try {
        mouseX = this.getMousePosition().x;
        mouseY = this.getMousePosition().y;
        } catch (Exception e){}

        //Entity Drawing
        paddle.draw(g2);
        gameObject.draw(g2);
        
        //UI Drawing
        g2.drawString("Lives: " + lives, 0, PREF_H - (PREF_H / 3) - 10);
        g2.drawString("Press ESC for settings", 0, PREF_H - 10);
        g2.drawString("Score: " + score, 0, PREF_H - (PREF_H / 3) + 10);
        g2.drawString("Angle: " + angle, 0, PREF_H - (PREF_H / 3) + 30);
        g2.drawString("Level: " + level, 0, PREF_H - (PREF_H / 3) + 50);

        for(Brick i : bricks)
            i.draw(g2);
        
        //Game States
        g2.setColor(Color.black);
        if(!ballActive && lives == totalLives && !gameOver)
            {
                message = "Press SPACE to play!";
                g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
            }
        if(!ballActive && lives < totalLives && lives > 0 && !gameOver)
            {
                message = "Press SPACE to continue";
                g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
            }
        if(bricks.size() <= 0)
            {
                gameOver = true;
                message = "Congrats, You Win! Press SPACE to play again!";
                ballActive = false;
                g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));

                if (playOnce)
                {
                    try {
                        Thread.sleep((long) 500);  
                    } catch (Exception e) {}

                    levelFinish.flush();
                    levelFinish.setFramePosition(0);
                    levelFinish.start();
                    playOnce = false;
                }
            }
        if(lives <= 0)
            {
            gameOver = true;
            message = "Oh No, You Lost! Press SPACE to play again!";
            g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
            }

        //Auto Play
        if(autonomous && ballActive && !((gameObject.getX() + 5) - paddle.getW()/2 < paddle.getXMin()) && !((gameObject.getX() + 5) - paddle.getW()/2 > paddle.getXMax() - paddle.getW()))
        paddle.setX(gameObject.getX() - ((paddle.getW() / 2) - 5));

        if(settings)
        {
            //Background fading and panel drawing
            g2.setColor(new Color(100, 100, 100, 200));
            g2.fillRect(0, 0, PREF_W, PREF_H);
            g2.setColor(new Color(255, 255, 255));
            g2.fillRect(50, 50, PREF_W - 100, PREF_H - 100);

            g2.setColor(speedSlider.getLineColor());
            speedSlider.draw();

            
            //Settings Drawing
            g2.drawString("Speed: " + speed, (int) speedSlider.getX() + speedSlider.getWidth() + 10, (int) speedSlider.getY() + 11);
            g2.drawString("Auto", 120, 180);
            lifeUp.draw();
            lifeDown.draw();   
            b.draw();
            b.text = "Lives " + lives + "/" + totalLives;
            auto.draw();

            //Slider Drag Reaction
            if (mouseClicked)
            {
                speedSlider.drag(mouseX);
                speed = speedSlider.getValue() + 1;
            } 
        }

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(!settings)
            paddle.keyWasPressed(key);

        if(key == KeyEvent.VK_SPACE && gameOver && !settings)
        {
            ballActive = true;             
            if(level < 14)
            level++;
            resetGame();
        }
        if(key == KeyEvent.VK_SPACE && !ballActive && !gameOver && !settings)
        {
            ballActive = true;
        }
            
        if(key == KeyEvent.VK_SHIFT)
            slowMode = true;

        if(key == KeyEvent.VK_ESCAPE && settings && totalLives != initialLives || speed != initialSpeed)
        {
            initialLives = totalLives;
            initialSpeed = speed;
            paddle.setDx(speed * 2);
            paddle.setDy(speed * 2);
            gameObject.setDx(speed);
            gameObject.setDy(speed);
            level = 0;
            fullResetGame();
        }

            if(key == KeyEvent.VK_ESCAPE)
                settings = !settings;
        }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(!settings)
            paddle.keyWasReleased(key);
        
        if(key == KeyEvent.VK_SHIFT && !settings)
        {
            slowMode = false;

            //Resetting Speed
            if (gameObject.getDx() < 0)
            gameObject.setDx(-speed);
            else
            gameObject.setDx(speed);
            
            if (gameObject.getDy() < 0)
            gameObject.setDy(-speed);
            else
            gameObject.setDy(speed);
            
            if (paddle.getDx() < 0)
            paddle.setDx(-speed * 2);
            else
            paddle.setDx(speed * 2);
        }
    }


    @Override
    public void keyTyped(KeyEvent e){}

    private static void createAndShowGUI() {
        BrickBreakout gamePanel = new BrickBreakout();
        JFrame frame = new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(auto.isInside(mouseX, mouseY))
        {
            auto.toggleState();
            autonomous = auto.getState();
            resetGame();
        }

        if(speedSlider.isInside(mouseX, mouseY))
            mouseClicked = true;

        speedSlider.setMouseDist(mouseX - (int) speedSlider.getX());
        if (lifeUp.mouseClick(e.getX(), e.getY())) totalLives++;
        if (lifeDown.mouseClick(e.getX(), e.getY()) && totalLives > 1) totalLives--;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) { 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
    }

    public void resetGame()
    {
        for (int i = bricks.size() - 1; i > 0; i--)
            bricks.remove(i);

        for (int i = 10; i < 10 + 5 * level; i += 5)
            for (int ii = 0; ii < 120; ii += 10)
                bricks.add(new Brick(ii * 5, i * 3, 50, 15, Color.getHSBColor(((ii * 5 + i * 3)/ (float) (PREF_W + 75)), 0.5f, 1f)));
        
        lives = totalLives;
        score = 0;
        gameOver = false;
        playOnce = true;
        ballActive = false;
    }

    public void fullResetGame()
    {
        for (int i = bricks.size() - 1; i > 0; i--)
            bricks.remove(i);

        for (int i = 8; i < 5 * level; i += 5)
            for (int ii = 0; ii < 120; ii += 10)
                bricks.add(new Brick(ii * 5, i * 3, 50, 15, Color.getHSBColor(((ii * 5 + i * 3)/ (float) (PREF_W + 75)), 0.5f, 1f)));
        
        lives = totalLives;
        score = 0;
        level = 0;
        gameOver = false;
        playOnce = true;
        ballActive = false;
    }

    public void playBreakSound()
    {
        int rand = (int) (Math.random() * 2 + 1);
        
        switch (rand) {
            case 1:
                break1.flush();
                break1.setFramePosition(0);
                break1.start();
                break;
            
            case 2:
                break2.flush();
                break2.setFramePosition(0);
                break2.start();
                break;

            default:
                break;
        }
    }

    public void setBallDirection()
    {
        double pointOfContactX = (gameObject.getX() - paddle.getX());
        
        angle = 90 + (90 - (90 * (pointOfContactX / (double) paddle.getW())));

        gameObject.setDx(speed * Math.cos(Math.toRadians(angle)));
        gameObject.setDy(-(speed * Math.sin(Math.toRadians(angle))));


    }

}