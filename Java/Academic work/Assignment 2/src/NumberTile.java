
/**
 *
 * @author Bill Kraynek
 * @editor Adam Andrade
 */
import java.util.Random;

public class NumberTile {

    private int[] tile; // an array that holds numbers for each side of the tile
    /*
     * tile[0] = Left
     * tile[1] = Top
     * tile[2] = Right
     * tile[3] = Bottom
     */

    public NumberTile() {
        Random randomNumber = new Random(); //creates a random number generator
        tile = new int[4]; //sets the array size to 4 when creating a NumberTile
        // for loop will assign a random number between 1-9 to each side of the NumberTile object
        for (int i = 0; i < tile.length; i++) {
            tile[i] = randomNumber.nextInt(9) + 1;// sets side to a random number
        }
    }//end constructor

    /**
     * Rotates the NumberTile 90 degrees to the right
     */
    public void rotate() {
        // This metods rotates this (NumberTile) 90 degrees
        int[] temp = {this.tile[3], this.tile[0], this.tile[1], this.tile[2]}; //creates an array with the tile sides shifted
        this.tile = temp;
    }//end rotate

    /**
     * Returns the Left side of the NumberTile
     *
     * @return a copy of value for the array at location 0
     */
    public int getLeft() {
        int x = tile[0];
        return x;
    }//end getLeft

    /**
     * Returns the Top side of the NumberTile
     *
     * @return a copy of value for the array at location 1
     */
    public int getTop() {
        int x = tile[1];
        return x;
    }//end getTop

    /**
     * Returns the Bottom side of the NumberTile
     *
     * @return a copy of value for the array at location 3
     */
    public int getBottom() {
        int x = tile[3];
        return x;
    }//end getBottom

    /**
     * Returns the Right side of the NumberTile
     *
     * @return a copy of value for the array at location 2
     */
    public int getRight() {
        int x = tile[2];
        return x;
    }// end getRight

    public String toString() {
        // Returns a string representation of a NumberTile as
        //		7
        //	6		9
        //		                 

        String out = "| " + this.getTop() + " |" + "\n"
                + "|" + this.getLeft() + " " + this.getRight() + "|" + "\n"
                + "| " + this.getBottom() + " |";
        return out;


    }
}
