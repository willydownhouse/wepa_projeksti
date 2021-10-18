package projekti;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AccountRepository accountRepository;

    public List<Comment> allComments(Tweet tweet){
        return commentRepository.findByTweet(tweet);
    }

    public Comment createComment(Comment comment, String username, Tweet tweet){
        Account account = accountRepository.findByUsername(username);

        comment.setAccount(account);
        comment.setTweet(tweet);

        return commentRepository.save(comment);   
    }
}
