package instagram.repository.impl;

import instagram.entity.Comment;
import instagram.entity.Post;
import instagram.repository.CommentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepository implements CommentRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveComment(Long postId, Comment comment) {
        Post post = entityManager.find(Post.class, postId);
        comment.setPost(post);
        post.getComments().add(comment);
        entityManager.persist(comment);
    }
}
