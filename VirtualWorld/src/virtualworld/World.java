/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piotrek
 */
public class World {
    private int width;
    private int height;
    private List<LivingBeing> organisms;
    
    World(int width, int height) {
        this.width = width;
        this.height = height;
        this.organisms = new ArrayList<LivingBeing>();
    }
    
    World() {
        this(20,20);
    }
}
