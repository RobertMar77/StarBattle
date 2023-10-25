package starb.client.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.awt.*;

import static starb.client.StarbClient.primaryStage;
import static starb.client.ui.DrawingPanel.st;

public class WinScene extends VBox {
    public static Button Next;
    public WinScene(){
        this.setAlignment(Pos.CENTER);
        Text con = new Text("NICE YOU SOLVED THE PUZZLE");
        Text con2 = new Text("READY FOR THE NEXT ONE");
        Next = new Button("NEXT PUZZLE");
        this.getChildren().addAll(con, con2, Next);

        Next.setOnAction(e -> {
            Scene newScene = new Scene(new GameScene());
            primaryStage.setScene(newScene);
            st.close();
        });
    }

    public Button getButton() {
        return Next;
    }



}
