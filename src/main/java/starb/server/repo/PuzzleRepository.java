package starb.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import starb.server.transfer.Puzzle;

public interface PuzzleRepository extends MongoRepository<Puzzle, String>
{
}