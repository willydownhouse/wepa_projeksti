package projekti;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractPersistable<Long>{
    
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Tweet> tweets = new ArrayList<>();
    
    @OneToMany
    @JoinTable(name="seuraajat")
    private List<User> seuraajat = new ArrayList<>();

    @OneToMany
    @JoinTable(name="seuraan")
    private List<User> seuraan = new ArrayList<>();
}


