package Intro.interfaces;

import java.util.ArrayList;

public class TrafficManager {

    public static Driveable Nova = new NovaAutoGroup();

    public static void main(String[] args) {

        // Nova.accelerate(10);
        NovaAutoGroup n = (NovaAutoGroup) Nova;
        n.accelerate(0);

        ((NovaAutoGroup) Nova).setStarPower(40000);

        ArrayList<Driveable> traffic = new ArrayList<Driveable>();
        traffic.add(Nova);
        traffic.add(new NovaAutoGroup());
        Nova.stop();
    }
}
