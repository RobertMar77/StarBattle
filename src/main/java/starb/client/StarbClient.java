package starb.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starb.client.ui.ExampleScene;

import java.io.IOException;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient extends Application {
    public static void main( String[] args ) throws IOException {
        // Start the GUI
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene( new Scene( new ExampleScene() ) );
        primaryStage.setTitle("Example Client");
        primaryStage.show();
    }
}
