/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void insertLivingBeing(LivingBeing adding) {
        boolean added = false;
        for(int i = 0; i < this.organisms.size(); i++) {
            if(this.organisms.get(i).getInitiative() < adding.getInitiative()) {
                this.organisms.add(i, adding);
                added = true;
                break;
            }
        }
        if(!added) {
            this.organisms.add(adding);
        }
    }
    
    public Optional<LivingBeing> findLivingBeing(Predicate<LivingBeing> predicate) {
        return this.organisms.stream().filter(predicate).findFirst();
    }
}
