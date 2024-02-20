package instagram.service.serviceImpl;

import instagram.entity.Comment;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.CommentRepo;
import instagram.service.CommentService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final UserService userService;
    @Override
    public void saveComment(Long postId, Comment comment) {
        comment.setCreateAt(ZonedDateTime.now().toLocalDate());
        User currentUser = userService.findUser();
        currentUser.getComments().add(comment);
        comment.setUser(currentUser);
        commentRepo.saveComment(postId, comment);
    }
}
