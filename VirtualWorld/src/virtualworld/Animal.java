/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.util.Optional;
import java.util.Random;
import sun.security.provider.certpath.BuildStep;

/**
 *
 * @author Piotrek
 */
public abstract class Animal extends Organism implements LivingBeing{
    Animal(World world, int strength, int initiative, int x, int y, String name, boolean canMove) {
        super(world, strength, initiative, x, y, name, canMove);
    }
  
    public abstract boolean reproduce();
    
    public boolean tryReproduce(LivingBeing child) {
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
                    child.setXY(tmpX, this.y);
                    this.world.insertLivingBeing(child);
                    return true;
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
			child.setXY(this.x, tmpY);
			this.world.insertLivingBeing(child);
			return true;
		}
	}
	return false;
    }
    
    public void action() {
        if(!this.canMove) {
            this.canMove = true;
            return;
        }
        final int newX;
        final int newY;
        String consoleLog = "Zwierzę " + this.getName() + " idzie na ";
        final int moveDirection = new Random().nextInt(4);
        switch(moveDirection) {
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
        System.out.print(consoleLog);
        Optional<LivingBeing> colliding = this.world.findLivingBeing(item -> item.getX() == newX && item.getY() == newY);
        if(colliding.isPresent()) {
            System.out.print("Kolizja z " + colliding.get().getName());
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
    
    public boolean collision(LivingBeing other) {
        String consoleLog;
        if(other.getClass() == this.getClass()) {
            boolean tryMultiply = this.reproduce();
            if(!tryMultiply) {
                tryMultiply = other.reproduce();
            }
            consoleLog = "Kolizja zwięrząt tego samego gatunku, ";
            if(tryMultiply) {
                System.out.print(consoleLog + "rozmożyli się");
            } else {
                System.out.print(consoleLog + "brak miejsca na potomstwo");
            }
            return true;
        } else {
            consoleLog = "Walka! ";
            if(this.strength > other.getStrength()) {
                consoleLog += "Atakujące zwierzę \"" + this.getName() + "\" przegrywa i umiera!";
                other.setToDelete();
            } else {
                consoleLog += "Atakujące zwierzę \"" + other.getName() + "\" wygrywa! Zwierze " + this.getName() + " umiera";
                this.toDelete = true;
            }
            System.out.print(consoleLog);
            return true;
        }
    }
}
