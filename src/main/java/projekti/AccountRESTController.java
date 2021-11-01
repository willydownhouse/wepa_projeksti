package projekti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRESTController {
    @Autowired
    AccountService accountService;

    @GetMapping("/users")
    public List<Account> getAllUsers(@RequestParam String username){
        return accountService.listByUsername(username);
    }
}
