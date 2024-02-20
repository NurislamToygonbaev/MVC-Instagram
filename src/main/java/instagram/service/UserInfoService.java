package instagram.service;

import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.exception.MyException;

public interface UserInfoService {
    void updateUserInfo(User user) throws MyException;
    UserInfo findUserById(Long id);
}
