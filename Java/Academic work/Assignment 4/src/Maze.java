
import java.io.Serializable;
import java.awt.Color;

/*
 * A class that describes a rectangular maze of rooms.
 * @author Bill Kraynek
 */
public class Maze implements Serializable {

    /**
     * the north wall of a room
     */
    public static final int NORTH = 0;
    /**
     * the east wall of a room
     */
    public static final int EAST = 1;
    /**
     * the south wall of a room
     */
    public static final int SOUTH = 2;
    /**
     * the west wall of a room
     */
    public static final int WEST = 3;
    /**
     * No direction from a room
     */
    public static final int NO_DIRECTION = 4;
    private static String[] walls = {"North", "East", "South", "West"};
    private Room[][] rooms;

    /**
     * Constructor
     * @param rows is the number of rows in the maze
     * @param columns is the number of columns in the maze
     */
    public Maze(int rows, int columns) {
        rooms = new Room[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rooms[i][j] = new Room();
            } // end for
        } // end for
    }

    /**
     * rows accessor
     * @return the number of rows in the maze
     */
    public int getRows() {
        return rooms.length;
    }

    /**
     * columns accessor
     * @return the number of columns in the maze
     */
    public int getColumns() {
        return rooms[0].length;
    }

    /**
     * Checks to see if a wall is closed
     * @param row the row number
     * @param column the column number
     * @param wall the wall number
     * @return true if wall is closed; false if it is open
     */
    public boolean isClosed(int row, int column, int wall) {
        return rooms[row][column].closed[wall];
    }

    /**
     * Opens the wall
     * @param row the row number
     * @param column the column number
     * @param wall the wall number
     */
    public void setOpen(int row, int column, int wall) {
        rooms[row][column].closed[wall] = false;
    }

    /**
     * Closes the wall
     * @param row the row number
     * @param column the column number
     * @param wall the wall number
     */
    public void setClosed(int row, int column, int wall) {
        rooms[row][column].closed[wall] = true;
    }

    public static class Door {

        int row;
        int column;
        int wall;
        Color color;

        public Door(int row, int column, int wall, Color color) {
            this.row = row;
            this.column = column;
            this.wall = wall;
            this.color = color;
        } // end constructor

        public boolean equals(Object x) {
            if ( x == null ) return false;
            if (!(x.getClass().equals(this.getClass()))) {
                return false;
            }
            Door door = (Door) x;
            return row == door.row && column == door.column && wall == door.wall && color.equals(door.color);
        } // end equal

        public int hashCode() {
            return row + column + wall + color.hashCode();
        }

        public String toString() {
            return row + " " + column + " " + walls[wall] + "\n";
        } // end toString()
    } // end Door

    private class Room implements Serializable {

        boolean[] closed;

        Room() {
            closed = new boolean[4];
            for (int i = 0; i < 4; i++) {
                closed[i] = true;
            } // end for
        }
    } // end Room
} // end Maze

