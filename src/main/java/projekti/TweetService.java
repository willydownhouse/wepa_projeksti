package projekti;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
    
    @Autowired
    TweetRepository tweetRepository;

    public List<Tweet> getAll(){
        return tweetRepository.findAll();
    }

    
}
