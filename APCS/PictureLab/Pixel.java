package PictureLab;

import java.awt.Color;
import java.awt.Point;

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

    public void setColor(Color color)
    {
        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();
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

    public void averagePixels(Pixel pix)
    {
        setColor((pix.red + red) / 2, (pix.green + green) / 2, (pix.blue + blue) / 2);
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

    public static Pixel averageWeightedPixels(Pixel[][] pixels)
    {
        int r = 0, g = 0, b = 0;
        double[][] weightMap = getGaussianMap(pixels.length, pixels[0].length);
        double weightTotal = 0;

        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[0].length; j++)
            {
                if(pixels[i][j] == null)
                    continue;
                r += pixels[i][j].getRed() * weightMap[i][j];
                g += pixels[i][j].getGreen() * weightMap[i][j];
                b += pixels[i][j].getBlue() * weightMap[i][j];
                weightTotal += weightMap[i][j];
            }

        return new Pixel((int) (r / weightTotal), (int) (g / weightTotal), (int) (b / weightTotal));
    }

    //Helper Functions

    public static double[][] getGaussianMap(int length, int width)
    {
        double[][] temp = new double[length][width];
        double a = 2.7182818284590452353602874713527;
        Point center = new Point(length / 2, width / 2);

        for(int i = 0; i < length; i++)
            for(int j = 0; j < width; j++)
            {
                double dist = Math.pow(center.x - j, 2) + Math.pow(center.y - i, 2);
                temp[i][j] = Math.pow(a, -(Math.pow(dist, 2) / (Math.max(length, width))));
            }

        return temp;
    }
}
