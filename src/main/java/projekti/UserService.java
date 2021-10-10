package projekti;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> list(){
        return userRepository.findAll();
    }

    public void createUser(String name, String email, String password){
        User a = new User();
        a.setName(name);
        a.setEmail(email);
        a.setPassword(password);
        a.setTweets(new ArrayList<>());
        a.setComments(new ArrayList<>());
        a.setSeuraajat(new ArrayList<>());
        a.setSeuraan(new ArrayList<>());
        

        if(!userRepository.findAll().contains(a)){
            userRepository.save(a);
        }

    }
}
