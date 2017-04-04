import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HangmanAI {

    public static void main(String[] args) throws FileNotFoundException {
        float totalCount = 0;
        float totalFreq = 0;

        float[] letters = new float[26];

        ArrayList<LetterNode> letterBank = new ArrayList<>();
        ArrayList<WordNode> wordBank = new ArrayList<>();
        Scanner textScanner = new Scanner(new File("hw2_word_counts_05.txt"));
        while (textScanner.hasNext()) {
            String wordName = textScanner.next();
            int wordCount = textScanner.nextInt();
            String left = wordName.substring(0, 2);
            String midpart = wordName.substring(1, 4);
            String right = wordName.substring(3);
            //if((wordName.indexOf('A') < 0) && (wordName.indexOf('O') < 0)){          
            //if(wordName.indexOf('B') == 0 && (midpart.indexOf('B') < 0) && (midpart.indexOf('E') < 0) 
            //&& wordName.indexOf('E') == 4 && wordName.indexOf('R') < 0){           
            //if(wordName.indexOf('H') == 2 && left.indexOf('H') < 0 && right.indexOf('H') < 0 
            //&& (wordName.indexOf('E') < 0) && (wordName.indexOf('I') < 0) && (wordName.indexOf('M') < 0) && 
            //(wordName.indexOf('N') < 0) && (wordName.indexOf('T') < 0)){
            //if((wordName.indexOf('E') < 0) && (wordName.indexOf('O') < 0)){    
            //if(wordName.indexOf('D') == 0 && wordName.indexOf('I') == 3 && wordName.indexOf('A') < 0){
            //if(wordName.indexOf('U') == 1 && (wordName.indexOf('A') < 0) && (wordName.indexOf('E') < 0) && 
            //(wordName.indexOf('I') < 0) && (wordName.indexOf('O') < 0) && (wordName.indexOf('S') < 0)){
            totalCount = totalCount + wordCount;
            WordNode wNode = new WordNode(wordName, wordCount);
            System.out.println(wordName);
            wordBank.add(wNode);
            
            char l1 = wordName.charAt(0);
            char l2 = wordName.charAt(1);
            char l3 = wordName.charAt(2);
            char l4 = wordName.charAt(3);
            char l5 = wordName.charAt(4);
            
            letters[l1 - 65] += wordCount;
            if(l2 != l1){
                letters[l2 - 65] += wordCount;
            }
            if(l3 != l1 && 
                    l3 != l2){
                letters[l3 - 65] += wordCount;            
            }
            if(l4 != l1 && l4 != l2 && l4 != l3
                    ){
                letters[l4 - 65] += wordCount;            
            }
            if(l5 != l1 && l5 != l2 && l5 != l3 && 
                    l5 != l4){
                letters[l5 - 65] += wordCount;            
            }
            
            totalFreq += (wordCount);
            //}
        }
        for (WordNode wordEntry : wordBank) {
            wordEntry.frequency = wordEntry.count / totalCount;
                
        }
        for (int k = 0; k < 26; k++) {
            float letFreq = letters[k];
            switch (k) {
                case 0:
                    LetterNode newA = new LetterNode('A');
                    newA.frequency = letFreq / totalFreq;
                    letterBank.add(newA);
                    break;
                case 1:
                    LetterNode newB = new LetterNode('B');
                    newB.frequency = letFreq / totalFreq;
                    letterBank.add(newB);
                    break;
                case 2:
                    LetterNode newC = new LetterNode('C');
                    newC.frequency = letFreq / totalFreq;
                    letterBank.add(newC);
                    break;
                case 3:
                    LetterNode newD = new LetterNode('D');
                    newD.frequency = letFreq / totalFreq;
                    letterBank.add(newD);
                    break;
                case 4:
                    LetterNode newE = new LetterNode('E');
                    newE.frequency = letFreq / totalFreq;
                    letterBank.add(newE);
                    break;
                case 5:
                    LetterNode newF = new LetterNode('F');
                    newF.frequency = letFreq / totalFreq;
                    letterBank.add(newF);
                    break;
                case 6:
                    LetterNode newG = new LetterNode('G');
                    newG.frequency = letFreq / totalFreq;
                    letterBank.add(newG);
                    break;
                case 7:
                    LetterNode newH = new LetterNode('H');
                    newH.frequency = letFreq / totalFreq;
                    letterBank.add(newH);
                    break;
                case 8:
                    LetterNode newI = new LetterNode('I');
                    newI.frequency = letFreq / totalFreq;
                    letterBank.add(newI);
                    break;
                case 9:
                    LetterNode newJ = new LetterNode('J');
                    newJ.frequency = letFreq / totalFreq;
                    letterBank.add(newJ);
                    break;
                case 10:
                    LetterNode newK = new LetterNode('K');
                    newK.frequency = letFreq / totalFreq;
                    letterBank.add(newK);
                    break;
                case 11:
                    LetterNode newL = new LetterNode('L');
                    newL.frequency = letFreq / totalFreq;
                    letterBank.add(newL);
                    break;
                case 12:
                    LetterNode newM = new LetterNode('M');
                    newM.frequency = letFreq / totalFreq;
                    letterBank.add(newM);
                    break;
                case 13:
                    LetterNode newN = new LetterNode('N');
                    newN.frequency = letFreq / totalFreq;
                    letterBank.add(newN);
                    break;
                case 14:
                    LetterNode newO = new LetterNode('O');
                    newO.frequency = letFreq / totalFreq;
                    letterBank.add(newO);
                    break;
                case 15:
                    LetterNode newP = new LetterNode('P');
                    newP.frequency = letFreq / totalFreq;
                    letterBank.add(newP);
                    break;
                case 16:
                    LetterNode newQ = new LetterNode('Q');
                    newQ.frequency = letFreq / totalFreq;
                    letterBank.add(newQ);
                    break;
                case 17:
                    LetterNode newR = new LetterNode('R');
                    newR.frequency = letFreq / totalFreq;
                    letterBank.add(newR);
                    break;
                case 18:
                    LetterNode newS = new LetterNode('S');
                    newS.frequency = letFreq / totalFreq;
                    letterBank.add(newS);
                    break;
                case 19:
                    LetterNode newT = new LetterNode('T');
                    newT.frequency = letFreq / totalFreq;
                    letterBank.add(newT);
                    break;
                case 20:
                    LetterNode newU = new LetterNode('U');
                    newU.frequency = letFreq / totalFreq;
                    letterBank.add(newU);
                    break;
                case 21:
                    LetterNode newV = new LetterNode('V');
                    newV.frequency = letFreq / totalFreq;
                    letterBank.add(newV);
                    break;
                case 22:
                    LetterNode newW = new LetterNode('W');
                    newW.frequency = letFreq / totalFreq;
                    letterBank.add(newW);
                    break;
                case 23:
                    LetterNode newX = new LetterNode('X');
                    newX.frequency = letFreq / totalFreq;
                    letterBank.add(newX);
                    break;
                case 24:
                    LetterNode newY = new LetterNode('Y');
                    newY.frequency = letFreq / totalFreq;
                    letterBank.add(newY);
                    break;
                case 25:
                    LetterNode newZ = new LetterNode('Z');
                    newZ.frequency = letFreq / totalFreq;
                    letterBank.add(newZ);
                    break;
            }
            System.out.println(letFreq + " " + " " + totalFreq + " " + letFreq / totalFreq);
        }
        Collections.sort(wordBank);
        Collections.sort(letterBank);

        System.out.println(letterBank.get(letterBank.size() - 1).letter + " " + letterBank.get(letterBank.size() - 1).frequency);

        /*
         System.out.println("Fifteen Most Frequent: ");
         System.out.println(wordBank.get(wordBank.size() - 1).word + " " + wordBank.get(wordBank.size() - 1).frequency);
         System.out.println(wordBank.get(wordBank.size() - 2).word + " " + wordBank.get(wordBank.size() - 2).frequency);
         System.out.println(wordBank.get(wordBank.size() - 3).word + " " + wordBank.get(wordBank.size() - 3).frequency);
         System.out.println(wordBank.get(wordBank.size() - 4).word + " " + wordBank.get(wordBank.size() - 4).frequency);
         System.out.println(wordBank.get(wordBank.size() - 5).word + " " + wordBank.get(wordBank.size() - 5).frequency);
         System.out.println(wordBank.get(wordBank.size() - 6).word + " " + wordBank.get(wordBank.size() - 6).frequency);
         System.out.println(wordBank.get(wordBank.size() - 7).word + " " + wordBank.get(wordBank.size() - 7).frequency);
         System.out.println(wordBank.get(wordBank.size() - 8).word + " " + wordBank.get(wordBank.size() - 8).frequency);
         System.out.println(wordBank.get(wordBank.size() - 9).word + " " + wordBank.get(wordBank.size() - 9).frequency);
         System.out.println(wordBank.get(wordBank.size() - 10).word + " " + wordBank.get(wordBank.size() - 10).frequency);
         System.out.println(wordBank.get(wordBank.size() - 11).word + " " + wordBank.get(wordBank.size() - 11).frequency);
         System.out.println(wordBank.get(wordBank.size() - 12).word + " " + wordBank.get(wordBank.size() - 12).frequency);
         System.out.println(wordBank.get(wordBank.size() - 13).word + " " + wordBank.get(wordBank.size() - 13).frequency);
         System.out.println(wordBank.get(wordBank.size() - 14).word + " " + wordBank.get(wordBank.size() - 14).frequency);
         System.out.println(wordBank.get(wordBank.size() - 15).word + " " + wordBank.get(wordBank.size() - 15).frequency);
        
         System.out.println("Fifteen Least Frequent: ");
         System.out.println(wordBank.get(0).word + " " + wordBank.get(0).frequency);
         System.out.println(wordBank.get(1).word + " " + wordBank.get(1).frequency);
         System.out.println(wordBank.get(2).word + " " + wordBank.get(2).frequency);
         System.out.println(wordBank.get(3).word + " " + wordBank.get(3).frequency);
         System.out.println(wordBank.get(4).word + " " + wordBank.get(4).frequency);
         System.out.println(wordBank.get(5).word + " " + wordBank.get(5).frequency);
         System.out.println(wordBank.get(6).word + " " + wordBank.get(6).frequency);
         System.out.println(wordBank.get(7).word + " " + wordBank.get(7).frequency);
         System.out.println(wordBank.get(8).word + " " + wordBank.get(8).frequency);
         System.out.println(wordBank.get(9).word + " " + wordBank.get(9).frequency);
         System.out.println(wordBank.get(10).word + " " + wordBank.get(10).frequency);
         System.out.println(wordBank.get(11).word + " " + wordBank.get(11).frequency);
         System.out.println(wordBank.get(12).word + " " + wordBank.get(12).frequency);
         System.out.println(wordBank.get(13).word + " " + wordBank.get(13).frequency);
         System.out.println(wordBank.get(14).word + " " + wordBank.get(14).frequency);
         */
    }
}
