package Intro.interfaces;

public class Zoomeez implements Driveable{

    private double scale;
    private double charge;

    public void setScale(double scale)
    {
        this.scale = scale;
    }

    public double getScale()
    {
        return scale;
    }

    @Override
    public void setCharge(double charge) {
        this.charge = charge;        
    }

    @Override
    public double getCharge() {
        return charge;
    }

    @Override
    public void accelerate(int rate) {
        System.out.println("Zoom");
        
    }

    @Override
    public void stop() {
        System.out.println("eez");
    }
    
}
