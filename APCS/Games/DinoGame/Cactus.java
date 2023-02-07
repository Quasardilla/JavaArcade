package Games.DinoGame;

public class Cactus extends Sprite {
    
    Cactus() {
        super(800, 200, -2, 0, 50, 50);
    }

    Cactus(int x) {
        super(x, 200, -2, 0, 50, 50);
    }

    @Override
    public void update() {
        x += dx;

        if(x < -50)
            x = 800;
    }
}
