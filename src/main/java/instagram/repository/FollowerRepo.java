package instagram.repository;

import instagram.entity.Follower;
import instagram.entity.User;

import java.util.Optional;

public interface FollowerRepo {

    Follower findById(Long userId);

    void save(Follower follower);

    Follower findByUserId(Long userId);

    User findUserById(Long currentUserId);
}
