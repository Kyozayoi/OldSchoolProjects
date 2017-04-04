
import java.util.LinkedList;

public class Project3 {

    public static void main(String args[]) {

        //Make our ten arrays and add the values.
        System.out.println(System.nanoTime());
        int[] HWarray1 = new int[10000];
        addManyFrontArray(HWarray1);
        System.out.println(System.nanoTime());
        int[] HWarray2 = new int[10000];
        addManyFrontArray(HWarray2);
        System.out.println(System.nanoTime());
        int[] HWarray3 = new int[10000];
        addManyFrontArray(HWarray3);
        System.out.println(System.nanoTime());
        int[] HWarray4 = new int[10000];
        addManyFrontArray(HWarray4);
        System.out.println(System.nanoTime());
        int[] HWarray5 = new int[10000];
        addManyFrontArray(HWarray5);
        System.out.println(System.nanoTime());
        int[] HWarray6 = new int[10000];
        addManyFrontArray(HWarray6);
        System.out.println(System.nanoTime());
        int[] HWarray7 = new int[10000];
        addManyFrontArray(HWarray7);
        System.out.println(System.nanoTime());
        int[] HWarray8 = new int[10000];
        addManyFrontArray(HWarray8);
        System.out.println(System.nanoTime());
        int[] HWarray9 = new int[10000];
        addManyFrontArray(HWarray9);
        System.out.println(System.nanoTime());
        int[] HWarray10 = new int[10000];
        addManyFrontArray(HWarray10);
        System.out.println(System.nanoTime());

        System.out.println("Between"); //Split it so I can tell where it stops
        
        //Make our ten LinkedLists, get the time for each of them.
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist1 = new LinkedList<>();
        addManyFrontList(HWlist1);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist2 = new LinkedList<>();
        addManyFrontList(HWlist2);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist3 = new LinkedList<>();
        addManyFrontList(HWlist3);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist4 = new LinkedList<>();
        addManyFrontList(HWlist4);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist5 = new LinkedList<>();
        addManyFrontList(HWlist5);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist6 = new LinkedList<>();
        addManyFrontList(HWlist6);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist7 = new LinkedList<>();
        addManyFrontList(HWlist7);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist8 = new LinkedList<>();
        addManyFrontList(HWlist8);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist9 = new LinkedList<>();
        addManyFrontList(HWlist9);
        System.out.println(System.nanoTime());
        LinkedList<Integer> HWlist10 = new LinkedList<>();
        addManyFrontList(HWlist10);
        System.out.println(System.nanoTime());

    }

    //Add the values by shifting the array before adding a new value
    public static void addManyFrontArray(int[] al) {
        for (int i = al.length - 1; i > 0; i--) {
            al[i] = al[i - 1];
            int random = (int) (Math.random() * 10) + 1;
            al[0] = random;
        }
    }

    //Add the values from the front. LinkedList has own implementation for it.
    public static void addManyFrontList(LinkedList ll) {
        for (int i = 0; i < 10000; i++) {
            int random = (int) (Math.random() * 10) + 1;
            ll.addFirst(random);
        }
    }
}
