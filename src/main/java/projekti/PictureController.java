package projekti;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    
    @Autowired
    PictureService pictureService;

    @GetMapping("/users/{username}/pictures")
    public List<Picture> usersPictures(@PathVariable String username){
        return pictureService.getUsersPictures(username);
    }

    @PostMapping("/users/{username}/pictures")
    public String savePicture(@PathVariable String username ,@RequestParam("file") MultipartFile file) throws IOException{
        pictureService.addNewPicture(username, file);
        return "redirect:/users/{username}";
    }
}
