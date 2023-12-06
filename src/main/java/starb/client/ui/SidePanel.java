package starb.client.ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.css.RGBColor;
import starb.client.ui.DrawingPanel;

import static javafx.scene.paint.Color.*;

public class SidePanel extends VBox {
    Stage SideStage = new Stage();
    private final Stage primaryStage;

    public SidePanel(Stage primaryStage, int userLevel) {
        this.primaryStage = primaryStage;
        this.setBorder(new Border(
                new BorderStroke(GRAY, BorderStrokeStyle.SOLID,
                        new CornerRadii(5), BorderWidths.DEFAULT,
                        new Insets(10,10,10,10)) )
        );
        this.setPadding(new Insets(10,10,10,10));

        Button Menu = new Button("Menu");
        Button Solution = new Button("View Solution");
        Button Auto = new Button("Auto Mark type");
        Background background = new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
        Background background2 = new Background(new BackgroundFill(RED, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));

        Auto.setBackground(background);


        this.getChildren().addAll(Menu, Solution, Auto);
        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });
        Auto.setOnAction(e -> {
            if(starb.client.ui.DrawingPanel.AutoOn == false){
                Auto.setBackground(background2);
                starb.client.ui.DrawingPanel.AutoOn = true;
            }
            else{
                Auto.setBackground(background);
                starb.client.ui.DrawingPanel.AutoOn = false;
            }
        });


        Solution.setOnAction(e -> {
            Scene newScene = new Scene(new SolutionScene(userLevel));
            primaryStage.setScene(newScene);
        });

    }


}
