package UNIVERSAL.UI;

import java.awt.BasicStroke;
import java.awt.Color;

public class Slider extends UIElement {
protected int x, y, width, segmentWidth, segmentHeight, segmentX, snapAmount, value, mouseDist, snapInterval;
protected BasicStroke lineThickness;
protected Color segmentColor, lineColor;
protected boolean line;

    public Slider(int x, int y, int width,
    BasicStroke lineThickness, Boolean line, Color lineColor, int snapAmount,
    int segmentWidth, int segmentHeight, Color segmentColor)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.lineThickness = lineThickness;
        this.line = line;
        this.lineColor = lineColor;
        this.segmentWidth = segmentWidth;
        this.segmentHeight = segmentHeight;
        this.segmentX = (int) x;
        this.segmentColor = segmentColor;
        this.segmentColor = lineColor;
        this.snapAmount = snapAmount;
        this.snapInterval = width / snapAmount;
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

    public int getSegmentX() {
        return this.segmentX;
    }

    public void setSegmentX(int segmentX) {
        this.segmentX = segmentX;
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

    public int getMouseDist() {
        return this.mouseDist;
    }

    public void setMouseDist(int mouseDist) {
        this.mouseDist = mouseDist;
    }

    public int getSnapInterval() {
        return this.snapInterval;
    }

    public void setSnapInterval(int snapInterval) {
        this.snapInterval = snapInterval;
    }

    public BasicStroke getLineThickness() {
        return this.lineThickness;
    }

    public void setLineThickness(BasicStroke lineThickness) {
        this.lineThickness = lineThickness;
    }

    public Color getSegmentColor() {
        return this.segmentColor;
    }

    public void setSegmentColor(Color segmentColor) {
        this.segmentColor = segmentColor;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public boolean isLine() {
        return this.line;
    }

    public boolean getLine() {
        return this.line;
    }

    public void setLine(boolean line) {
        this.line = line;
    }

    

    @Override
    public void drawElement()
    {
        // if(line)
        // {
        g2.setColor(lineColor);
        g2.setStroke(lineThickness);
        g2.drawLine((int) x, (int) y + segmentHeight / 2, (int) x + width, (int) y + segmentHeight / 2);
        // }
        g2.setColor(segmentColor);
        g2.fillRect((int) x + ((value * snapInterval)), (int) y, segmentWidth, segmentHeight);
    }

    public void drag(int mouseX)
    {
        if(mouseX - mouseDist > x && mouseX - mouseDist < x + width)
        this.segmentX = mouseX - mouseDist;
        
        value = (int) ((segmentX - x) / snapInterval);

    }

    public boolean isInside(int mouseX, int mouseY)
    {
        int x2 = this.segmentX + this.segmentWidth;
        int y2 = this.y + this.segmentHeight;

        if(mouseX > this.segmentX && mouseY > this.y && mouseX < x2 && mouseY < y2)
            return true;

        return false;
    }
}