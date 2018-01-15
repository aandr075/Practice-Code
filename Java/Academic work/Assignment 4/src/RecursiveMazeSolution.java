/* ==============================
 *  Assignment 4
 * ==============================
 * Author: Adam Andrade
 * Date: 3/6/13
 * Due: 3/7/13 11AM
 * I certify that this work is built upon the IceCreamShopLite and was modified
 *  by only the Author listed above.
 * 
 * _______________________________________________________________
 * Purpose: To create a recursive method to provide an arraylist of doors that 
 * lead to the exit
 */

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Skeleton class that implements the interface MazeSolver
 *
 * @author Bill Kraynek
 * @modder Adam Andrade
 */
public class RecursiveMazeSolution implements MazeSolver {

    boolean[][] marked;//a 2d array that marks room coordinates as entered
    //arraylist to store maze doors that lead to target room
    ArrayList<Maze.Door> path;

    //method used to solve the maze
    //takes in the coordinates of the first room, ending room,
    //and the maze object
    public ArrayList<Maze.Door> solveMaze(int startRow, int finishRow,
            int startCol, int finishCol, Maze maze) {
        //sets the 2d array to the size of the maze
        //and also resets array each time maze is solved
        marked = new boolean[maze.getRows()][maze.getColumns()];
        //initializes/resets arraylist
        path = new ArrayList<Maze.Door>();
        //return the arraylist returned by pathFinder method
        return pathFinder(startRow, finishRow, startCol, finishCol, maze);
    } // end solveMaze

    //returns an arraylist of doors to destination
    public ArrayList<Maze.Door> pathFinder(int startRow, int finishRow,
            int startCol, int finishCol, Maze maze) {
        //sets current room to already visited
        marked[startRow][startCol] = true;

        //if the current room is the final room
        if (startRow == finishRow && startCol == finishCol) {
            //add the current room to the path arraylist
            path.add(0, new Maze.Door(startRow, startCol, maze.NO_DIRECTION, Color.red));
            return path;//return the path
        }//end base case check

        //if we can go east and have not been there already
        if (maze.isClosed(startRow, startCol, maze.EAST) == false
                && marked[startRow][startCol + 1] == false) {
            //check if the recursive function leads to a solution
            if (pathFinder(startRow, finishRow, startCol + 1, finishCol, maze) != null) {
                //if so, add this door to the path list
                path.add(0, new Maze.Door(startRow, startCol, maze.EAST, Color.red));
                return path;//return the path
            }//end recursive check
        }//end east door check

        //this code is identical to the east path above, except it checks south
        if (maze.isClosed(startRow, startCol, maze.SOUTH) == false
                && marked[startRow + 1][startCol] == false) {
            //recursive call from one room south of current
            if (pathFinder(startRow + 1, finishRow, startCol, finishCol, maze) != null) {
                path.add(0, new Maze.Door(startRow, startCol, maze.SOUTH, Color.red));
                return path;
            }//end recursive check
        }//end south door check

        //checks western room for possible solution
        if (maze.isClosed(startRow, startCol, maze.WEST) == false
                && marked[startRow][startCol - 1] == false) {
            //recursive call out west
            if (pathFinder(startRow, finishRow, startCol - 1, finishCol, maze) != null) {
                path.add(0, new Maze.Door(startRow, startCol, maze.WEST, Color.red));
                return path;
            }//end recursive check
        }//end west door check

        //checks if there is a solution to the north
        if (maze.isClosed(startRow, startCol, maze.NORTH) == false
                && marked[startRow - 1][startCol] == false) {
            //northern recursion
            if (pathFinder(startRow - 1, finishRow, startCol, finishCol, maze) != null) {
                path.add(0, new Maze.Door(startRow, startCol, maze.NORTH, Color.red));
                return path;
            }//end recursive check
        }//end north door check

        //if the room is not the final room, and there are no doors to new rooms
        return null;//return null
    } // end getPath
} // end MazeSolution

