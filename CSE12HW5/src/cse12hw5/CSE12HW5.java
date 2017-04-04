/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */

public class CSE12HW5 {

    public static void main(String[] args) {
        int sL = 600000;
        long raTimeIS = 0,reTimeIS = 0,alTimeIS = 0,
             raTimeMS = 0,reTimeMS = 0,alTimeMS = 0,
             raTimeQS = 0,reTimeQS = 0,alTimeQS = 0;
        long timeTaken = System.nanoTime();
        int[] randomTest = new int[sL];
        int[] reverseTest = new int[sL];
        int[] almostTest = new int[sL];

        for (int i = 0; i < 100; i++) {
            int stacker = sL;
            for (int k = 0; k < sL; k++) {
                randomTest[k] = (int) (Math.random() * 200) - 100;
                reverseTest[k] = stacker;
                almostTest[k] = k;
                stacker--;
            }
            for (int j = 0; j < sL / 5; j++) {
                almostTest[(int) (Math.random() * sL)]
                        = (int) (Math.random() * 100) - 100;
            }
            
            long raStartIS = System.nanoTime();
            CSE12HW5.insertionSort(randomTest);
            long raEndIS = System.nanoTime();
            raTimeIS += (raEndIS - raStartIS);

            long reStartIS = System.nanoTime();
            CSE12HW5.insertionSort(reverseTest);
            long reEndIS = System.nanoTime();
            reTimeIS += (reEndIS - reStartIS);

            long alStartIS = System.nanoTime();
            CSE12HW5.insertionSort(almostTest);
            long alEndIS = System.nanoTime();
            alTimeIS += (alEndIS - alStartIS);
            
            long raStartMS = System.nanoTime();
            randomTest = CSE12HW5.mergeSort(randomTest);
            long raEndMS = System.nanoTime();
            raTimeMS += (raEndMS - raStartMS);

            long reStartMS = System.nanoTime();
            reverseTest = CSE12HW5.mergeSort(reverseTest);
            long reEndMS = System.nanoTime();
            reTimeMS += (reEndMS - reStartMS);

            long alStartMS = System.nanoTime();
            almostTest = CSE12HW5.mergeSort(almostTest);
            long alEndMS = System.nanoTime();
            alTimeMS += (alEndMS - alStartMS);
            
            long raStartQS = System.nanoTime();
            CSE12HW5.randomizedQuicksort(randomTest, sL);
            long raEndQS = System.nanoTime();
            raTimeQS += (raEndQS - raStartQS);

            long reStartQS = System.nanoTime();
            CSE12HW5.randomizedQuicksort(reverseTest, sL);
            long reEndQS = System.nanoTime();
            reTimeQS += (reEndQS - reStartQS);

            long alStartQS = System.nanoTime();
            CSE12HW5.randomizedQuicksort(almostTest, sL);
            long alEndQS = System.nanoTime();
            alTimeQS += (alEndQS - alStartQS);
        }
        
        System.out.println(raTimeIS / 100);
        System.out.println(reTimeIS / 100);
        System.out.println(alTimeIS / 100);
        System.out.println(raTimeMS / 100);
        System.out.println(reTimeMS / 100);
        System.out.println(alTimeMS / 100);
        System.out.println(raTimeQS / 100);
        System.out.println(reTimeQS / 100);
        System.out.println(alTimeQS / 100);

    }

    /*
     Insertion sort algorithm taken from Wikipedia
     */
    public static void insertionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int x = a[i];
            int j = i;
            while (j > 0 && x < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = x;
        }
    }

    /*
     Merge sort algorithm taken from Wikipedia
     */
    public static int[] mergeSort(int array[]) {
        if (array.length > 1) {
            int size1 = array.length / 2;
            int size2 = array.length - size1;
            int a1[] = new int[size1];
            int a2[] = new int[size2];
            for (int i = 0; i < size1; i++) {
                a1[i] = array[i];
            }
            for (int i = size1; i < size1 + size2; i++) {
                a2[i - size1] = array[i];
            }
            a1 = mergeSort(a1);
            a2 = mergeSort(a2);
            int i = 0, j = 0, k = 0;
            while (a1.length != j && a2.length != k) {
                if (a1[j] < a2[k]) {
                    array[i] = a1[j];
                    i++;
                    j++;
                } else {
                    array[i] = a2[k];
                    i++;
                    k++;
                }
            }
            while (a1.length != j) {
                array[i] = a1[j];
                i++;
                j++;
            }
            while (a2.length != k) {
                array[i] = a2[k];
                i++;
                k++;
            }
        }
        return array;
    }

    /*
     Quicksort Algorithm taken from 
     http://www.personal.kent.edu
     ~rmuhamma/Algorithms/MyAlgorithms/Sorting/quickSort.htm
     */
    public static void randomizedQuicksort(int a[], int aSize) {
        quickSort(a, 0, aSize - 1);
    }

    public static void quickSort(int numbers[], int left, int right) {
        int pivot, lspot, rspot;

        lspot = left;
        rspot = right;
        pivot = numbers[(int) (Math.random() * (right-left))];
        while (left < right) {
            while ((numbers[right] >= pivot) && (left < right)) {
                right--;
            }
            if (left != right) {
                numbers[left] = numbers[right];
                left++;
            }
            while ((numbers[left] <= pivot) && (left < right)) {
                left++;
            }
            if (left != right) {
                numbers[right] = numbers[left];
                right--;
            }
        }
        numbers[left] = pivot;
        pivot = left;
        left = lspot;
        right = rspot;
        if (left < pivot) {
            quickSort(numbers, left, pivot - 1);
        }
        if (right > pivot) {
            quickSort(numbers, pivot + 1, right);
        }
    }
}
