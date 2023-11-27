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

import java.util.ArrayList;
import java.util.List;

import static starb.client.StarbClient.primaryStage;

public class PuzzleCompletedScene extends VBox{
    private List<Button> levelButtons = new ArrayList<>();
    private DrawingPanel graphicsPanel;
    private SidePanel sidePanel;
    public PuzzleCompletedScene(int userLevel) {
        for (int i = 1; i <= userLevel; i++) {
            levelButtons.add(createLevelButton(i));
        }

        for (int i = 0; i < levelButtons.size(); i += 5) {
            HBox hbox = new HBox(5);
            hbox.getChildren().addAll(levelButtons.subList(i, Math.min(i + 5, levelButtons.size())));
            hbox.setAlignment(Pos.CENTER);
            this.getChildren().add(hbox);
        }

    }


    private Button createLevelButton(int level) {
        Button levelButton = new Button("Level " + level);
        levelButton.setOnAction(e -> {
            displayLevelPuzzle(level);
        });
        return levelButton;
    }

    private void displayLevelPuzzle(int level) {
        Scene scene = new Scene(new GameScene(level));
        primaryStage.setScene(scene);
    }
}
