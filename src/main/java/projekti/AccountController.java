package projekti;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired 
    private TweetService tweetService;

    @GetMapping("/login")
    public String showLoginPage(){
        return "index";
    }

    @PostMapping("/signup")
    public String create(@RequestParam String username, @RequestParam String email, @RequestParam String password, HttpServletRequest request) throws ServletException{
        System.out.println("create user next");
        accountService.createUser(username, email, password);
        request.login(username, password);
        return "redirect:/tweets";
    }

    @GetMapping("/users/{username}")
    public String showMyPage(Model model, @PathVariable String username){
        model.addAttribute("tweets", tweetService.getAllByAccount(username));
        return "myPage";
    }

    
}
