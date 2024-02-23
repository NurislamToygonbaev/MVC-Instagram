package instagram.repository;

import instagram.entity.User;
import instagram.exception.MyException;

import java.util.List;

public interface UserRepo {
    void signUp(User newUser);
    List<User> findAllUsers();
    User findUserByNameAndPassword(User user) throws MyException;

    User findUser(User currentUser);

    void deleteUserById(Long userId);

    User findUserById(Long userId);

    List<User> searchUsers(String keyword);
}
