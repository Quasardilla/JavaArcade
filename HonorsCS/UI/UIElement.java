package UI;

import java.awt.Graphics2D;

public abstract class UIElement {
    public double x;
    public double y;
    protected Graphics2D g2;
    protected boolean isShown = true;


    public void draw()
    {
        if (isShown)
        {
            drawElement();
        }
    }

    protected abstract void drawElement();

    public void setGraphics(Graphics2D g2)
    {
        this.g2 = g2;
    }

    public void show()
    {
        isShown = true;
    }

    public void hide()
    {
        isShown = false;
    }
}