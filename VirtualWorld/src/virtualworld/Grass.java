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
public class Grass extends Plant implements LivingBeing{
    Grass(World world, int x, int y, boolean canMove) {
        super(world, 0, x, y, "Trawa", Color.green, canMove, 50);
    }
}
