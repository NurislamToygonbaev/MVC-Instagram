package instagram.repository.impl;

import instagram.entity.Like;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.PostRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepository implements PostRepo {

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void createPost(Post newPOst) {
        entityManager.merge(newPOst);
    }

    @Override
    public Post findById(Long postId) {
        return entityManager.find(Post.class, postId);
    }

    @Override
    public List<Post> findAllPosts() {
        return entityManager.createQuery("select p from Post p order by p.id desc ", Post.class)
                .getResultList();
    }

    @Override
    public void updatePOst(Long postId, Post post) {
        Post findPost = entityManager.find(Post.class, postId);
        findPost.setTitle(post.getTitle());
        findPost.setDescription(post.getDescription());
    }

    @Override
    public void deletePostById(Long postId) {
        entityManager.remove(entityManager.find(Post.class, postId));
    }

    @Override
    public Like findPost(Long postId) {
        return entityManager.createQuery("select p.like from Post p where p.id =:postId", Like.class)
                .setParameter("postId", postId)
                .getSingleResult();

    }
}
