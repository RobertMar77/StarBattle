package starb.server;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import starb.server.controller.PuzzleController;
import starb.server.repo.PuzzleRepository;
import starb.server.transfer.Puzzle;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PuzzleControllerTest
{

    private final int[][] answer1 = {
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1}
    };

    private final int[][] layout1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 2, 2},
            {1, 1, 1, 1, 1, 1, 5, 1, 2, 2},
            {3, 4, 4, 4, 1, 5, 5, 5, 2, 2},
            {3, 3, 3, 3, 6, 7, 7, 5, 2, 2},
            {3, 3, 3, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 8, 8, 7, 7, 7, 9, 2},
            {3, 3, 3, 3, 3, 3, 7, 7, 9, 2},
            {3, 10, 10, 10, 10, 10, 10, 10, 9, 9},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 9}
    };
    private final int[][] answer2 = {
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1}
    };

    private final int[][] layout2 = {
            {1, 1, 1, 1, 1, 1, 1, 2, 2, 2},
            {1, 1, 1, 1, 1, 1, 5, 1, 2, 2},
            {3, 4, 4, 4, 1, 5, 5, 5, 2, 2},
            {3, 3, 3, 3, 6, 7, 7, 5, 2, 2},
            {3, 3, 3, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 8, 8, 7, 7, 7, 9, 2},
            {3, 3, 3, 3, 3, 3, 7, 7, 9, 2},
            {3, 10, 10, 10, 10, 10, 10, 10, 9, 9},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 9}
    };
    private final int[][] answer3 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1}
    };

    private final int[][] layout3 = {
            {1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 1, 1, 5, 1, 2, 2},
            {3, 4, 4, 4, 1, 5, 5, 5, 2, 2},
            {3, 3, 3, 3, 6, 7, 7, 5, 2, 2},
            {3, 3, 3, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 6, 6, 6, 7, 7, 2, 2},
            {3, 3, 8, 8, 8, 7, 7, 7, 9, 2},
            {3, 3, 3, 3, 3, 3, 7, 7, 9, 2},
            {3, 10, 10, 10, 10, 10, 10, 10, 9, 9},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 9}
    };
    private final Puzzle puzzle1 = new Puzzle(layout1, answer1, "1");
    private final Puzzle puzzle2 = new Puzzle(layout1, answer1, "2");
    private final Puzzle puzzle3 = new Puzzle(layout1, answer1, "test-id");
    @Autowired
    private PuzzleRepository repo;
    @Autowired
    private PuzzleController controller;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        List<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(puzzle1);
        puzzles.add(puzzle2);
        puzzles.add(puzzle3);
        repo.saveAll(puzzles);
    }

    @Test
    public void getSinglePuzzle()
    {
        Puzzle result = controller.getById(puzzle1.getId());
        MatcherAssert.assertThat(result, samePropertyValuesAs(puzzle1));
    }

    @Test
    public void getAllPuzzles()
    {
        List<Puzzle> result = controller.getAll();

        List<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(puzzle1);
        puzzles.add(puzzle2);
        MatcherAssert.assertThat(result, samePropertyValuesAs(puzzles));
    }

    @Test
    public void getSinglePuzzleById()
    {
        Puzzle result = controller.getById("test-id");

        MatcherAssert.assertThat(result, samePropertyValuesAs(puzzle3));
    }

    @Test
    public void addPuzzle()
    {
        Puzzle result = controller.addPuzzle(puzzle1);

        MatcherAssert.assertThat(result, samePropertyValuesAs(puzzle1));
    }

    @Test
    public void getLayout()
    {
        int[][] result = controller.getLayout(puzzle1.getId());

        MatcherAssert.assertThat(result, samePropertyValuesAs(layout1));
    }

    @Test
    public void getAnswer()
    {
        int[][] result = controller.getAnswer(puzzle1.getId());

        MatcherAssert.assertThat(result, samePropertyValuesAs(answer1));
    }
}