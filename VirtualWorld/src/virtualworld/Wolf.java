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
public class Wolf extends Animal implements LivingBeing{
    public Wolf(World world, int x, int y, boolean canMove) {
        super(world, 9, 5, x, y, "Wilk", Color.BLACK, canMove);
    }
}
