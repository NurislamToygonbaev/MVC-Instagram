package instagram.controller;

import instagram.entity.User;
import instagram.exception.MyException;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reg")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/page")
    public String signPage(Model model) {
        model.addAttribute("login", new User());
        return "sign-page";
    }

    @GetMapping("/new")
    public String createAccount(Model model) {
        model.addAttribute("newUser", new User());
        return "new-user";
    }

    @PostMapping("/save")
    public String signUp(@ModelAttribute("newUser") User user, Model model) {
        try {
            userService.signUp(user);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Duplicate email or username. Please choose another one.");
            return "error-page";
        }
        return "redirect:/reg/page";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("login") User user, Model model) {
        try {
            User currentUser = userService.findUserByNameAndPassword(user);
            if (currentUser != null) {
                model.addAttribute("currentUser", currentUser);
                return "redirect:/home/main";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "error-page";
            }
        } catch (MyException e) {
            return "error-page";
        }
    }

    @GetMapping("/delUserWIthPass/{userId}")
    public String passwordForDelete(@PathVariable Long userId,
                                    Model model) {
        try {
            User findUser = userService.getUser(userId);
            model.addAttribute("curUser", findUser);
            model.addAttribute("passwordForDelete", new User());
            return "deleteUser-page";
        } catch (MyException e) {
            return "error-page";
        }
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUserById(@PathVariable Long userId,
                                 @ModelAttribute("passwordForDelete") User user) {
        User currentUser = userService.findUser();
        if (currentUser.getPassword().equals(user.getPassword())) {
            userService.deleteUserById(userId);
            return "redirect:/reg/page";
        } else {
            return "error-page";
        }
    }
}

