package Survivorio;

public class Enemy extends Entity{

    protected boolean isBoss;

    public Enemy()
    {

    }

    @Override
    public void drawElement()
    {
        g2.drawImage(img, (int) x, (int) y, null);
    }

}