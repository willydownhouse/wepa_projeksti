package projekti;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private CommentService commentService;
    
    @GetMapping("/tweets")
    public String allTweets(Model model, Principal principal){
        String username = principal.getName();
        System.out.println("Current user");
        System.out.println(username);
        model.addAttribute("tweets", tweetService.getAll());
        model.addAttribute("username", username);
        return "tweets";
    }

    @PostMapping("/tweets")
    public String add(@RequestParam String text, Principal principal){
        tweetService.createTweet(text, principal);
        return "redirect:/tweets";
    }

    // @GetMapping("/tweets/{id}/comments")
    // public String tweetComments(Model model, @PathVariable Long id){
    //     Tweet tweet = tweetService.getById(id);


    //     System.out.println("Tweet");
    //     System.out.println(tweet);
        
    //     model.addAttribute("comments", commentService.allComments(tweet));
    //     return "tweets";
    // }

    // @PostMapping("/tweets/{id}/comments")
    // public String addComment(@PathVariable Long id){
    //     return "redirect:/tweets";
    // }

    
}
