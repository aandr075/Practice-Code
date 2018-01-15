/*
 * ==============================
 *  Assignment 2
 * ==============================
 * Author: Adam Andrade
 * Date: 1/30/13
 * Due: 1/31/13 11AM
 * I certify that this work was completed by the author listed above and not some other person:
 * 
 * _______________________________________________________________
 * Purpose:
 * 
 *  
 */

import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author KhaosKlub_2
 */
public class Assignment2 {
    //static "global" variables for use in various methods of this class

    static TileGame game;   //The board for the game     
    static NumberTile[] initial1; // Player 1's initial hand
    static NumberTile[] initial2; // player 2's initial hand
    static NumberTile[] player1; // player 1's current hand
    static NumberTile[] player2; // player 2's current hand       
    static String endMessage; // end game message
    static int wins1 = 0; // player 1's wins
    static int wins2 = 0; // player 2's wins
    static int ties = 0; // number of tie games

    public static void main(String[] args) throws IOException {

        playGame();// plays a single game
        displayTiles(); // displays the hands and the board

        //resets wins and ties
        wins1 = 0;
        wins2 = 0;
        ties = 0;
        //plays game 10,000 times
        for (int i = 0; i < 10000; i++) {
            playGame();
        }
        //displays the results of the 10,000 games
        JOptionPane.showMessageDialog(null,
                " Player 1 Wins: " + wins1
                + "\n Player 2 Wins: " + wins2
                + "\n Tie Games: " + ties, "Results of 10,000 Games!", -1);

    }

    /**
     * Plays an entire Tile game
     */
    public static void playGame() {
        game = new TileGame();//starts a new game
        player1 = game.getHand(); // creates a hand for player 1
        initial1 = player1; // stores player 1's initial hand for display purposes
        //repeats for player 2
        player2 = game.getHand();
        initial2 = player2;
        //while loop to play game until someone's hand is empty
        while (player1.length > 0 && player2.length > 0) {
            player1 = game.makeMove(player1);//player 1's move
            player2 = game.makeMove(player2);//player 2's move
        }//end of while loop
        if (player1.length == 0) {// if player 1's hand is empty at end of turn             
            if (player2.length == 0) {// check if player 2's hand is also empty
                ties += 1;// if so, the game is a tie
                endMessage = "This game is a Tie...";
            } else {//otherwise, only player 1 wins
                wins1 += 1;
                endMessage = "Player 1 is the Winner!";
            }
        } else {//and if player 1 didn't win, that means player 2 won
            wins2 += 1;
            endMessage = "Player 2 is the Winner!";
        }
    }

    /**
     * This method will display the initial and final hands as well as the final
     * game board
     */
    public static void displayTiles() {
        //displays data in an organized visual way
        String out = " Player 1 Initial hand:\n" + game.toString(initial1)
                + "\n\n Player 2 Initial hand:\n" + game.toString(initial2)
                + "\n\n Board: \n" + game.toString(game.getBoard())
                + "\n\n Player 1 Final hand:\n" + game.toString(player1)
                + "\n\n Player 2 Final hand:\n" + game.toString(player2)
                + "\n\n" + endMessage;
        //creates a JTextArea to house the results
        JTextArea showBoard = new JTextArea(out, 28, 35);
        //sets the font to monospaced for better formatting
        showBoard.setFont(new Font("monospaced", Font.PLAIN, 12));
        //displays results in a JOptionPane with Scroll bars
        JOptionPane.showMessageDialog(null, new JScrollPane(showBoard), "Sample Game", -1);
    }
}
