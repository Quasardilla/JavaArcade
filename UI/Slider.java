package UI;

import java.awt.BasicStroke;
import java.awt.Color;

public class Slider {
int x;
int y;
int width;
int height;
BasicStroke lineThickness;
int segmentX;
int segmentY;
int segmentWidth;
int segmentHeight;
Color segmentColor;
    
    public Slider(int x, int y, int width, int height, BasicStroke lineThickness, int segmentX, int segmentY, int segmentWidth, int segmentHeight, Color segmentColor)
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
    }

    
}