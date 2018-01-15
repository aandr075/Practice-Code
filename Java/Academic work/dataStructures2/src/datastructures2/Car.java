/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures2;

/**
 *
 * @author Erik
 */
public class Car {
    
    //fields
    public Car prev; //previous parking spot
    public Car next; //next parking spot
    private String plate; // this car's plate
    private int removes;
    private int parks;
    
    //costructors
    
    
    public Car(String plate, Car prev, Car next){
        
        this.plate = plate;
        this.prev = prev;
        this.next = next;
        removes=parks=0;
    }
    
    //methods
    
    
    public void removed(){
        removes++;
    }
    
    public void parked(){
        parks++;
    }
    
    public String toString(){
        return plate;    
    }
    
    public int getRemoves(){
        return removes;
    }
    
    public int getParks(){
        return parks;
    }
}
