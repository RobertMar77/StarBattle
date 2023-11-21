package starb.server.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

/**
 * Represents a Star Battle puzzle.  Data is immutable.
 */
@Document("puzzles")
public class Puzzle {

    @Id
    private final String id;
    private final int level;
    private final List<List<Cell>> regions;
    private final List<Cell> solution;
    private final int gridSize;
    private final int numStars;
    private int[][] layout;
    private int[][] answer;

    @JsonCreator
    public Puzzle(
            @JsonProperty("id") String id,
            @JsonProperty("level") int level,
            @JsonProperty("regions") List<List<Cell>> regions,
            @JsonProperty("solution") List<Cell> solution,
            @JsonProperty("gridSize") int gridSize,
            @JsonProperty("numStars") int numStars ) {
        this.id = String.valueOf(level);
        this.level = level;
        this.regions = Collections.unmodifiableList(regions);
        this.solution = Collections.unmodifiableList(solution);
        this.gridSize = gridSize;
        this.numStars = numStars;
    }




    public int[][] getAnswer() {
        int[][] ans = new int[10][10];
        for(Cell k: solution){
            ans[k.getRow()][k.getCol()] =1;
        }
        return ans;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int[][] getLayout() {
       int[][] lay = new int[10][10];
       int num=1;
        for(List<Cell> i: regions){
            for(Cell j: i){
                lay[j.getRow()][j.getCol()] = num;
            }
            num++;
        }
        return lay;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getNumStars() {
        return numStars;
    }

    @Override
    public String toString() {
        return "Puzzle: size = " + gridSize + " stars = " + numStars + " level = " + level + "\n" +
                regions;
    }
}