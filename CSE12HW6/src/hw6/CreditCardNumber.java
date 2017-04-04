public class CreditCardNumber {
//private instance variable for a String with 6 digits called issuerID (default is "000000").

    private String issuerID = "000000";
//private instance variable for a String with 9 digits called accountNum (default is "999999999").
    private String accountNum = "999999999";
//private instance variable for a one-digit int (default 0) called checkDigit
    private int checkDigit;

    /*a constructor which has 2 String parameters (one for issuerID, another for
     accountNum). If the parameters aren't null AND there are the correct number of
     characters in each String (6 and 9) AND each character in the string is a digit
     (should call another method to check for digits), assign the parameters to the
     String instance variables (otherwise, assign or leave the default values). Then call
     a method to calculate and assign the checkDigit (see details in method A. below)
     */
    public CreditCardNumber(String tempIssuerID, String tempAccountNum) {
        if (tempIssuerID != null && tempAccountNum != null && tempIssuerID.length() == 6 && tempAccountNum.length() == 9) {
            if (checkDigits(tempIssuerID) && checkDigits(tempAccountNum)) {
                issuerID = tempIssuerID;
                accountNum = tempAccountNum;
                checkDigit = calcCheckDigits();
            }
        }
    }

    //a default constructor (assign or leave the instance variables to the defaults)

    public CreditCardNumber() {
        //checkDigit = 0;
    }

    public boolean checkDigits(String temp) {
        for (int i = 0; i < temp.length(); i++) {
            char digit = temp.charAt(i);
            if (digit < '0') {
                return false;
            }
            if (digit > '9') {
                return false;
            }
        }
        return true;
    }

    //accessors, one for issuerID (String), one for accountNum (String), and one for checkDigit (int)

    public String getIssuerID() {
        return issuerID;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public int getCheckDigits() {
        return checkDigit;
    }

    private int calcCheckDigits() {
        /*a private method that has NO PARAMETERS, that assigns the appropriate value to
         checkDigit. To do this, call the method to calculate the check sum based on the
         Luhn algorithm (see details in the method C. specifications below), then assign the
         correct value to checkDigit so that the check sum + checkDigit is a multiple of 10
         (use the % operator).*/
        int sum;
        sum = checkSum();
        if ((sum + checkDigit) % 10 != 0) {
            checkDigit = sum - (sum % 10);
        }
        return checkDigit;

    }
    /*a public method with a String parameter for a new issuerID that creates a new valid
     credit card number. Follow the following rules:*/

    public void createCard(String tempIssuerID) {
        /*IF the parameter isn't null AND it's 6 characters long AND each char is a digit,
         assign the parameter to the instance variable for issuerID (otherwise LEAVE THE
         issuerID AS IT WAS BEFORE)*/
        if (tempIssuerID != null && tempIssuerID.length() == 6 && checkDigits(tempIssuerID)) {
            issuerID = tempIssuerID;
        } else {
            issuerID = "000000";
        }
        /*Randomly assign 9 digits with '0' through '9' (1 char at a time) to a LOCAL
         StringBuilder object (in a loop, using Math.random similar to Prog. HW 2 and 3)
         */
        StringBuilder tempString = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            tempString = tempString.append((Math.random() * 9));
        }
        /*Assign a String version the local StringBuilder to the accountNum instance
         variable*/
        accountNum = tempString.toString();
        //Call method A. to set the check digit
        checkDigit = calcCheckDigits();
    }

    /* private method (NO PARAMETERS) that calculates the "check sum" using the
     Luhn algorithm (BE SURE TO USE A LOCAL StringBuilder object here):*/
    private int checkSum() {
        /*Copy the chars in issuerID to the first 6 chars of the StringBuilder, and the chars
         in accountNum after that in the StringBuilder*/
        StringBuilder temp = new StringBuilder();
        int num;
        int sum = 0;
        /*Copy the chars in issuerID to the first 6 chars of the StringBuilder, and the chars
         in accountNum after that in the StringBuilder
         b. In each of the chars with an EVEN index (0, 2, 4, 6, etc.)
         i. get the int value from the char (for example, 7 from '7')
         ii. multiply it by 2 (for example 7*2, which is 14) (CONTINUED ===>)
         iii. if doubling it has 2 digits, add those digits (for example, 1 + 4, which is 5),
         otherwise, just use the doubled value (if it has one digit)
         iv. put above result back into the StringBuilder at the same index
         c. Add the values of each digit in the StringBuilder (remember to convert from char
         to int)
         d. Return the sum (in a return statement)*/
        for (int i = 0; i < issuerID.length(); i++) {
            temp.append(issuerID.length());
            for (int j = 0; j < accountNum.length(); j++) {
                temp.append(accountNum.length());
            }
        }
        for (int k = 0; k < temp.length(); k += 2) {
            num = temp.charAt(k) - '0';
            num *= 2;
            if (num >= 10) {
                num = 1 + (num % 10);
            }
            temp.setCharAt(k, (char) num);
        }
        for (int v = 0; v < temp.length(); v++) {
            sum += temp.charAt(v) - '0';
        }

        return sum;
    }
    /*a public method called toString (NO PARAMETERS) that returns (in a return
     statement) the issuerID, accountNum and checkDigit , BUT WITH A '-' BETWEEN
     EVERY 4 CHARACTERS! (don't change any of the instance variables here!)
     */

    public String toString() {
        String str = issuerID + accountNum + checkDigit;
        return str;
    }

}



/*
my shitty output
Please enter Issuer ID: 321321
Please enter Account Number: 654654654
The complete number from your input:
3213   2165   4654   654-   930
Please enter size of the array: 7
Please enter Issuer ID Number (6 Digits): 789789

Credit Card Number 0:
too much crap being displayed for each credit card
7897   896.   1374   5137   5465   4853   .090   6336   4158   9914   5.37   5415   4803   7323   44.6   1961   4035   4725   1152   .236   1266   0700   5416   73.1   5688   7676   5160   21.8   8559   7282   3223   24.3   1289   6277   7106   572.   5496   3655   6767   4912   -569   40
Credit Card Number 1:

7897   891.   5556   8599   4811   8777   0.24   1619   0836   9313   7780   .093   5797   7685   5065   021.   0730   1727   2906   5273   6.42   0001   0433   8649   34.5   4326   0382   3727   648.   8552   6782   3090   8956   .599   2210   6400   2211   .741   5603   0965   7665   -569   90
Credit Card Number 2:

7897   890.   7441   3949   8563   6625   7.48   0133   9738   7987   27.5   7766   3531   0230   35.0   8737   7196   6861   113.   3951   4727   1173   3834   8.77   2266   8053   3717   52.6   6326   3365   8418   145.   4766   0086   4733   2344   .633   8039   8618   3961   5-60   110
Credit Card Number 3:

7897   891.   7860   8732   8878   5504   3.13   7400   2820   7000   478.   9206   4165   4922   0656   .808   4923   2224   0366   2.09   2351   9993   0748   85.9   9256   9228   7950   894.   7164   4981   3586   6482   .252   4862   3221   1090   67.2   5178   9842   9888   32-5   9080   
Credit Card Number 4:

7897   892.   3641   1321   8209   620.   7009   8355   2207   6448   .629   9248   7035   5438   1.52   5214   4295   9862   878.   1652   6543   2146   9761   .324   3208   0632   7723   44.9   6435   9853   0407   355.   6389   8799   4099   238.   2358   3220   9146   546-   5798   0
Credit Card Number 5:

7897   897.   5795   2835   3835   4472   .930   2283   4460   1740   58.2   4600   4033   6907   996.   0502   0446   6847   6451   .539   3226   0328   9367   70.2   3857   8382   7898   9143   .574   8966   9397   7113   6.04   6982   1367   7027   57.4   9271   3153   7601   41-5   9080   
Credit Card Number 6:

7897   896.   9806   0696   0936   7505   7.64   3971   3681   6532   62.9   4034   9568   4731   993.   4492   3917   6425   1025   1.60   6040   0513   5808   373.   3313   8974   5864   7412   2.95   2443   1603   8402   45.0   8407   1938   1670   210.   8081   1367   4632   027-   5806   0

Get more credit card numbers? (n for no): 

What it should look like
Enter a credit card issuer number: 321321 
Enter an account number: 654654654 
The complete number from your input: 3213-2165-4654-6549 
Enter the number of elements in the array: 7 
Enter an issuer ID# (6 digits): 789789 
Credit Card # 0: 
7897-8931-4062-1219 
Credit Card # 1: 
7897-8920-2125-3522 
Credit Card # 2: 
7897-8971-9793-0944 
Credit Card # 3: 
7897-8979-3216-3090 
Credit Card # 4: 
7897-8995-0461-8493 
Credit Card # 5: 
7897-8948-8037-5909 
Credit Card # 6: 
7897-8966-0251-9953
*/