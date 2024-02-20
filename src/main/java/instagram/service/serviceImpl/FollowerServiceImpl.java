package instagram.service.serviceImpl;

import instagram.entity.Follower;
import instagram.repository.FollowerRepo;
import instagram.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {

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
        Follower follower = followerRepo.findByUserId(userId);

        List<Long> subscribers = follower.getSubscribers();
        if (!subscribers.contains(subscriberId)) {
            subscribers.add(subscriberId);
            follower.setSubscribers(subscribers);
            followerRepo.save(follower);
        }
    }
}
