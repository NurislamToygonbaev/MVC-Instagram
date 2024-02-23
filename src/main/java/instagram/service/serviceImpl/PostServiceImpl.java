package instagram.service.serviceImpl;

import instagram.entity.*;
import instagram.exception.MyException;
import instagram.repository.PostRepo;
import instagram.service.PostService;
import instagram.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final UserService userService;

    @Override
    public void createPost(Post newPOst) throws MyException {
        if (newPOst.getImage() != null){
            Like like = new Like();
            newPOst.setLike(like);
            newPOst.setCreateAt(ZonedDateTime.now().toLocalDate());
            User user = userService.findUser();
            user.getPosts().add(newPOst);
            newPOst.setUser(user);
            postRepo.createPost(newPOst);
        }else {
            throw new MyException();
        }
    }

    @Override
    public Post findById(Long postId) {
        return postRepo.findById(postId);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepo.findAllPosts();
    }

    @Override
    public void updatePOst(Long postId, Post post) {
        User user = userService.findUser();
        user.getPosts().add(post);
        post.setUser(user);
        postRepo.updatePOst(postId, post);
    }

    @Override
    public void deletePostById(Long postId) {
        postRepo.deletePostById(postId);
    }

    @Override
    @Transactional
    public void getLikePost(Long currentUserId, Long postId) {
        Post post = postRepo.findById(postId);

        List<Long> isLikes = post.getLike().getIsLikes();
        if (isLikes.contains(currentUserId)){
            isLikes.remove(currentUserId);
        }else {
            isLikes.add(currentUserId);
        }
    }

    @Override
    public int getNumberOfLikes(Long userId, Long postId) {
        Like like = postRepo.findPost(postId);
        return like.getIsLikes().isEmpty() ? 0 : like.getIsLikes().size();
    }
}
