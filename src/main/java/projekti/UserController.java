package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public void allUsers(Model model){
        model.addAttribute("users", this.userService.list());
    }

    @PostMapping("/signup")
    public String create(@RequestParam String name, @RequestParam String email, @RequestParam String password){
        userService.createUser(name, email, password);
        return "redirect:/tweets";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String email, @RequestParam String password){
        System.out.println("LETS LOGGIN!!!!");
        return "redirect:/tweets";
    }
}
