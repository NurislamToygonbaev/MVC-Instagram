package instagram.service;

import instagram.entity.User;
import instagram.exception.MyException;

import java.util.List;

public interface UserService {
    void signUp(User newUser);
    List<User> findAllUsers();
    User findUserByNameAndPassword(User user) throws MyException;

    User findUser();

    List<User> findUserByUserName(String keyword) throws MyException;

    void deleteUserById(Long userId);

    User findUserById(Long userId) ;

    User getUser(Long subId) throws MyException;

}
