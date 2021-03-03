package csci2020u.lab05;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<StudentRecord> table = new TableView<StudentRecord>();
    final HBox hb = new HBox();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Student Info");
        stage.setWidth(700);
        stage.setHeight(550);

        final Label label = new Label("Student Records");
        label.setFont(new Font("Franklin Gothic Medium", 20));

        //table.setEditable(true);

        TableColumn StudentID = new TableColumn("SID");
        StudentID.setCellValueFactory(new PropertyValueFactory<>("SId"));
        TableColumn Assignments = new TableColumn("Assignments");
        Assignments.setCellValueFactory(new PropertyValueFactory<>("assign"));
        TableColumn Midterm = new TableColumn("Midterm");
        Midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        TableColumn FinalExam = new TableColumn("Final Exam");
        FinalExam.setCellValueFactory(new PropertyValueFactory<>("finalEx"));
        TableColumn FinMark = new TableColumn("Final Mark");
        FinMark.setCellValueFactory(new PropertyValueFactory<>("finMark"));
        TableColumn letterGrade = new TableColumn("Letter Grade");
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        ObservableList<StudentRecord> marks = DataSource.getAllMarks();
        table.setItems(marks);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(StudentID, Assignments, Midterm, FinalExam, FinMark, letterGrade);


        final TextField addSId = new TextField();
        addSId.setPromptText("Student ID");

        final TextField addAssing = new TextField();
        addAssing.setPromptText("Assignments/100");

        final TextField addMid = new TextField();
        addMid.setPromptText("Midterm/100");

        final TextField addFinEx = new TextField();
        addFinEx.setPromptText("Final Exam/100");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                float a = Float.parseFloat(addAssing.getText());
                float b = Float.parseFloat(addMid.getText());
                float c = Float.parseFloat(addFinEx.getText());
                marks.add(new StudentRecord(addSId.getText(), a, b, c));
                addSId.clear();
                addFinEx.clear();
                addMid.clear();
                addAssing.clear();
            }
        });

        hb.getChildren().addAll(addSId, addAssing, addMid, addFinEx, addButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
