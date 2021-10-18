package projekti;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long>{

    @EntityGraph(attributePaths = {"account"})
    List<Tweet> findAll();
}
