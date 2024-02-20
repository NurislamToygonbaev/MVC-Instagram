package instagram.repository;

import instagram.entity.Follower;

import java.util.Optional;

public interface FollowerRepo {

    Follower findById(Long userId);

    void save(Follower follower);

    Follower findByUserId(Long userId);
}
