package demo;
import java.util.Scanner;

public class Demo {
	
	public static void main(String[] args){
		//ch2 challenge
		
		Scanner input = new Scanner(System.in);
		
		
		String[] parts = {"wall", "window", "door"};
		double surfaceArea = surfaceAreaFinder(parts[0],input) - surfaceAreaFinder(parts[1], input) - surfaceAreaFinder(parts[2],input);
		System.out.println("Total Surface Area to be Painted: " + surfaceArea );
		
		
		
		
		
		
		
		
		/*{
			//System.out.println((3 + 5 + 8 )/ 3);
			//System.out.println((3 + 5 + 8 )/ 3.0); //implicit conversion
	
			System.out.printf("The volume of a sphere with radius 10 is: %1.2f\n", fakeVolumeSphere(10));
	
			System.out.printf("The correct volume of a sphere with radius 10 is: %1.2f\n", realVolumeSphere(10.0));
		}*/
		
		
		/*{
			double[] prices = new double[5];
			double total = 0;
			Scanner input = new Scanner(System.in);
			System.out.println("Remember the Red!\n");
			System.out.println("Enter "+ prices.length + " Prices. ");
			
			for (double x: prices){
				System.out.println("Please enter an amount:");
				x = input.nextDouble();
				System.out.printf("you entered: $%1.2f\n",  x);
				total += x;
			}
			
			
			System.out.printf("The total of all the prices is: $%1.2f\n", total);
		}*/
		
	}
	
	public static double VolumeSphere(int r) {
		return 4/3 * Math.PI * r*r*r; //error with 4/3, will = 1
	}
	
	public static double VolumeSphere(double r) {
		return 4/3.0 * Math.PI * r*r*r; //will return 4/3.0 as a double
	}
	
	public static double area(double height, double width){
		return (height * width);
	}
	
	public static double surfaceAreaFinder ( String object, Scanner input){
		
		
		
		double totalArea = 0;
		String response = "";
		double height;
		double width;
		int count = 0;
		int x = 0;
		
		while ( response != "yes"){
			
			
			System.out.println("How many " + object +"s does the house have?");
			count = input.nextInt();
			System.out.printf("Does the house have %d %ss?(y/n)\n", count, object);
			
			while(!input.hasNext());

			response =  input.next().toLowerCase();
		
			if( response.equals("y")){response = "yes";}
		}
		
		while (count > 0){
			System.out.println(object + " Height:");
			height = input.nextDouble();
			System.out.println(object + " Width:");
			width = input.nextDouble();
			System.out.println("How many " +object + "s of these dimensions?:");
			x = input.nextInt();
			if( x > count){
				System.out.println("number of " +object + "s must be under" + count + "\nPlease try again.");
				continue;
			}
			totalArea += x*area(height, width);
			count -= x;
		}
		
		return totalArea;
	}

}
