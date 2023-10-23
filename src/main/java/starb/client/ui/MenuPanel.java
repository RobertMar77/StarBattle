package starb.client.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MenuPanel extends HBox {

    public MenuPanel() {
        Label label = new Label("WELCOME TO STAR GAME");
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(label);
    }
}
