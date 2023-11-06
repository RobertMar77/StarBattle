package starb.server.transfer;

public class Cell {
    private int row;
    private int col;

    public Cell(){

    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
