package instagram.repository.impl;

import instagram.entity.User;
import instagram.exception.MyException;
import instagram.repository.UserRepo;
import instagram.service.UserService;
import instagram.service.serviceImpl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository implements UserRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void signUp(User newUser) {
        entityManager.persist(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User findUserByNameAndPassword(User newUser) throws MyException {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(newUser.getUserName()) &&
                    user.getPassword().equals(newUser.getPassword())) {
                return user;
            }
        }
        throw new MyException("Login or password Incorrect");
    }

    @Override
    public User findUser(User currentUser) {
        return entityManager.createQuery("select u from User u " +
                        " where u.id =:id", User.class)
                .setParameter("id", currentUser.getId())
                .getSingleResult();
    }

    @Override
    public User findUserByUserName(User user) {
        return entityManager.createQuery("select u from User u " +
                        " where u.userName = :userName", User.class)
                .setParameter("userName", user.getUserName())
                .getSingleResult();
    }

    @Override
    public void deleteUserById(Long userId) {
        entityManager.remove(entityManager.find(User.class, userId));
    }

    @Override
    public User findUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }

}
