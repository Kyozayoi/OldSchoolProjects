package ValueIteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class valueIteration {

    public static void main(String[] args) throws FileNotFoundException {

        double omega = 0.9925;
        int[] Rewards = new int[81];
        double[][] tableV = new double[2000][81];

        int[][] probWest = new int[187][2];
        double[] westProb = new double[187];
        int[][] probNorth = new int[187][2];
        double[] northProb = new double[187];
        int[][] probEast = new int[187][2];
        double[] eastProb = new double[187];
        int[][] probSouth = new int[187][2];
        double[] southProb = new double[187];

        Scanner rewardScanner = new Scanner(new File("rewards.txt"));
        Scanner a1 = new Scanner(new File("prob_a1.txt"));
        Scanner a2 = new Scanner(new File("prob_a2.txt"));
        Scanner a3 = new Scanner(new File("prob_a3.txt"));
        Scanner a4 = new Scanner(new File("prob_a4.txt"));
        
        int r = 0;
        while (rewardScanner.hasNextInt()) {
            
            Rewards[r] = rewardScanner.nextInt();
            tableV[1][r] = Rewards[r];
            r++;
            
        }
        
        int a = 0;
        while (a1.hasNext()) {
            
            probWest[a][0] = a1.nextInt();
            probWest[a][1] = a1.nextInt();
            westProb[a] = a1.nextDouble();

            probNorth[a][0] = a2.nextInt();
            probNorth[a][1] = a2.nextInt();
            northProb[a] = a2.nextDouble();

            probEast[a][0] = a3.nextInt();
            probEast[a][1] = a3.nextInt();
            eastProb[a] = a3.nextDouble();

            probSouth[a][0] = a4.nextInt();
            probSouth[a][1] = a4.nextInt();
            southProb[a] = a4.nextDouble();

            a++;
            
        }
        
        for (int k = 2; k < 2000; k++) {
            for (int i = 1; i < 82; i++) {
                
                double max = 0;
                double maxW = 0;
                double maxN = 0;
                double maxE = 0;
                double maxS = 0;

                for (int j = 0; j < 187; j++) {                   
                    if (probWest[j][0] == i) {                    
                        int sPrime = probWest[j][1];
                        maxW = maxW + westProb[j] * tableV[k - 1][sPrime - 1];
                        maxN = maxN + northProb[j] * tableV[k - 1][sPrime - 1];
                        maxE = maxE + eastProb[j] * tableV[k - 1][sPrime - 1];
                        maxS = maxS + southProb[j] * tableV[k - 1][sPrime - 1];                       
                    }                  
                }
                
                max = maxW;
                if (maxN > max) {
                    max = maxN;
                }
                if (maxE > max) {
                    max = maxE;
                }
                if (maxS > max) {
                    max = maxS;
                }
                
                double vkp1S = Rewards[i - 1] + omega * max;
                tableV[k][i-1] = vkp1S;
            }
        }
        
        for(int l = 0; l < 81; l++){
            System.out.println("V*(" + (l+1) + "): " + tableV[1999][l]);
        }
        
        for(int pi = 0; pi < 81; pi++){
            if(tableV[1999][pi] > 0){
                String direction;
                double argMaxW = 0;
                double argMaxN = 0;
                double argMaxE = 0;
                double argMaxS = 0;
                for (int j = 0; j < 187; j++) {
                    if (probWest[j][0] == pi+1) {
                        int sPrime = probWest[j][1];
                        argMaxW = argMaxW + westProb[j] * tableV[1999][sPrime - 1];
                        argMaxN = argMaxN + northProb[j] * tableV[1999][sPrime - 1];
                        argMaxE = argMaxE + eastProb[j] * tableV[1999][sPrime - 1];
                        argMaxS = argMaxS + southProb[j] * tableV[1999][sPrime - 1];
                    }
                }
                argMaxW = Rewards[pi] + omega * argMaxW;
                argMaxN = Rewards[pi] + omega * argMaxN;
                argMaxE = Rewards[pi] + omega * argMaxE;
                argMaxS = Rewards[pi] + omega * argMaxS;
                double argMax = argMaxW;
                direction = "West/Left";
                if(argMaxN > argMax){
                    argMax = argMaxN;
                    direction = "North/Up";
                }
                if(argMaxE > argMax){
                    argMax = argMaxE;
                    direction = "East/Right";
                }
                if(argMaxS > argMax){
                    argMax = argMaxS;
                    direction = "South/Down";
                }
                
                System.out.println((pi+1) + ": " + direction);
                
            }
        }
    }
}