package UNIVERSAL.ENTITY;

import java.awt.Image;

public class Item extends Entity{

    protected int originX;
    protected int originY;
    protected double speed;

    public Item(int originX, int originY)
    {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this.img = null;
    }

    public Item(int x, int y, String name, Image img, double speed)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.img = img;
        this.originX = x;
        this.originY = y;
        this.speed = speed;
    }

    public void moveByCamera(int cameraOffsetX, int cameraOffsetY)
    {
        if (cameraOffsetX != 0)
            x = originX - cameraOffsetX;
        if (cameraOffsetY != 0)
            y = originY - cameraOffsetY;
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

    public void moveToward(int x, int y, int cameraOffsetX, int cameraOffsetY)
    {
        int directionX;
        int directionY;

        if (this.x - x > 0)
            directionX = 1;
        else if (this.x - x < 0)
            directionX = -1;
        else
            directionX = 0;

        if (this.y - y > 0)
            directionY = 1;
        else if (this.y - y < 0)
            directionY = -1;
        else
            directionY = 0;

        this.originX -= 1 * directionX;
        this.originY -= 1 * directionY;

        moveByCamera(cameraOffsetX, cameraOffsetY);

        
    }

}
