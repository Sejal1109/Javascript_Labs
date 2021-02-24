package csci2020u.lab04;

import com.sun.javafx.event.EventHandlerManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import static java.awt.Font.*;
import static javafx.scene.text.Font.font;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Registration Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30,30,30,30));

        Label Username = new Label("Username: ");
        TextField UsernameTxt = new TextField();

        Label Password = new Label("Password: ");
        PasswordField Pass = new PasswordField();

        Label FullName = new Label("Full Name: ");
        TextField FullNameTxt = new TextField();

        Label Email = new Label("E-mail: ");
        TextField EmailTxt = new TextField();

        Label PhoneNum = new Label("Phone Number: ");
        TextField PhoneNumTxt = new TextField();

        Label DOB = new Label("Date of Birth: ");
        DatePicker Date = new DatePicker();

        Button btn = new Button("Register");
        HBox hBtn = new HBox(10);
        hBtn.setAlignment(Pos.BOTTOM_CENTER);
        hBtn.getChildren().add(btn);

        final Text actionTarget = new Text();

        grid.add(Username, 0, 1);
        grid.add(UsernameTxt, 1, 1);
        grid.add(Password, 0, 2);
        grid.add(Pass, 1, 2);
        grid.add(FullName, 0, 3);
        grid.add(FullNameTxt, 1, 3);
        grid.add(Email, 0, 4);
        grid.add(EmailTxt, 1, 4);
        grid.add(PhoneNum, 0, 5);
        grid.add(PhoneNumTxt, 1, 5);
        grid.add(DOB, 0, 6);
        grid.add(Date, 1, 6);
        grid.add(hBtn, 1, 7);
        grid.add(actionTarget, 1, 8);

        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actEvent) {
                System.out.println(UsernameTxt.getText());
                System.out.println(Pass.getText());
                System.out.println(FullNameTxt.getText());
                System.out.println(EmailTxt.getText());
                System.out.println(PhoneNumTxt.getText());
                System.out.println(Date.getValue());
            }
        });

        Scene scene = new Scene(grid, 500,375);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
