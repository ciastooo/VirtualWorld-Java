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
        String log = this.name + " próbuje się rozmnożyć";
        int randomNumber = new Random().nextInt(100) + 1;
        if(randomNumber > this. reproducingChance) {
            log +=  " - nie udało się";
            this.world.consoleLogLn(log);
        } else {
            this.world.consoleLog(log);
            this.tryReproduce();
        }
        this.canMove = false;
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        String log = other.getName() + " napotyka roślinę " + this.getName();
        if(this.strength > other.getStrength()) {
            log += ", rani się na niej i umiera";
            other.setToDelete();
        } else {
            log += " i ją zjada";
            this.toDelete = true;
        }
        this.world.consoleLogLn(log);
        return true;
    }
    
    @Override
    public boolean tryReproduce() {
        String log = " na ";
        int direction = new Random().nextInt(4);
        final int newX;
        final int newY;
        switch(direction) {
            case 0:
                log += "północ";
                if(this.y == 1) {
                    newY = this.world.getHeight();
                } else {
                    newY = this.y - 1;
                }
                newX = this.x;
                break;
            case 1:
                log += "wschód";
                if(this.x == this.world.getWidth()) {
                    newX = 1;
                } else {
                    newX = this.x + 1;
                }
                newY = this.y;
                break;
            case 2:
                log += "południe";
                if(this.y == this.world.getHeight()) {
                    newY = 1;
                } else {
                    newY = this.y + 1;
                }
                newX = this.x;
                break;
            case 3:
            default:
                log += "zachód";
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
            log += " ale to pole jest już zajęte";
            this.world.consoleLogLn(log);
            return false;
        } else {
            log += ". Sukces";
            this.world.consoleLogLn(log);
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
