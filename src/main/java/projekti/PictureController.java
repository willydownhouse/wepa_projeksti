package projekti;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    
    @Autowired
    PictureService pictureService;

    @GetMapping("/users/{username}/pictures")
    @ResponseBody
    public byte[] usersPictures(@PathVariable String username){
        return pictureService.getUserPicture(username).getContent();
    }

    @PostMapping("/users/{username}/pictures")
    public String savePicture(@PathVariable String username ,@RequestParam("file") MultipartFile file) throws IOException{

        pictureService.addNewPicture(username, file);
        
        return "redirect:/users/{username}";
    }
}
