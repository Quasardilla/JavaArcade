package Games.DinoGame;

import java.awt.event.KeyEvent;

import javax.crypto.spec.DHPublicKeySpec;

public class Dinosaur extends Sprite {
    
    private int jumpKey, altJumpKey, duckKey;
    private boolean isJumping, isDucking, isFalling;
    private double gravity, jumpSpeed;

    public Dinosaur() {
        super(30, 100, 0, 0, 50, 50);

        jumpKey = KeyEvent.VK_UP;
        altJumpKey = KeyEvent.VK_SPACE;
        duckKey =  KeyEvent.VK_DOWN;
    }

    public Dinosaur(int jumpKey, int duckKey) {
        super(30, 100, 0, 0, 50, 50);

        this.jumpKey = jumpKey;
        this.duckKey = duckKey;
    }

    public Dinosaur(int jumpKey, int altJumpKey, int duckKey) {
        super(30, 100, 0, 0, 50, 50);

        this.jumpKey = jumpKey;
        this.altJumpKey = altJumpKey;
        this.duckKey = duckKey;
    }

    //METHODS

    @Override
    public void update() {
        x++;
    }

    public void keyWasPressed(int key) {
        if(key == jumpKey || key == altJumpKey)
            isJumping = true;
        if(key == duckKey)
            isDucking = true;
    }

    public void keyWasReleased(int key) {
        if(key == jumpKey || key == altJumpKey)
            System.out.println("STOP JUMP!!!!!!!!!");
        if(key == duckKey)
            isDucking = false;
    }

    public void setJumpKey(int jumpKey) {
        this.jumpKey = jumpKey;
    }

    public void setAltJumpKey(int altJumpKey) {
        this.altJumpKey = altJumpKey;
    }

    public void setDuckKey(int duckKey) {
        this.duckKey = duckKey;
    }
    

}
