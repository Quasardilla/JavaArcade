package Intro.interfaces;

import java.util.ArrayList;

public class TrafficManager {
    public static void main(String[] args) {

        ArrayList<Driveable> traffic = new ArrayList<Driveable>();
        traffic.add(new Zoomeez());
        traffic.add(new NovaAutoGroup());
        traffic.add(new ChuckWagon());
        traffic.add(new ChuckWagon());
        traffic.add(new Zoomeez());
        traffic.add(new NovaAutoGroup());
        traffic.add(new Zoomeez());
        traffic.add(new NovaAutoGroup());
        traffic.add(new ChuckWagon());
        traffic.add(new ChuckWagon());
        traffic.add(new Zoomeez());
        traffic.add(new NovaAutoGroup());

        for(Driveable i : traffic)
            i.accelerate(10);
        for(Driveable i : traffic)
            i.stop();
    }
}
