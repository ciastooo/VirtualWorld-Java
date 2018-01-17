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
public class Lion extends Animal implements LivingBeing{
    public Lion(World world, int x, int y, boolean canMove) {
        super(world, 11, 7, x, y, "Lew", Color.YELLOW, canMove);
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        if(other.getStrength() < 5) {
            this.world.consoleLogLn("Zwierzę " + other.getName() + " ma mniej niż 5 siły. " + other.getName() + " wycofuje się przed lwem.");
            return false;
        } else {
            return super.collision(other);
        }
    }
}
