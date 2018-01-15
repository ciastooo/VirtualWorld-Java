/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author Piotrek
 */
public abstract class Plant extends Organism implements LivingBeing {
    protected int reproducingChance;
        
    public Plant(World world, int strength, int x, int y, String name, Color color, boolean canMove, int reproducingChance) {
        super(world, strength, 0, x, y, name, color, canMove);
        this.reproducingChance = reproducingChance;
    }
    
    @Override
    public void action() {
        if(!this.canMove) {
            this.canMove = true;
            return;
        }
        String consoleLog = this.name + " próbuje się rozmnożyć";
        int randomNumber = new Random().nextInt(100) + 1;
        if(randomNumber > this. reproducingChance) {
            consoleLog +=  " - nie udało się";
            System.out.println(consoleLog);
        } else {
            System.out.print(consoleLog);
            this.tryReproduce();
        }
        this.canMove = false;
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        String consoleLog = other.getName() + " napotyka roślinę " + this.getName();
        if(this.strength > other.getStrength()) {
            consoleLog += ", rani się na niej i umiera";
            other.setToDelete();
        } else {
            consoleLog += " i ją zjada";
            this.toDelete = true;
        }
        System.out.println(consoleLog);
        return true;
    }
    
    @Override
    public boolean tryReproduce() {
        String consoleLog = " na ";
        int direction = new Random().nextInt(4);
        final int newX;
        final int newY;
        switch(direction) {
            case 0:
                consoleLog += "północ";
                if(this.y == 1) {
                    newY = this.world.getHeight();
                } else {
                    newY = this.y;
                }
                newX = this.x;
                break;
            case 1:
                consoleLog += "wschód";
                if(this.x == this.world.getWidth()) {
                    newX = 1;
                } else {
                    newX = this.x + 1;
                }
                newY = this.y;
                break;
            case 2:
                consoleLog += "południe";
                if(this.y == this.world.getHeight()) {
                    newY = 1;
                } else {
                    newY = this.y + 1;
                }
                newX = this.x;
                break;
            case 3:
            default:
                consoleLog += "zachód";
                if(this.x == 1) {
                    newX = this.world.getWidth();
                } else {
                    newX = this.x - 1;
                }
                newY = this.y;
                break;
        }
        Optional<LivingBeing> colliding = this.world.findLivingBeing(item -> item.getX() == newX && item.getY() == newY);
        if(colliding.isPresent()) {
            consoleLog += " ale to pole jest już zajęte";
            System.out.println(consoleLog);
            return false;
        } else {
            consoleLog += ". Sukces";
            System.out.println(consoleLog);
            try {
                Plant child = this.getClass().getConstructor(World.class, int.class, int.class, boolean.class).newInstance(this.world, newX, newY, false);
                this.world.insertLivingBeing(child);
            } catch(Exception ex) {
                return false;
            }
            return true;
        }
    }
}
