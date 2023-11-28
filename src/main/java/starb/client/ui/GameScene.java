package starb.client.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import starb.client.ServerClient;
import starb.client.StarbClient;

/**
 * The root of our main Scene.  This is an HBox that contains two children:
 * an ExampleDrawingPanel and an ExampleSidePanel.
 */
public class GameScene extends HBox {

    private DrawingPanel graphicsPanel;

    private SidePanel sidePanel;
    private ServerClient serv = new ServerClient();
    private String UserID;
    public static int userLevel;


    public GameScene() {
        if(serv.getUserID().isEmpty()){
            UserID = serv.postUserID();
            System.out.println("Post UserID: "+ UserID);
        }else {
            UserID = serv.getUserID();
            System.out.println("Get UserID: "+ UserID);
        }
        this.userLevel = serv.getUserLevel(UserID);

        graphicsPanel = new DrawingPanel(userLevel);
        Stage primaryStage = StarbClient.getStage();
        sidePanel = new SidePanel(primaryStage, userLevel);



        this.getChildren().addAll(graphicsPanel, sidePanel);

    }
    public GameScene(int userLevel){
        graphicsPanel = new DrawingPanel(userLevel);
        Stage primaryStage = StarbClient.getStage();
        sidePanel = new SidePanel(primaryStage, userLevel);



        this.getChildren().addAll(graphicsPanel, sidePanel);
    }



}
