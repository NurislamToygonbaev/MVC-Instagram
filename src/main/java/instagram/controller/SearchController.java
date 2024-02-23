package instagram.controller;

import instagram.entity.User;
import instagram.exception.MyException;
import instagram.service.FollowerService;
import instagram.service.PostService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;
    private final FollowerService followerService;
    private final PostService postService;

    @GetMapping("/createSearch")
    public String createSearch() {
        return "search-page";
    }

    @PostMapping("/mSearch")
    public String pageTOSearch(@RequestParam String keyword, Model model) {
        try {
            List<User> findUsers = userService.findUserByUserName(keyword);
            model.addAttribute("users", findUsers);
            return "search-page";
        } catch (MyException e) {
            return "redirect:/home/profUser";
        }
    }

    @GetMapping("/addSubscriber/{userId}")
    public String addSubscriber(@PathVariable Long userId) {
        Long currentUserId = getCurrentUserId();
        followerService.addSubscriber(currentUserId, userId);
        return "redirect:/home/profUser";
    }

    @GetMapping("/likes/{postId}")
    public String isLike(@PathVariable Long postId){
        Long currentUserId = getCurrentUserId();
        postService.getLikePost(currentUserId, postId);
        postService.getNumberOfLikes(currentUserId, postId);
        return "redirect:/search/mSearch";
    }

    private Long getCurrentUserId() {
        User user = userService.findUser();
        return user.getId();
    }
}
