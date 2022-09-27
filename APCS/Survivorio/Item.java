package Survivorio;

import java.awt.Image;

public class Item extends Entity{

    int originX;
    int originY;
    
    Item(int originX, int originY)
    {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this.img = null;
    }

    Item(int x, int y, String name, Image img, int originX, int originY)
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
