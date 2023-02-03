package Games.DinoGame;

import java.util.Objects;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Sprite {

    protected double x, y, dx, dy;
    protected int w, h;
    

    public Sprite() {
        this.x = 0;
        this.y = 0;
        this.dx = 0;
        this.dy = 0;
        this.w = 0;
        this.h = 0;
    }    

    public Sprite(double x, double y, double dx, double dy, int w, int h) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.w = w;
        this.h = h;
    }


    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return this.dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return this.dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }


    @Override
    public String toString() {
        return "Sprite {" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", dx='" + getDx() + "'" +
            ", dy='" + getDy() + "'" +
            ", w='" + getW() + "'" +
            ", h='" + getH() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Sprite))
            return false;
        if (o == this)
            return true;

        Sprite sprite = (Sprite) o;
        return x == sprite.x && y == sprite.y && dx == sprite.dx && dy == sprite.dy && w == sprite.w && h == sprite.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, dx, dy, w, h);
    }

    public void update() {
        System.out.println("Updating the sprite!");
    }

    public void draw(Graphics2D g2) {
        g2.fillRect((int) x, (int) y, w, h);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    public void reset() {
        System.out.println("Resetting the sprite!");
    }
    
    public boolean isCollidingWith(Sprite spr) {
        if(spr.getBounds().intersects(getBounds()))
        {
            System.out.println("Colliding!!!");
            return true;
        }

        return false;
    }

}
