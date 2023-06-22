package pl.agntyp.boardgames.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.agntyp.boardgames.sec.UserInfoDto;
import pl.agntyp.boardgames.sec.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String adminPanel(Model model) {
//        List<String> allUserNames = userService.findAllUsersNames();
//        model.addAttribute("userNames", allUserNames);
        List<UserInfoDto> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @GetMapping("/delete-user")
    String deleteUser(@RequestParam String username) {
        userService.deleteUserByName(username);
        return "redirect:/admin";
    }
}