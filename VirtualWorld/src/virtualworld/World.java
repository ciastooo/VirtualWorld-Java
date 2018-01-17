/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import javax.swing.JOptionPane;
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
    
    public void exportToFile() {
        String filename = JOptionPane.showInputDialog("Podaj nazwę pliku do którego ma zostać zapisany stan świata:");
        try(PrintWriter out = new PrintWriter(filename+".txt")) {
            out.println(this.width);
            out.println(this.height);
            out.print(this.organisms.getExportData());
        } catch (Exception ex) {
            
        }
    }
    
    public void importFromFile() {
        String filename = JOptionPane.showInputDialog("Podaj nazwę pliku z którego ma zostać odczytany stan świata:");
        try(BufferedReader br = new BufferedReader(new FileReader(filename + ".txt"))) {
            StringBuilder sb = new StringBuilder();
            this.organisms = new LinkedList();
            this.width = Integer.parseInt(br.readLine());
            this.height = Integer.parseInt(br.readLine());
            System.out.println("świat o szerokości = " + this.width + "    oraz wysokości = " + this.height);
            String line = br.readLine();
            while (line != null) {
                LivingBeing importingOrganism;
                int x,y;
                switch(line) {
                    case "Wilk":
                        System.out.print("Wilk:");
                        importingOrganism = new Wolf(this, 0 , 0, false);
                        break;
                    case "Owca":
                        System.out.print("Owca:");
                        importingOrganism = new Sheep(this, 0 , 0, false);
                        break;
                    case "Królik":
                        System.out.print("Królik:");
                        importingOrganism = new Rabbit(this, 0 , 0, false);
                        break;
                    case "Lew":
                        System.out.print("Lew:");
                        importingOrganism = new Lion(this, 0 , 0, false);
                        break;
                    case "Lis":
                        System.out.print("Lis:");
                        importingOrganism = new Fox(this, 0 , 0, false);
                        break;
                    case "Trawa":
                        System.out.print("Trawa:");
                        importingOrganism = new Grass(this, 0 , 0, false);
                        break;
                    case "Wilcze jagody":
                        System.out.print("Wilcze jagody:");
                        importingOrganism = new Wolfberry(this, 0 , 0, false);
                        break;
                    case "Cierń":
                    default:
                        System.out.print("Cierń:");
                        importingOrganism = new Thorn(this, 0 , 0, false);
                        break;
                }
                x = Integer.parseInt(br.readLine());
                y = Integer.parseInt(br.readLine());
                System.out.print(" na pozycji x = " + x + "   y = " + y + "\n");
                importingOrganism.setXY(x, y);
                this.organisms.insert(importingOrganism);
                line = br.readLine();
            }
        } catch(Exception ex) {
            
        }
    }
    
    public void addAtPixelPosition(int x, int y) {
        String[] organisms = {"Wilk", "Owca", "Lis", "Lew", "Królik", "Trawa", "Cierń", "Wilcze jagody"};
        int n = JOptionPane.showOptionDialog(null,
            "Który organizm chcesz dodać?",
            "Wybierz organizm",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            organisms,
            organisms[0]);
        LivingBeing importingOrganism;
        switch(organisms[n]) {
            case "Wilk":
                System.out.print("Wilk:");
                importingOrganism = new Wolf(this, x , y, true);
                break;
            case "Owca":
                System.out.print("Owca:");
                importingOrganism = new Sheep(this, x , y, true);
                break;
            case "Królik":
                System.out.print("Królik:");
                importingOrganism = new Rabbit(this, x , y, true);
                break;
            case "Lew":
                System.out.print("Lew:");
                importingOrganism = new Lion(this, x , y, true);
                break;
            case "Lis":
                System.out.print("Lis:");
                importingOrganism = new Fox(this, x , y, true);
                break;
            case "Trawa":
                System.out.print("Trawa:");
                importingOrganism = new Grass(this, x , y, true);
                break;
            case "Wilcze jagody":
                System.out.print("Wilcze jagody:");
                importingOrganism = new Wolfberry(this, x , y, true);
                break;
            case "Cierń":
            default:
                System.out.print("Cierń:");
                importingOrganism = new Thorn(this, x, y, true);
                break;
        }
        System.out.print("x = " + x + "   y = " + y + "\n");
        Optional<LivingBeing> colliding = this.findLivingBeing(item -> item.getX() == x && item.getY() == y);
        if(colliding.isPresent()) {
            JOptionPane.showMessageDialog(null, "To pole jest już zajęte!");
        } else {
            this.insertLivingBeing(importingOrganism);
        }        
    }
}
