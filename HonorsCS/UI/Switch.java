package UI;

import java.awt.Color;

public class Switch extends UIElement{

    int x, y, width, height, gap;
    boolean state;
    Color bgClr, swchClr, swchClrOn;

    public Switch(int x, int y, int width, int height, int gap, Color bgClr, Color swchClr, Color swchClrOn)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gap = gap;
        this.bgClr = bgClr;
        this.swchClr = swchClr;
        this.swchClrOn = swchClrOn;
    }

    public boolean getState()
    {
        return state;
    }

    @Override
    void drawElement() {
        g2.setColor(bgClr);
        g2.fillRect(x, y, width, height);

        if (state)
        {
            g2.setColor(swchClrOn);
            g2.fillRect(x + gap + (width / 2), y + gap, (width / 2) - (gap * 2), height - (gap * 2));
        }
        else
        {
            g2.setColor(swchClr);
            g2.fillRect(x + gap, y + gap, (width / 2) - (gap * 2), height - (gap * 2));
        }
    } 

    public void toggleState()
    {
        state = !state;
    }

    public boolean isInside(int x1, int y1)
    {
        int x2 = this.x + this.width;
        int y2 = this.y + this.height;

        if(x1 > this.x && y1 > this.y && x1 < x2 && y1 < y2)
            return true;
        else
            return false;
    }

}
