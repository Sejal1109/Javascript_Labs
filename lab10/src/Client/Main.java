package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Message connection = Message.connect("localhost", 16789);
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 10 Client");
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,300,300);

        Label Username = new Label("Username: ");
        TextField User = new TextField();
        User.setPromptText("Enter Username");

        Label Message = new Label("Message");
        TextField Mssg = new TextField();
        Mssg.setPromptText("Enter Message");


        Button btn1 = new Button("Send");

        Button btn2 = new Button("Exit");

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(Username,0,0);
        grid.add(User,1,0);
        grid.add(Message,0,1);
        grid.add(Mssg,1,1);
        grid.add(btn1,0,2);
        grid.add(btn2,0,3);



        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.send(User.getText(), Mssg.getText());
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.exit();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
