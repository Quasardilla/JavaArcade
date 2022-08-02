package UI;

import java.util.ArrayList;
import java.awt.Point;

public class Trail {

    protected int interval, amount, counter = 0;
    protected boolean opacityFalloff = false;
    protected ArrayList<Point> points = new ArrayList<Point>();
    protected ArrayList<Integer> opactiy = new ArrayList<Integer>();

    public Trail(int interval, int amount)
    {
        this.interval = interval;
        this.amount = amount;
    }

    public Trail(int interval, int amount, boolean opacityFalloff)
    {
        this.opacityFalloff = opacityFalloff;
        this.interval = interval;
        this.amount = amount;
    }

    public void trailCounter(int x, int y)
    {
        if(counter >= interval)
        {
            counter = 0;
            points.add(new Point(x, y));

            if(points.size() > amount)
            {
                points.remove(0);
                opactiy.remove(0);
            }

            if(opacityFalloff)
            {
            opactiy.clear();

            for(int i = 0; i < amount; i++)
                opactiy.add((int) (i * (255 / amount)));
            }
            else
                opactiy.add(255);
        }
        else
            counter++;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Integer> getOpactiy() {
        return opactiy;
    }

    public void setOpactiy(ArrayList<Integer> opactiy) {
        this.opactiy = opactiy;
    }

    public boolean isOpacityFalloff() {
        return opacityFalloff;
    }

    public void setOpacityFalloff(boolean opacityFalloff) {
        this.opacityFalloff = opacityFalloff;
    }

    public void clear()
    {
        points.clear();
        opactiy.clear();
    }
}
