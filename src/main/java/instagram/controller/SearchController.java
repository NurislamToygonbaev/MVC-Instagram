package instagram.controller;

import instagram.entity.User;
import instagram.exception.MyException;
import instagram.service.FollowerService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;
    private final FollowerService followerService;

    @GetMapping("/createSearch")
    public String createSearch(Model model){
        model.addAttribute("userName", new User());
        return "search-page";
    }

    @PostMapping("/mSearch")
    public String pageTOSearch(@ModelAttribute("userName") User user,
                               Model model){
        try {
           User findUser = userService.findUserByUserName(user);
            model.addAttribute("currentUser", findUser);
            return "search-page";
        } catch (MyException e) {
            return "error-page";
        }
    }

    @PostMapping("/addSubscriber")
    public String addSubscriber(@RequestParam("subscriberId") Long subscriberId) {
        Long userId = getCurrentUserId();
        followerService.addSubscriber(userId, subscriberId);
        return "redirect:/home/profUser";
    }

    private Long getCurrentUserId() {
        User user = userService.findUser();
        return user.getId();
    }
}
