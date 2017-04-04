/*
 Anthony Shih
 A11295870
 cs12wei
 */

package hw1;

/**
 * A class that implements a simple statistic tracking array
 *
 * @since 2016-01-04
 * @version 1.0
 * @author Anthony Shih
 */
public class CounterStat {

//private array of size 3
    private int[] Stat = new int[3];

    /**
     * creates an empty array
     */
    public CounterStat() {

    }

    /**
     * Creates an array with specified values
     *
     * @param first: assigned to the first cell
     * @param second: assigned to the second cell
     * @param third: assigned to the third cell
     */
    public CounterStat(int first, int second, int third) {
        Stat[0] = first;
        Stat[1] = second;
        Stat[2] = third;
    }

    /**
     * Calculates the number of games played
     *
     * @return The total number of played games
     */
    public int getTotalGames() {
        return Stat[0] + Stat[1] + Stat[2];
    }

    /**
     * Increments the number of games won by a user
     */
    public void incrementUserWins() {
        Stat[0]++;
    }

    /**
     * Increments the number of games won by a computer
     */
    public void incrementComputerWins() {
        Stat[1]++;
    }

    /**
     * Increments the number of ties between a user and a computer
     */
    public void incrementTies() {
        Stat[2]++;
    }

    /**
     *
     * @param choice: depending on the value of choice the corresponding average
     * (percent) is returned: if choice is 0, return the average for a user if
     * choice is 1, return the average for a computer if choice is 2, return the
     * average for ties otherwise return -1
     * @return percentage of won games or ties, depending on the parameter
     * choice.
     */
    public int averageGames(int choice) {
        int value = 0;
        switch (choice) {
            case 0:
                value = Stat[0] * 100 / this.getTotalGames();
                break;
            case 1:
                value = Stat[1] * 100 / this.getTotalGames();
                break;
            case 2:
                value = Stat[2] * 100 / this.getTotalGames();
                break;
            default:
                value = -1;
                break;
        }
        return value;
    }

    /**
     * Prints the statistics message
     */
    public void printStats() {
        System.out.println("I won: " + 
                averageGames(0) + 
                "%   You won: " + 
                averageGames(1) + 
                "%    We tied: " + 
                averageGames(2) + "%\n");
    }

    /**
     * Resets the statistic array if the player wants to play again
     */
    public void reset() {
        Stat[0] = 0;
        Stat[1] = 0;
        Stat[2] = 0;

    }

    public void resetWrong() {
        Stat[0] = 1;
        Stat[1] = 98;
        Stat[2] = 1;
    }

}
