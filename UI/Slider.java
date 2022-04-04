package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Slider extends UIElement {
    int width;
    int height;
    BasicStroke lineThickness;
    int segmentX;
    int segmentY;
    int segmentWidth;
    int segmentHeight;
    Color segmentColor;
    
    public Slider(int x, int y, int width, int height, BasicStroke lineThickness, int segmentX, int segmentY, int segmentWidth, int segmentHeight, Color segmentColor, Graphics2D g2)
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
        this.lineThickness = lineThickness;
        this.segmentX = segmentX;
        this.segmentY = segmentY;
        this.segmentWidth = segmentWidth;
        this.segmentHeight = segmentHeight;
        this.segmentColor = segmentColor;
        this.g2 = g2;
    }

    @Override
    void drawElement() 
    {
        //put draw method here
    }

    
}