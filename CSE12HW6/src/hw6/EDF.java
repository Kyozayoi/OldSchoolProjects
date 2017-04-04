/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw6;

import java.io.*;
import java.util.Scanner;

public class EDF {

    /*
     Very important method, do not break
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public static void main(String[] args) {
        //Should only be accepting one file
        //If there are any more, exit the program
        if (args.length != 1) {
            System.err.println("Incorrect number of arguments: " + args.length);
            System.err.println("java hw6.EDF <input-file>");
            System.exit(1);
        }
        File file = new File(args[0]);
        //Initialize the Priority Queue with an initial capacity of 10
        MyPriorityQueue<Record> queue = new MyPriorityQueue<>(10);
        //setting the current time to 0
        long current_time = 0;
        try {
            if (file == null) {
                throw new FileNotFoundException();
            }
            //Create the scanner
            Scanner reader = new Scanner(file);
            //Continue loop until reader has read through entire file
            while (reader.hasNextLine()) {
                //Check first String value for schedule/run
                String command = reader.next();
                if (command.equals("schedule")) {
                    //Add record to queue appropriately
                    String process = reader.next();
                    int deadline = reader.nextInt();
                    int duration = reader.nextInt();
                    Record newRecord = new Record(process, deadline, duration);
                    queue.add(newRecord);
                    System.out.println(current_time + ": adding "
                            + newRecord.toString());
                } else if (command.equals("run")) {
                    //Check for multiple cases with run
                    int Time = reader.nextInt();
                    while (current_time != Time) {
                        //We continue performing actions while we have time
                        Record action = queue.poll();
                        System.out.println(current_time + ": busy with "
                                + action.toString());
                        //Check if the task has been completed
                        if (action.GetDuration() + current_time < Time) {
                            current_time = current_time + action.GetDuration();
                            System.out.println(current_time + ": done with "
                                    + action.toString(current_time));
                            continue;
                        //Check if time hasn't been completed    
                        } else if (action.GetDuration()
                                + current_time - Time > 0) {
                            //Update and add back into queue
                            Record reaction
                                    = new Record(action, (action.GetDuration()
                                            + current_time - Time));
                            queue.add(reaction);
                            System.out.println(Time + ": adding "
                                    + reaction.toString());
                        }
                        current_time = Time;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + file);
            System.exit(1);
        }
        System.exit(0);
    }
}
