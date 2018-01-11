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
public class Rabbit extends Animal implements LivingBeing{
    Rabbit(World world, int x, int y, boolean canMove) {
        super(world, 1, 8, x, y, "Królik", canMove);
    }
    
    @Override
    public boolean tryReproduce() {
        if(super.tryReproduce()) {
            super.tryReproduce();
            return true;
        } else {
            return false;
        }
    }
}
