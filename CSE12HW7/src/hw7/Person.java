/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */

package hw7;

public class Person {
    //Important Variables
    private String myName;
    private int myKey;

    //Constructor
    public Person(String name, int key){
            myName = name;
            myKey = key;      
    }

    //Setting myName variable
    public void setName(String name) {
        myName = name;
    }

    //Returning myName variable
    public String getName() {
        return myName;
    }

    //Returning myKey variable
    public int getKey() {
        return myKey;
    }

}
