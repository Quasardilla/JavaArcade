package BrickClass;

public class Projectile extends GameObject {

    public Projectile(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    public Projectile(int x, int y, int width, int height, Color color) //Another constructor with optional values
    {
        super(x, y, width, height, color);
    }

    public Projectile(int x, int y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public Projectile(int x, int y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public Projectile(int x, int y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public Projectile(int x, int y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, img);
    }
    
    
}
