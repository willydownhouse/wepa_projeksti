package projekti;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
    
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    AccountRepository accountRepository;


    public Page<Tweet> getAll(){

        //sorting
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return tweetRepository.findAll(pageable);
    }

    public void createTweet(String text, Principal principal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Account account = accountRepository.findByUsername(principal.getName());

        if(account != null){
            Tweet tweet = new Tweet();

            LocalDateTime dateTime = LocalDateTime.now();
            String date = dateTime.format(formatter);
            
            tweet.setText(text);
            tweet.setCreatedAt(date);
            tweet.setAccount(account);

            tweetRepository.save(tweet);
        }

        
    }

    public Tweet getById(Long id){
        return tweetRepository.getOne(id);
    }

    
}
