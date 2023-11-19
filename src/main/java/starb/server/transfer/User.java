package starb.server.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class User {
    @Id
    private String userId;
    private static int levelsSolved;
    private static int puzzlesSolved;
    private static String title;

    @JsonCreator
    public User()
    {
        this.userId = UUID.randomUUID().toString();
        levelsSolved = 0;
        puzzlesSolved = 0;
        title = "";
    }

    public String getUserId(){ return userId; }

    public void setLevelsSolved(int levelsSolved){ this.levelsSolved= levelsSolved; }

    public int getLevelsSolved(){ return levelsSolved; }


    public void addPuzzlesSolved(){ puzzlesSolved++; }

    public int getPuzzlesSolved(){ return puzzlesSolved; }

    //Title is only called for player info, so we don't need a setter
    public static String getTitle() {
        if(levelsSolved==0){
            title= "New Player";
        } else if (levelsSolved <5) {
            if(puzzlesSolved >= 10){
                title = "Easy Mode Expert";
            } else {
                title = "Noob";
            }
        } else if (levelsSolved <10) {
            if(puzzlesSolved >= 20){
                title = "Enthusiastic Rookie";
            } else {
                title = "Rookie";
            }
        } else if (levelsSolved <15) {
            if(puzzlesSolved >= 40){
                title = "Hardworking Talent";
            } else {
                title = "Talented";
            }
        } else if (levelsSolved < 20) {
            if(puzzlesSolved >= 60){
                title = "Seasoned Veteran";
            } else {
                title = "Veteran";
            }
        } else if (levelsSolved < 24) {
            if(puzzlesSolved >= 80){
                title = "Experienced Master";
            } else {
                title = "Master";
            }
        }else {
            if(puzzlesSolved >= 100){
                title = "Expert Grandmaster";
            } else {
                title = "Grandmaster";
            }
        }

        return title;
    }
}
