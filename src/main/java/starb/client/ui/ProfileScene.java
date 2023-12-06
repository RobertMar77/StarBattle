package starb.client.ui;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import starb.client.ServerClient;

import static starb.client.StarbClient.primaryStage;

public class ProfileScene extends VBox {
    private ServerClient serv = new ServerClient();
    private String UserID = serv.getUserID();

    /*
     * Makes the scene for the Profile which has a button to return to the menu scene expands if you full screen it.
     */
    public ProfileScene(){
        this.setAlignment(Pos.CENTER);

        Text GlobalID = new Text("Global ID: "+serv.getUserID());
        Text Title = new Text("Rank: " + serv.getTitle(UserID));
        Text levelsSolved = new Text("Levels Solved: " + serv.getUserLevel(UserID));
        Text puzzSolved = new Text("Puzzles Solved: " + serv.getUserSolved(UserID));

        Button Menu = new Button("Menu");
        ProfileScene.setMargin(Menu, new Insets(10, 10, 10, 10));

        // Bind font size to scene width so it expands if you expand the screen
        double fontSizeMultiplier = 0.04;
        GlobalID.fontProperty().bind(Bindings.createObjectBinding(
                () -> Font.font("Arial", primaryStage.getWidth() * fontSizeMultiplier),
                primaryStage.widthProperty()
        ));

        Title.fontProperty().bind(Bindings.createObjectBinding(
                () -> Font.font("Arial", (0.8*primaryStage.getWidth() * fontSizeMultiplier)),
                primaryStage.widthProperty()
        ));

        Menu.fontProperty().bind(GlobalID.fontProperty());
        levelsSolved.fontProperty().bind(Title.fontProperty());
        puzzSolved.fontProperty().bind(Title.fontProperty());


        this.getChildren().addAll(GlobalID, Title, levelsSolved, puzzSolved, Menu);
        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });
    }

}
