package week5;

//import java.awt.Button;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")

public class Week5 extends Application {
    // Creating variables 
    private Label fnLabel, phLabel, caLabel, sizLabel;
    private TextField fnField, phField;
    private RadioButton sRad, mRad, lRad;
    private CheckBox freeDel;
    private ComboBox cakeBox;
    private Button saveBut, quitBut;
    private ToggleGroup group;
    Pane pane = new Pane();
    //Calling the start method by using the override and passing stage as a param
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        //From line 41-83 I will be just creating all the requiered lables, radios, checkbox,comboBox, textField 
        fnLabel = new Label("Name: ");
        grid.add(fnLabel, 0, 0);
        fnField = new TextField();
        grid.add(fnField, 1, 0);
        phLabel = new Label("Phone: ");
        grid.add(phLabel, 0, 1);
        phField = new TextField();
        grid.add(phField, 1, 1);

        caLabel = new Label("Cake: ");
        grid.add(caLabel, 0, 2);
        cakeBox = new ComboBox();
        cakeBox.getItems().add("Apple");
        cakeBox.getItems().add("Carrot");
        cakeBox.getItems().add("Cheesecake");
        cakeBox.getItems().add("Chocolate");
        cakeBox.getItems().add("Coffee");
        cakeBox.getItems().add("Opera");
        cakeBox.getItems().add("Tiramisu");
        grid.add(cakeBox, 1, 2);

        sizLabel = new Label("Size: ");
        grid.add(sizLabel, 0, 3);

        sRad = new RadioButton("Small");
        grid.add(sRad, 1, 3);
        mRad = new RadioButton("Medium");
        grid.add(mRad, 1, 4);
        lRad = new RadioButton("Larg");
        grid.add(lRad, 1, 5);

        group = new ToggleGroup();
        sRad.setToggleGroup(group);
        mRad.setToggleGroup(group);
        lRad.setToggleGroup(group);

        freeDel = new CheckBox("Free delivery");
        grid.add(freeDel, 1, 7);

        saveBut = new Button("Save");
        grid.add(saveBut, 1, 8);
        quitBut = new Button("Quit");
        grid.add(quitBut, 1, 9);

        Scene scen = new Scene(grid);
        stage.setTitle("Bakey store");
        stage.setScene(scen);
        stage.show();
        //Making arrow function using setOnAction to submite the form
        saveBut.setOnAction(ee -> {
            try {
                //Writing to the file ORDERS.TXT 
                FileWriter fw = new FileWriter("Orders.txt");
                PrintWriter pw = new PrintWriter(fw);
                //Printing to the file
                pw.write("Customer Name: " + fnField.getText());
                pw.write(" Phone Number: " + phField.getText());
                pw.write(" Cake: " + cakeBox.getValue());
                //Using if condition to see what the size is and print the size that is selected
                //Printing small using the isSelected function
                if (sRad.isSelected()) {
                    pw.write(" Size: Small ");
                    //prinintg Meidum
                } else if (mRad.isSelected()) {
                    pw.write(" Size: Medium ");
                    //Printing Larg
                } else if (lRad.isSelected()) {
                    pw.write(" Size: Large  ");
                } else {
                    //If nothing is selected we print no free delivery
                    pw.write("  No free delivery sorry....");
                }
                //Closimg the file printer 
                pw.close();
                //Updating everything when the button is clicked
                fnField.setText("");
                phField.setText("");
                freeDel.setSelected(false);
            } catch (IOException e) {

            }
        });

    }

    public static void main(String[] args) {
        launch(args);

    }

}
