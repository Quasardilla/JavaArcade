import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.GroupLayout;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    private int x, y ,width, height;
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


    public String toString()
    {
        return ("x=" + x + ", y=" + y + ", w=" + width + ", h= " + height 
        + ", color=" + color);
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(this.color);
        g2.fillRect(this.x, this.y, this.width, this.height);
    }
}
