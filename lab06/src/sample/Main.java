package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;



public class Main extends Application {

    private static double[] avgHousingPricesByYear = { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};
    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    private Canvas canvas;
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene= new Scene(root, 1000, 600);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("lab06");
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        drawPieChart();
        drawBarChart(100, 400, avgHousingPricesByYear, Color.RED, 0, 600000.0 );
        drawBarChart(100,400, avgCommercialPricesByYear, Color.BLUE, 100/avgCommercialPricesByYear.length, 500000.0);
    }

    void drawBarChart(int w, int h, double[] data, Color color, int xAxis, double y){
        gc.setFill(color);

        double max = Double.NEGATIVE_INFINITY, min = Double.MAX_VALUE;
        for(double val: data){
            if(val > max){
                max = val;
            }
            if(val < min){
                min = val;
            }
        }

        double barW = w / data.length;
        double x = xAxis;
        for(double val: data){
            double barH = ((val - min) / (y)) * h;
            gc.fillRect(x, (h - barH), barW, barH);
            x += 2 * barW + 10;
        }
    }
    void drawPieChart(){

        int tot = 0;
        for(int p: purchasesByAgeGroup){
            tot += p;
        }

        double angle = 0.0;
        for(int i=0; i< purchasesByAgeGroup.length; i++) {
            double curve = (double) purchasesByAgeGroup[i] / (double)tot;
            double sweep = curve * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(500, 50, 350, 350, angle, sweep, ArcType.ROUND);

            angle += sweep;
        }

    }

    public static void main(String[] args) {

        launch(args);
    }
}