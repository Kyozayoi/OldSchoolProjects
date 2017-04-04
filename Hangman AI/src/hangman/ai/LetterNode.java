public class LetterNode implements Comparable<LetterNode> {
    public char letter;
    public float frequency;
    public String word;
    
    public LetterNode(char l){
        letter = l;
    }

    @Override
    public int compareTo(LetterNode other) {
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
