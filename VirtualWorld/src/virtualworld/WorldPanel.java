/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Piotrek
 */
public class WorldPanel extends JPanel {
    private World world;
    
    WorldPanel(World world) {
        super();
        this.world = world;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        List<LivingBeing> organisms = this.world.getOrganisms();
        g.clearRect(0, 0, world.getWidth()*10, world.getHeight()*10);
        for(int i = 0; i < organisms.size(); i++) {
            LivingBeing current = organisms.get(i);
            g2.setColor(current.getColor());
            g2.fillRect((current.getX()-1)*10, (current.getY()-1)*10, 10, 10);
        }
    }
}
