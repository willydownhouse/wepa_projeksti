package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LikesController {
    @Autowired
    LikesService likeService;

    @PostMapping("tweets/{id}/likes")
    public String addLike(@PathVariable Long id,Principal principal, HttpServletRequest request){
        String username = principal.getName();
        likeService.like(id, username);

        return "redirect:/tweets";
    }
}
