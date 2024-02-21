package instagram.service.serviceImpl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.exception.MyException;
import instagram.repository.FollowerRepo;
import instagram.service.FollowerService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final UserService userService;
    private final FollowerRepo followerRepo;

    @Override
    public int getNumberOfSubscribers(Long userId) {
        Follower follower = followerRepo.findById(userId);
        return follower.getSubscribers().isEmpty() ? 0 : follower.getSubscribers().size();
    }
    @Override
    public int getNumberOfSubscriptions(Long userId) {
        Follower follower = followerRepo.findById(userId);
        return follower.getSubscriptions().isEmpty() ? 0 : follower.getSubscriptions().size();
    }
    @Override
    public void addSubscriber(Long userId, Long subscriberId) {
        User user = userService.findUserById(userId);
//        User subscriber = userService.getUser(subscriberId);
//        subscriber.getFollower().setSubscriptions(new ArrayList<>());
        Follower follower = user.getFollower();

        if (follower != null) {
            List<Long> subscribers = follower.getSubscribers();
            if (!subscribers.contains(subscriberId)) {
                subscribers.add(subscriberId);
                followerRepo.save(follower);
//                subscriber.getFollower().getSubscriptions().add(user.getId());
            }
        }
    }

}
