package projekti;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String showcase(Model model) {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("*")
    public String error(Model model) {
        return "index";
    }
}



