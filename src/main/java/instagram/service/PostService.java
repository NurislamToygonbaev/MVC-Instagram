package instagram.service;

import instagram.entity.Post;
import instagram.exception.MyException;

import java.util.List;

public interface PostService {
    void createPost(Post newPOst) throws MyException;

    Post findById(Long postId);

    List<Post> findAllPosts();

    void updatePOst(Long postId, Post post);

    void deletePostById(Long postId) throws MyException;

}
