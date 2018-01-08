package demo6;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;


public class Demo6 {
	
	public static void main(String[] args){
		
		
		File inputFile = new File("colors.txt"); //file must be located just outside the src folder, in project folder
		
		
		Scanner input = new Scanner(System.in);
		ArrayList<String> colors = new ArrayList<String>();
		String color, response = "y";
		Random rand = new Random();
		
		try {
			FileWriter fw = new FileWriter(inputFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter output = new PrintWriter(bw);
			System.out.println("Input a color:");
			output.println(input.nextLine());
			output.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try{
			Scanner fileReader = new Scanner(inputFile);
			while(fileReader.hasNextLine()){
				colors.add(fileReader.nextLine());
			}
			fileReader.close();
		}
		catch(Exception e){
			System.out.println("Input File was not found...");
			System.out.println(e.toString());
		}
		System.out.println("Colors: " + colors.toString().split("\\]|\\[")[1]);
		System.out.println("Random color: " + colors.get(rand.nextInt(colors.size())));
		
	}
	

}
