/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

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
    
    Organism(World world, int strength, int initiative, int x, int y, String name, boolean canMove) {
        this.world = world;
        this.strength = strength;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.name = name;
        this.canMove = canMove;        
    }
    
    public abstract void action();
    public abstract boolean collision(LivingBeing colliding);
    public void draw() {
        
    }
    public abstract boolean reproduce();
    
    public int getInitiative() {
        return this.initiative;
    }
    public World getWorld() {
        return this.world;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getStrength() {
        return this.strength;
    }
    public String getName() {
        return this.name;
    }
    public boolean getCanMove() {
        return this.canMove;
    }
    public void setCanMove(boolean newValue) {
        this.canMove = newValue;
    }
}
