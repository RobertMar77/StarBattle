package starb.server.transfer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Document
public class Puzzle
{
    @Id
    private String id;
    private int[][] layout;
    private int[][] answer;
    public Puzzle(int[][] layout, int[][] answer, String id )
    {
        this.layout = layout;
        this.answer = answer;
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
    }
    public int[][] getLayout() {
        return layout;
    }
    public int[][] getAnswer() {
        return answer;
    }

    public String getId() {
        return id;
    }
}
