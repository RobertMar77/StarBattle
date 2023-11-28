package starb.client.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import starb.client.Puzzle;
import starb.client.ServerClient;

import java.util.ArrayList;

import static starb.client.StarbClient.primaryStage;
import static starb.client.ui.constants.*;

public class SolutionScene extends VBox {
    private Image redStar;
    private Image blackStar;
    private Canvas canvas;
    private GraphicsContext g;
    private final ServerClient serv = new ServerClient();
    private final int[][] layout;
    private final Puzzle userPuzzle;
    private final String UserID;
    private final int userLevel;

    public SolutionScene(int userLevel){
        UserID = serv.getUserID();

        //set User level to 24 to test all puzzles
//        serv.setUserLevel(UserID, 24);       //2 & 4 & 12 & 13 & 23  Don't work

        this.userLevel = userLevel;
        System.out.println("Level: "+ userLevel);
        this.layout = this.serv.getLayout(userLevel);
        int[][] answer = this.serv.getAnswer(userLevel);
        this.userPuzzle = new Puzzle(answer, this.layout);

        loadImages();
        setupCanvas();
        drawGrid();
        drawLayout();
    }

    private void drawLayout() {
        GraphicsContext g = this.g;
        g.beginPath();
        g.setLineWidth(5.0);
        int[][] answer = this.serv.getAnswer(userLevel);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int curr = layout[j][i];

                if (i > 0 && curr != layout[j][i - 1]) { // Draw line to the left
                    g.moveTo(i * cellSize + gridUpperLeft.getX(), j * cellSize + gridUpperLeft.getY());
                    g.lineTo(i * cellSize + gridUpperLeft.getX(), (j + 1) * cellSize + gridUpperLeft.getY());
                }

                if (i < 9 && curr != layout[j][i + 1]) { // Draw line to the right
                    g.moveTo((i + 1) * cellSize + gridUpperLeft.getX(), j * cellSize + gridUpperLeft.getY());
                    g.lineTo((i + 1) * cellSize + gridUpperLeft.getX(), (j + 1) * cellSize + gridUpperLeft.getY());
                }

                if (j > 0 && curr != layout[j - 1][i]) { // Draw line above
                    g.moveTo(i * cellSize + gridUpperLeft.getX(), j * cellSize + gridUpperLeft.getY());
                    g.lineTo((i + 1) * cellSize + gridUpperLeft.getX(), j * cellSize + gridUpperLeft.getY());
                }

                if (j < 9 && curr != layout[j + 1][i]) { // Draw line below
                    g.moveTo(i * cellSize + gridUpperLeft.getX(), (j + 1) * cellSize + gridUpperLeft.getY());
                    g.lineTo((i + 1) * cellSize + gridUpperLeft.getX(), (j + 1) * cellSize + gridUpperLeft.getY());
                }

                if(answer[j][i]==1){
                    drawStar(j,i,g);
                }
                System.out.print(answer[i][j]);             //print solution taken from server
            }
            System.out.println();
        }
        g.closePath();
        g.stroke();
    }

    private void drawStar( int row, int col, GraphicsContext g ) {
        boolean isStarPlaced = userPuzzle.placeStar(col, row);

        if (isStarPlaced) {
            g.drawImage(blackStar,
                    gridUpperLeft.getX() + col * cellSize,
                    gridUpperLeft.getY() + row * cellSize,
                    cellSize, cellSize
            );
        } else {                // we will remove redstars when we fix the 5 puzzles
            g.drawImage(redStar,
                    gridUpperLeft.getX() + col * cellSize,
                    gridUpperLeft.getY() + row * cellSize,
                    cellSize, cellSize
            );
        }
    }

    private void drawGrid() {
        GraphicsContext g = this.g;
        g.setFill(Color.BLACK);

        // Example grid
        g.setLineWidth(1.0);
        g.beginPath();
        for( int i = 0; i < rows + 1; i++ ) {
            double x1 = gridUpperLeft.getX();
            double y1 = gridUpperLeft.getY() + i * cellSize;
            double x2 = gridUpperLeft.getX() + cellSize * cols;
            double y2 = y1;
            g.moveTo(x1, y1);
            g.lineTo(x2, y2);
        }
        for( int i = 0; i < cols + 1; i++ ) {
            double x1 = gridUpperLeft.getX() + i * cellSize;
            double y1 = gridUpperLeft.getY();
            double x2 = x1;
            double y2 = gridUpperLeft.getY() + cellSize * rows;
            g.moveTo( x1, y1 );
            g.lineTo( x2, y2 );
        }
        g.stroke();
        g.closePath();
    }

    private void loadImages() {
        try {
            this.blackStar = new Image(STAR_BLACK_IMAGE_FILE.toURI().toURL().toString());
            this.redStar = new Image(STAR_RED_IMAGE_FILE.toURI().toURL().toString());
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setupCanvas() {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.g = canvas.getGraphicsContext2D();

        HBox hbox2 = new HBox(10);

        Button Menu = new Button("Menu");

        hbox2.getChildren().addAll(Menu);
        this.getChildren().addAll(this.canvas, hbox2);

        Menu.setOnAction(e -> {
            Scene newScene = new Scene(new MenuScene());
            primaryStage.setScene(newScene);
        });
    }

}
