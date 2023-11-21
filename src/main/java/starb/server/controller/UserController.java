package starb.server.controller;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import starb.server.repo.UserRepository;
import starb.server.transfer.Puzzle;
import starb.server.transfer.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private UserRepository repo;

    public UserController( UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        List<User> list = repo.findAll();
        return list;
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String userId) {
        Optional<User> opt = repo.findById(userId);
        if( opt.isEmpty() ) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return opt.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser() {
        User body = new User();
        repo.save(body);
        return body.getUserId();
    }




    // New endpoint to get a User's solved levels by ID
    @GetMapping("/{userId}/level")
    public int getLevels(@PathVariable String userId) {
        return repo.findById(userId)
                .map(User::getLevelsSolved)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // New endpoint to change a User's solved levels by ID
    @PatchMapping("/{userId}/{level}")
    public void setLevel(@PathVariable String userId, @PathVariable int level) {
        Optional<User> optUser = repo.findById(userId);

        if( optUser.isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        optUser.get().setLevelsSolved(level);
        repo.save(optUser.get());
    }

    // New endpoint to get a User's solved puzzles by ID
    @GetMapping("/{userId}/solved")
    public int getPuzzlesSolved(@PathVariable String userId) {
        return repo.findById(userId)
                .map(User::getPuzzlesSolved)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }


    // New endpoint to get a Users solved level by ID
    @PatchMapping("/{userId}/solved")
    public void addPuzzlesSolved(@PathVariable String userId) {
        Optional<User> optUser = repo.findById(userId);

        if( optUser.isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        optUser.get().addPuzzlesSolved();
        repo.save(optUser.get());
    }


    @GetMapping("/{userId}/title")
    public String getTitle(@PathVariable String userId) {
        return repo.findById(userId)
                .map(User::getTitle)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
