package projekti;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;

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
