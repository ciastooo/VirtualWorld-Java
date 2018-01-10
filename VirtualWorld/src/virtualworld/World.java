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
public class World {
    private int width;
    private int height;
    
    World(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    World() {
        this(20,20);
    }
}
