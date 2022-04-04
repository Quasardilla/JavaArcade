package UI;

import java.awt.Color;
import java.awt.Font;

public class Button extends UIElement{
    public double width;
    public double height;
    public double xOffset;
    public double yOffset;
    public double fontSize;
    public Font font;
    public String text;
    public Color textColor;
    public Color backColor;

    public Button(double x, double y, double width, double height, double xOffset, double yOffset, double fontSize, Font font, String text, Color textColor, Color backColor)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.fontSize = fontSize;
        this.font = font;
        this.font = new Font(this.font.getFontName(), Font.PLAIN, (int) this.fontSize);
        this.text = text;
        this.textColor = textColor;
        this.backColor = backColor;
    }

    @Override
    void drawElement() 
    {
        //draw background
        g2.setColor(backColor);
        g2.fillRect((int) x, (int) y, (int) width, (int) height); 

        //draw text
        g2.setFont(font);
        this.font = new Font(this.font.getFontName(), Font.PLAIN, (int) this.fontSize);
        g2.setColor(textColor);
        g2.drawString(text, (int) (x + xOffset), (int) (y + yOffset));
    }

    public boolean mouseClick(double mousex, double mousey)
    {
        if (mousex > x && mousex < mousex + width && mousey > y && mousey < y + height) return true;
        else return false;
    }

}
