package starb.client.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ExampleSidePanel extends VBox {

    public ExampleSidePanel() {
        this.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                        new CornerRadii(5), BorderWidths.DEFAULT,
                        new Insets(10,10,10,10)) )
        );
        this.setPadding(new Insets(10,10,10,10));

        Button b1 = new Button("Menu");
        Button b2 = new Button("View Solution");
        Button b3 = new Button("Mark type");
        Label label = new Label("Auto Mark type");
        TextField textField = new TextField();
        TextArea textArea = new TextArea();

        this.getChildren().addAll(b1, b2, label, textField, b3, textArea);
    }
}
