package starb.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import starb.client.ui.Scene;
import java.io.IOException;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient extends Application {
    public static Stage primaryStage;
    public static void main(String[] args ) throws IOException {
        // Start the GUI
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setScene( new Scene(new starb.client.ui.GameScene()));
        primaryStage.setTitle("Example Client");
        primaryStage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }
}
