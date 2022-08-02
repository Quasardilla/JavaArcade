package BrickClass;
import java.awt.Color;
import java.awt.Image;

import Pong.pongGame;

public class GameObject extends Brick{
   public int combo;
   public int hit;


    public GameObject(double x, double y, int width, int height)
    {
        super(x, y, width, height);
    }

    public GameObject(double x, double y, int width, int height, Image img)
    {
        super(x, y, width, height, img);
    }

    public GameObject(double x, double y, int width, int height, Color color) //Another constructor with optional values
    {
        super(x, y, width, height, color);
    }

    public GameObject(double x, double y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public GameObject(double x, double y, int width, int height, Color color, double dx, double dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public GameObject(double x, double y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public GameObject(double x, double y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public GameObject(double x, double y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, img);
    }

public void update()
{
   x += dx;
   y += dy;
}

public boolean checkIfNotPlayer()
{
    if (upKey > 0 || downKey > 0 || leftKey > 0 || rightKey > 0)
        return false;
    else
        return true;
}

public void returnToCenter()
{
   x = ((XMax / 2) - width);
   y = (int)(Math.random() * ((pongGame.PREF_H-50)-50+1)) + 50;
}

public void pointTowardsWinner()
{
   dx = -dx;
   dy = -dy;
}

}
