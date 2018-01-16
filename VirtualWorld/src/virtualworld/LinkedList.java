/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Graphics2D;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author Piotrek
 */
public class LinkedList {
    private LinkedListItem head;
    
    public LinkedList() {
        this.head = null;
    }
    
    public void insert(LivingBeing organism) {
        LinkedListItem newItem = new LinkedListItem(organism);
        if(head == null) {
            this.head = newItem;
        } else {
            LinkedListItem search = head;
            while(search.getOrganism().getInitiative() >= organism.getInitiative()) {
                if(search.getNext() == null) {
                    break;
                } else {
                    search = search.getNext();
                }
            }
            if(search.getOrganism().getInitiative() >= organism.getInitiative()) {
                if(search.getNext() != null) {
                    search.getNext().setPrev(newItem);
                    newItem.setNext(search.getNext());
                }
                search.setNext(newItem);
                newItem.setPrev(search);
            } else {
                if(search.getPrev() != null) {
                    search.getPrev().setNext(newItem);
                    newItem.setPrev(search.getPrev());
                } else {
                    head = newItem;
                }
                newItem.setNext(search);
                search.setPrev(newItem);
            }            
        }
    }
    
    public void doAction() {
        LinkedListItem current = head;
        while(current != null) {
            if(current.getOrganism().getToDelete()) {
                LinkedListItem toRemove = current;
                current = current.getNext();
                this.remove(toRemove);
            } else {
                current.getOrganism().action();
                current = current.getNext();
            }
        }
    }
    
    public void remove(LinkedListItem toRemove) {
        LinkedListItem tmpPrev = toRemove.getPrev();
        LinkedListItem tmpNext = toRemove.getNext();
        if(tmpNext == null && tmpPrev == null) {
            head = null;
        } else {
            if(tmpPrev != null) {
                tmpPrev.setNext(tmpNext);
            }
            if(tmpNext != null) {
                tmpNext.setPrev(tmpPrev);
                if(head == toRemove) {
                    head = tmpNext;
                }
            }
        }
    }
    
    public Optional<LivingBeing> search(Predicate<LivingBeing> predicate) {
        LinkedListItem search = head;
        while(search != null) {
            LivingBeing organism = search.getOrganism();
            if(organism.getToDelete()) {
                LinkedListItem toRemove = search;
                search = search.getNext();
                this.remove(toRemove);
            } else {
                if(predicate.test(organism)) {
                    return Optional.of(organism);
                } else {
                    search = search.getNext();
                }
            }
        }
        return Optional.empty();
    }
    
    public void refreshMove() {
        LinkedListItem search = head;
        while(search != null) {
            if(search.getOrganism().getToDelete()) {
                LinkedListItem toRemove = search;
                search = search.getNext();
                this.remove(toRemove);
            } else {
                search.getOrganism().setCanMove(true);
                search = search.getNext();
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        LinkedListItem search = this.head;
        while(search != null) {
            LivingBeing current = search.getOrganism();
            g2.setColor(current.getColor());
            g2.fillRect((current.getX()-1)*10, (current.getY()-1)*10, 10, 10);
            search = search.getNext();
        }
    }
}
