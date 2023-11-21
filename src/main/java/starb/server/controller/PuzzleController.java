package starb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import starb.server.repo.PuzzleRepository;
import starb.server.transfer.Puzzle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("puzzles")
public class PuzzleController {

    private PuzzleRepository repo;

    public PuzzleController( PuzzleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Puzzle> getAll() {
        List<Puzzle> list = repo.findAll();
        return list;
    }

    @GetMapping("{level}")
    @ResponseStatus(HttpStatus.OK)
    public Puzzle getById(@PathVariable int level) {
        Optional<Puzzle> opt = repo.findByLevel(level);
        if( opt.isEmpty() ) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return opt.get();
    }

    // New endpoint to get a puzzle layout by ID
    @GetMapping("/{level}/layout")
    public int[][] getLayout(@PathVariable int level) {
        return repo.findByLevel(level)
                .map(Puzzle::getLayout)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Puzzle not found"));
    }

    // New endpoint to get a puzzle answer by ID
    @GetMapping("/{level}/answer")
    public int[][] getAnswer(@PathVariable int level) {
        return repo.findByLevel(level)
                .map(Puzzle::getAnswer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Puzzle not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Puzzle addPuzzle(@RequestBody Puzzle body) {
        return repo.save(body);
    }
}
