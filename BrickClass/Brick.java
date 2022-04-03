package BrickClass;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    protected int x, y ,width;
    protected int height;
    protected double dx, dy;
    protected int XMin, XMax, YMin, YMax;
    protected Color color;
    protected boolean left, right, up, down;
    protected int leftKey, rightKey, upKey, downKey;
    protected Image img;
    
    public Brick(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
    }

    public Brick(int x, int y, int width, int height, Color color) //Another constructor with optional values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Brick(int x, int y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = dx;
        this.dy = dy;
    }

    public Brick(int x, int y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = dx;
        this.dy = dy;
        this.XMax = XMax;
        this.XMin = XMin;
        this.YMax = YMax;
        this.YMin = YMin;
    }

    public Brick(int x, int y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = dx;
        this.dy = dy;
        this.XMax = XMax;
        this.XMin = XMin;
        this.YMax = YMax;
        this.YMin = YMin;
    }

    public Brick(int x, int y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = dx;
        this.dy = dy;
        this.XMax = XMax;
        this.XMin = XMin;
        this.YMax = YMax;
        this.YMin = YMin;
        this.img = img;
    }

    public Brick(int x, int y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.dx = dx;
        this.dy = dy;
        this.XMax = XMax;
        this.XMin = XMin;
        this.YMax = YMax;
        this.YMin = YMin;
        this.img = img;
    }

    //Standard getter & setter methods
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }

    public int getW()
    {
        return this.width;
    }
    
    public void setW(int w)
    {
        this.width = w;
    }

    public int getH()
    {
        return this.height;
    }
    
    public void setH(int h)
    {
        this.height = h;
    }
    public Color getColor()
    {
        return this.color;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }

    public double getDx()
    {
        return this.dx;
    }
    
    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public void setDx(double dx)
    {
        this.dx = dx;
    }

    public double getDy()
    {
        return this.dy;
    }

    public void setDy(int dy)
    {
        this.dy = dy;
    }

    public void setDy(double dy)
    {
        this.dy = dy;
    }
    
    public void setXMax(int XMax)
    {
        this.XMax = XMax;
    }

    public int getXMax()
    {
        return this.XMax;
    }
    
    public void setXMin(int XMin)
    {
        this.XMin = XMin;
    }

    public int getXMin()
    {
        return this.XMin;
    }

    public void setYMax(int YMax)
    {
        this.YMax = YMax;
    }

    public int getYMax()
    {
        return this.YMax;
    }
    
    public void setYMin(int YMin)
    {
        this.YMin = YMin;
    }

    public int getYMin()
    {
        return this.YMin;
    }

    public void setUpKey(int upKey)
    {
        this.upKey = upKey;
    }

    public int getUpKey()
    {
        return this.upKey;
    }

    public void setDownKey(int downKey)
    {
        this.downKey = downKey;
    }

    public int getDownKey()
    {
        return this.downKey;
    }

    public void setLeftKey(int leftKey)
    {
        this.leftKey = leftKey;
    }

    public int getLeftKey()
    {
        return this.leftKey;
    }

    public void setRightKey(int rightKey)
    {
        this.rightKey = rightKey;
    }

    public int getrightKey()
    {
        return this.rightKey;
    }

    public void setDirectionKeys(int upKey, int downKey, int leftKey, int rightKey)
    {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public Brick killBrick()
    {
        this.upKey = 0;
        this.downKey = 0;
        this.leftKey = 0;
        this.rightKey = 0;
        this.color = Color.lightGray;

        return new Brick(x, y, width, height, color);
    }


    public String toString()
    {
        return ("x=" + x + ", y=" + y + ", w=" + width + ", h= " + height 
        + ", color=" + color);
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(this.color);
        g2.fillRect(this.x, this.y, this.width, this.height);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);
    }

    public void drawCircle(Graphics2D g2)
    {
        g2.setColor(new Color(230, 230, 230));
        g2.fillOval(x, y, width, height);
    }

    public void drawImage(Graphics2D g2)
    {
        g2.drawImage(img, x, y, width, height, null);
    }

    public static Color getRandomColor()
    {
        int r = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        int g = (int) (Math.random()*256);
        return new Color(r, g, b);
    }

    public void randomColor()
    {
        int r = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        int g = (int) (Math.random()*256);
        this.setColor(new Color(r, g, b));
    }

    public void updateKeyMovement()
    {
        if (up && y > YMin)
        y -= dy;
        if (down && y < YMax - (height + 2))
        y += dy;
        if (left && x > XMin)
        x -= dx;
        if (right && x < XMax - (width + 3))
        x += dx;
    }

    public void update()
    {
        x += dx;
        y += dy;
        if (y > YMax - height || y < YMin)
        dy = -dy;
        if (x > XMax - width || x < XMin)
        dx = -dx;
    }  

    public void updateGravity(int mousex, int mousey, int screenw, int screenh)
    {
        int cx = screenw / 2;
        int cy = screenh / 2;

        int chx = mousex - cx;
        int chy = mousey - cy;

        String f = asFraction(chy, chx);

        double gx = Double.parseDouble(f.substring(0, f.indexOf("/")));
        double gy = Double.parseDouble(f.substring(f.indexOf("/") + 1));

        System.out.println(gx + " " + gy);

        dx += gx/100;
        dy += gy/100;

        x += dx;
        y += dy;
        if (y > YMax - height || y < YMin)
        dy = -dy;
        if (x > XMax - width || x < XMin)
        dx = -dx;
    }  

       //Less efficient than using the key pressed method
   public void keyWasPressed(int key)
   {
      if (key == upKey)
         up = true;
      if (key == downKey)
         down = true;
      if (key == leftKey)
         left = true;
      if (key == rightKey)
         right = true;
   }

   public void keyWasReleased(int key)
   {
      if (key == upKey)
         up = false;
      if (key == downKey)
         down = false;
      if (key == leftKey)
         left = false;
      if (key == rightKey)
         right = false;
   }

   public boolean checkAndReactToCollisionWith(Brick r)
{
   int xm = x + width/2; //use the center of the moving brick as a reference
   int ym = y + height/2; //use the center of the moving brick as a reference
   
   int side = getSideForIntersection(r, xm, ym); //get the moving brick in relation to the other brick
   
   if(side == 0)      //Is the moving brick above the other brick?
      return checkCollisionTopOfRectangle(r);
   else if(side == 1) //Is the moving brick to the right of the other brick?
      return checkCollisionRightSideOfRectangle(r);
   else if(side == 2) //Is the moving brick below the other brick?
      return checkCollisionBottomOfRectangle(r);
   else if(side == 3) //Is the moving brick to the left of the other brick?
      return checkCollisionLeftSideOfRectangle(r);
   
   return false;
}

/**Returns the side where a collision would occur if possible
*    0 = top
*    1 = right
*    2 = bottom
*    3 = left
*/
private int getSideForIntersection(Brick r, int x1, int y1)
{
   double slopeMajor = (double) r.height / r.width;         //major diagonal slope
   double slopeMinor = (double) -r.height / r.width;        //minor diagonal slope
   double bMajor = r.y - slopeMajor * r.x;         //major diagonal y-intercept
   double bMinor = r.y - slopeMinor * (r.x + r.width); //minor diagonal y-intercept
   
   boolean aboveMajor = y1 < slopeMajor * x1 + bMajor; //Is the given point above the major diagonal
   boolean aboveMinor = y1 < slopeMinor * x1 + bMinor; //Is the given point above the minor diagonal
   
   if(aboveMajor  && aboveMinor)  return 0; //The point is above the other brick
   if(aboveMajor  && !aboveMinor) return 1; //The point is to the right of the other brick
   if(!aboveMajor && !aboveMinor) return 2; //The point is below the other brick
   if(!aboveMajor && aboveMinor)  return 3; //The point is to the left of the other brick
   
   return -1;   //Should never get here since "not above" is below OR ON a diagonal
}

private boolean checkCollisionLeftSideOfRectangle(Brick r)
{
   boolean collision = false;
   
   if(y + height > r.y && y < r.y + r.height) {
      if(x + width > r.x) {
        if (checkIfNotPlayer())
         dx = -dx;
         x = r.x - width;
         if(x <= XMin) {  //don't let the brick get bumped off the panel
            x = XMin;
            r.x = x + width;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionRightSideOfRectangle(Brick r)
{
   boolean collision = false;
   
   if(y + height > r.y && y < r.y + r.height) {
   if(x < r.x + r.width) {
        if (checkIfNotPlayer())
         dx = -dx;
         x = r.x + r.width;
         if(x + width >= XMax) {  //don't let the brick get bumped off the panel
            x = XMax - width;
            r.x = x - r.width;       //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionBottomOfRectangle(Brick r)
{
   boolean collision = false;
   
   if(x + width > r.x && x < r.x + r.width) {
      if(y < r.y + r.height) {
        if (checkIfNotPlayer())
         dy = -dy;
         y = r.y + r.height;
         if(y + height >= YMax) { //don't let the brick get bumped off the panel
            y = YMax - height;
            r.y = y - r.height;   //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionTopOfRectangle(Brick r)
{
   boolean collision = false;
   
   if(x + width > r.x && x < r.x + r.width) {
      if(y + height > r.y) {
        if (checkIfNotPlayer())
         dy = -dy;
         y = r.y - height;
         if(y <= YMin) {  //don't let the brick get bumped off the panel
            y = YMin;
            r.y = y + height;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}


//Linear RGB
// public void colorAdjust()
// {
//     if (x + y > 0 && x + y < 256)
//         color = new Color(x+y, 0, 0);
//     else if (x + y > 255 && x + y < 512)
//         color = new Color(255 - ((x + y) - 256), (x+y) - 256, 0);
//     else if (x + y > 511 && x + y < 767) 
//         color = new Color (0, 255 - ((x + y) - 512), (x + y) - 512);
//     else if (x + y > 766 && x + y < 1024)
//         color = new Color((x + y) - 766, 0, 255 - ((x + y) - 766));

// }

// public Color getChangedColor(int x1, int y1)
// {
//     if (x1 + y1 > 0 && x1 + y1 < 256)
//         return new Color(x1+y1, 0, 0);
//     else if (x1 + y1 > 255 && x1 + y1 < 512)
//         return new Color(255 - ((x1 + y1) - 256), (x1+y1) - 256, 0);
//     else if (x1 + y1 > 511 && x1 + y1 < 767) 
//         return new Color (0, 255 - ((x1 + y1) - 512), (x1 + y1) - 512);
//     else if (x1 + y1 > 766 && x1 + y1 < 1024)
//         return new Color((x1 + y1) - 766, 0, 255 - ((x1 + y1) - 766));
//     else 
//         return new Color(0, 0, 0);
// }

public void colorAdjust(int MAXW, int MAXH)
{
    color = Color.getHSBColor(((x + y)/ (float) (MAXW + MAXH)), 1f, 1f);
}

public Color getChangedColor(int x1, int y1, int MAXW, int MAXH)
{
    return Color.getHSBColor(((x1 + y1)/ (float) (MAXW + MAXH)), 1f, 1f);
}

public boolean checkIfNotPlayer()
{
    if (upKey > 0 || downKey > 0 || leftKey > 0 || rightKey > 0)
        return false;
    else
        return true;
}

public static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
}

public static String asFraction(long a, long b) {
    long gcd = gcd(a, b);
    return (a / gcd) + "/" + (b / gcd);
}

}
