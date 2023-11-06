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
import starb.server.FakeServ;

import java.util.ArrayList;
import java.util.Arrays;

import static starb.client.StarbClient.primaryStage;
import static starb.client.ui.constants.*;

public class DrawingPanel extends VBox{
    public static boolean AutoOn = false;
    private Image redStar;
    private Image blackStar;
    private Image dotImage;
    private Canvas canvas;
    private GraphicsContext g;
    public int[][] boardd;
   // private final FakeServ serv = new FakeServ();
    private ServerClient serv = new ServerClient();
    private int[][] layout;
    private Puzzle userPuzzle;

    public static Stage st = new Stage();

    public DrawingPanel(){
        this.layout = this.serv.getLayout("1");
        int[][] answer = this.serv.getAnswer("1");
        this.userPuzzle = new Puzzle(answer, this.layout);
        this.boardd = new int[10][10];
        redStars = new ArrayList<>();
        loadImages();
        setupCanvas();
        drawGrid();
        drawLayout();
    }

    private void drawLayout() {
        GraphicsContext g = this.g;
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

    private void updateRedStars(int row, int col){
        for(int i=0; i < redStars.size(); i++){
            int[] redS = redStars.get(i);
            if(redS[0]==row && redS[1] == col){     //remove current redStar
                redStars.remove(redS);
//                System.out.println("remove");
            }
        }

        for(int i=0; i < redStars.size(); i++){
            int[] redS = redStars.get(i);
            if(userPuzzle.placeStar(redS[1], redS[0])){     //update others
                drawStar(redS[0], redS[1], g);
                redStars.remove(redS);
                i--;
            }
//            System.out.println("r"+Arrays.toString(redS));
        }
    }

    private static ArrayList<int[]> redStars;
    private void drawStar( int row, int col, GraphicsContext g ) {
        boolean isStarPlaced = userPuzzle.placeStar(col, row);
//        int[][] board = userPuzzle.getBoard();
//        for(int i=0; i < board.length; i++){
//            System.out.println(Arrays.toString(board[i]));     //prints out board can be used tio check board
//        }
//        System.out.println();


        if (isStarPlaced) {
            g.drawImage(blackStar,
                    gridUpperLeft.getX() + col * cellSize,
                    gridUpperLeft.getY() + row * cellSize,
                    cellSize, cellSize
            );
        } else {
            g.drawImage(redStar,
                    gridUpperLeft.getX() + col * cellSize,
                    gridUpperLeft.getY() + row * cellSize,
                    cellSize, cellSize
            );
            int[] rStar=new int[2];
            rStar[0]= row;
            rStar[1]= col;
            redStars.add(rStar);
        }

        if(userPuzzle.isCorrect()){ //add Win popup
            st.setScene(new Scene(new WinScene()));
            st.show();
        }
    }

    private void drawDot(int row, int col, GraphicsContext g){
        g.drawImage(dotImage,
                gridUpperLeft.getX() + col * cellSize,
                gridUpperLeft.getY() + row * cellSize,
                cellSize, cellSize
        );
    }

    private void clearIm(int row, int col, GraphicsContext g){
        // Clear the contents of the grid cell
        g.clearRect(
                gridUpperLeft.getX() + col * cellSize,
                gridUpperLeft.getY() + row * cellSize,
                cellSize,
                cellSize
        );
        g.setFill(Color.BLACK);

        g.setLineWidth(1.0);
        g.strokeRect(
                gridUpperLeft.getX() + col * cellSize,
                gridUpperLeft.getY() + row * cellSize,
                cellSize,
                cellSize
        );

        drawLayout();
    }

    private void mouseClicked(MouseEvent e) {
        double X = e.getX();
        double Y = e.getY();

        // Calculate the row and column from mouse coordinates
        int Row = (int) ((Y - gridUpperLeft.getY()) / 40);
        int Col = (int) ((X - gridUpperLeft.getX()) / 40);

        clearIm(Row,Col,g);
        if (boardd[Row][Col] == 0 ) {
            drawStar(Row, Col, g);
            // Draw the star at the calculated row and column
            boardd[Row][Col] = 1;
        } else if (boardd[Row][Col] == 1) {
            // Draw the dot at the calculated row and column
            drawDot(Row, Col, g);
            boardd[Row][Col] = 2;
            userPuzzle.clearSpace(Col, Row);

            updateRedStars(Row,Col);
        } else {
            boardd[Row][Col] = 0;
        }

        if(AutoOn) {
            autoDraw();
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
            this.dotImage= new Image(DOT_IMAGE_FILE.toURI().toURL().toString());
            this.redStar = new Image(STAR_RED_IMAGE_FILE.toURI().toURL().toString());
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setupCanvas() {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.g = canvas.getGraphicsContext2D();

        HBox hbox1= new HBox(50);
        HBox hbox2 = new HBox(10);

        Label time = new Label("Time");
        Label Ftime = new Label("Fastest Time");
        Button TimeSl = new Button("Time solve");
        Button Rest = new Button("Rest");
        hbox1.getChildren().addAll(time, Ftime);
        hbox2.getChildren().addAll(TimeSl, Rest);
        this.getChildren().addAll(hbox1, this.canvas, hbox2);

        this.canvas.setOnMouseClicked( e -> mouseClicked(e));

        Rest.setOnAction(e -> {
            Scene newScene = new Scene(new GameScene());
            primaryStage.setScene(newScene);
        });
    }

    private void autoDraw() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if(boardd[row][col] != 1) {
                    if (userPuzzle.vaildStar(col, row)) {
                        clearIm(row, col, g);
                        boardd[row][col] = 0;
                    }
                }
            }
        }

        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
                    if (!userPuzzle.vaildStar(col, row)) {
                        clearIm(row, col, g);
                        drawDot(row, col, g);
                        boardd[row][col] = 2;
                    }
            }
        }
    }

}
