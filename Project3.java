package project3;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Project3 extends Application {
    @Override
    public void start(Stage primaryStage) {        
        System.out.println("enter x-axis for canvas");
        Scanner input = new Scanner(System.in);        
        double convert = input.nextInt();
        double xcanvas = convert;
        while (true){
            System.out.println("enter y-axis for canvas");
            input = new Scanner(System.in);            
            convert= input.nextInt();
            if(convert<xcanvas) break;
            else{ System.out.println("choose a bigger y-axis than the x-axis");}
        }
        double ycanvas =convert;
        
        Group root = new Group();
        Scene s = new Scene(root, xcanvas, ycanvas,Color.WHITE);
        Canvas canvas = new Canvas(xcanvas,ycanvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        double xcenter =xcanvas/2;
        double ycenter = ycanvas/2;
        double xradius = xcanvas*.8;
        double yradius =ycanvas*.8;
        System.out.println("enter the string");
        String conv="";
        input = new Scanner(System.in);
        while(input.hasNextLine()){
            String use = new String(); 
            use = input.nextLine();
            conv+=use;
            if (use.matches(" ")){
                input.close();
                break;
            }
        }
        
        root.getChildren().add(canvas);
        HistogramLetters hl = new HistogramLetters(conv,xcenter-xradius/2, ycenter-yradius/2,xradius,yradius);        
        hl.tokenizeChar();
        hl.sortArray();
        hl.draw(gc);
        primaryStage.setTitle("Ostavo Palacios Assignment #3");
        primaryStage.setScene(s);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
