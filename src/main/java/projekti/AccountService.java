package projekti;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Account> list(){
        return accountRepository.findAll();
    }

    public List<Account> listByUsername(String keyword){
        return accountRepository.findByUsernameContaining(keyword);
    }

    public void createUser(String username, String email, String password){

        Account accountAlreadyExists = accountRepository.findByUsername(username);


        if(accountAlreadyExists == null){
            Account a = new Account();
            a.setUsername(username);
            a.setEmail(email);
            a.setPassword(passwordEncoder.encode(password));
            
            accountRepository.save(a);
        }



    }
}
