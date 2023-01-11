package Intro.interfaces;

public class ChuckWagon implements Driveable {

    private int horses;
    private double charge;

    public void setHorses(int horses)
    {
        this.horses = horses;
    }

    public int getHorses()
    {
        return horses;
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
        System.out.println("Giddyup");
    }

    @Override
    public void stop() {
        System.out.println("Neigh");
    }
    
}
