    package BrickBreakerGame;
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
    import java.util.ConcurrentModificationException;

    import javax.swing.ImageIcon;
    import java.awt.Image;

    import javax.sound.sampled.AudioInputStream;
    import javax.sound.sampled.AudioSystem;
    import javax.sound.sampled.Clip;
    import javax.sound.sampled.LineUnavailableException;
    import javax.sound.sampled.UnsupportedAudioFileException;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import javax.swing.SwingUtilities;
    import javax.swing.Timer;
    import javax.swing.event.MouseInputListener;

    import BrickClass.Brick;
    import BrickClass.GameObject;

    public class BrickBreakerGame extends JPanel implements KeyListener, MouseInputListener
    {
    private static final long serialVersionUID = 1L;
    private static int PREF_W = 600;
    public static int PREF_H = 400;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private Font font = new Font("Quicksand", Font.PLAIN, 25);
    private Timer timer;
    private static FontMetrics metrics;
    private int mouseX, mouseY;
    private double speed = 2.0;
    private Brick paddle = new Brick(PREF_W / 2 - 40, 325, 80, 20, Color.LIGHT_GRAY, speed * 2, speed * 2, 0, PREF_W, 0, PREF_H);
    private GameObject gameObject = new GameObject(paddle.getX() + (paddle.getW() / 2), paddle.getY() - 10, 10, 10, Color.white, speed, speed, 0, PREF_W, 0, PREF_H);
    private boolean ballActive, slowMode, gameOver, settings;
    private int lives = 3;
    private int totalLives = 3;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private String message;
    private Brick lifeButtonUp = new Brick(75, 65, 10, 10, Color.lightGray);
    private Brick lifeButtonDown = new Brick(lifeButtonUp.getX(), lifeButtonUp.getY() + lifeButtonUp.getH(), lifeButtonUp.getW(), lifeButtonUp.getH(), Color.lightGray);

    public BrickBreakerGame()
    {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        
        //   URL file = this.getClass().getResource("Ping-pong-ball-bounce.wav");
        //               AudioInputStream audio;
        //               try {
        //                  audio = AudioSystem.getAudioInputStream(file);
        //                  bounceSound = AudioSystem.getClip();
        //                  bounceSound.open(audio);
        //               } catch (IOException | LineUnavailableException e1) {} //initialize a sound clip objectxs   
        //               catch (UnsupportedAudioFileException e1) {
        //               }
        
        
        paddle.setDirectionKeys(0, 0, 65, 68);
        paddle.setSecondaryDirectionKeys(0, 0, 37, 39);
                
        resetGame();

        timer = new Timer(10, 
            new ActionListener(){
                
            @Override   
            public void actionPerformed(ActionEvent e) { 
                    
                paddle.updateKeyMovement();

                if(ballActive)
                gameObject.update();
                else
                {
                    gameObject.setX(paddle.getX() + (paddle.getW() / 2) - 5);
                    gameObject.setY(paddle.getY() - 10);
                }

                gameObject.checkAndReactToCollisionWith(paddle);

                try {
                    for(Brick i : bricks)
                    {   
                        if (gameObject.checkAndReactToCollisionWith(i))
                            bricks.remove(i);
                    }
                } catch (ConcurrentModificationException a) {} 

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
                    else
                    {}

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
        g2.setRenderingHints(hints);
        g2.setFont(font);
        metrics = g2.getFontMetrics(font);
        
        try {
        mouseX = this.getMousePosition().x;
        mouseY = this.getMousePosition().y;
        }
        catch (Exception e){}

        paddle.draw(g2);
        gameObject.draw(g2);
        
        g2.drawString("Lives: " + lives, 0, PREF_H - (PREF_H / 3));
        g2.drawString("Press ESC for settings", 0, PREF_H - 10);

        for(Brick i : bricks)
            i.draw(g2);
        
        g2.setColor(Color.black);
        if(!ballActive && lives == 3 && !gameOver)
            {
                message = "Press SPACE to play!";
                g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
            }
        if(!ballActive && lives < 3 && lives > 0 && !gameOver)
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
            }
            if(lives <= 0)
            {
                gameOver = true;
                message = "Oh No, You Lost! Press SPACE to play again!";
                g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
            }

            if(settings)
            {
                g2.setColor(new Color(100, 100, 100, 200));
                g2.fillRect(0, 0, PREF_W, PREF_H);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRect(50, 50, PREF_W - 100, PREF_H - 100);

                lifeButtonUp.draw(g2);
                lifeButtonDown.draw(g2);

                g2.drawString("Lives: " + totalLives, lifeButtonUp.getX() + 20, lifeButtonDown.getY() + 9);
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
            resetGame();
        }
        if(key == KeyEvent.VK_SPACE && !ballActive && !gameOver && !settings)
            ballActive = true;
        if(key == KeyEvent.VK_SHIFT)
            slowMode = true;
        if(key == KeyEvent.VK_ESCAPE && settings)
            resetGame();
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
        BrickBreakerGame gamePanel = new BrickBreakerGame();
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

        if (lifeButtonUp.isInside(mouseX, mouseY))
            totalLives++;

        if (lifeButtonDown.isInside(mouseX, mouseY))
            totalLives--;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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

        for (int i = 8; i < 75; i += 5)
            for (int ii = 0; ii < 120; ii += 10)
                bricks.add(new Brick(ii * 5, i * 3, 50, 15, Color.getHSBColor(((ii * 5 + i * 3)/ (float) (PREF_W + 75)), 1f, 1f)));
        
        lives = totalLives;
        gameOver = false;
    }

    }