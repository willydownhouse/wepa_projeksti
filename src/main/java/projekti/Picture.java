package projekti;

import javax.persistence.ManyToOne;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Lob;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture extends AbstractPersistable<Long> {
    
    @ManyToOne
    Account account;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
