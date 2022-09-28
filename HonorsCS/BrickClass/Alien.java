package BrickClass;

import java.awt.Image;
import java.util.ArrayList;

import UNIVERSAL.RANDOM.SpriteSheet;

import java.awt.Color;

public class Alien extends Brick{

    public int variant;
    boolean canShoot;
    boolean hasShot;
    Projectile projectile;

    public Alien(double x, double y, int width, int height, int variant)
    {
        super(x, y, width, height);
        this.variant = variant;
    }

    public Alien(double x, double y, int width, int height, int variant, Image img)
    {
        super(x, y, width, height, img);
        this.variant = variant;
    }

    public Alien(double x, double y, int width, int height, int variant, SpriteSheet ss)
    {
        super(x, y, width, height, ss);
        this.variant = variant;
    }

    public Alien(double x, double y, int width, int height, int variant, SpriteSheet ss, double dx, double dy)
    {
        super(x, y, width, height, ss, dx, dy);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Image img, int dx, int dy)
    {
        super(x, y, width, height, img, dx, dy);
        this.variant = variant;
    }

    public Alien(double x, double y, int width, int height, int variant, SpriteSheet ss, int dx, int dy)
    {
        super(x, y, width, height, ss, dx, dy);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color) //Another constructor with optional values
    {
        super(x, y, width, height, color);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color, int dx, int dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color, double dx, double dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color, int dx, int dy,
    int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color, double dx, double dy,
    int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, null);
        this.variant = variant;
    }
    
    public Alien(double x, double y, int width, int height, int variant, Color color, double dx, double dy,
    int XMin, int XMax, int YMin, int YMax, Image img) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax, img);
        this.variant = variant;
    }


    public int getVariant() {
        return this.variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public boolean isCanShoot() {
        return this.canShoot;
    }

    public boolean getCanShoot() {
        return this.canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public boolean isHasShot() {
        return this.hasShot;
    }

    public boolean getHasShot() {
        return this.hasShot;
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

    public void projectileChance(ArrayList<Projectile> projList, Projectile projectile)
    {
        int rand = (int) (Math.random() * 101 + 1);
        if (rand <= 5)
            shootProjectile(projList, projectile);

    }

    public void shootProjectile(ArrayList<Projectile> projList, Projectile proj)
    {
        // if(!projList.contains(proj))
        //     canShoot = true;  

        // if(canShoot)
        this.projectile = new Projectile(x + width/2, y + height, proj.getW(), proj.getH(), proj.getColor(),
         proj.getDx(), proj.getDy(), proj.getXMin(),  proj.getXMax(), proj.getYMin(),  proj.getYMax());
        this.projectile.ss = proj.ss;

        projList.add(projectile);

        // canShoot = false;
    }

}
