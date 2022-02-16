import java.awt.Color;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    private int x, y ,width, height, dx, dy;
    private Color color;
    
    public Brick(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
    }

    public Brick(int x, int y, int width, int height, int dx, int dy, Color color) //Another constructor with optional values
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
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
        + ", dx=" + dx + ", dy=" + dy + ", color=" + color);
    }
}
