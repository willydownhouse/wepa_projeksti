package projekti;

import java.security.Principal;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.web.WebAttributes;
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

    @Autowired
    PictureService pictureService;

    @GetMapping("/login")
    public String showLoginPage(){
        return "index";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            // AuthenticationException ex = (AuthenticationException) session
            //         .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            BadCredentialsException ex = (BadCredentialsException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                //errorMessage = ex.getMessage();
                errorMessage = "Bad credentials. Please try again!";
            }
        }
        model.addAttribute("errorMessage", errorMessage);
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
    public String showMyPage(Model model, @PathVariable String username, Principal principal){
        model.addAttribute("tweets", tweetService.getAllByAccount(username));
        model.addAttribute("currentUser", principal.getName());
        model.addAttribute("thisUsersPage", username);
        
        model.addAttribute("profilePicture", pictureService.getUserPicture(username));
        return "myPage";
    }

    
}
