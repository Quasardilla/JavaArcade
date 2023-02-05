package Games.DinoGame;

import java.awt.event.KeyEvent;

public class Dinosaur extends Sprite {
    
    private int jumpKey, altJumpKey, duckKey, groundLevel;
    private boolean isJumping, isDucking, isFalling;
    private double gravity, jumpSpeed;

    public Dinosaur() {
        super(30, 200, 0, 0, 50, 50);
        groundLevel = 200;

        jumpKey = KeyEvent.VK_UP;
        altJumpKey = KeyEvent.VK_SPACE;
        duckKey =  KeyEvent.VK_DOWN;
    }

    public Dinosaur(int jumpKey, int duckKey) {
        super(30, 200, 0, 0, 30, 50);
        groundLevel = 200;

        this.jumpKey = jumpKey;
        this.duckKey = duckKey;
    }

    public Dinosaur(int jumpKey, int altJumpKey, int duckKey) {
        super(30, 200, 0, 0, 30, 50);
        groundLevel = 200;

        this.jumpKey = jumpKey;
        this.altJumpKey = altJumpKey;
        this.duckKey = duckKey;
    }

    //METHODS

    @Override
    public void update() {
        if(y > groundLevel)
        {
            dy = 0;
            y = groundLevel;
            isFalling = false;
        }

        if(isJumping) {
            dy = -5;
            isJumping = false;
            isFalling = true;
        }
        else if(isFalling) {
            dy += 0.08;
        }

        if(isDucking) {
            h = 30;
            w = 50;
            y = groundLevel + (50 - h);
        }

        y += dy;
        x += dx;
    }

    public void keyWasPressed(int key) {
        if((key == jumpKey || key == altJumpKey) && !isFalling)
            isJumping = true;
        if(key == duckKey)
            isDucking = true;
    }

    public void keyWasReleased(int key) {
        // if(key == jumpKey || key == altJumpKey)
        //     isJumping = false;
        if(key == duckKey) {
            isDucking = false;
            h = 50;
            w = 30;
            y = groundLevel;
        }
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

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setJumpSpeed(double jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }
    

    @Override
    public String toString() {
        return super.toString() + " {" +
            " jumpKey='" + this.jumpKey + "'" +
            ", altJumpKey='" + this.altJumpKey + "'" +
            ", duckKey='" + this.duckKey + "'" +
            ", groundLevel='" + this.groundLevel + "'" +
            ", isJumping='" + this.isJumping + "'" +
            ", isDucking='" + this.isDucking + "'" +
            ", isFalling='" + this.isFalling + "'" +
            ", gravity='" + this.gravity + "'" +
            ", jumpSpeed='" + this.jumpSpeed + "'" +
            "}";
    }


}
