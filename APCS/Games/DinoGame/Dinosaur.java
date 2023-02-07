package Games.DinoGame;

import java.awt.event.KeyEvent;

public class Dinosaur extends Sprite {
    
    private int jumpKey, altJumpKey, duckKey, groundLevel;
    private boolean isJumping, isDucking, isFalling;
    private double gravity, jumpSpeed;

    public Dinosaur() {
        super(30, 200, 0, 0, 50, 50);
        groundLevel = (int) (super.y + super.h);
        
        jumpSpeed = 2.5;
        gravity = 0.025;

        jumpKey = KeyEvent.VK_UP;
        altJumpKey = KeyEvent.VK_SPACE;
        duckKey =  KeyEvent.VK_DOWN;
    }

    public Dinosaur(int jumpKey, int duckKey) {
        super(30, 200, 0, 0, 30, 50);
        groundLevel = (int) (super.y + super.h);

        jumpSpeed = 2.5;
        gravity = 0.025;
        
        this.jumpKey = jumpKey;
        this.duckKey = duckKey;
    }
    
    public Dinosaur(int jumpKey, int altJumpKey, int duckKey) {
        super(30, 200, 0, 0, 30, 50);
        groundLevel = (int) (super.y + super.h);

        jumpSpeed = 2.5;
        gravity = 0.025;
        
        this.jumpKey = jumpKey;
        this.altJumpKey = altJumpKey;
        this.duckKey = duckKey;
    }

    //METHODS

    @Override
    public void update() {
        if(y + h + dy > groundLevel) {
            dy = 0;
            y = groundLevel - h;
            isFalling = false;
        }

        if(isJumping) {
            dy = -this.jumpSpeed;
            isJumping = false;
            isFalling = true;
        }
        else if(isFalling) {
            dy += this.gravity;
        }

        if(isDucking) {
            if(isFalling) {
                dy += 0.5;
            }
            else {
                h = 30;
                w = 50;
                y = groundLevel - h;
            }
        }

        y += dy;
    }

    @Override
    public void reset() {
        super.reset();
        
        isDucking = false;
        isFalling = false;
        isJumping = false;
        dy = 0;
        y = groundLevel - h;
    }

    public void keyWasPressed(int key) {
        if((key == jumpKey || key == altJumpKey) && !isFalling)
            isJumping = true;
        if(key == duckKey)
            isDucking = true;
    }

    public void keyWasReleased(int key) {
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
