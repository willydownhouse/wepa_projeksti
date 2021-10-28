package projekti;

import java.security.Principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TweetService {
    
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Tweet> getAllByAccount(String username){
        Account account = accountRepository.findByUsername(username);

        return tweetRepository.findByAccount(account);
    }

    
    public Page<Tweet> getAll(String page, Integer tweetsOnOnePage){
        Integer pageAsInt = Integer.parseInt(page);

        Pageable pageable = PageRequest.of(pageAsInt, tweetsOnOnePage, Sort.by("createdAt").descending());
        
        return tweetRepository.findAll(pageable);
    }

    public Long tweetCount(){
        return tweetRepository.count();
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
            tweet.setLikes(0);

            tweetRepository.save(tweet);
        }

        
    }

    public Tweet getById(Long id){
        return tweetRepository.getOne(id);
    }

    @Transactional
    public void deleteOne(Long id){
        Tweet tweet = tweetRepository.getOne(id);
        tweetRepository.delete(tweet);
    }

    
}
