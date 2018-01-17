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
public class Sheep extends Animal implements LivingBeing{
    public Sheep(World world, int x, int y, boolean canMove) {
        super(world, 4, 4, x, y, "Owca", Color.LIGHT_GRAY, canMove);
    }
}
