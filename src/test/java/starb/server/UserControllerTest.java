package starb.server;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import starb.server.controller.UserController;
import starb.server.repo.UserRepository;
import starb.server.transfer.Puzzle;
import starb.server.transfer.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest
{
    private final User user1 = new User();
    private final User user2 = new User();
    private final User user3 = new User();
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserController controller;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        repo.saveAll(users);
    }

    @Test
    public void getSingleUser()
    {
        User result = controller.get(user1.getUserId());
        MatcherAssert.assertThat(result, samePropertyValuesAs(user1));
    }

    @Test
    public void addUser()
    {
        User result = controller.addUser(user1);

        MatcherAssert.assertThat(result, samePropertyValuesAs(user1));
    }

    @Test
    public void getAllUsers()
    {
        List<User> result = controller.getAll();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        MatcherAssert.assertThat(result, samePropertyValuesAs(users));
    }
}