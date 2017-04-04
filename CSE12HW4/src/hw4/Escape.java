/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw4;

public class Escape {

    /*
    Main method here we go
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public static void main(String[] args) {
        //String userInput = args[0];
        DarkRoom DR = new DarkRoom();
        DR.readFromFile("C:\\Users\\Anthony\\Documents\\NetBeansProjects\\CSE12HW4\\src\\hw4\\Room1_1.txt");
        DR.escapeDarkRoom("Stack");
        DR.clear();
        DR.escapeDarkRoom("Queue");
    }
}
