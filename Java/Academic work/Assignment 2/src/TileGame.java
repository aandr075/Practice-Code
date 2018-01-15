
/**
 *
 * @author Bill Kraynek
 * @editor Adam Andrade
 */
public class TileGame {

    private NumberTile[] board;

    public TileGame() {
        board = new NumberTile[1];
        board[0] = new NumberTile();

    }

    /**
     * Accessor for the board
     */
    public NumberTile[] getBoard() {
        NumberTile[] boardCopy = board;
        return boardCopy;// returns a copy of the NumberTile array object
    }// end getBoard

    /**
     * Constructs and returns a hand of 5 random NumberTiles
     */
    public NumberTile[] getHand() {
        NumberTile[] hand = new NumberTile[5];// sets size of NumberTile array to 5
        // for loop creats 5 new NumberTiles
        for (int i = 0; i < hand.length; i++) {
            hand[i] = new NumberTile();//each location in the array is given a NumberTile
        }
        return hand;// returns the hand
    }// end getHand
    
    /**
     * Constructs and returns a hand of x random NumberTiles 
     * @param x the custom size of the hand
     * @return the hand.
     */
    public NumberTile[] getHand(int x) {
        // sets size of NumberTile array to however many tiles are needed
        NumberTile[] hand = new NumberTile[x];
        // for loop creats 5 new NumberTiles
        for (int i = 0; i < hand.length; i++) {
            hand[i] = new NumberTile();//each location in the array is given a NumberTile
        }
        return hand;// returns the hand
    }// end getHand
    
    /**
     * Finds a suitable location for a Tile on the board
     * @param tile the tile that we will check for
     * @return the index for the location to place the tile into. 
     */
    public int getIndexForFit(NumberTile tile) {
        /* Calculates and returns the index in the board array of a position
         * to legally insert tile if one exits. If not return -1. Do not
         rotate tile in this method*/

        //for loop will check if the tile can be placed in each position
        for (int i = 0; i <= this.board.length; i++) {
            if (i > 0) { // if there is a tile before the current index
                if (tile.getLeft() != this.board[i - 1].getRight()) { //and if the tile sides don't match up
                    continue;// restart check in next index
                }
            }
            //if piece matches on left side, go on to check the right side
            if (i < this.board.length) {// if there is a tile to the right of the index
                if (tile.getRight() != this.board[i].getLeft()) {// and if the sides don't match up
                    continue;//move on to the next index
                }
            }
            return i;// if the sides are not mismatched for this index, return the index
        }// end for loop for comparing sides for each index            
        return -1;// no index found, return -1

    }//end getIndexForFit    

    /**
     * Inserts a tile if possible.
     * @param tile a number tile that will be placed.
     * @return whether a move can be made or not
     */
    public boolean insertTile(NumberTile tile) {
        // Insert tile into board at position returned by the getIndexForFit method
        // if position >= 0 and return true. Else return false. tile can be rotated
        // in this method

        //code to handle checking if tile fits and rotating it if not
        int rotation = 0;// used to check number of rotations
        // while no match is found, keep looking
        while (this.getIndexForFit(tile) == -1) {
            if (rotation < 3) {// as long as the number or rotations is under 3
                tile.rotate();//rotates the tile
                rotation += 1;//increments the rotation counter
            } else {//if it has been rotated 3 times and still no match, return false
                return false;
            }// end rotation and index checking                
        }//end while loop

        //to place tile on board
        //creates a board to copy the original into plus the tile being inserted
        NumberTile[] boardCopy = new NumberTile[this.board.length + 1];
        //records the location index in which to add the tile
        int index = this.getIndexForFit(tile);
        // this loop copies board into the board copy plus the inserted tile
        for (int i = 0; i < boardCopy.length; i++) {
            //for all tiles before the inserted one            
            if (i < index) {
                boardCopy[i] = board[i];// copy to boardCopy                
            }
            //if this is where the new tile is being inserted
            if (i == index) {
                boardCopy[i] = tile;//place a copy of the tile                
            }
            // this handles all the tiles after the inserted tile
            if (i >= index) {
                //so long as there are tiles left to copy
                if (i < board.length) {
                    boardCopy[i + 1] = board[i];//copy it to the boardCopy
                } else {
                    break;
                }// else exit the for loop
            }
        }//end tile insertion for loop
        this.board = boardCopy;//replace the old board with the new one   
        return true;// return confirmation that a piece can be placed 
    }//end insertTile()

    /**
     * This method makes an actual move from hand to board
     * @param hand The hand that is making the move
     * @return the hand after the play has been made
     */
    public NumberTile[] makeMove(NumberTile[] hand) {
        // This method make a move from hand to the board. This means if a tile can
        // be inserted call insertTile and remove tile from hand. If not the add a
        // a tile to hand.

        //this for loop checks if a move can be made and makes one
        for (int i = 0; i < hand.length; i++) {
            //if a move can be made and has been made with the tile hand[i]
            if (this.insertTile(hand[i])) {
                //creates a copy of the hand that is one smaller than the hand array
                NumberTile[] handCopy = new NumberTile[hand.length - 1];
                //for loop to copy all but played tile to the copy of hand[]
                for (int j = 0; j < handCopy.length; j++) {
                    //copies all tiles before the inserted tile
                    if (j < i) {
                        handCopy[j] = hand[j];// copy to boardCopy                         
                    } // this handles all the tiles after the inserted tile
                    else {
                        handCopy[j] = hand[j + 1];//copy it to the boardCopy
                    }
                }// end remove from hand for loop                
                return handCopy;//return new hand for the player
            }//end if tile has been inserted
        }// end move making for loop  

        //if no move can be made, draw a tile
        // creates an array one larger than the original to add a tile to
        NumberTile[] handCopy = new NumberTile[hand.length + 1];
        //loop to copy array values to new hand
        for (int i = 0; i < hand.length; i++) {
            handCopy[i] = hand[i];
        }
        //adds another piece to the hand
        handCopy[handCopy.length - 1] = new NumberTile();
        //returns new hand with extra tile
        return handCopy;
    }
    /**
     * shows an aspect of the game
     * @param tiles an array of tiles that will be displayed
     * @return the tiles "drawn" as a string
     */
    public String toString(NumberTile[] tiles) {
        // Return a string representation of tiles as
        //		7			8			1
        //	5		7	4		8	6		3
        //		7			4			3	
        String topRow = "";//top row of display
        String midRow = "";//middle row of display
        String botRow = "";// bottom row of display
        //for loop to build the display of the board one line at a time
        for (int i = 0; i < tiles.length; i++) {
            topRow += " | " + tiles[i].getTop() + " | ";
            midRow += " |" + tiles[i].getLeft() + " " + tiles[i].getRight() + "| ";
            botRow += " | " + tiles[i].getBottom() + " | ";
        }
        String out = topRow + "\n" + midRow + "\n" + botRow;//combines all rows into one string
        return out;// returns the string
    }
}
