package starb.client.ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import starb.client.ui.DrawingPanel;

public class SidePanel extends VBox {
    Stage SideStage = new Stage();
    private final Stage primaryStage;

    public SidePanel(Stage primaryStage, int userLevel) {
        this.primaryStage = primaryStage;
        this.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                        new CornerRadii(5), BorderWidths.DEFAULT,
                        new Insets(10,10,10,10)) )
        );
        this.setPadding(new Insets(10,10,10,10));

        Button Menu = new Button("Menu");
        Button Solution = new Button("View Solution");
        Button Auto = new Button("Auto Mark type");


        this.getChildren().addAll(Menu, Solution, Auto);
        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });
        Auto.setOnAction(e -> {
            if(starb.client.ui.DrawingPanel.AutoOn == false){
                starb.client.ui.DrawingPanel.AutoOn = true;
            }
            else{
                starb.client.ui.DrawingPanel.AutoOn = false;
            }
        });


        Solution.setOnAction(e -> {
            Scene newScene = new Scene(new SolutionScene(userLevel));
            primaryStage.setScene(newScene);
        });

    }


}
