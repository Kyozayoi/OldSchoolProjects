public class Word implements Comparable<Word>{
    public String word;
    public int place;
    public float number;
    public float frequency;
    public float sfrequency;
    public float[] fWords;
    
    public Word(String sInput, int nInput1, int nInput2){
        word = sInput;
        place = nInput1;
        number = nInput2;
        fWords = new float[500];
    }

    @Override
    public int compareTo(Word other) {
        if(this.frequency > other.frequency){
            return -1;
        }
        else if(this.frequency < other.frequency){
            return 1;
        }
        else{
            return 0;
        }
    }   
}
