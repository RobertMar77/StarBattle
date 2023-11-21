package starb.server;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import starb.server.controller.PuzzleController;
import starb.server.repo.PuzzleRepository;
import starb.server.transfer.Puzzle;
import starb.server.transfer.Cell;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PuzzleControllerTest
{

    private final List<Cell> answer1 = List.of(
            new Cell(0, 1), new Cell(0, 4),
            new Cell(1, 6), new Cell(1, 8),
            new Cell(2, 1), new Cell(2, 3),
            new Cell(3, 7), new Cell(3, 9),
            new Cell(4, 3), new Cell(4, 5),
            new Cell(5, 0), new Cell(5, 7),
            new Cell(6, 2), new Cell(6, 4),
            new Cell(7, 6), new Cell(7, 8),
            new Cell(8, 1), new Cell(8, 4),
            new Cell(9, 5), new Cell(9, 9)
    );

    private final List<List<Cell> > layout1 = List.of(
            List.of(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(0, 3), new Cell(0, 4), new Cell(0, 5), new Cell(0, 6), new Cell(0, 7), new Cell(1, 0), new Cell(1, 2), new Cell(1, 3), new Cell(1, 4), new Cell(1, 5), new Cell(1, 7)),
            List.of(new Cell(0, 8), new Cell(0, 9),new Cell(1, 8), new Cell(1, 9),new Cell(2, 8), new Cell(2, 9),new Cell(3, 8), new Cell(3, 9),new Cell(4, 8), new Cell(4, 9),new Cell(5, 8), new Cell(5, 9), new Cell(6, 9)),
            List.of(new Cell(2, 0),new Cell(3, 0), new Cell(3, 1),new Cell(4, 0), new Cell(4, 1),new Cell(5, 0), new Cell(5, 1),new Cell(6, 0), new Cell(6, 1),new Cell(7, 0), new Cell(7, 1),new Cell(3, 2), new Cell(3, 3),new Cell(4, 3), new Cell(7, 2),new Cell(7, 3), new Cell(7, 4),new Cell(7, 5), new Cell(8, 0)),
            List.of(new Cell(2, 1),new Cell(2, 3), new Cell(2, 4)),
            List.of(new Cell(1, 6),new Cell(2, 5), new Cell(2, 6),new Cell(2, 7), new Cell(3, 8)),
            List.of(new Cell(3, 4),new Cell(4, 3), new Cell(4, 4),new Cell(4, 5), new Cell(5, 3),new Cell(5, 4), new Cell(5, 5)),
            List.of(new Cell(3, 5),new Cell(3, 6), new Cell(4, 6),new Cell(4, 7), new Cell(5, 6),new Cell(5, 7), new Cell(6, 5),new Cell(6, 6), new Cell(6, 7),new Cell(7, 6), new Cell(7, 7)),
            List.of(new Cell(5, 2),new Cell(6, 2), new Cell(6, 3), new Cell(6, 4)),
            List.of(new Cell(6, 8),new Cell(7, 8), new Cell(8, 8),new Cell(9, 0), new Cell(8, 9), new Cell(9,9)),
            List.of(new Cell(9, 0), new Cell(9, 1), new Cell(9, 2), new Cell(9, 3), new Cell(9, 4), new Cell(9, 5), new Cell(9, 6), new Cell(9, 7), new Cell(9, 8), new Cell(8, 1), new Cell(8,2), new Cell(8, 3), new Cell(8,4), new Cell(8, 5), new Cell(8,6), new Cell(8,7))
    );
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
    private final Puzzle puzzle1 = new Puzzle("1", 1, layout1, answer1,10,2);
    private final Puzzle puzzle2 = new Puzzle("2", 2, layout1, answer1,10,2);
    private final Puzzle puzzle3 = new Puzzle("test-id", 3, layout1, answer1,10,2);
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
        Puzzle result = controller.getById(puzzle1.getLevel());
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
        Puzzle result = controller.getById(3);

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
        int[][] result = controller.getLayout(puzzle1.getLevel());

        MatcherAssert.assertThat(result, samePropertyValuesAs(layout1));
    }

    @Test
    public void getAnswer()
    {
        int[][] result = controller.getAnswer(puzzle1.getLevel());

        MatcherAssert.assertThat(result, samePropertyValuesAs(answer1));
    }
}