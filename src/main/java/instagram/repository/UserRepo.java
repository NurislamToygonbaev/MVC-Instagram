package instagram.repository;

import instagram.entity.User;
import instagram.exception.MyException;

import java.util.List;

public interface UserRepo {
    void signUp(User newUser);
    List<User> findAllUsers();
    User findUserByNameAndPassword(User user) throws MyException;

    User findUser(User currentUser);

    User findUserByUserName(User user);

    void deleteUserById(Long userId);

    User findUserById(Long userId);

}
