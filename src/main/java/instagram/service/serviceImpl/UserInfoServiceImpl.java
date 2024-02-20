package instagram.service.serviceImpl;

import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.exception.MyException;
import instagram.repository.UserInfoRepo;
import instagram.service.UserInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepo userInfoRepo;

    @Override
    public void updateUserInfo(User user) throws MyException {
        if (user != null) {
            UserServiceImpl.cerruntUser = user;
            userInfoRepo.updateUserInfo(user);
        } else {
            throw new MyException();
        }
    }

    @Override
    public UserInfo findUserById(Long id) {
        return userInfoRepo.findUserById(id);
    }
}
