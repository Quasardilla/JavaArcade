package UNIVERSAL.ENTITY;

import java.awt.Image;

public class Player extends Entity{

    public Player(){}

    public Player(int health, int damage, double speed, String name, Image img)
    {
        super(health, damage, speed, name, img);
    }

    @Override
    public void drawElement()
    {
        g2.drawImage(img, (int) x, (int) y, null);
    }

}
