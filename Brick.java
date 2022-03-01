import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import javax.swing.GroupLayout;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    private int x, y ,width, height;
    private double dx, dy;
    private int XMin, XMax, YMin, YMax;
    private Color color;
    private boolean left, right, up, down;
    private int leftKey, rightKey, upKey, downKey;
    
    public Brick(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
    }

    public Brick(int x, int y, int width, int height, Color color) //Another constructor with optional values
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Brick(int x, int y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        this.x = x;
        this.y = x;
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
        this.y = x;
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

    public double getDy()
    {
        return this.dy;
    }

    public void setDy(int dy)
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


    public String toString()
    {
        return ("x=" + x + ", y=" + y + ", w=" + width + ", h= " + height 
        + ", color=" + color);
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(this.color);
        g2.fillRect(this.x, this.y, this.width, this.height);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(color.BLACK);
        g2.drawRect(x, y, width, height);
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
            if (x > XMax - width || x < XMin)
            dx = -dx;
            if (y > YMax - height || y < YMin)
            dy = -dy;
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
}
