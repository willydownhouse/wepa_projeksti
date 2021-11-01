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
public class Tweet extends AbstractPersistable<Long>{

    private String text;
    private String createdAt;
    //
    private Integer likes;
    private Integer comments;
    

    @ManyToOne
    private Account account;

}

