package starb.client.ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SidePanel extends VBox {
    Stage SideStage = new Stage();
    private final Stage primaryStage;

    public SidePanel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                        new CornerRadii(5), BorderWidths.DEFAULT,
                        new Insets(10,10,10,10)) )
        );
        this.setPadding(new Insets(10,10,10,10));

        Button Menu = new Button("Menu");
        Button Solution = new Button("View Solution");
        Button Mark = new Button("Mark type");
        Button Auto = new Button("Auto Mark type");


        this.getChildren().addAll(Menu, Solution, Mark, Auto);
        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });

    }


}
