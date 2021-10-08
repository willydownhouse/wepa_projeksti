package projekti;
import java.time.LocalDate;

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
    private LocalDate createdAt;

    @ManyToOne
    Tweet tweet;

    @ManyToOne
    private User user;
}
