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
    public User findUserByUserName(User user) throws MyException {
        List<User> allUsers = userRepo.findAllUsers();
        for (User allUser : allUsers) {
            if (allUser.getUserName().equalsIgnoreCase(user.getUserName())){
               return userRepo.findUserByUserName(user);
            }
        }
        throw new MyException();
    }

    @Override
    public void deleteUserById(Long userId){
        userRepo.deleteUserById(userId);
    }
}
