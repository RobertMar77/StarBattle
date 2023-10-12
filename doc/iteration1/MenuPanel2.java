package starb.client.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static starb.client.StarbClient.primaryStage;

public class MenuPanel2  extends HBox {
   public MenuPanel2() {
       Button b1 = new Button("PUZZLES");
       Button play = new Button("PLAY");
       this.getChildren().addAll(b1, play);

       play.setOnAction(e -> {
           Scene newScene = new Scene(new GameScene());
           primaryStage.setScene(newScene);
       });
   }
}
