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

    
}
