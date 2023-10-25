package starb.client.ui;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import starb.client.Puzzle;
import starb.server.FakeServ;

import java.io.File;
import java.util.Arrays;

import static starb.client.ui.WinScene.Next;

public class DrawingPanel extends VBox{

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private static final File STAR_RED_IMAGE_FILE = new File("image/star_red.png");
    private Image star_redImage;

    private static final File STAR_IMAGE_FILE = new File("image/star_gold.png");
    private Image starImage;
    private static final File SPOT_IMAGE_FILE = new File("image/spot_the_cow.png");
    private static final File DOT_IMAGE_FILE = new File("image/star_black.png");
    private Image spotImage;
    private Image dotImage;
    private Canvas canvas;

    // Grid dimensions and location
    private double cellSize = 40.0;
    private int rows = 10;
    private int cols = 10;
    private Point2D gridUpperLeft = new Point2D(15,15);
    GraphicsContext g;
    public int[][] boardd = new int[10][10];



    private final FakeServ serv = new FakeServ();
    private final int[][] layout = serv.getLayout(0);       //the int will be the puzzle number we will change later
    private final int[][] answer = serv.getAnswer(0);
    private Puzzle userPuzzle = new Puzzle(answer, layout);

    public static Stage st = new Stage();

    public DrawingPanel(){
        canvas = new Canvas(WIDTH, HEIGHT);
        HBox hbox1= new HBox(50);
        HBox hbox2 = new HBox(10);

        // Load the image files
        try {
            starImage = new Image(STAR_IMAGE_FILE.toURI().toURL().toString());
            spotImage = new Image(SPOT_IMAGE_FILE.toURI().toURL().toString());
            dotImage= new Image(DOT_IMAGE_FILE.toURI().toURL().toString());
            star_redImage= new Image(STAR_RED_IMAGE_FILE.toURI().toURL().toString());
        } catch(Exception e) {
            String message = "Unable to load image: " + STAR_IMAGE_FILE;
            System.err.println(message);
            System.err.println(e.getMessage());
            throw new RuntimeException(message);
        }
        Label time = new Label("Time");
        Label Ftime = new Label("Fastest Time");
        Button TimeSl = new Button("Time solve");
        Button Rest = new Button("Rest");
        hbox1.getChildren().addAll(time, Ftime);
        hbox2.getChildren().addAll(TimeSl, Rest);
        this.getChildren().addAll(hbox1, canvas, hbox2);
        canvas.setOnMouseClicked( e -> mouseClicked(e));
        draw();

    }



    public void draw() {
        g = canvas.getGraphicsContext2D();
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

        drawLayout(g);
        // Draw a thicker line, left side of cells in column
        /*g.setLineWidth(5.0);
        int column = 1, startCellY = 1, endCellY = 3;
        double x1 = column * cellSize + gridUpperLeft.getX();
        double y1 = startCellY * cellSize + gridUpperLeft.getY();
        double x2 = x1;
        double y2 = (endCellY + 1) * cellSize + gridUpperLeft.getY();
        g.strokeLine(x1, y1, x2, y2);*/

        // Draw stars in a few cells of the grid
        //drawStar( 3, 4, g );
       // drawStar( 1, 1, g );
        //drawStar( 0, 2, g );

        // Draw the cow, just for fun.  This is demonstrating how you can draw any custom
        // image.  Remove this... :)
        float scale = 0.4f;
        g.drawImage( spotImage, gridUpperLeft.getX() + cellSize * cols + 5,
                gridUpperLeft.getY(),
                spotImage.getWidth() * scale, spotImage.getHeight() * scale);
    }

    private void drawLayout(GraphicsContext g) {
        g.beginPath();
        g.setLineWidth(5.0);
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
            }
        }
        g.closePath();
        g.stroke();
    }


    private void drawStar( int row, int col, GraphicsContext g ) {
        boolean isStar = userPuzzle.placeStar(col, row);

        System.out.println(isStar);

        if(isStar){     //star placement not illegal
            g.drawImage(starImage,
                    gridUpperLeft.getX() + row * cellSize,
                    gridUpperLeft.getY() + col * cellSize,
                    cellSize, cellSize
            );
        }
        else{
            g.drawImage(star_redImage,
                    gridUpperLeft.getX() + row * cellSize,
                    gridUpperLeft.getY() + col * cellSize,
                    cellSize, cellSize
            );
        }

        if(userPuzzle.isCorrect()){             //add Win popup
            System.out.println("correct");

            st.setScene(new Scene(new WinScene()));
            st.show();
        }
    }

    private void drawDot(int row, int col, GraphicsContext g){
        g.drawImage(dotImage,
                gridUpperLeft.getX() + row * cellSize,
                gridUpperLeft.getY() + col * cellSize,
                cellSize, cellSize
        );

        userPuzzle.clearSpace(col, row);
    }

    private void clearIm(int row, int col, GraphicsContext g){
        // Clear the contents of the grid cell
        g.clearRect(
                gridUpperLeft.getX() + row * cellSize,
                gridUpperLeft.getY() + col * cellSize,
                cellSize,
                cellSize
        );
        g.setFill(Color.BLACK);

        g.setLineWidth(1.0);
        g.strokeRect(
                gridUpperLeft.getX() + row * cellSize,
                gridUpperLeft.getY() + col * cellSize,
                cellSize,
                cellSize
        );
        drawLayout(g);
        userPuzzle.clearSpace(col, row);
    }

    private void mouseClicked(MouseEvent e) {
        double X = e.getX();
        double Y = e.getY();

        // Calculate the row and column from mouse coordinates
        int Row = (int) ((Y - gridUpperLeft.getY()) / 40);
        int Col = (int) ((X - gridUpperLeft.getX()) / 40);

        if(boardd[Row][Col] == 0){
            drawDot(Col, Row, g);
            // Draw the spot at the calculated row and column
            boardd[Row][Col] = 1;
            userPuzzle.clearSpace(Col, Row);
        } else if (boardd[Row][Col] == 1) {
            clearIm(Col,Row,g);
            // Draw the star at the calculated row and column
            drawStar(Col, Row, g);
            boardd[Row][Col] = 2;
        }
        else{
            userPuzzle.clearSpace(Col, Row);
            clearIm(Col,Row,g);
            boardd[Row][Col] = 0;

        }

    }
}
