package BrickClass;

import java.awt.Image;

import UI.SpriteSheet;

import java.awt.Color;

public class Alien extends Brick{

    int variant;
    boolean canShoot;
    boolean hasShot;

    public Alien(double x, double y, int width, int height)
    {
        super(x, y, width, height);
    }

    public Alien(double x, double y, int width, int height, Image img)
    {
        super(x, y, width, height, img);
    }

    public Alien(double x, double y, int width, int height, SpriteSheet ss)
    {
        super(x, y, width, height, ss);
    }

    public Alien(double x, double y, int width, int height, SpriteSheet ss, double dx, double dy)
    {
        super(x, y, width, height, ss, dx, dy);
    }

    public Alien(double x, double y, int width, int height, SpriteSheet ss, int dx, int dy)
    {
        super(x, y, width, height, ss, dx, dy);
    }

    public Alien(double x, double y, int width, int height, Color color) //Another constructor with optional values
    {
        super(x, y, width, height, color);
    }

    public Alien(double x, double y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public Alien(double x, double y, int width, int height, Color color, double dx, double dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public Alien(double x, double y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public Alien(double x, double y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
    }

    public Alien(double x, double y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, img);
    }

}
