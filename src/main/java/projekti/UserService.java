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
        a.name = name;
        a.email = email;
        a.password = password;
        //a.seuraajat = new ArrayList<>();

        if(!userRepository.findAll().contains(a)){
            userRepository.save(a);
        }

    }
}
