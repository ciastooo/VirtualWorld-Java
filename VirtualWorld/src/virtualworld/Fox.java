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
public class Fox extends Animal implements LivingBeing{
    public Fox(World world, int x, int y, boolean canMove) {
        super(world, 3, 7, x, y, "Lis", Color.ORANGE, canMove);
    }
    
    @Override
    public void action() {
        if(!this.canMove) {
            this.canMove = true;
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
        Optional<LivingBeing> colliding = this.world.findLivingBeing(item -> item.getX() == newX && item.getY() == newY);
        if(colliding.isPresent()) {
            LivingBeing collidingOrganism = colliding.get();
            if(collidingOrganism.getStrength() > this.strength) {
                this.action();
                return;
            }
            this.world.consoleLogLn(log);
            this.world.consoleLogLn("Kolizja z " + colliding.get().getName());
            if(colliding.get().collision(this)) {
                this.x = newX;
                this.y = newY;
            }
        } else {
            this.world.consoleLogLn(log);
            this.x = newX;
            this.y = newY;
        }
        this.canMove = false;
    }
}
