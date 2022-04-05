package UI;

import java.awt.Color;
import java.awt.Font;

public class Bar extends UIElement {
    public double backWidth;
    public double backHeight;
    public double foreWidth;
    public double foreHeight;
    public double foreXOffset;
    public double foreYOffset;
    public double textXOffset;
    public double textYOffset;
    public double fontSize;
    public double value;
    public double maxValue;
    public double minValue;
    public String text;
    public Color backColor;
    public Color foreEndColor;
    public Color foreStartColor;
    public Color textColor;
    public Font textFont;

    public Bar(double x, double y, double backWidth, double backHeight, double foreWidth, double foreHeight, double foreXOffset, double foreYOffset, double textXOffset, double textYOffset, double fontSize, double value, double maxValue, double minValue, String text, Color backColor, Color foreEndColor, Color forestartcColor, Color textColor, Font textFont)
    {
        this.x = x;
        this.y = y;
        this.backWidth = backWidth;
        this.backHeight = backHeight;
        this.foreWidth = foreWidth;
        this.foreHeight = foreHeight;
        this.foreXOffset = foreXOffset;
        this.foreYOffset = foreYOffset;
        this.textXOffset = textXOffset;
        this.textYOffset = textYOffset;
        this.fontSize = fontSize;
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.text = text;
        this.backColor = backColor;
        this.foreEndColor = foreEndColor;
        this.foreStartColor = forestartcColor;
        this.textColor = textColor;
        this.textFont = textFont;
        this.textFont = new Font(this.textFont.getFontName(), Font.PLAIN, (int) this.fontSize);
    }


    @Override
    public void drawElement() 
    {
        //fix value
        if (value > maxValue) value = maxValue;
        if (value < minValue) value = minValue;

        //draw background
        g2.setColor(backColor);
        g2.fillRect((int) x, (int) y, (int) backWidth, (int) backHeight);

        //set correct foreground color
        float[] endHSBvals = new float[3];
        endHSBvals = Color.RGBtoHSB(foreEndColor.getRed(), foreEndColor.getGreen(), foreEndColor.getBlue(), endHSBvals);
        float[] startHSBvals = new float[3];
        startHSBvals = Color.RGBtoHSB(foreStartColor.getRed(), foreStartColor.getGreen(), foreStartColor.getBlue(), startHSBvals);
        g2.setColor(new Color(Color.HSBtoRGB((startHSBvals[0] - endHSBvals[0]) * (float) (value / (maxValue - minValue)), (endHSBvals[1] + startHSBvals[1])/2, (endHSBvals[2] + startHSBvals[2])/2)));
        
        //draw foreground
        g2.fillRect((int) (x + foreXOffset), (int) (y + foreYOffset), (int) (foreWidth * (value / (maxValue - minValue))), (int) foreHeight);
    
        //draw text
        this.textFont = new Font(this.textFont.getFontName(), Font.PLAIN, (int) this.fontSize);
        g2.setColor(textColor);
        g2.setFont(textFont);
        g2.drawString(text, (int) (x + textXOffset), (int) (y + textYOffset));

    }

    public void setValAsFrac(double val)
    {
        value = minValue + ((maxValue - minValue) * val);
    }
    
}
