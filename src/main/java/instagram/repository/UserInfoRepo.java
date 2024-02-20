package instagram.repository;

import instagram.entity.User;
import instagram.entity.UserInfo;

public interface UserInfoRepo {

    void updateUserInfo(User user);

    UserInfo findUserById(Long id);
}
