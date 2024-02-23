package instagram.service.serviceImpl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.exception.MyException;
import instagram.repository.UserRepo;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public static User cerruntUser;

    @Override
    public void signUp(User newUser) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        follower.setSubscriptions(new ArrayList<>());
        follower.setSubscribers(new ArrayList<>());
        newUser.setFollower(follower);
        newUser.setUserInfo(userInfo);
        userRepo.signUp(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAllUsers();
    }

    @Override
    public User findUserByNameAndPassword(User user) throws MyException {
        cerruntUser =  userRepo.findUserByNameAndPassword(user);
        return cerruntUser;
    }

    @Override
    public User findUser() {
        return userRepo.findUser(cerruntUser);
    }

    @Override
    public List<User> findUserByUserName(String keyword) throws MyException {
        List<User> users = userRepo.searchUsers("%"+keyword+"%");
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(cerruntUser.getUserName())){
                throw new MyException();
            }
        }
        return users;
    }

    @Override
    public void deleteUserById(Long userId){
        userRepo.deleteUserById(userId);
    }

    @Override
    public User findUserById(Long userId) {
       return userRepo.findUserById(userId);
    }

    @Override
    public User getUser(Long userId) throws MyException {
        for (User allUser : findAllUsers()) {
            if (allUser.getId().equals(userId)){
                return allUser;
            }
        }
        throw new MyException();
    }
}
