package projekti;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
    
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    AccountRepository accountRepository;


    public List<Tweet> getAll(){
        return tweetRepository.findAll();
    }

    public void createTweet(String text, Principal principal){
        Account account = accountRepository.findByUsername(principal.getName());

        System.out.println("account from tweet service");
        System.out.println(account);

        if(account != null){
            Tweet tweet = new Tweet();

            tweet.setText(text);
            //tweet.setComments(new ArrayList<>());
            tweet.setCreatedAt(LocalDate.now());
            tweet.setAccount(account);

            tweetRepository.save(tweet);
        }

        
    }

    
}
