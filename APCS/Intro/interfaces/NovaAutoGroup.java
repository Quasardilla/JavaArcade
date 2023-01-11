package Intro.interfaces;

public class NovaAutoGroup implements Driveable{

    private int power;
    private double charge;

    //"Nova Auto Group: The driving force of the universe."


    public void setStarPower(int power)
    {
        this.power = power;
    }

    public int getStarPower()
    {
        return power;
    }

    @Override
    public void accelerate(int rate) {
        System.out.println("VROOOOOOOOM");
        
    }

    @Override
    public void stop() {
        System.out.println("SKRRRRRRRT");        
    }

    @Override
    public void setCharge(double charge) {
        this.charge = charge;        
    }

    @Override
    public double getCharge() {
        return charge;
    }
    
}
