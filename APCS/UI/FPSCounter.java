package UI;

/**
 * Simple FPS class
 */

public class FPSCounter {
    
    private double totalFrames = 0;
    private double lastFPSCheck = 0;
    private double currentFPS = 0;

        /** Run this every frame
        *  Had no idea what to call it
        */

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

    public double getFPS()
    {
        return currentFPS;
    }
}
