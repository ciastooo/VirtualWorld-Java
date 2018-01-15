/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;

/**
 *
 * @author Piotrek
 */
public abstract class Organism implements LivingBeing{
    protected int strength;
    protected int initiative;
    protected int x;
    protected int y;
    protected World world;
    protected String name;
    protected boolean canMove;
    protected boolean toDelete = false;
    protected Color color;
    
    Organism(World world, int strength, int initiative, int x, int y, String name, Color color, boolean canMove) {
        this.world = world;
        this.strength = strength;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.canMove = canMove;        
    }
    
    @Override
    public abstract void action();
    @Override
    public abstract boolean collision(LivingBeing colliding);
    @Override
    public abstract boolean tryReproduce();
    
    @Override
    public int getInitiative() {
        return this.initiative;
    }
    @Override
    public World getWorld() {
        return this.world;
    }
    @Override
    public int getX() {
        return this.x;
    }
    @Override
    public int getY() {
        return this.y;
    }
    @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int getStrength() {
        return this.strength;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public boolean getCanMove() {
        return this.canMove;
    }
    @Override
    public void setCanMove(boolean newValue) {
        this.canMove = newValue;
    }
    @Override
    public boolean getToDelete() {
        return this.toDelete;
    }
    @Override
    public void setToDelete() {
        this.toDelete = true;
    }
    @Override
    public Color getColor() {
        return this.color;
    }
}
