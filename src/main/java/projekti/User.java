package projekti;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Entity;
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
    
    @OneToMany
    private List<Seuraaja> seuraajat;
}


