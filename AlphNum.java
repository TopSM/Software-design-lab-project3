package project3;
public class AlphNum {
    private char aChar;
    private double freq;
    //CONSTRUCTOR     
    AlphNum(){
        aChar='\0';
        freq=0;
    }
    //MODIFYING MEMBER METHODS
    public double countfreq(){
        freq++;
        return freq;
    }
    
    public void setChar(char c){
        aChar=c;
    }
    //CONSTANT MEMBER METHODS
    public double getPercent(int total){
        return freq/total;
    }
    public char getChar(){
        return aChar;
    }
    public double getfreq(){
        return freq;
    }    
}
