/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DarkRoom implements DarkRoomInterface {

    protected char[][] darkRoom;
    protected int numRows = 0;
    protected int numCols;
    protected int numVisited;

    public void readFromFile(String fname) {

        String line;                //Our String
        BufferedReader inputStrem;  //Our Reader
        StringTokenizer st;         //Iterates through the string

        try {                       //Trying
            int currentRow = 0;     //Start from beginning
            //Initialize
            inputStrem = new BufferedReader(new FileReader(fname));
            //Loop through entire file
            while ((line = inputStrem.readLine()) != null) {
                if (numRows == 0) { //Starting code
                    st = new StringTokenizer(line);
                    numRows = Integer.parseInt(st.nextToken());
                    numCols = Integer.parseInt(st.nextToken());
                    darkRoom = new char[numRows][numCols]; //char array
                } else if (line.length() == 1) {    //Ending loop
                    break;
                } else {    //Main loop, prints out the room
                    for (int c = 0; c < numCols; c++) {
                        darkRoom[currentRow][c] = line.charAt(c);
                    }
                    currentRow++;
                }
            }
        } catch (IOException e) {   //Catching IOException
            System.out.println(e.toString());   //Big error
            System.out.println("Could not find file " + fname);
        }

    }


    /*
     Method that returns the Location of "start"
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public Location findStart() {
        Location start = null;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (darkRoom[i][j] == 'S') {
                    start = new Location(i, j);
                }
            }
        }
        return start;
    }


    /*
     Method that checks if the goal was found
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean isDoor(Location loc) {
        return (darkRoom[loc.getRow()][loc.getColumn()] == 'D');
    }

    /*
     Method that checks if you can move
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean canMove(Location loc) {
        if (darkRoom[loc.left().getRow()]
                [loc.left().getColumn()] == ' ') {
            return true;
        } else if (darkRoom[loc.up().getRow()]
                [loc.up().getColumn()] == ' ') {
            return true;
        } else if (darkRoom[loc.right().getRow()]
                [loc.right().getColumn()] == ' ') {
            return true;
        } else if (darkRoom[loc.down().getRow()]
                [loc.down().getColumn()] == ' ') {
            return true;
        } else {
            return false;
        }
    }

    /*
     Helper method to check if the door is nearby
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean doorNear(Location loc) {
        if (darkRoom[loc.left().getRow()][loc.left().getColumn()] == 'D'
            || darkRoom[loc.up().getRow()][loc.up().getColumn()] == 'D'
            || darkRoom[loc.right().getRow()][loc.right().getColumn()] == 'D'
            || darkRoom[loc.down().getRow()][loc.down().getColumn()] == 'D') {
            return true;
        }
        return false;
    }

    /*
     Marks explored (visited) positions
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void markVisited(Location loc) {
        darkRoom[loc.getRow()][loc.getColumn()] = '.';
        numVisited++;
    }

    /*
     counts the number of visited positions
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int countVisited() {
        return numVisited;
    }

    /*
     removes marks from visiting (removes '.')
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void clear() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (darkRoom[i][j] == '.') {
                    darkRoom[i][j] = ' ';
                }
            }
        }
        numVisited = 0;
    }

    /*
     prints your array that represents a room
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void printRoom() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(darkRoom[i][j]);
            }
            System.out.println();
        }
    }

    /*
     Getting the hell out of here
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void escapeDarkRoom(String choice) {
        Stack_QueueInterface<Location> movesMade;
        if (choice.equals("Stack")) {
            movesMade = new MyStack<Location>();
        } else {
            movesMade = new MyQueue<Location>();
        }
        Location start = findStart();
        int stepsTaken = 0;
        while (!isDoor(start)) {
            movesMade.addElement(start);
            if (doorNear(start)) {
                if (darkRoom[start.left().getRow()]
                        [start.left().getColumn()] == 'D') {
                    stepsTaken++;
                    start = start.left();
                    break;
                } else if (darkRoom[start.up().getRow()]
                        [start.up().getColumn()] == 'D') {
                    stepsTaken++;
                    start = start.up();
                    break;
                } else if (darkRoom[start.right().getRow()]
                        [start.right().getColumn()] == 'D') {
                    stepsTaken++;
                    start = start.right();
                    break;
                } else if (darkRoom[start.down().getRow()]
                        [start.down().getColumn()] == 'D') {
                    stepsTaken++;
                    start = start.down();
                    break;
                }
            }
            if (canMove(start)) {
                if (darkRoom[start.left().getRow()]
                        [start.left().getColumn()] == ' ') {
                    markVisited(start.left());
                    start = start.left();
                } else if (darkRoom[start.up().getRow()]
                        [start.up().getColumn()] == ' ') {
                    markVisited(start.up());
                    start = start.up();
                } else if (darkRoom[start.right().getRow()]
                        [start.right().getColumn()] == ' ') {
                    markVisited(start.right());
                    start = start.right();
                } else if (darkRoom[start.down().getRow()]
                        [start.down().getColumn()] == ' ') {
                    markVisited(start.down());
                    start = start.down();
                }
            } else {
                while (!canMove(start)) {
                    start = movesMade.removeElement();
                }
            }
            stepsTaken++;
        }
        printRoom();
        int leftOvers = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (darkRoom[i][j] == ' ') {
                    leftOvers++;
                }
            }
        }
        printGoal(choice, stepsTaken, leftOvers);
    }

    /*
     Print out the final result of DarkRoom
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void printGoal(String choice, int stepsTaken, int positionsLeft) {
        System.out.println("Goal found (with " + choice + "): It took "
                + stepsTaken + " explored positions");
        System.out.println("There is (are) " + positionsLeft
                + " position(s) left to explore in " + choice);

    }

}
