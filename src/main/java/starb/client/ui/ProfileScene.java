package starb.client.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static starb.client.StarbClient.primaryStage;

public class ProfileScene extends VBox {

    /*
     * Makes the scene for the Profile which has a button to return to the menu.
     */
    public ProfileScene(){
        this.setAlignment(Pos.CENTER);

        Text GlobalID = new Text("Global ID: "+"#####");
        GlobalID.setStyle("-fx-font: 24 arial;");
        Text Title = new Text("Rank: " + "(Current Rank)");
        Text puzzSolved = new Text("Puzzled Solved: " + "0");
        Text minSolvTime = new Text("Min Solve Time: " + "00:00");
        Text avgSolvTime = new Text("Average Solve Time: " + "00:00");

        Button Menu = new Button("Menu");
        ProfileScene.setMargin(Menu, new Insets(10, 10, 10, 10));


        this.getChildren().addAll(GlobalID, Title, puzzSolved, minSolvTime, avgSolvTime, Menu);
        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });
    }

}
