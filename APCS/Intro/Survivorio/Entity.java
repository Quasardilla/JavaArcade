package Intro.Survivorio;

import java.awt.Image;

import UNIVERSAL.UI.UIElement;

abstract class Entity extends UIElement{
    
    protected int health;
    protected int damage;
    // protected int resistance;
    protected String name;
    protected Image img;

    @Override
    public void drawElement()
    {

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


}
