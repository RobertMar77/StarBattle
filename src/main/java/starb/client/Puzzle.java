package starb.client;

public class Puzzle {
    private static int[][] starLocations;
    private static int[][] answer;
    private static int[][] layout;

    private int[] adjacentX = {-1,-1,-1, 0, 0, 1, 1, 1};
    private int[] adjacentY = {-1, 0, 1,-1, 1,-1, 0, 1};



    /**
     * Creates a new board and takes the answer and region arrays for use in creating/checking the board
     * @param answer     An int array that represents the correct star placement for the puzzle.
     * @param layout     An int array that represents the layout for each coordinate.
     */
    public Puzzle(int[][] answer, int[][] layout){
        this.answer = answer;
        this.layout = layout;
        this.starLocations = new int[10][10];
    }

    /**
     * Places a star as a 1 and checks if the given x, y region has 2 or less stars
     * Also makes sure the X-row and Y-col have only 1 star (the new star)
     * @param x     New stars row.
     * @param y     New stars column.
     * @return boolean  true if legal placement otherwise false.
     */
    public boolean placeStar(int x, int y){
        int starsAdjacent = numStarsAdjacent(x, y);
        int starsInRow = numStarsInRow(x, y);
        int starsInCol = numStarsInCol(x, y);
        int starsInRegion = numStarsInRegion(x, y);

        if (starsAdjacent < 1 &&
                starsInRow < 2 &&
                starsInCol < 2 &&
                starsInRegion < 2
        ) {
            starLocations[y][x]= 1;
            return true;
        } else {
            starLocations[y][x]= 1;
            return false;
        }
    }

    public boolean vaildStar(int x, int y){
        int starsAdjacent = numStarsAdjacent(x, y);
        int starsInRow = numStarsInRow(x, y);
        int starsInCol = numStarsInCol(x, y);
        int starsInRegion = numStarsInRegion(x, y);

        if (starsAdjacent < 1 &&
                starsInRow < 2 &&
                starsInCol < 2 &&
                starsInRegion < 2
        ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Counts the number of stars around the coordinates (x, y)
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    private int numStarsAdjacent(int x, int y) {
        int numStars = 0;

        for(int i=0; i<8; i++){
            int aX = x + adjacentX[i];
            int aY = y + adjacentY[i];

            if(aX >= 0 && aX < 10 && aY >= 0 && aY < 10){
                if(starLocations[aY][aX]==1){
                    numStars++;
                }
            }
        }

        return numStars;
    }

    /**
     * Counts the number of stars in the "y" row
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    private int numStarsInRow(int x, int y) {
        int numStars = 0;

        for (int i = 0; i < 10; i++) {
            if (i == x) {
                continue;
            }

            if (starLocations[y][i] == 1) {
                numStars++;
            }
        }

        return numStars;
    }

    /**
     * Counts the number of stars in the "x" column
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    private int numStarsInCol(int x, int y) {
        int numStars = 0;

        for (int j = 0; j < 10; j++) {
            if (j == y) {
                continue;
            }

            if (starLocations[j][x] == 1) {
                numStars++;
            }
        }

        return numStars;
    }

    /**
     * Counts the number of stars contained in the region
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    private int numStarsInRegion(int x, int y) {
        int numStars = 0;
        int currentRegion = layout[y][x];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (layout[j][i] == currentRegion) {
                    if (starLocations[j][i] == 1) {
                        numStars++;
                    }
                }
            }
        }

        return numStars;
    }

    /*
     * Reset one space to 0
     * @param x     erase row coordinate.
     * @param y     erase column coordinate.
     */
    public void clearSpace(int x, int y){
        starLocations[y][x]= 0;
    }

    /*
    * Checks to see if the board is correct
    * @return true  if the board's stars match the answers stars
    */
    public boolean isCorrect(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(answer[j][i] != starLocations[j][i]){
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Removes player decisions with a new board
     */
    public void reset(){
        starLocations = new int[10][10];
    }

    /*
     * Place an empty spot/dot
     * @param x     empty spot row coordinate.
     * @param y     empty spot column coordinate.
     */
    public void placeEmpty(int x, int y){
        starLocations[y][x]= -1;
    }

    //allows us to update current board   //maybe will remove
    public int[][] getBoard() {
        return starLocations;
    }
}
