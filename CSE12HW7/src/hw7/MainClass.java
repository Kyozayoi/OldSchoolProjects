package hw7;

public class MainClass {
    public static void main(String[] args){
        BSTree BS = new BSTree();
        BS.insert("Leslie", 15);
        BS.insert("Vanessa", 14);
        BS.insert("Tiffany", 56);
        BS.insert("Carmen", 56);
        System.out.println(BS.leafCount());
        System.out.println(BS.FindDepth(BS.getRoot()));
        System.out.println(BS.delete(56, "Tiffany").getName());
        System.out.println(BS.delete(56, "Tiffany").getName());
    }
}
