package instagram.repository.impl;

import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.repository.UserInfoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserInfoRepository implements UserInfoRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void updateUserInfo(User currentUser) {
        entityManager.merge(currentUser);
    }

    @Override
    public UserInfo findUserById(Long id) {
        return entityManager.find(UserInfo.class, id);
    }
}
