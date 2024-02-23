package instagram.repository;

import instagram.entity.Like;
import instagram.entity.Post;
import instagram.entity.User;

import java.util.List;

public interface PostRepo {
    void createPost(Post newPOst);

    Post findById(Long postId);

    List<Post> findAllPosts();

    void updatePOst(Long postId, Post post);

    void deletePostById(Long postId);

    Like findPost(Long postId);
}
