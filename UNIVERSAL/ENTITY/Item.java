package UNIVERSAL.ENTITY;

import java.awt.Image;

public class Item extends Entity{

    protected int originX;
    protected int originY;
    
    public Item(int originX, int originY)
    {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this.img = null;
    }

    public Item(int x, int y, String name, Image img, int originX, int originY)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.img = img;
    }

    @Override
    public void drawElement()
    {
        g2.drawImage(img, (int) x, (int) y, null);
    }


    public int getOriginX() {
        return this.originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return this.originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }


}
