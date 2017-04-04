import java.util.Scanner;

/**
 *
 * @author Anthony
 */
public class Prog4 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
		//Call a static method in this Prog4 class that returns a CreditCardNumber object based
        //on user input (see 1. below) and assign to the CreditCardNumber object variable.

        CreditCardNumber[] objArray;
        CreditCardNumber objCred = getUserInput();
		//Display the object variable using toString for the object variable with a label,
        //something like "The complete number from your input: "
        displayCred("The complete number from your input:", objCred);
		//Call a static method in this Prog4 class that returns an array of CreditCardNumber
        //objects based on user input (see 2. below).
        // Call a static method in this Prog4 class that displays all the elements in the array of
        //CreditCardNumbers. (see 3. below)
        objArray = getInputArray();
        displayArray(objArray);
        tryAnother(objArray);
    }

    public static CreditCardNumber getUserInput() {
        /*In the method that returns a CreditCardNumber object, prompt the user for a 6-digit
         issuer ID number, AND a 9-digit account number, reading into local Strings.
         Instantiate a CreditCardNumber object passing the local Strings and return it in a
         return statement*/
        String ID;
        String accountNum;
        CreditCardNumber userNum;
        System.out.printf("Please enter Issuer ID: ");
        ID = scanner.nextLine();
        System.out.printf("Please enter Account Number: ");
        accountNum = scanner.nextLine();
        userNum = new CreditCardNumber(ID, accountNum);
        return userNum;
    }

    public static void displayCred(String ch, CreditCardNumber cred) {
        System.out.println(ch);
        System.out.println(cred.toString().replaceAll(".{4}", "$0-"));
    }

    public static CreditCardNumber[] getInputArray() {
        /*In the method that returns an array of CreditCardNumber objects, first prompt the
         user and read a size for the array (assign to 1 if < 1), allocate memory for size
         elements, prompt the user and read a 6-digit issuer ID# into a local String. For each
         element (in a for loop), instantiate a default CreditCardNumber, assign to an element
         in the array, then call method B on the array element (passing the issuer ID# you read
         in this method). Return the array in a return statement.*/
        int size;
        CreditCardNumber[] tempArray;
        String tempID;

        System.out.printf("Please enter size of the array: ");
        size = scanner.nextInt();
        if (size < 1) {
            size = 1;
        }
        tempArray = new CreditCardNumber[size];
        System.out.printf("Please enter Issuer ID Number (6 Digits): ");
        tempID = scanner.next();
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = new CreditCardNumber();
            tempArray[i].createCard(tempID);
        }
        return tempArray;
    }

    public static void displayArray(CreditCardNumber[] objArray) {
        //In the method that displays the array, display to System.out each element of the array with a label like "Credit Card #=" (call toString for each element). 
        for (int i = 0; i < objArray.length; i++) {
            displayCred("Credit Card Number " + i + ":" + '\n', objArray[i]);
        }
        System.out.println();
    }

    public static boolean tryAnother(CreditCardNumber[] cred) {
        String s;
        System.out.printf("Get more credit card numbers? (n for no): ");
        s = scanner.next();
        if (s.charAt(0) != 'N' && s.charAt(0) != 'n') {
            cred = getInputArray();
            displayArray(cred);
            if (s.charAt(0) != 'N' && s.charAt(0) != 'n') {
                tryAnother(cred);
            }
        }
        return false;
    }
}