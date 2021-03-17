package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

//    private int ff = 0;
//    private int st = 0;
//    private int sm = 0;
//    private int t  = 0;
    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    private GraphicsContext gc;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);

        BufferedReader br = new BufferedReader(new FileReader("./resources/weatherwarnings-2015.csv"));
        String line = null;

        while ((line = br.readLine()) != null){

           String str[] = line.split(",");
           count(str[5]);
//           if(str[5].equals("FLASH FLOOD"))
//               ff++;
//           else if(str[5].equals("SEVERE THUNDERSTORM"))
//               st++;
//           else if(str[5].equals("SPECIAL MARINE"))
//               sm++;
//           else if(str[5].equals("TORNADO"))
//               t++;
        }
        for(String keys: map.keySet()){
            System.out.println(keys + ":" + map.get(keys));
        }
//        System.out.println(ff);
//        System.out.println(st);
//        System.out.println(sm);
//        System.out.println(t);


        primaryStage.setTitle("lab07");
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        drawPieChart();


    }

    private void count(String word){
        if(map.containsKey(word)){
            int prev = map.get(word);
            map.put(word, prev+1);
        }else{
            map.put(word, 1);
        }
    }
    private void drawPieChart(){
        int tot =0;
        for(int p: map.values()){
            tot+=p;
        }
        ArrayList<String> keys = new ArrayList<String>();
        double angle =0.0;
        int i =0;
        Color pieColors[] = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON};
        for(int p: map.values()){
            double curve = (double)p/(double)tot;
            double sweep = curve *360;
            gc.setFill(pieColors[i]);
            i++;
            gc.fillArc(500, 50, 350, 350, angle, sweep, ArcType.ROUND);

            angle += sweep;
        }
        for(String key: map.keySet()){
            keys.add(key);
        }
        for(int x=0; x<pieColors.length; x++){
            gc.setFill(pieColors[x]);
            gc.fillRect(80,80*(x+1), 50, 30);
        }

        int y =0;
        for(String key: keys){
            gc.setFill(Color.DARKBLUE);
            gc.fillText(key, 140, 98+(80*y));
            y++;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
