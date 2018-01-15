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
public class Wolfberry extends Plant implements LivingBeing{

    public Wolfberry(World world, int x, int y, boolean canMove) {
        super(world, 0, x, x, "Wilcze jagody", Color.RED, canMove, 25);
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        if(super.collision(other) && this.toDelete) {
            // If we got here, it means that colliding organism ate Wolfberry => it dies
            System.out.print(this.name + " jest trująca! " + other.getName() + " zatruwa się");
            other.setToDelete();
            return true;
        }
        return false;
    }
}
