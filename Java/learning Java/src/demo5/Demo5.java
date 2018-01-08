package demo5;

import java.util.Scanner;
import java.util.Random;

import demo3.Cazako;

import java.util.ArrayList;

public class Demo5 {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		ArrayList<Cazako> characters = new ArrayList<Cazako>();
		
		Cazako erik = new Cazako("Erik", "Remember the Red", "Khaos Klub");
		Cazako adam = new Cazako("Adam", "Enter the Darkness", "Khaos Klub");
		Cazako zaper = new Cazako("Zaper", "", "Legends of Zeolinth");
		
		characters.add(erik);
		characters.add(adam);
		characters.add(zaper);
		
		int studentsCount;
		double[] heights;
		System.out.println("How many students?");
		studentsCount = input.nextInt();
		heights = new double[studentsCount];
		
		Random rand = new Random();
		
		
		for( int i = 0; i < heights.length; ++i){
			System.out.println("Enter height:");
			heights[i] = input.nextDouble();
		}
		
		//System.out.println(characters.toString());
		printArray(heights);
		selectSort(heights.clone());
		insertSort(heights.clone());
		
		double randbl = heights[rand.nextInt(heights.length)];
		
		System.out.print("Search for index of "+ randbl + ": " + binarySearch(randbl,heights));
		
		
		input.close();
		
	}
	
	
	public static int binarySearch(double x, double[] heights){
		return binarySearch2(x,selectSort(heights),0,heights.length);
	}
	
	public static int binarySearch2(double x, double[] heights, int lhs, int rhs){
		int index = (lhs + rhs) / 2 ;
		
		if (heights[index] < x){
			return binarySearch2(x,heights, index+1, rhs);
		}
		else if (heights[index] > x){
			return binarySearch2(x,heights,lhs, index-1);
		}
		else{
			if (heights[index] != x) return -1;
			return index;
		}
		
		
		
	}
	
	public static double[] insertSort(double[] heights){
		int i,j;
		double key;
		
		for (j = 1; j < heights.length; ++j){
			key = heights[j];
			for(i = j-1; (i>=0) && (heights[i] > key); --i){
				heights[i+1] = heights[i];
			}
			heights[i+1] = key;
		}

		System.out.print("insertion sort: ");
		printArray(heights);
		return heights;
		
	}
	
	
	public static double[] selectSort(double[] heights){
		int i,j,first; //for sorting
		double temp;
		
		//selection sort
		for (i = 0; i <  heights.length; ++i){
			first = i;
			for(j = heights.length -1; j >= i; --j){
				if (heights[j] < heights[first]) first = j;
			}
			if (first != i) {
				temp = heights[first];
				heights[first] = heights[i];
				heights[i] = temp; 
			}
		}

		System.out.print("Selection sort: ");
		printArray(heights);
		return heights;
	}
	
	public static void printArray(double[] heights){
		for( double x: heights){
			System.out.print(x + " ");
		}
		System.out.println("");
	}
}
