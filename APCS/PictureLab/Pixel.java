package PictureLab;

import java.awt.Color;
import java.awt.Point;
import java.util.Objects;

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

    //parameter constructor but with color
    public Pixel(Color clr)
    {
        this.setColor(clr);
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
        try {
            return new Color(red, green, blue);
            
        } catch (Exception e) {
            System.out.println("r: " + red + " g: " + green + " b: " + blue);
            return new Color(0, 0, 0);
        }
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

    public void setPixel(Pixel pix)
    {
        this.red = pix.red;
        this.green = pix.green;
        this.blue = pix.blue;
        this.alpha = pix.alpha;
    }

    public void setToGray()
    {
        //int gray = (red + green + blue) / 3;
        //changed to use weights because its supposedly better 
        //https://en.wikipedia.org/wiki/Grayscale
        int gray = (int)(0.2126 * red + 0.7152 * green + 0.0722 * blue);
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

    public void setEven()
    {
        red = red - red % 2;
        green = green - green % 2;
        blue = blue - blue % 2;
    }

    /**
     * 
     * @param clr
     *          Which color to set to even
     */
    public void setEven(Color clr)
    {
        if (clr == Color.RED)
            red = red - red % 2;
        else if (clr == Color.GREEN)
            green = green - green % 2;
        else if (clr == Color.BLUE)
            blue = blue - blue % 2;
        else
            throw new IllegalArgumentException("Color must be red, green, or blue");
    }

    public void setOdd()
    {
        red = red - red % 2 + 1;
        green = green - green % 2 + 1;
        blue = blue - blue % 2 + 1;
    }

    /**
     * 
     * @param clr
     *          Which color to set to odd
     */
    public void setOdd(Color clr)
    {
        if(clr == Color.RED)
            red = red - red % 2 + 1;
        else if(clr == Color.GREEN)
            green = green - green % 2 + 1;
        else if(clr == Color.BLUE)
            blue = blue - blue % 2 + 1;
        else
            throw new IllegalArgumentException("Color must be red, green, or blue");
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

    public static Pixel averagePixels(Pixel[][] pixels)
    {
        int r = 0, g = 0, b = 0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[0].length; j++)
            {
                r += pixels[i][j].getRed();
                g += pixels[i][j].getGreen();
                b += pixels[i][j].getBlue();
            }

        return new Pixel(r / (pixels.length * pixels[0].length), g / (pixels.length * pixels[0].length), b / (pixels.length * pixels[0].length));
    }

    public static Pixel averagePixels(Pixel[][] pixels, int size)
    {
        int r = 0, g = 0, b = 0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[0].length; j++)
            {
                if (pixels[i][j] != null)
                {
                    r += pixels[i][j].getRed();
                    g += pixels[i][j].getGreen();
                    b += pixels[i][j].getBlue();
                }
            }

        return new Pixel(r / size, g / size, b / size);
    }

    public static Pixel averageWeightedPixels(Pixel[][] pixels, Pixel center, double standard_deviation, double[][] weightMap)
    {
        int r = 0, g = 0, b = 0;
        double weightTotal = 0;

        int skipped = 0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[0].length; j++)
            {
                if(pixels[i][j] == null)
                {
                    skipped++;
                    continue;
                }
                // System.out.println("Weight: " + weightMap[i][j]);
                r += (center.getRed() - pixels[i][j].getRed()) * weightMap[i][j]+pixels[i][j].getRed();
                g += (center.getGreen() - pixels[i][j].getGreen()) * weightMap[i][j]+pixels[i][j].getGreen();
                b += (center.getBlue() - pixels[i][j].getBlue()) * weightMap[i][j]+pixels[i][j].getBlue();
                // weightTotal += weightMap[i][j];
            }

        weightTotal = pixels.length * pixels[0].length - skipped;
        return new Pixel((int) (r / weightTotal), (int) (g / weightTotal), (int) (b / weightTotal));
        // return new Pixel(r, g, b);
    }

    //Helper Functions

    public static double[][] getGaussianMap(int length, int width, double standard_deviation)
    {
        double[][] temp = new double[length][width];
        Point center = new Point(width / 2, length / 2);

        for(int i = 0; i < length; i++)
            for(int j = 0; j < width; j++)
            {
                double dist = Math.sqrt(Math.pow(center.x - j, 2) + Math.pow(center.y - i, 2));
                temp[i][j] = standard_deviation * Math.pow(Math.E, -(Math.pow(dist, 2) / Math.pow((Math.max(length, width)/2), 2)));
            }

        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pixel)) {
            return false;
        }
        Pixel pixel = (Pixel) o;
        return red == pixel.red && green == pixel.green && blue == pixel.blue && alpha == pixel.alpha;
    }

    public boolean equals(Pixel p, int tolerance)
    {
        return p.colorDistance(this.getColor()) < tolerance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, alpha);
    }

}
