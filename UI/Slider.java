package UI;

import java.awt.BasicStroke;
import java.awt.Color;

public class Slider extends UIElement {
protected int width, segmentWidth, segmentHeight, segmentX, snapAmount, value, mouseDist, snapInterval;
protected BasicStroke lineThickness;
protected Color segmentColor, lineColor;

    public Slider(double x, double y, int width, BasicStroke lineThickness, int segmentWidth, int segmentHeight, int segmentY, Color segmentColor, Color linecColor, int snapAmount)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.lineThickness = lineThickness;
        this.segmentWidth = segmentWidth;
        this.segmentHeight = segmentHeight;
        this.segmentX = (int) x;
        this.segmentColor = segmentColor;
        this.segmentColor = lineColor;
        this.snapAmount = snapAmount;
        this.snapInterval = width / snapAmount;
    }


    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BasicStroke getLineThickness() {
        return this.lineThickness;
    }

    public void setLineThickness(BasicStroke lineThickness) {
        this.lineThickness = lineThickness;
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

    public void setValue(int value) {
        this.value = value;
    }

    public int getMouseDist() {
        return this.mouseDist;
    }

    public void setMouseDist(int mouseDist) {
        this.mouseDist = mouseDist;
    }

    @Override
    void drawElement()
    {
        g2.setColor(Color.black);
        g2.drawLine((int) x, (int) y + segmentHeight / 2, (int) x + width, (int) y + segmentHeight / 2);
        g2.fillRect((int) (x + (value * snapInterval)), (int) y, segmentWidth, segmentHeight);
    }

    public void drag(int mouseX)
    {
        if(mouseX - mouseDist > x && mouseX - mouseDist < x + width)
        this.segmentX = mouseX - mouseDist;
        
        value = segmentX / snapInterval;

    }
}