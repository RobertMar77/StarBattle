package starb.client.ui;

import javafx.geometry.Point2D;

import java.io.File;

public class constants {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final File STAR_RED_IMAGE_FILE = new File("image/star_red.png");
    public static final File STAR_BLACK_IMAGE_FILE = new File("image/star_black.png");
    public static final File DOT_IMAGE_FILE = new File("image/dot.png");
    public static double cellSize = 40.0;
    public static int rows = 10;
    public static int cols = 10;
    public static Point2D gridUpperLeft = new Point2D(15,15);


}
