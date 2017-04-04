import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
Inspiration for implementing a min-max Heap 
with two arrays came from Eran Levy at
http://eranle.blogspot.com/2012/08/min-max-heap-java-implementation.html
*/

public class minMaxHeap {

    private int heapSize;
    private int[] maxHeap = null;
    private int[] minHeap = null;

    public minMaxHeap() {
        this.heapSize = 0;
    }

    public static void main(String[] args) throws Exception {
        File sourceCode = new File(args[0]);
        FileReader Iread = new FileReader(sourceCode);
        BufferedReader IRead = new BufferedReader(Iread);
        String Command = IRead.readLine();
        minMaxHeap monster = new minMaxHeap();
        while (Command != null) {
            Command = Command.trim();
            if (Command.contains("buildMinMaxHeap")) {
                ArrayList<Integer> ArrayTemp = new ArrayList<>();
                Scanner in = new Scanner(Command).useDelimiter("[^0-9]+");
                while (in.hasNextInt()) {
    int numb = in.nextInt();
    ArrayTemp.add(numb);
                }
                int[] array = new int[ArrayTemp.size()];
                for (int i = 0; i < array.length; i++) {
    array[i] = ArrayTemp.get(i);
                }
                monster.buildMinMaxHeap(array);
            }
            else if (Command.contains("peekMin")) {
                monster.peekMin();
            }
            else if (Command.contains("peekMax")) {
                monster.peekMax();
            }
            else if (Command.contains("deleteMin")) {
                monster.deleteMin();
            }
            else if (Command.contains("deleteMax")) {
                monster.deleteMax();
            }
            else if (Command.contains("insert")) {
                int numb = 0;
                Scanner in = new Scanner(Command).useDelimiter("[^0-9]+");
                if (!in.hasNextInt()) {
    System.out.println("Nothing Inserted");
    System.exit(-1);
                }
                numb = in.nextInt();

                monster.insert(numb);
            }
            else if (Command.contains("printMinMaxHeap")) {
                monster.printMinMaxHeap();
            } else {
                System.out.println("Error: Method Doesn't Exiat");
                System.exit(-1);
            }
            Command = IRead.readLine();
        }
        System.exit(0);
    }

    public void buildMinMaxHeap(int[] heaper) {
        this.heapSize = heaper.length;
        maxHeap = Arrays.copyOf(heaper, heaper.length);
        minHeap = Arrays.copyOf(heaper, heaper.length);
        for (int i = (heaper.length / 2) - 1; i >= 0; i--) {
            maxHeapSort(maxHeap, i);
        }
        for (int i = (heaper.length / 2) - 1; i >= 0; i--) {
            minHeapSort(minHeap, i);
        }
    }

    public void peekMax() {
        if (heapSize == 0 || heapSize == 1) {
            System.out.println("Error: No Maximum");
            System.exit(-1);
        }
        System.out.println("Maximum: " + maxHeap[0]);
    }

    public void peekMin() {
        if (heapSize == 0) {
            System.out.println("Error: No Minimum");
            System.exit(-1);
        }
        System.out.println("Minimum: " + minHeap[0]);
    }

    public void deleteMax() {
        int max = 0;
        if (heapSize < 1) {
            System.out.println("Heap underflow");
            System.exit(-1);
        } else {
            max = maxHeap[0];
            int mark = maxHeap[heapSize];
            int marker = maxHeap.length - 1;
            maxHeap[0] = mark;
            maxHeap = Arrays.copyOf(maxHeap, maxHeap.length - 1);
            if (marker == heapSize) {
                minHeap = Arrays.copyOf(minHeap, minHeap.length - 1);
                heapSize = heapSize - 1;
                maxHeapSort(maxHeap, 0);
                minHeapSort(minHeap, 0);
            } else {
                int marik = minHeap[heapSize];
                minHeap[marker] = mark;
                minHeap = Arrays.copyOf(minHeap, minHeap.length - 1);
                heapSize = heapSize - 1;
                maxHeapSort(maxHeap, 0);
                minHeapSort(minHeap, marker);
            }
        }
    }

    public void deleteMin() {
        int min = 0;
        if (heapSize < 1) {
            System.out.println("Heap underflow");
            System.exit(-1);
        } else {
            min = minHeap[0];
            int marker = maxHeap.length - 1;
            int mark = minHeap[heapSize];
            minHeap[0] = mark;
            minHeap = Arrays.copyOf(minHeap, minHeap.length - 1);
            if (marker == heapSize) {
                maxHeap = Arrays.copyOf(maxHeap, maxHeap.length - 1);
                heapSize = heapSize - 1;
                minHeapSort(minHeap, 0);
                maxHeapSort(maxHeap, 0);
            } else {
                int marik = maxHeap[heapSize];
                maxHeap[marker] = marik;
                maxHeap = Arrays.copyOf(maxHeap, maxHeap.length - 1);
                heapSize = heapSize - 1;
                minHeapSort(minHeap, 0);
                maxHeapSort(maxHeap, marker);
            }
        }
    }

    public void insert(int x) {
        heapSize = heapSize + 1;
        int insert = x;
        insertMax(insert);
        insertMin(insert);
        for (int i = (maxHeap.length) - 1; i >= 0; i--) {
            maxHeapSort(maxHeap, i);
        }
        for (int i = (minHeap.length) - 1; i >= 0; i--) {
            minHeapSort(minHeap, i);
        }
        System.out.println("Inserted: " + x);
    }

    public void printMinMaxHeap() {
        int minIndex = 0;
        int maxIndex = 0;
        int depth = 0;
        int i = 0;
        while (i < heapSize) {
            if (depth % 2 == 0) {
                if (depth == 0) {
    System.out.print(minHeap[0]);
    minIndex++;
    i++;
                } else {
    for (int j = 0; j < Math.pow(2, depth); j++) {
        System.out.print(minHeap[minIndex] + " ");
        minIndex++;
        i++;
        if (i == heapSize) {
            break;
        }
        if (minIndex == minHeap.length) {
            break;
        }
    }
                }
            } else {
                for (int j = 0; j < Math.pow(2, depth); j++) {
    System.out.print(maxHeap[maxIndex] + " ");
    maxIndex++;
    i++;
    if (i == heapSize) {
        break;
    }
    if (maxIndex == maxHeap.length) {
        break;
    }
                }
            }
            depth++;
            System.out.println();
        }
    }

    private void insertMax(int insert) {
        int i = heapSize;
        maxHeap = Arrays.copyOf(maxHeap, maxHeap.length + 2);
        while (i > 0 && (maxHeap[(i - 1) / 2] < insert)) {
            int temp = maxHeap[(i - 1) / 2];
            maxHeap[i] = temp;
            i = (i - 1) / 2;
        }
        maxHeap[i] = insert;
    }

    private void insertMin(int insert) {
        int i = heapSize;
        minHeap = Arrays.copyOf(minHeap, minHeap.length + 2);
        while (i > 0 && (minHeap[(i - 1) / 2] > insert)) {
            int temp = minHeap[(i - 1) / 2];
            minHeap[i] = temp;
            i = (i - 1) / 2;
        }
        minHeap[i] = insert;
    }

    private void maxHeapSort(int[] max, int index) {
        int largest;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;
        if (left <= heapSize && max[left] > max[index]) {
            largest = left;
        } else {
            largest = index;
        }
        if (right <= heapSize && max[right] > max[largest]) {
            largest = right;
        }
        if (largest != index) {
            Swap(max, index, largest);
            maxHeapSort(max, largest);
        }
    }

    private void minHeapSort(int[] min, int index) {
        int smallest;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;
        if (left <= heapSize && min[left] < min[index]) {
            smallest = left;
        } else {
            smallest = index;
        }
        if (right <= heapSize && min[right] < min[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            Swap(min, index, smallest);
            minHeapSort(min, smallest);
        }
    }

    private void Swap(int[] heap, int index, int place) {
        int tempval = heap[index];
        heap[index] = heap[place];
        heap[place] = tempval;
    }

}
