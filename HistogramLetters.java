package project3;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
public class HistogramLetters{
    private final String letter;
    private final AlphNum[] arrayAN;
    private final double xpoint;
    private final double ypoint;
    private final double xradius;
    private final double yradius;
    private double startAngle;
    private double charCount;
    //CONSTRUCTOR
    HistogramLetters(String sInp,double x, double y,double rx,double ry){
        String s="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,;:'-_?/!*[]()%#@=`~%&";
        letter =sInp;
        xpoint =x;
        ypoint =y;
        xradius =rx;
        yradius =ry;
        arrayAN = new AlphNum[s.length()];                  
        for (int i=0;i<arrayAN.length;i++){
            AlphNum an= new AlphNum();
            an.setChar(s.charAt(i));
            arrayAN[i]=an;
        }      
    }   
    //MODIFYING MEMBER METHODS 
    public void tokenizeChar(){
        for (int i=0;i<letter.length();i++){            
            for (int j=0;j<arrayAN.length;j++){
                if(letter.charAt(i)==arrayAN[j].getChar()){
                    arrayAN[j].countfreq();
                    charCount++;
                }                
            }
        }
    }
    public void sortArray(){
        AlphNum an = new AlphNum();
        int biggest;
        for (int i=0;i<arrayAN.length-1;i++){
            biggest=i;
            for(int j=i+1;j<arrayAN.length;j++){
                if(arrayAN[biggest].getfreq()<arrayAN[j].getfreq()){
                   biggest=j;
                }
            }
            an=arrayAN[i];
            arrayAN[i]=arrayAN[biggest];
            arrayAN[biggest]=an;
        }     
    }
    // OUTPUTS TO THE GUI 
    void draw(GraphicsContext gc){
        int i=0;
        startAngle=0;
        double yp=10;
        double xp=xpoint*8;
        gc.fillText("Char: percent", xp, yp);
        gc.setFill(Color.BLACK);
        gc.fillText(getString(arrayAN.length),xp,yp+=10);
        while(arrayAN[i].getfreq()!=0){
            getColor(gc);
            gc.fillArc(xpoint-xradius/8, ypoint, xradius, yradius, startAngle,getAngle(i), ArcType.ROUND);           
            gc.fillText(getString(i), xp, yp+=11); 
            if(yp>=ypoint*10){
                xp=xpoint*6-5;
                yp=ypoint*5+10;
                gc.setFill(Color.BLACK);
                gc.fillText("Char: percent", xp, yp);
            }
            startAngle+=getAngle(i);
            i++;
        }             
    }
    //CONSTANT MEMER METHODS
     public void charList(){
        System.out.println("total is: "+Double.toString(letter.length()));
        for (int i=0;i<arrayAN.length;i++){
            System.out.printf("%c ",arrayAN[i].getChar());
            System.out.print(arrayAN[i].getfreq());
            System.out.println(" "+arrayAN[i].getPercent(letter.length()));
        }
    } 
    public double getAngle(int index){
        return arrayAN[index].getPercent(letter.length())*360;
    }
    public String getString(int index){
        if(index==arrayAN.length){
            String num= Double.toString((letter.length()-charCount)/letter.length());
            String c="Other";
        return c+" :  "+num;
        }
        String num= Double.toString(arrayAN[index].getPercent(letter.length()));
        String c= Character.toString(arrayAN[index].getChar());
        return c+" :  "+num;
    }
    public Color getColor(GraphicsContext gc){
       Color c=Color.rgb(randomGenerator(),randomGenerator(),randomGenerator());
       gc.setFill(c);
       return c;
   }
     public int randomGenerator(){
       Random rgn = new Random();
       return rgn.nextInt(256);
   }
}