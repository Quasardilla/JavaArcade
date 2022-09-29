package UNIVERSAL.ENTITY;

import java.awt.Image;
import java.awt.Point;

public class Enemy extends Entity{

    protected double originX;
    protected double originY;
    protected boolean isBoss;
    protected double speed;
    
    public Enemy(int originX, int originY)
    {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this.img = null;
    }

    public Enemy(int x, int y, String name, Image img, double speed)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.img = img;
        this.originX = x;
        this.originY = y;
        this.speed = speed;
    }

    public void moveToward(Point p, int cameraOffsetX, int cameraOffsetY)
    {
        //not working
        originX += ((p.getX()+cameraOffsetX) - originX) * speed;
        originY += ((p.getY()+cameraOffsetY) - originY) * speed;
        moveByCamera(cameraOffsetX, cameraOffsetY);
        // System.out.println("originx: "+originX+" originy: "+originY);
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


    public double getOriginX() {
        return this.originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return this.originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }


}
