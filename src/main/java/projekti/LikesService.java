package projekti;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikesService {

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    AccountRepository accountRepository;

    public void like(Long id, String username){
        
        Tweet tweet = tweetRepository.getOne(id);
        Account account = accountRepository.findByUsername(username);

        List<Likes> tweetAlreadyLiked = likesRepository.findByTweetAndAccount(tweet, account);

        if(tweetAlreadyLiked.size() == 0){
            Likes like = new Likes();
            like.setAccount(account);
            like.setTweet(tweet);
            likesRepository.save(like);


            tweet.setLikes(tweet.getLikes() + 1);tweetRepository.save(tweet);
        }

    }
    
    @Transactional
    public void deleteLikes(Long id){
        Tweet tweet = tweetRepository.getOne(id);

        likesRepository.deleteByTweet(tweet);
    }
}
