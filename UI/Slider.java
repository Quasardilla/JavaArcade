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
int snapAmount;
int value;

    public Slider(int x, int y, int width, int height, BasicStroke lineThickness, int segmentX, int segmentY, int segmentWidth, int segmentHeight, Color segmentColor, int snapAmount)
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
        this.snapAmount = snapAmount;
    }


    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BasicStroke getLineThickness() {
        return this.lineThickness;
    }

    public void setLineThickness(BasicStroke lineThickness) {
        this.lineThickness = lineThickness;
    }

    public int getSegmentX() {
        return this.segmentX;
    }

    public void setSegmentX(int segmentX) {
        this.segmentX = segmentX;
    }

    public int getSegmentY() {
        return this.segmentY;
    }

    public void setSegmentY(int segmentY) {
        this.segmentY = segmentY;
    }

    public int getSegmentWidth() {
        return this.segmentWidth;
    }

    public void setSegmentWidth(int segmentWidth) {
        this.segmentWidth = segmentWidth;
    }

    public int getSegmentHeight() {
        return this.segmentHeight;
    }

    public void setSegmentHeight(int segmentHeight) {
        this.segmentHeight = segmentHeight;
    }

    public Color getSegmentColor() {
        return this.segmentColor;
    }

    public void setSegmentColor(Color segmentColor) {
        this.segmentColor = segmentColor;
    }

    public int getSnapAmount() {
        return this.snapAmount;
    }

    public void setSnapAmount(int snapAmount) {
        this.snapAmount = snapAmount;
    }

    public int getValue() {
        return this.value;
    }

}