
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Viterbi {

    private static int[] States = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static int[] Observe = {0, 1};
    private static float[] ISD = new float[26];
    private static float[][] TM = new float[26][26];
    private static float[][] EM = new float[2][26];
    private static int[] O = new int[54000];

    public int[] forwardViterbi(int[] O, int[] S, float[] ISD, float[][] TM, float[][] EM) {
        int numO = O.length;
        int numS = S.length;
        float[][] LV = new float[numS + 1][numO + 1];
        int[][] BLV = new int[numS + 1][numO + 1];
        
        //a) Base Case
        float baseMax = 0;
        for (int b = 0; b < numS; b++) {
            int cState = S[b];
            float baseCase = (float) (Math.log(ISD[b]) + Math.log(EM[O[0]][b]));
            LV[b][0] = baseCase;
            BLV[b][0] = 0;
        }

        //b) Recursive Step
        for (int i = 1; i < numO; i++) {
            int input = O[i];
            for (int j = 0; j < numS; j++) {
                float maxProb = Float.MAX_VALUE;
                float argMax = Float.MAX_VALUE;
                int backTrack = 0;
                for (int k = 0; k < numS; k++) {
                    float vValue1 = LV[k][i - 1];
                    if (vValue1 == 0.0) {
                        continue;
                    }
                    int pState = S[k];
                    float vValue2 = (float) (vValue1 + Math.log(TM[k][j]));
                    if (vValue2 < argMax) {
                        argMax = vValue2;
                        backTrack = pState;
                    }
                    float vValue3 = (float) (vValue2 + Math.log(EM[input][j]));
                    if (vValue3 < maxProb) {
                        maxProb = vValue3;
                    }
                }
                LV[j][i] = maxProb;
                BLV[j][i] = backTrack;
            }
        }
        
        //c) Backtracking Step
        LV[numS][numO] = Float.MAX_VALUE;
        for (int s = 0; s < numS; s++) {
            float fValue = (float) (LV[s][numO - 1] + Math.log(EM[O[numO - 1]][s]));
            if (fValue < LV[numS][numO]) {
                LV[numS][numO] = fValue;
                BLV[numS][numO] = S[s];
            }
        }
        int[] argmax = new int[numO];
        int bI = numS;
        int sI = numO;
        int index = BLV[bI][sI];
        while (index != 0 && sI != 0) {
            argmax[sI - 1] = index;
            for (int i = 0; i < numS; i++) {
                if (S[i] == index) {
                    bI = i;
                    break;
                }
            }
            index = BLV[bI][--sI];
        }
        System.out.print("Viterbi path: [");
        for (int i = 0; i < argmax.length; i++) {
            System.out.print(argmax[i] + ", ");
        }
        System.out.println("]");
        return argmax;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner tsISD = new Scanner(new File("initialStateDistribution.txt"));
        Scanner tsEM = new Scanner(new File("emissionMatrix.txt"));
        Scanner tsO = new Scanner(new File("observations.txt"));
        Scanner tsTM = new Scanner(new File("transitionMatrix.txt"));
        while (tsO.hasNextInt()) {
            for (int o = 0; o < 54000; o++) {
                O[o] = tsO.nextInt();
            }
        }
        while (tsISD.hasNextFloat()) {
            for (int I = 0; I < 26; I++) {
                ISD[I] = tsISD.nextFloat();
            }
        }
        while (tsTM.hasNextFloat()) {
            for (int x1 = 0; x1 < 26; x1++) {
                for (int y1 = 0; y1 < 26; y1++) {
                    TM[x1][y1] = tsTM.nextFloat();
                }
            }
        }
        while (tsEM.hasNextFloat()) {
            for (int y1 = 0; y1 < 26; y1++) {
                for (int x1 = 0; x1 < 2; x1++) {
                    EM[x1][y1] = tsEM.nextFloat();
                }
            }
        }

        System.out.println("Downloaded All Data");

        Viterbi v = new Viterbi();
        v.forwardViterbi(O, States, ISD, TM, EM);
    }

}
