package SpaceInvaders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;
import java.awt.Point;
import java.awt.image.BufferedImage;
import BrickClass.Alien;
import BrickClass.Brick;
import BrickClass.GameObject;
import BrickClass.Projectile;
import Font.FontInstaller;
import UI.Slider;
import UI.SoundLoader;
import UI.SpriteSheet;
import UI.Switch;
import UI.Bar;
import UI.Button;

public class SpaceInvaders extends JPanel implements KeyListener, MouseInputListener
{
private static final long serialVersionUID = 1L;
private static int PREF_W = 600;
public static int PREF_H = 800;
private Timer timer;
private int mouseX, mouseY;

//Game Booleans
private boolean ballActive, tempBallActive, gameOver, settings, mouseClicked, playOnce = true, autonomous, debug, flipAliens;

//Sounds
private SoundLoader shoot = new SoundLoader(this.getClass().getResource("sound/shoot.wav"));
private SoundLoader alienHit = new SoundLoader(this.getClass().getResource("sound/alienHit.wav"));
private SoundLoader shipHit = new SoundLoader(this.getClass().getResource("sound/shipHit.wav"));
private SoundLoader ufoSound = new SoundLoader(this.getClass().getResource("sound/ufoSound.wav"));

//Player Variables
private int lives = 3, totalLives = 3, initialLives = 3;
private double speed = 5, initialSpeed = speed;
private Brick ship = new Brick(PREF_W / 2 - 40, PREF_H - PREF_H/7, 39, 24, Color.LIGHT_GRAY, speed, speed, 0, PREF_W, 0, PREF_H);

//Non-Player Variables
private int alienTimer = 0;
private ArrayList<Alien> alien = new ArrayList<Alien>();
private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
private Projectile laser = new Projectile((ship.getX() + (ship.getW() / 2)), (ship.getY() - 10), 5, 15, Color.white, (double) 0, -speed, 0, PREF_W, 0, PREF_H);

//Images
private SpriteSheet laser1 = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser1.png").getImage(), 3, 7, 4);
private SpriteSheet laser2 = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser2.png").getImage(), 3, 7, 4);
private SpriteSheet laser3 = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser3.png").getImage(), 3, 7, 4);
private SpriteSheet laser4 = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser4.png").getImage(), 3, 7, 4);
private SpriteSheet laser5 = new SpriteSheet(new ImageIcon("SpaceInvaders/projectiles/laser5.png").getImage(), 3, 7, 4);
private SpriteSheet alien1 = new SpriteSheet(new ImageIcon("SpaceInvaders/aliens/alien1.png").getImage(), 8, 8, 2);
private SpriteSheet alien2 = new SpriteSheet(new ImageIcon("SpaceInvaders/aliens/alien2.png").getImage(), 11, 8, 2);
private SpriteSheet alien3 = new SpriteSheet(new ImageIcon("SpaceInvaders/aliens/alien3.png").getImage(), 12, 8, 2);
private SpriteSheet shipShoot = new SpriteSheet(new ImageIcon("SpaceInvaders/random/ship.png").getImage(), 13, 8, 3);
private SpriteSheet alienDeathParticles = new SpriteSheet(new ImageIcon("SpaceInvaders/particles/alienDeath.png").getImage(), 16, 11, 6);
private SpriteSheet projectileCollisionParticles = new SpriteSheet(new ImageIcon("SpaceInvaders/particles/projectileCollision.png").getImage(), 8, 8, 5);
private Image ufo = new ImageIcon("SpaceInvaders/aliens/ufo.png").getImage();
//explosions
private Image explosion = new ImageIcon("SpaceInvaders/random/explosion.png").getImage();
private ArrayList<Point> explosions = new ArrayList<Point>();
//blockers
private Image blocker = new ImageIcon("SpaceInvaders/random/blocker.png").getImage();
private ArrayList<Point> blockers = new ArrayList<Point>();

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


//animations
private double alienAnim = 0;
private double shipLaserAnim = 0;
private double deathAnim = 0;
private boolean playDeathAnim = false;
private int daX = 0;
private int daY = 0;
private double hitAnim = 0;
private boolean playHitAnim = false;
private int haX = 0;
private int haY = 0;
private boolean canShoot = false;

//alien shooting
private ArrayList<Brick> lowAliens = new ArrayList<Brick>();

public SpaceInvaders()
{
    addKeyListener(this);
    addMouseListener(this);
    setFocusable(true);
    requestFocus();
    
    FontInstaller.installFont();

    ship.setDirectionKeys(0, 0, 65, 68);
    ship.setSecondaryDirectionKeys(0, 0, 37, 39);

    ship.ss = shipShoot;
    laser.ss = laser5;

    for (int i = 1; i <= 4; i++)
        blockers.add(new Point(((PREF_W / 5) * i)-25, PREF_H - 200));
            
    resetGame();

    timer = new Timer(10, new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) { 
                
            alienTimer++;

            //Updating & Collision Checks
            if(autonomous && !ballActive || !autonomous)
                ship.updateKeyMovement();

            if(ballActive && !settings)
                laser.update();
            else
            {
                laser.setX(ship.getX() + (ship.getW() / 2) - 5);
                laser.setY(ship.getY() - 10);
            }

            laser.checkAndReactToCollisionWith(ship);
                   
                //Brick Removing
            try {
                for(Alien i : alien)
                {   
                    if (laser.checkAndReactToCollisionWith(i))
                    {
                        playDeathAnim = true;
                        alienHit.get().setFramePosition(0);
                        alienHit.play();

                        daX = (int) i.getX();
                        daY = (int) i.getY();
                        if (i.getValue() <= 0)
                        alien.remove(i);
                        else
                        {
                            Color rgb = i.getColor();
                            float[] arr = Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
                            i.setValue(i.getValue()-1);
                            i.setColor(Color.getHSBColor(arr[0], (float) (arr[1] - 0.25), arr[2]));
                        }
                        if(!autonomous)
                        score += 10;

                        ballActive = false; 
                    }

                }
            } catch (ConcurrentModificationException a) {} 
            if (alienTimer >= 50)
            {
                for (Alien i : alien)
                {
                    if (i.getX() > ((PREF_W - i.getW()) - 5) || i.getX() < 5)
                    {
                        flipAliens = true;
                        i.setX((int) (i.getX() + (i.getDx() * 10)));
                    }
                    else
                        i.setX((int) (i.getX() + (i.getDx() * 10))); 
                    
                    if(lowAliens.contains(i))
                    i.projectileChance(projectiles, new Projectile(0, 0, 3, 7, Color.white, 0, speed/2, 0, PREF_W, 0, PREF_H));
                }
                    
            alienAnim += 1;
            if (alienAnim >= 2) alienAnim = 0;

                alienTimer = 0;
            }

            if (flipAliens)
            {
                for(Alien j : alien)
                {
                    j.setDx(-j.getDx());
                    j.setX((int) (j.getX() + (j.getDx() * 20)));
                    j.setY((int) (j.getY() + j.getDy() * 20));
                }
                flipAliens = false;
            }

            if (laser.getY() < laser.getYMin() || laser.getY() > laser.getYMax() - laser.getH())
            {
                ballActive = false;
                playHitAnim = true;
                haX = (int) laser.getX();
                haY = (int) laser.getY();
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
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, PREF_W, PREF_H);
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

    setLowAliens();

    //Entity Drawing
    ship.drawImage(g2, (tempBallActive && shipLaserAnim < ship.ss.getLength()) ? ((int) shipLaserAnim % ship.ss.getLength()) : 0);

    
    if (!tempBallActive && !ballActive) shipLaserAnim = 0;
    
    shipLaserAnim += 0.1;
    
    if (ballActive && canShoot) 
    {
        shoot.get().setFramePosition(0);
        shoot.play();
        canShoot = false;
    }
    
    if (shipLaserAnim >= ship.ss.getLength()) 
    {
        ballActive = true;
        tempBallActive = false;
    }
    
    if (playDeathAnim) 
    {
        g2.drawImage(alienDeathParticles.get((int) (deathAnim % alienDeathParticles.getLength())).getScaledInstance(alien.get(0).getW(), alien.get(0).getH(), Image.SCALE_DEFAULT), daX, daY, null);
        deathAnim+=0.1;
    }
    if (deathAnim >= alienDeathParticles.getLength())
    {
        playDeathAnim = false;
        deathAnim = 0;
    }
    
    if (playHitAnim)
    {
        g2.drawImage(projectileCollisionParticles.get((int) (hitAnim % projectileCollisionParticles.getLength())).getScaledInstance(20, 20, Image.SCALE_DEFAULT), haX-(laser.getW()/2), haY, null);
        hitAnim+=0.1;
    }
    if (hitAnim >= projectileCollisionParticles.getLength())
    {
        playHitAnim = false;
        hitAnim = 0;
    }
    
    g2.setColor(Color.white);
    
    //UI Drawing
    g2.drawString("Lives: " + lives, 0, PREF_H - (PREF_H / 3) - 10);
    g2.drawString("Press ESC for settings", 0, PREF_H - 50);
    g2.drawString("Score: " + score, 0, PREF_H - (PREF_H / 3) + 10);
    
    if(debug)
    {
        g2.drawString("Speed: " + speed, 0, PREF_H - (PREF_H / 3) + 50);
        g2.drawString("Alien Timer: " + alienTimer, 200, PREF_H - (PREF_H / 3) + 30);
        g2.drawString("X: " + alien.get(1).getX(), 200, PREF_H - (PREF_H / 3) + 50);
    }
    
    for(Alien i : alien)
    i.drawImage(g2, (int) alienAnim);
    
    
    BufferedImage bi = new BufferedImage(PREF_W, PREF_H, BufferedImage.TYPE_INT_RGB);
    Graphics2D gg = bi.createGraphics();
    for (Point p: blockers)
    {
        g2.drawImage(SpriteSheet.toBufferedImage(blocker).getScaledInstance(50, 50, Image.SCALE_SMOOTH), p.x, p.y, null);
        gg.drawImage(SpriteSheet.toBufferedImage(blocker).getScaledInstance(50, 50, Image.SCALE_SMOOTH), p.x, p.y, null);
    }
    
    for (Point p: explosions)
    {
        g2.drawImage(SpriteSheet.toBufferedImage(explosion).getScaledInstance(30, 30, Image.SCALE_SMOOTH), p.x-15, p.y-15, null);
        gg.drawImage(SpriteSheet.toBufferedImage(explosion).getScaledInstance(30, 30, Image.SCALE_SMOOTH), p.x-15, p.y-15, null);
    }
    
    if (bi.getRGB((int) (laser.getX()+(laser.getW()/2)), (int) (laser.getY()+laser.getH())) == Color.GREEN.getRGB() && !ship.checkAndReactToCollisionWith(laser))
    {
        explosions.add(new Point((int) (laser.getX()+(laser.getW()/2)), (int) (laser.getY()+laser.getH())));
        ballActive = false;
    }
    // g2.drawImage((Image) bi, 0, 0, null);
    
    for(Projectile i : projectiles)
    {
        i.draw(g2);
        i.update();
        if(i.checkAndReactToCollisionWith(ship))
        {
            lives--;
            projectiles.remove(i);
        }
    }
    
    if (ballActive)
        laser.drawImage(g2, (int) shipLaserAnim % laser.ss.getLength());
    
    //Game States
    g2.setColor(Color.white);
    if(!ballActive && lives == totalLives && !gameOver)
        {
            // message = "Press SPACE to play!";
            message = "";
            g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));
        }
    if(alien.size() <= 0)
        {
            gameOver = true;
            message = "Congrats, You Won! Press SPACE to play again!";
            ballActive = false;
            g2.drawString(message, ((PREF_W/2) - metrics.stringWidth(message) / 2), PREF_H - (PREF_H / 4));

            if (playOnce)
            {
                try {
                    Thread.sleep((long) 500);  
                } catch (Exception e) {}

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
    if(autonomous && ballActive && !((laser.getX() + 5) - ship.getW()/2 < ship.getXMin()) && !((laser.getX() + 5) - ship.getW()/2 > ship.getXMax() - ship.getW()))
    ship.setX(laser.getX() - ((ship.getW() / 2) - 5));

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
        ship.keyWasPressed(key);

    if(key == KeyEvent.VK_SPACE && gameOver && !settings)
    {
        ballActive = true;             
        // resetGame();
    }
    if(key == KeyEvent.VK_SPACE && !ballActive && !gameOver && !settings)
    {
        // ballActive = true;
        canShoot = true;
        tempBallActive = true;
    }

    if(key == KeyEvent.VK_ESCAPE && settings && totalLives != initialLives || speed != initialSpeed)
    {
        initialLives = totalLives;
        initialSpeed = speed;
        ship.setDx(speed * 2);
        ship.setDy(speed * 2);
        laser.setDy(speed);
        fullResetGame();
    }

        if(key == KeyEvent.VK_F1)
            debug = !debug;

        // if(key == KeyEvent.VK_ESCAPE)
        //     settings = !settings;

    }

@Override
public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();

    if(!settings)
        ship.keyWasReleased(key);
}


@Override
public void keyTyped(KeyEvent e){}

private static void createAndShowGUI() {
    SpaceInvaders gamePanel = new SpaceInvaders();
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

    explosions.add(e.getPoint());

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
    for (int i = alien.size() - 1; i > 0; i--)
        alien.remove(i);

        int verticalDist = 50;
        int horizontalDist = 40;
        
        
        for (int i = 0; i < 5; i += 1) //y
        for (double ii = 1.2; ii < (PREF_W / horizontalDist) - 1; ii += 1) //x
        {
            int x = (int) (ii * horizontalDist);
            int y = i * verticalDist;
            double alienScale = 0.6;
            if(i == 0)
            {
                int alienWidth = (int) (alien1.get().getWidth(null) * alienScale);
                int alienHeight = (int) (alien1.get().getHeight(null) * alienScale);
                alien.add(new Alien(x, y, alienWidth * 4, alienHeight * 4, 1, alien1, 1, 1));
            }
            else if(i == 1 || i == 2)
            {
                int alienWidth = (int) (alien2.get().getWidth(null) * alienScale);
                int alienHeight = (int) (alien2.get().getHeight(null) * alienScale);
                alien.add(new Alien(x, y, alienWidth * 4, alienHeight * 4, 1, alien2, 1, 1));
            }
            else if(i == 3 || i == 4)
            {
                int alienWidth = (int) (alien3.get().getWidth(null) * alienScale);
                int alienHeight = (int) (alien3.get().getHeight(null) * alienScale);
                alien.add(new Alien(x, y, alienWidth* 4, alienHeight * 4, 1, alien3, 1, 1));
            }
        }

    lives = totalLives;
    score = 0;
    gameOver = false;
    playOnce = true;
    ballActive = false;
}

public void fullResetGame()
{    
    resetGame();

    lives = totalLives;
    score = 0;
    gameOver = false;
    playOnce = true;
    ballActive = false;
}

    public boolean aliensMoveDirection()
    {
        for (Alien i : alien)
        {
            if (i.getX() > PREF_W - i.getW() - 5 || i.getX() < 5)
            {
                i.setDx(-i.getDx());
                return true;
            }
        }

        return false;
    }

    public void moveAliens(boolean vertical)
    {
        if(vertical)
        {
            for(Alien i : alien)
                i.setY((int) (i.getY() + i.getDy()));
        }
        else
        {
            for(Alien i : alien)
                i.setX((int) (i.getX() + i.getDx()));
        }
    }

    public void spawnAlienProjectile(int x, int y)
    {
        int rand = (int) (Math.random() * 6) + 1;
        switch (rand) {
            case 1:
                projectiles.add(new Projectile(x, y, 3, 7, laser1.get(0)));
                break;
            case 2:
                projectiles.add(new Projectile(x, y, 3, 7, laser2.get(0)));
                break;
            case 3:
                projectiles.add(new Projectile(x, y, 3, 7, laser3.get(0)));
                break;
            case 4:
                projectiles.add(new Projectile(x, y, 3, 7, laser4.get(0)));
                break;
            case 5:
                projectiles.add(new Projectile(x, y, 3, 7, laser5.get(0)));
                break;
        
            default:
                break;
        }
    }

    public void setLowAliens()
    {
        /*
        steps:
        1.) 
            a.) clear lowaliens
            b.) sort alien list by x values smallest to biggest
            c.) put each column (sorted by x val) in a 2d matrix
        2.)
            a.) find the brick with the lowest y value in each column
            b.) add it to the list
        */

        //1.) a.)
        lowAliens.clear();

        //1.) b.)
        Brick.compareX = true;
        Collections.sort(alien);

        //1.) c.)
        ArrayList<ArrayList<Brick>> temp = new ArrayList<ArrayList<Brick>>();
        int lastX = (int) alien.get(0).getX();
        temp.add(new ArrayList<Brick>());
        temp.get(0).add(alien.get(0));
        for (int i = 1; i < alien.size(); i++)
        {
            if ((int) alien.get(i).getX() == lastX)
            {
                temp.get(temp.size()-1).add(alien.get(i));
            }
            else
            {
                temp.add(new ArrayList<Brick>());
                temp.get(temp.size()-1).add(alien.get(i));
            }

            lastX = (int) alien.get(i).getX();
        }

        //2.) a.)
        Brick.compareX = false;
        for (ArrayList<Brick> a: temp)
            Collections.sort(a);

        //2.) b.)
        for (ArrayList<Brick> a: temp)
            lowAliens.add(a.get(a.size()-1));
    }

    public void checkBlockerHit(Brick b, BufferedImage bi)
    {
        if (bi.getRGB((int) (b.getX()+(b.getW()/2)), (int) (b.getY()+b.getH())) == Color.GREEN.getRGB())
        {
            explosions.add(new Point((int) (b.getX()+(b.getW()/2)), (int) (b.getY()+b.getH())));
        }
    }
}