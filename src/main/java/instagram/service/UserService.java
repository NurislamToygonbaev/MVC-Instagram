package instagram.service;

import instagram.entity.User;
import instagram.exception.MyException;

import java.util.List;

public interface UserService {
    void signUp(User newUser);
    List<User> findAllUsers();
    User findUserByNameAndPassword(User user) throws MyException;

    User findUser();

    User findUserByUserName(User user) throws MyException;

    void deleteUserById(Long userId);
}
