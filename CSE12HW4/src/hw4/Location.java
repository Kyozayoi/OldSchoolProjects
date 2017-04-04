/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw4;

public class Location {

    protected int row;   //Essentially Marks X coordinate
    protected int column;//Essentially Marks Y coordinate

    public Location(int currRow, int currCol) {
        row = currRow;   //Assigns X coordinate
        column = currCol;//Assigns Y coordinate
    }

    public int getRow() {
        return row;     //Returns X coordinate
    }

    public int getColumn() {
        return column;  //Returns Y coordinate
    }

    /* LEFT, UP, RIGHT, DOWN */
    public Location left() {
        return new Location(row, column - 1);   //Returns location of Left
    }

    public Location up() {
        return new Location(row - 1, column);   //Returns location of Up
    }

    public Location right() {
        return new Location(row, column + 1);   //Returns location of Right
    }

    public Location down() {
        return new Location(row + 1, column);   //Returns location of Down
    }

}
