package PictureLab;

import java.awt.Color;

public class Pixel 
{
    //integer color values for red green and blue and alpha, 0-255
    private int red, green, blue, alpha;

    //default constructor
    public Pixel()
    {
        red = 0;
        green = 0;
        blue = 0;
        alpha = 0;
    }

    //constructor with parameters
    public Pixel(int r, int g, int b, int a)
    {
        red = r;
        green = g;
        blue = b;
        alpha = a;
    }

    //parameter constructor but without alpha
    public Pixel(int r, int g, int b)
    {
        red = r;
        green = g;
        blue = b;
        alpha = 255;
    }

    //getters and setters for all instance variables
    public int getRed()
    {
        return red;
    }

    public void setRed(int r)
    {
        red = r;
    }

    public int getGreen()
    {
        return green;
    }

    public void setGreen(int g)
    {
        green = g;
    }

    public int getBlue()
    {
        return blue;
    }

    public void setBlue(int b)
    {
        blue = b;
    }

    public int getAlpha()
    {
        return alpha;
    }

    public void setAlpha(int a)
    {
        alpha = a;
    }

    public Color getColor()
    {
        return new Color(red, green, blue);
    }

    public void setColor(int r, int g, int b)
    {
        red = r;
        green = g;
        blue = b;
    }

    public void setToGray()
    {
        int gray = (red + green + blue) / 3;
        red = gray;
        green = gray;
        blue = gray;
    }

    public void setNegative()
    {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }

    public double colorDistance(Color col)
    {
        int r = col.getRed();
        int g = col.getGreen();
        int b = col.getBlue();
        return Math.sqrt(Math.pow(r - red, 2) + Math.pow(g - green, 2) + Math.pow(b - blue, 2));
    }

    public String toString()
    {
        return "r: " + red + " g: " + green + " b: " + blue + " a: " + alpha;
    }

    public static Pixel averagePixels(Pixel pix, Pixel pix2)
    {
        return new Pixel((pix.red + pix2.red) / 2, (pix.green + pix2.green) / 2, (pix.blue + pix2.blue) / 2);
    }

    public static Pixel averagePixels(Pixel[] pixels)
    {
        int r = 0, g = 0, b = 0;
        for(Pixel pix : pixels)
            {
                r += pix.getRed();
                g += pix.getRed();
                b += pix.getRed();
            }

        return new Pixel(r / pixels.length, g / pixels.length, b / pixels.length);
    }
}
