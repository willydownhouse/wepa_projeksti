package projekti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long>{
    List<Likes> findByTweet(Tweet tweet);

    List<Likes> findByTweetAndAccount(Tweet tweet, Account account);
}
