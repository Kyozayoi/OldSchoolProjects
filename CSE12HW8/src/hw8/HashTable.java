/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw8;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class HashTable implements IHashTable {

    public String[] HashArray;
    public int tableSize;
    public float loadFactor;
    public int currentSize;
    private int expandCount;
    private int collisionCount;
    private FileWriter writer1 = null;
    private PrintWriter writer2 = null;
    boolean madeText = false;

    /*
     Constructor for a new HashTable.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    public HashTable() {
        tableSize = 31;
        HashArray = new String[tableSize];
        currentSize = 0;
        loadFactor = 31 / 2f;
        expandCount = 0;
        collisionCount = 0;
    }

    /*
     Insertion method. Generate the Hash of the given string and insert it into
     the array. If there already exists a value in the bucket use double hashing
     to find another bucket.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public boolean insert(String value) {
        int loc = hashFunction(value);
        int checkSize = 0;
        //If the first location is available, then put the value in it.
        if (HashArray[loc] == null) {
            HashArray[loc] = value;
            currentSize++;
            return true;
        } //Double Hash to find next available location.
        else {
            while (HashArray[loc] != null) {
                int probe = 1;
                collisionCount++;
                if (HashArray[loc].equals(value)) {
                    return false;
                }
                if (HashArray[loc].equals("")) {
                    HashArray[loc] = value;
                    currentSize++;
                    return true;
                }
                loc = (loc + probe * doubleHashFunction(value)) % tableSize;
                probe++;
                checkSize++;
                if (checkSize == tableSize) {
                    return false;
                }
            }
            HashArray[loc] = value;
            currentSize++;
            if (currentSize > loadFactor) {
                reHash();
            }
            return true;
        }
    }

    /*
     Deletion method. Generate the Hash of the value that we want to delete, 
     and search through the Hash the same way we would insert. If the value is
     incorrect, keep doubleHashing until we either search through the entire 
     hashTable or find the value and delete.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public boolean delete(String value) {
        int posLoc = hashFunction(value);
        int checkCount = 0;
        //If the key doesn't have a value in the first place.
        if (HashArray[posLoc] == null) {
            return false;
        }
        //Search through the entire HashTable
        while (!HashArray[posLoc].equals(value)) {
            int probe = 1;
            checkCount++;
            if (checkCount == tableSize || HashArray[posLoc] == null) {
                return false;
            }
            posLoc = (posLoc + probe * doubleHashFunction(value)) % tableSize;
            if (HashArray[posLoc] == null) {
                return false;
            }
            probe++;
        }
        //Assuming the loop ends, that means we found the 
        HashArray[posLoc] = "";
        currentSize--;
        return true;
    }

    /*
     Lookup method. Similar to delete, we iterate through the hashTable via
     the hash functions to find our value. 
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public boolean lookup(String value) {
        int posLoc = hashFunction(value);
        int checkCount = 0;
        //If the first hash has nothing in it.
        if (HashArray[posLoc] == null) {
            return false;
        }
        //Iterate through the hashTable
        while (!HashArray[posLoc].equals(value)) {
            int probe = 1;
            checkCount++;
            if (checkCount == tableSize || HashArray[posLoc] == null) {
                return false;
            }
            posLoc = (posLoc + probe * doubleHashFunction(value)) % tableSize;
            if (HashArray[posLoc] == null) {
                return false;
            }
            if (checkCount == currentSize) {
                return false;
            }
            probe++;
        }
        //If the loop ends, that means we found it. Return statement.
        return true;
    }

    /*
     Print method. Goes through the list top down and prints out the key
     and the values, if they have any.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public void print() {
        for (int i = 0; i < tableSize; i++) {
            if (HashArray[i] != null) {
                System.out.println(i + ": " + HashArray[i]);
            } else {
                System.out.println(i + ":");
            }
        }
    }

    /*
     Helper method to create the first hash.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    private int hashFunction(String key) {
        int hashVal = 0;
        for (int j = 0; j < key.length(); j++) {
            int letter = key.charAt(j) - 96;
            hashVal = (hashVal * 26 + letter) % tableSize;
        }
        if (hashVal < 0) {
            return hashVal * -1;
        }
        return hashVal;
    }

    /*
     Helper method to take care of doubleHashing
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    private int doubleHashFunction(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            int leftShiftedValue = hashValue << 5;
            int rightShiftedVale = hashValue >> 27;
            hashValue = (leftShiftedValue | rightShiftedVale) ^ key.charAt(i);
        }
        if (hashValue < 0) {
            return (hashValue * -1) % tableSize;
        } else {
            return hashValue % tableSize;
        }
    }

    /*
     Helper method to take care of reHashing.
     Replacing the current hashArray and deleteArray
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    private void reHash() {
        tableSize = tableSize * 2;
        String[] replaceHA = new String[tableSize];
        boolean[] replaceDA = new boolean[tableSize];
        loadFactor = loadFactor * 2;
        for (int i = 0; i < HashArray.length; i++) {
            if (HashArray[i] == null) {
            } else if (HashArray[i].equals("")) {
                replaceHA[i] = HashArray[i];
            } else {
                int newLoc = hashFunction(HashArray[i]);
                while (replaceHA[newLoc] != null) {
                    int probe = 1;
                    newLoc = (newLoc + probe
                            * doubleHashFunction(HashArray[i])) % tableSize;
                    probe++;
                }
                replaceHA[newLoc] = HashArray[i];
            }
        }
        expandCount++;
        writeToStats();
        madeText = true;
        HashArray = replaceHA;
    }

    private void writeToStats() {
        try {
            writer1 = new FileWriter("stats.txt", madeText);
            writer2 = new PrintWriter(writer1);
            writer2.println(expandCount + " resizes, load factor "
                    + loadFactor + ", " + collisionCount + " collisions");
            writer2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create file");
        } catch (IOException ex) {
            System.out.println("Cannot write file");
        }

    }
}
