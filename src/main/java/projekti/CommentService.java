package projekti;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> allComments(){
        return commentRepository.findAll();
    }
}
