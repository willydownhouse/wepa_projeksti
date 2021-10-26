package projekti;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService {
    
    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    AccountRepository accountRepository;

    
    public Picture getUserPicture(String username){
        Account account = accountRepository.findByUsername(username);

        return pictureRepository.findByAccount(account);
           
    }

    @Transactional
    public void addNewPicture(String username, MultipartFile file) throws IOException{
        Account account = accountRepository.findByUsername(username);

        Picture picture = new Picture();
        picture.setAccount(account);
        picture.setContent(file.getBytes());

        Picture exists = pictureRepository.findByAccount(account);

        if(exists == null){
            pictureRepository.save(picture);
        }else {
            pictureRepository.deleteByAccount(account);

            pictureRepository.save(picture);
        }
    
    }
}
