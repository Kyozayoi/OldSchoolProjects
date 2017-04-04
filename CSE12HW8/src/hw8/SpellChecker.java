/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.util.ArrayList;

public class SpellChecker implements ISpellChecker {

    public HashTable dictionary;
    public static FileReader mainReader;
    public char[] alphaArray
            = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /*
     Constructor for the SpellChecker, initializes the all important
     HashTable.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    public SpellChecker() {
        dictionary = new HashTable();
    }

    /*
     Inserting all strings in the dictionary into the HashTable
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public void readDictionary(Reader reader) {
        BufferedReader betterReader = new BufferedReader(reader);
        try {
            //Reading through the entire file
            while (betterReader.ready()) {
                String word = betterReader.readLine();
                dictionary.insert(word);
            }
            betterReader.close();
        } catch (IOException ex) {
            System.err.println("Failed to read file");
            System.exit(1);
        }
    }

    /*
     Checking for spelling errors or the word provided. Dynamically 
     add related strings to a string arraylist, and convert that to an
     array afterwards.
     @author Anthony Shih
     @version 1.0
     @since 03-05-2016
     */
    @Override
    public String[] checkWord(String word) {
        ArrayList<String> similarsAL = new ArrayList();
        //We found the word
        if (dictionary.lookup(word) == true) {
            return null;
        } else {
            //Did not find word, find possible correct spellings
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    //Replacing characters with alphabet
                    char[] pieces = word.toCharArray();
                    pieces[i] = alphaArray[j];
                    String complete = new String(pieces);
                    if (dictionary.lookup(complete) && !complete.equals(word)) {
                        similarsAL.add(complete);
                    }
                }
            }
        }
        //Found strings go into a custom sized string array
        String[] similars = new String[similarsAL.size()];
        for (int k = 0; k < similars.length; k++) {
            similars[k] = similarsAL.get(k);
        }
        return similars;
    }

    public static void main(String[] args) // This is used for Part 2
    {
        File fileName = new File(args[1]);
        File dictFile = new File(args[0]);
        SpellChecker checker = new SpellChecker();
        try {
            mainReader = new FileReader(dictFile);
            checker.readDictionary(mainReader);
            mainReader = new FileReader(fileName);
            try (BufferedReader progReader = new BufferedReader(mainReader)) {
                while (progReader.ready()) {
                    String word = progReader.readLine();
                    String[] results = checker.checkWord(word);
                    if (results != null) {
                        System.out.print(word + ": ");
                        for (int i = 0; i < results.length - 1; i++) {
                            System.out.print(results[i] + ", ");
                        }
                        if (results.length > 0) {
                            System.out.println(results[results.length - 1]);
                        } else {
                            System.out.println();
                        }
                    }

                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open " + fileName);
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Cannot read the file");
            System.exit(1);
        }
    }
}
