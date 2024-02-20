package instagram.repository.impl;

import instagram.entity.Follower;
import instagram.repository.FollowerRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class FollowerRepository implements FollowerRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Follower findById(Long userId) {
        return entityManager.createQuery("select u.follower from User u where u.id = " +
                        ":userId", Follower.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public void save(Follower follower) {
        entityManager.merge(follower);
    }

    @Override
    public Follower findByUserId(Long userId) {
        return entityManager.createQuery("select u.follower from User u " +
                        " where u.id =:userId", Follower.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

}
