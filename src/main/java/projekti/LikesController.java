package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class LikesController {
    @Autowired
    LikesService likeService;

    @PostMapping("tweets/{id}/likes")
    public String addLike(@PathVariable Long id,Principal principal){
        String username = principal.getName();
        likeService.like(id, username);

        
        return "redirect:/tweets";
    }
}
