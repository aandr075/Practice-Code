package datastructures2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;



/**
 *
 * @author Erik
 */
public class dataStructures2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        Garage garage = new Garage(); //parking garage
        File file; //file to be scanned
        Scanner read; // scanner that reads the file
        String action; //used to determine what car is doing
        String car; //used to find a car's plate
        
        file = new File(JOptionPane.showInputDialog("File to be read: \n"
                + "Example: \"input.txt\"","input.txt"));
        
        read = new Scanner(file);
        
        while(read.hasNext()){
            action = read.next();
            car = read.next();
            
            if (action.equals("A")){
                if (!garage.hasCar(car)){
                    garage.addCar(car);
                }
            }
            
            if (action == "D"){
                if (garage.hasCar(car)){
                    garage.removeCar(car);
                }
            }
         System.out.print(garage);
            
        }
        
        
        
    }
    
}
