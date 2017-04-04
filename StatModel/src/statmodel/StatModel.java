import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StatModel {

    public static void main(String[] args) throws FileNotFoundException {
        Word[] wordBank = new Word[500];
        int placeHolder = 0;
        float totalFreq = 0;
        float theFreq = 0;
        int ts3n1 = 0;
        int ts3n2 = 0;
        float ts3n3 = 0;
        ArrayList<Word> theList = new ArrayList<>();
        float[] stockMarket = new float[7];
        String[] wordList = new String[500];

        Scanner textScanner1 = new Scanner(new File("hw4_vocab.txt"));
        Scanner textScanner2 = new Scanner(new File("hw4_unigram.txt"));
        Scanner textScanner3 = new Scanner(new File("hw4_bigram.txt"));

        while (textScanner1.hasNext()) {
            String input1 = textScanner1.next();
            int input2 = textScanner2.nextInt();
            totalFreq = totalFreq + input2;
            Word newWord = new Word(input1, placeHolder, input2);
            wordBank[placeHolder] = newWord;
            wordList[placeHolder] = input1;
            placeHolder++;
        }
        while (textScanner3.hasNext()) {
            ts3n1 = textScanner3.nextInt() - 1;
            ts3n2 = textScanner3.nextInt() - 1;
            ts3n3 = textScanner3.nextInt();
            wordBank[ts3n1].fWords[ts3n2] = ts3n3;
        }
        for (Word wordEntry : wordBank) {
            /*if(wordEntry.word.equals("THE")){
             for(int i = 0; i < 500; i++){
             wordBank[i].frequency = wordEntry.fWords[i] / (wordEntry.number);
             theList.add(wordBank[i]);
             }
             }            
             if(wordEntry.word.startsWith("A")){
             System.out.println(wordEntry.word + " " + wordEntry.frequency);
             }*/
            wordEntry.frequency = wordEntry.number / totalFreq;
            if (wordEntry.word.equals("<s>")) {
                stockMarket[0] = (float) Math.log10((.33) * (wordBank[3].frequency)
                        + (.67) * (wordEntry.fWords[3] / wordEntry.number));
            }
            if (wordEntry.word.equals("THE")) {
                stockMarket[1] = (float) Math.log10((.33) * (wordBank[498].frequency)
                        + (.67) * (wordEntry.fWords[498] / wordEntry.number));
            }
            if (wordEntry.word.equals("SIXTEEN")) {
                stockMarket[2] = (float) Math.log10((.33) * (wordBank[133].frequency)
                        + (.67) * (wordEntry.fWords[133] / wordEntry.number));
            }
            if (wordEntry.word.equals("OFFICIALS")) {
                stockMarket[3] = (float) Math.log10((.33) * (wordBank[499].frequency)
                        + (.67) * (wordEntry.fWords[499] / wordEntry.number));
            }
            if (wordEntry.word.equals("SOLD")) {
                stockMarket[4] = (float) Math.log10((.33) * (wordBank[443].frequency)
                        + (.67) * (wordEntry.fWords[443] / wordEntry.number));
            }
            if (wordEntry.word.equals("FIRE")) {
                stockMarket[5] = (float) Math.log10((.33) * (wordBank[488].frequency)
                        + (.67) * (wordEntry.fWords[488] / wordEntry.number));
            }
            /*if(wordEntry.word.equals("INSURANCE")){
             stockMarket[6] = (float) Math.log10(wordEntry.frequency);
             }
             if(wordEntry.word.equals("WEEK")){
             stockMarket[10] = (float) Math.log10(wordEntry.frequency);
             }*/
        }
        Collections.sort(theList);
        for (int j = 0; j < 10; j++) {
            System.out.println(theList.get(j).word);
        }
        for (int j = 0; j < 10; j++) {
            System.out.println(theList.get(j).frequency);
        }
        float totalLog = 0;
        for (float stocks : stockMarket) {
            totalLog = totalLog + stocks;
        }
        System.out.println(totalLog);
    }
}