package instagram.service.serviceImpl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.exception.MyException;
import instagram.repository.FollowerRepo;
import instagram.service.FollowerService;
import instagram.service.UserService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public void addSubscriber(Long currentUserId, Long subscriberId) {
        User currentUser = followerRepo.findUserById(currentUserId);
        User subscriberUser = followerRepo.findUserById(subscriberId);

        if (currentUser != null && subscriberUser != null) {
            Follower currentUserFollower = currentUser.getFollower();
            Follower subscriberFollower = subscriberUser.getFollower();

            List<Long> currentUserSubscriptions = currentUserFollower.getSubscriptions();
            List<Long> subscriberSubscribers = subscriberFollower.getSubscribers();

            if (currentUserSubscriptions.contains(subscriberId)) {
                currentUserSubscriptions.remove(subscriberId);
                subscriberSubscribers.remove(currentUserId);
            } else {
                currentUserSubscriptions.add(subscriberId);
                subscriberSubscribers.add(currentUserId);
            }
        }
    }

}
