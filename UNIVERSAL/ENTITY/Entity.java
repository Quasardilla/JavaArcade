package UNIVERSAL.ENTITY;

import java.awt.Image;

import UNIVERSAL.UI.UIElement;

abstract class Entity extends UIElement{
    
    protected int health = 0;
    protected int damage = 0;
    protected double speed = 0;
    // protected int resistance;
    protected String name = "";
    protected Image img = null;
    protected double x = 0;
    protected double y = 0;

    public Entity(){}

    public Entity(int health, int damage, double speed, String name, Image img)
    {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.name = name;
        this.img = img;
    }

    @Override
    public void drawElement()
    {
    }


    public Image getImg() {
        return this.img;
    }

    public void setImg(Image img) {
        this.img = img;
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


    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double Speed) {
        this.speed = Speed;
    }
    
    

}
