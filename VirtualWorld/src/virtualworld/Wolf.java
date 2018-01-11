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
public class Wolf extends Animal implements LivingBeing{
    Wolf(World world, int x, int y, boolean canMove) {
        super(world, 9, 5, x, y, "Wilk", canMove);
    }
}
