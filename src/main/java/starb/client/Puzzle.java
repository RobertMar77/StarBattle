package starb.client;

public class Puzzle {
    private static int[][] board;
    private static int[][] answer;
    private static int[][] region;



    /*
     * Creates a new board and takes the answer and region arrays for use in creating/checking the board
     * @param Answer     A int array that represents the correct star placement for the puzzle.
     * @param Region     A int array that represents the region for each coordinate.
     */
    public Puzzle(int[][] Answer, int[][] Region){
        answer=Answer;
        region=Region;
        board = new int[10][10];
    }

    /*
     * Places a star as a 1 and checks if the given x, y region has 2 or less stars
     * Also makes sure the X-row and Y-col have only 1 star (the new star)
     * @param x     New stars row.
     * @param y     New stars column.
     * @return boolean  true if legal placement otherwise false.
     */
    public boolean placeStar(int x, int y){
        board[x][y]= 1;

        int currReg= region[x][y];
        int legalReg = 0;

        int legalCol = 0;
        int legalRow = 0;

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(region[i][j] == currReg && board[i][j] == 1){
                    legalReg++;
                }
                if(board[x][j] == 1){
                    legalRow++;
                }
            }
            if(board[i][y] == 1){
                legalCol++;
            }
        }

        if(legalReg <= 2 && legalCol ==1 && legalRow ==1){ //2 per region 1 star per row/col
            return true;
        }
        else{
            board[x][y]= 0;
            return false;
        }
    }





    /*
     * Reset one space to 0
     * @param x     erase row coordinate.
     * @param y     erase column coordinate.
     */
    public void clearSpace(int x, int y){
        board[x][y]= 0;
    }


    /*
    * Checks to see if the board is correct
    * @return true  if the board's stars match the answers stars
    */
    public boolean isCorrect(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(answer[i][j]==1){
                    if(board[i][j]!=1){
                        return false;
                    }
                }
            }
        }

        //if all stars match
        return true;
    }

    /*
     * Removes player decisions with a new board
     */
    public void reset(){
        board = new int[10][10];
    }




    /*
     * Place an empty spot/dot
     * @param x     empty spot row coordinate.
     * @param y     empty spot column coordinate.
     */
    public void placeEmpty(int x, int y){
        board[x][y]= -1;
    }

    //allows us to update current board   //maybe will remove
    public int[][] getBoard() {
        return board;
    }
}
