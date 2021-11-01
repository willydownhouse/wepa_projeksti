package projekti;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractPersistable<Long>{
    
    private String text;
    private String createdAt;

    @ManyToOne
    private Account account;
    
    @ManyToOne
    private Tweet tweet;

    
    
}
