package starb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import starb.server.repo.UserRepository;
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
    public User addUser(@RequestBody User body) {
        return repo.save(body);
    }
}
