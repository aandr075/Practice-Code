/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures2;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class Garage {
    //fields
    private Car header;
    private Car waitingLine;
    private int maxSize = 7;
    private int size;
    private Stack toReturn;
    private ArrayList<Car> gone;
    
    //constructors
    
    public Garage(){
        header = new Car("",null,null);
        waitingLine = new Car("",null,null);
        size = 0;
        toReturn = new Stack();
        gone = new ArrayList();
    }
    
    //methods
    
   
   public boolean hasCar(String car){
       Car spot = header;
        while (spot.next != null){
            if (spot.next.toString() == car) return true;
            spot = spot.next;
        }
        spot = waitingLine;
        while (spot.next != null){
            if (spot.next.toString() == car) return true;
            spot = spot.next;
        }
        return false;
   }
    
    
        
   public void addCar(String car){
       Car spot = header;
        while (spot.next != null && size !=7){
            spot = spot.next;
        }
        if (size !=7){
            spot.next = new Car(car,spot,null);
            //checks to see if car was previously removed
            for(Car x:gone){
                if (car.equals(x.toString())) spot.next = x;
            }
            
            spot.next.parked();
            size++;
            System.out.print(car+" has been added to space " + size + "\n");
        }
        else {
            spot = waitingLine;
            while (spot.next != null){
                spot = spot.next;
            }
            spot.next = new Car(car,spot,null);
            //check to see if car was previously removed
            for(Car x:gone){
                if (car.equals(x.toString())) spot.next = x;
            }
            
            System.out.print(car+" is waiting for a space\n");
        }
   }
   
   public void removeCar(String car){
       Car spot = header;
       Car front;
       while (spot.next != null){
           if (spot.toString() != car) spot = spot.next;
        }
       front = spot.next;
       
   }
   
   
   public boolean carWaiting(){
       return waitingLine.next != null;
   }
   
   public String toString(){
       String garage = "Garage status\n";
       Car spot = header.next;
       int i = 1;
        while (spot != null){
            garage += "space "+i+": "+ spot.toString() + "\n";
            spot = spot.next;
            i++;
        }
        
        if (carWaiting()){
            spot = waitingLine.next;
            garage+= "Waiting List:\n";
            while (spot != null){
                garage +=  spot.toString()+"\n";
                spot = spot.next;
        }
        }
       return garage;
   }
    
}
