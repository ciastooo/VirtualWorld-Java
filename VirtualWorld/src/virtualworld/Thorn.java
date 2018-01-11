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
public class Thorn extends Plant implements LivingBeing{
    Thorn(World world, int x, int y, boolean canMove) {
        super(world, 2, x, y, "Cierń", canMove, 100);
    }
}
