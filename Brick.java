import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import javax.swing.GroupLayout;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    private int x, y ,width, height;
    private int dx, dy;
    private int XMin, XMax, YMin, YMax;
    private Color color;
    
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

    public int getDx()
    {
        return this.dx;
    }
    
    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public int getDy()
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

    public void randomColor()
    {
        int r = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        int g = (int) (Math.random()*256);
        this.setColor(new Color(r, g, b));
    }

    public void update()
    {
        x += dx;
        y += dy;
        if (x > XMax - width || x < 0)
        dx = -dx;
        if (y > YMax - height || y < 0)
        dy = -dy;
    }
}
