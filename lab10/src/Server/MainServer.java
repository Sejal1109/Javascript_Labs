package Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainServer extends Application {

    public static TextArea board = new TextArea();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Server server = new Server();
        primaryStage.setTitle("Lab 10 Server");
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 500, 400);

        Button exit = new Button("Exit");

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                server.close();
            }
        });

        board.setEditable(false);

        grid.add(board,0,0);
        grid.add(exit,0,1);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

