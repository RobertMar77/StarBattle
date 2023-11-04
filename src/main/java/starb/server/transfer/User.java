package starb.server.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class User {
    @Id
    private String userId;

    @JsonCreator
    public User()
    {
        this.userId = UUID.randomUUID().toString();
    }

    public String getUserId(){ return userId; }
}
