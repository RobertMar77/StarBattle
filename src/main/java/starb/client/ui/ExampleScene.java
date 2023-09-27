package starb.client.ui;

import javafx.scene.layout.HBox;

/**
 * The root of our main Scene.  This is an HBox that contains two children:
 * an ExampleDrawingPanel and an ExampleSidePanel.
 */
public class ExampleScene extends HBox {

    private ExampleDrawingPanel graphicsPanel;

    private ExampleSidePanel sidePanel;

    public ExampleScene() {
        graphicsPanel = new ExampleDrawingPanel();
        sidePanel = new ExampleSidePanel();

        this.getChildren().addAll(graphicsPanel, sidePanel);
    }

}
