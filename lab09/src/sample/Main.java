package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main extends Application {
    private GraphicsContext gc;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 700, 500);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 09");
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        ArrayList<Float> stock1 = downloadStockPrices("GOOG");
        ArrayList<Float> stock2 = downloadStockPrices("AAPL");
        drawLinePlot(stock1, stock2);

    }

    public ArrayList<Float> downloadStockPrices(String stockTicker) {

        ArrayList<Float> closeVal = new ArrayList<Float>();
         try {
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" + stockTicker + "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                String str[] = line.split(",");
                if(str[4].equals("Close")){
                    continue;
                }
                else {
                    closeVal.add(Float.valueOf(str[4]));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return closeVal;
    }

    public void drawLinePlot(ArrayList<Float> f1, ArrayList<Float> f2){
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,25, 50, 450);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,450, 650, 450);

        plotline(Color.RED, f1);
        plotline(Color.DARKBLUE, f2);

    }
    private void plotline(Color c, ArrayList<Float> f){

        double x = 50;

        for(int i =0; i+1<f.size(); i++){
            double y = f.get(i) *0.5;
            double y2 = f.get(i+1) *0.5;
            gc.setStroke(c);
            gc.strokeLine(x,450 - y,x+8 , 450 - y2);
            x += 8;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
