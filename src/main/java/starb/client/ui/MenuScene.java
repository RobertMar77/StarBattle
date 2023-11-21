package starb.client.ui;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Shape;
import com.sun.javafx.scene.text.GlyphList;
import com.sun.javafx.scene.text.TextLayout;
import com.sun.javafx.scene.text.TextLine;
import com.sun.javafx.scene.text.TextSpan;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.PathElement;
import static starb.client.StarbClient.primaryStage;

public class MenuScene extends VBox {
    private MenuPanel panel1;
    private MenuPanel2 panel2;
    public MenuScene(){
        this.setAlignment(Pos.CENTER);
        panel1 = new MenuPanel();
        panel2 = new MenuPanel2();
        Button Profile = new Button("PROFILE");

        this.getChildren().addAll(panel1, panel2, Profile);

        Profile.setOnAction(e -> {
            Scene newScene = new Scene(new ProfileScene(), 300, 200);
            primaryStage.setScene(newScene);
        });
    }

}
