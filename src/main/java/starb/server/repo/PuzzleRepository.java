package starb.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import starb.server.transfer.Puzzle;

import java.util.Optional;

public interface PuzzleRepository extends MongoRepository<Puzzle, String>
{
    Optional<Puzzle> findByLevel(int level);
}