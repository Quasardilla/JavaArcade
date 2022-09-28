package UNIVERSAL.UI;

import java.awt.Color;

/**
 * Simple FPS class
 */

public class FPSCounter extends UIElement{
    
    private double totalFrames = 0;
    private double lastFPSCheck = 0;
    private double currentFPS = 0;
    protected int FPSLimit = 60;

        /*
        * Run this every frame
        *  Had no idea what to call it
        */

    public FPSCounter(){}

    public FPSCounter(int FPSLimit)
    {
        this.FPSLimit = FPSLimit;
    }

    public void frame()
    {
        totalFrames++;
		if (System.nanoTime() > lastFPSCheck + 1000000000)
		{
			lastFPSCheck = System.nanoTime();
			currentFPS = totalFrames;
			totalFrames = 0;
		}
    }

    /*
     * Runs a thread.sleep to ensure current
     * thread meets FPS limit
     */
    public void FPSLimitPause()
    {
        long millis = System.currentTimeMillis();
        try
        {
        Thread.sleep((long) ((1000/FPSLimit) - millis % (1000/FPSLimit)));
        } catch (InterruptedException e) {System.out.println(e);}
    }

    public double getFPS()
    {
        return currentFPS;
    }

    public void setFPSLimit(int FPSLimit)
    {
        this.FPSLimit = FPSLimit;
    }

    public int getFPSLimit()
    {
        return FPSLimit;
    }

    @Override
    public void drawElement() 
    {
        g2.setColor(Color.WHITE);
        g2.fillRect(90, 80, 100, 27);
        g2.setColor(Color.BLACK);
        g2.drawString("FPS: "+currentFPS, 100, 100);
    }


}
