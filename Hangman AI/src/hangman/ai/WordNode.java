public class WordNode implements Comparable<WordNode> {
    public String word;
    public int count;
    public float frequency;
    public char[] spelling = new char[5];
    public boolean remaining;
    
    public WordNode(String winput, int cinput){
        word = winput;
        count = cinput;
        frequency = 0;
        for(int i = 0; i < 5; i++){
            spelling[i] = winput.charAt(i);
        }
        remaining = true;
    }

    @Override
    public int compareTo(WordNode other) {
        if(this.frequency > other.frequency){
            return 1;
        }
        else if(this.frequency < other.frequency){
            return -1;
        }
        else{
            return 0;
        }
    }
}
