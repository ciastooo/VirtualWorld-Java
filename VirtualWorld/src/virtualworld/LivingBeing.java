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
public interface LivingBeing {
    public void action();
    public boolean collision(LivingBeing colliding);
    public void draw();
    public boolean tryReproduce();
    public int getInitiative();
    public World getWorld();
    public int getX();
    public int getY();
    public void setXY(int x, int y);
    public int getStrength();
    public String getName();
    public boolean getCanMove();
    public void setCanMove(boolean newValue);
    public void setToDelete();
}
