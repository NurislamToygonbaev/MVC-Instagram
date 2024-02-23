package instagram.controller;

import instagram.entity.Post;
import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.exception.MyException;
import instagram.repository.UserRepo;
import instagram.repository.impl.UserRepository;
import instagram.service.FollowerService;
import instagram.service.PostService;
import instagram.service.UserInfoService;
import instagram.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/home")
@RequiredArgsConstructor
public class MainController {

    private final UserInfoService userInfoService;
    private final UserService userService;
    private final PostService postService;
    private final FollowerService followerService;

    @GetMapping("/main")
    public String mainPage(Model model){
        List<Post> allPosts = postService.findAllPosts();
        model.addAttribute("allPosts", allPosts);
        return "home-page";
    }

    @GetMapping("/profUser")
    public String profileEdit(Model model) {
        User user = userService.findUser();
        Long id = user.getId();
        int subscribers = followerService.getNumberOfSubscribers(id);
        int subscriptions = followerService.getNumberOfSubscriptions(id);
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("currentUser", user);
        return "profile-page";
    }

    @GetMapping("/editProf")
    public String editUser(Model model){
        User user = userService.findUser();
        model.addAttribute("current", user);
        return "edit-profile";
    }

    @PostMapping("/saveProfiles")
    public String saveProfile(@ModelAttribute("current") User currentUser,
                              Model model) {
        try {
            userInfoService.updateUserInfo(currentUser);
            return "redirect:/home/profUser";
        } catch (DataIntegrityViolationException | MyException e) {
            model.addAttribute("errorMessage", "Duplicate email or username. Please choose another one.");
            return "error-page";
        }
    }

    @GetMapping("/like/{postId}")
    public String Like(@PathVariable Long postId){
        Long currentUserId = getCurrentUserId();
        postService.getLikePost(currentUserId, postId);
        postService.getNumberOfLikes(currentUserId, postId);
        return "redirect:/home/profUser";
    }

    private Long getCurrentUserId() {
        User user = userService.findUser();
        return user.getId();
    }
}