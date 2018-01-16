/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import javax.swing.JPanel;

/**
 *
 * @author Piotrek
 */
public class World{
    private int width;
    private int height;
    private LinkedList organisms;
    
    World(int width, int height) {
        this.width = width;
        this.height = height;
        this.organisms = new LinkedList();
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
        this.organisms.insert(adding);
        
    }
    
    public Optional<LivingBeing> findLivingBeing(Predicate<LivingBeing> predicate) {
        return this.organisms.search(predicate);
    }
    
    public void tick() {
        this.organisms.doAction();
        this.organisms.refreshMove();
    }
    
    public void draw(Graphics2D g2) {
        this.organisms.draw(g2);
    }
}
