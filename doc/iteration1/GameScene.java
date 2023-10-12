package starb.client.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import starb.client.StarbClient;

/**
 * The root of our main Scene.  This is an HBox that contains two children:
 * an ExampleDrawingPanel and an ExampleSidePanel.
 */
public class GameScene extends HBox {

    private DrawingPanel graphicsPanel;

    private SidePanel sidePanel;


    public GameScene() {

        graphicsPanel = new DrawingPanel();
        Stage primaryStage = StarbClient.getStage();
        sidePanel = new SidePanel(primaryStage);



        this.getChildren().addAll(graphicsPanel, sidePanel);

    }



}
