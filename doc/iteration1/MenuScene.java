package starb.client.ui;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Shape;
import com.sun.javafx.scene.text.GlyphList;
import com.sun.javafx.scene.text.TextLayout;
import com.sun.javafx.scene.text.TextLine;
import com.sun.javafx.scene.text.TextSpan;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.PathElement;

public class MenuScene extends VBox {
    private MenuPanel panel1;
    private MenuPanel2 panel2;
    public MenuScene(){

        panel1 = new MenuPanel();
        panel2 = new MenuPanel2();
        Button Profile = new Button("PROFILE");
        Label leaderBoard = new Label("LEADERBOARD");
        TextArea leader = new TextArea();
        leaderBoard.setLabelFor(leader);
        leader.setEditable(false);

        this.getChildren().addAll(panel1, panel2, Profile,leaderBoard, leader);


    }

}
