package starb.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import starb.server.transfer.User;

public interface UserRepository extends MongoRepository<User, String>
{
}