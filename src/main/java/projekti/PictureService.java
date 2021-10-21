package projekti;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService {
    
    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    AccountRepository accountRepository;

    public List<Picture> getUsersPictures(String username){
        Account account = accountRepository.findByUsername(username);

        return pictureRepository.findByAccount(account);
    }

    public Picture addNewPicture(String username, MultipartFile file) throws IOException{
        Account account = accountRepository.findByUsername(username);

        Picture picture = new Picture();
        picture.setAccount(account);
        picture.setContent(file.getBytes());

        pictureRepository.save(picture);

        return picture;
    }
}
