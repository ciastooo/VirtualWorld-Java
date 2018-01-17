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
public abstract class Animal extends Organism implements LivingBeing{
    Animal(World world, int strength, int initiative, int x, int y, String name, Color color, boolean canMove) {
        super(world, strength, initiative, x, y, name, color, canMove);
    }
   
    @Override
    public boolean tryReproduce() {
        for (int x = this.x - 1; x <= this.x + 1; x += 2) {
		final int tmpX;
		if (x < 1) {
                    tmpX = this.world.getWidth();
		}
		else if (x > this.world.getWidth()) {
                    tmpX = 1;
		} else {
                    tmpX = x;
                }
		Optional<LivingBeing> occupied = this.world.findLivingBeing(item -> item.getX() == tmpX && item.getY() == this.y);
		
                if (!occupied.isPresent()) {
                    try {
                        Animal child = this.getClass().getConstructor(World.class, int.class, int.class, boolean.class).newInstance(this.world, tmpX, this.y, false);
                        this.world.insertLivingBeing(child);
                        return true;
                    } catch(Exception ex) {
                        return false;
                    }
                }
	}
	for (int y = this.y - 1; y <= this.y + 1; y += 2) {
		final int tmpY;
		if (y < 1) {
                    tmpY = this.world.getHeight();
		}
		else if (y > this.world.getHeight()) {
                    tmpY = 1;
		}else {
                    tmpY = y;
                }
		Optional<LivingBeing> occupied = this.world.findLivingBeing(item -> item.getX() == this.x && this.y == tmpY);
		if (!occupied.isPresent()) {
                    try {
                        Animal child = this.getClass().getConstructor(World.class, int.class, int.class, boolean.class).newInstance(this.world, this.x, tmpY, false);
                        this.world.insertLivingBeing(child);
                        return true;
                    } catch(Exception ex) {
                        return false;
                    }
		}
	}
	return false;
    }
    
    @Override
    public void action() {
        if(!this.canMove) {
            return;
        }
        final int newX;
        final int newY;
        String log = "Zwierzę " + this.getName() + " idzie na ";
        final int moveDirection = new Random().nextInt(4);
        switch(moveDirection) {
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
        this.world.consoleLogLn(log);
        Optional<LivingBeing> colliding = this.world.findLivingBeing(item -> item.getX() == newX && item.getY() == newY);
        if(colliding.isPresent()) {
            this.world.consoleLogLn("Kolizja z " + colliding.get().getName());
            if(colliding.get().collision(this)) {
                this.x = newX;
                this.y = newY;
            }
        } else {
            this.x = newX;
            this.y = newY;
        }
        this.canMove = false;
    }
    
    @Override
    public boolean collision(LivingBeing other) {
        String log;
        if(other.getClass() == this.getClass()) {
            boolean tryMultiply = this.tryReproduce();
            if(!tryMultiply) {
                tryMultiply = other.tryReproduce();
            }
            log = "Kolizja zwięrząt tego samego gatunku, ";
            if(tryMultiply) {
                this.world.consoleLogLn(log + "rozmożyli się");
            } else {
                this.world.consoleLogLn(log + "brak miejsca na potomstwo");
            }
            return true;
        } else {
            log = "Walka! ";
            if(this.strength > other.getStrength()) {
                log += "Atakujące zwierzę \"" + this.getName() + "\" przegrywa i umiera!";
                other.setToDelete();
            } else {
                log += "Atakujące zwierzę \"" + other.getName() + "\" wygrywa! Zwierze " + this.getName() + " umiera";
                this.toDelete = true;
            }
            this.world.consoleLogLn(log);
            return true;
        }
    }
}
