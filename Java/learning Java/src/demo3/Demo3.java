package demo3;
import java.util.Random;
import java.util.Scanner;

public class Demo3 {

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		Cazako erik = new Cazako("Erik", "Remember the Red", "Khaos Klub");
		Cazako adam = new Cazako("Adam", "Enter the Darkness", "Khaos Klub");
		Cazako zaper = new Cazako("Zaper", "", "Legends of Zeolinth");
		
		System.out.println(erik.toString());
		
		
		
		
		
		
		
		
		//System.out.println("3.5 becomes:" + flooring(3.99));
		
		
		
		
		/*//dice rolling random number
		int count = 0;
		int dice1, dice2;
		
		Random rand = new Random();
		
		for (int i  = 0; i < 100; ++i){
			dice1 = rand.nextInt(6)+1;
			dice2 = rand.nextInt(6)+1;
			if(dice1 == dice2){
				++count;
			}
		}
		System.out.println("total doubles rolled: " + count );*/
			
	}//end main
	
	public static double flooring(double x){
		return (double) (int) x;
	}
}
