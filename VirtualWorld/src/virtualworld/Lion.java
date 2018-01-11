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
public class Lion extends Animal implements LivingBeing{
    Lion(World world, int x, int y, boolean canMove) {
        super(world, 11, 7, x, y, "Lew", canMove);
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        if(other.getStrength() < 5) {
            System.out.print("Zwierzę " + other.getName() + " ma mniej niż 5 siły. " + other.getName() + " wycofuje się przed lwem.");
            return false;
        } else {
            return super.collision(other);
        }
    }
}
