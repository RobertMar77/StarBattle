package starb.server.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import starb.server.transfer.Puzzle;
import starb.server.repo.PuzzleRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This component provides an event handler method (onApplicationStart) that is called just after
 * the server is started.  If the database is empty, it loads data from a JSON file and populates
 * the database with that data.
 */
@Component
public class PuzzleBootstrap {

    private static final String PUZZLE_DATA_FILE = "Puzzel Json/puzzles.json";
    private PuzzleRepository repo;
    public PuzzleBootstrap( PuzzleRepository repo ) {
        this.repo = repo;
    }

    /**
     * This method is called automatically, shortly after the server is started.
     * It checks to see if the database is empty, and if so, loads data from the JSON
     * file into the database.  Otherwise, it does nothing.
     *
     * @param event ignored
     */
    @EventListener
    public void onApplicationStart(ApplicationReadyEvent event) {
        // If the database already has data, do nothing.
       if( repo.count() > 0 ){
           return;}

        // Load the JSON file, and insert each object into the database
        ObjectMapper mapper = new ObjectMapper();
        File puzzFile = new File(PUZZLE_DATA_FILE);
        System.out.println("Loading puzzles into DB.");
        try {
            Puzzle[] puzz = mapper.readValue(puzzFile, Puzzle[].class);
            repo.saveAll(List.of(puzz));
        } catch(IOException e) {
            System.err.println("Unable to load puzzles: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
