package projekti;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired 
    TweetRepository tweetRepository;

    @GetMapping("/tweets/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id){
        Tweet tweet = tweetRepository.findById(id).get();
        return commentService.allComments(tweet);
    }

    @PostMapping("/tweets/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Comment comment, Principal principal){
        Tweet tweet = tweetRepository.findById(id).get();
        String username = principal.getName();
        return commentService.createComment(comment, username, tweet);
    }
}
