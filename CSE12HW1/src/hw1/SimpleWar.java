/*
 Anthony Shih
 A11295870
 cs12wei
 */
package hw1;

import java.util.LinkedList;
import java.util.Scanner;

public class SimpleWar {

    public static void main(String[] args) {
        //Use to mark which cards used. 1 means card has been used.
        int[][] usedCards = new int[13][4];
        //Counts how many cards have been used
        int cardsUsed = 0;
        //Scanner object that takes input from the user 
        Scanner playerInput = new Scanner(System.in);
        //Making CounterStat for end-game calculations
        CounterStat GM = new CounterStat(0, 0, 0);
        String[] Suits = {"Hearts", "Diamonds", "Spades", "Clubs"};//0,1,2,3 
        String[] Values = {"Two", "Three", "Four", "Five", "Six",//0,1,2,3,4
            "Seven", "Eight", "Nine", "Ten", "Jack",//5,6,7,8,9
            "Queen", "King", "Ace"};//10,11,12

        //stores computer moves
        LinkedList<String> computersMoves = new LinkedList<>();
        //stores users moves
        LinkedList<String> playersMoves = new LinkedList<>();

        int suitIndex, valueIndex;//Suit and value chosen randomly by a computer
        boolean playing = true;//Allows the game to continue
        while (playing) {//The loop that allows the game to go
            //Ends the game if the deck has been used up
            if (cardsUsed == 52) {
                System.out.println("The deck has been used up, "
                        + "ending the game");
                //Print the stats
                System.out.println("I won: "
                        + GM.averageGames(1)
                        + "%   You won: "
                        + GM.averageGames(0)
                        + "%   We tied: "
                        + GM.averageGames(2)
                        + "\n");
                //Print out all moves played. 
                //Stop at players because computers has one extra.
                while (!playersMoves.isEmpty()) {
                    System.out.println("My Moves: "
                            + computersMoves.pop()
                            + " Your Moves: "
                            + playersMoves.pop());
                }
                //Ask if they want to play again.
                System.out.print("Play again? ");
                String userInput = playerInput.next();
                switch (userInput) {
                    case "y":      //Case y: reset the game.
                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 4; j++) {
                                usedCards[i][j] = 0;
                            }
                        }
                        cardsUsed = 0;
                        System.out.println("");
                        continue;
                    case "n":      //Case n: exit the game.
                        System.out.println("Bye, see you later!");
                        playing = false;
                        break;
                }
            }
            suitIndex = (int) (Math.random() * (4));  //Get Computer's Suit
            valueIndex = (int) (Math.random() * (13));//Get Computer's Value
            //Check to make sure card is available
            while (usedCards[valueIndex][suitIndex] == 1) {
                suitIndex = (int) (Math.random() * (4));
                valueIndex = (int) (Math.random() * (13));
            }
            //Mark that the card has been used
            usedCards[valueIndex][suitIndex] = 1;
            cardsUsed++;
            //Add card to Computer's database
            computersMoves.add(Values[valueIndex] + " of "
                    + Suits[suitIndex]);

            //Print out Computer's card
            System.out.println("My card is: " + Values[valueIndex]
                    + " of " + Suits[suitIndex]);
            //Ask user to input value
            System.out.print("What is your card (kind)? "
                    + "(2­14, ­1 to finish the game): ");
            int numberInput = playerInput.nextInt();
            //Checks for input of -1, initiate end game sequence
            if (numberInput == -1) {
                System.out.println("I won: "
                        + GM.averageGames(1)
                        + "%   You won: "
                        + GM.averageGames(0)
                        + "%   We tied: "
                        + GM.averageGames(2)
                        + "\n"); //Print all these stats

                //Print out all moves played. 
                //Stop at players because computers has one extra.
                while (!playersMoves.isEmpty()) {
                    System.out.println("My Moves: "
                            + computersMoves.pop()
                            + " Your Moves: "
                            + playersMoves.pop());
                }
                //Ask if they want to play again.
                System.out.print("Play again? ");
                String charInput = playerInput.next();
                switch (charInput) {
                    case "y":      //Case y: reset the game.
                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 4; j++) {
                                usedCards[i][j] = 0;
                            }
                        }
                        cardsUsed = 0;
                        System.out.println("");
                        continue;
                    case "n":      //Case n: exit the game.
                        System.out.println("Bye, see you later!");
                        playing = false;
                        break;
                }
            }
            //Input is 2 more than card in Values
            int playerCard = numberInput - 2;
            //Randomize suit for the player
            suitIndex = (int) (Math.random() * (4));

            //Check that value is available
            while (usedCards[playerCard][0] == 1
                    && usedCards[playerCard][1] == 1
                    && usedCards[playerCard][2] == 1
                    && usedCards[playerCard][3] == 1) {
                //Ask for another value.
                System.out.print("There are no more suits of that card, "
                        + "please select another number: ");
                playerCard = playerInput.nextInt() - 2;
            }
            //Check that card is available
            while (usedCards[playerCard][suitIndex] == 1) {
                suitIndex = (int) (Math.random() * (4));
            }
            usedCards[playerCard][suitIndex] = 1;
            cardsUsed++;
            //Add card to player database
            playersMoves.add(Values[playerCard] + " of "
                    + Suits[suitIndex]);
            System.out.println(Values[playerCard] + " of "
                    + Suits[suitIndex]);

            //Compare player's and computer's cards
            if (playerCard < valueIndex) {
                System.out.println("I won" + "\n");
                GM.incrementComputerWins();
            } else if (playerCard > valueIndex) {
                System.out.println("You won" + "\n");
                GM.incrementUserWins();
            } else {
                System.out.println("A tie" + "\n");
                GM.incrementTies();
            }
        }
    }

}
