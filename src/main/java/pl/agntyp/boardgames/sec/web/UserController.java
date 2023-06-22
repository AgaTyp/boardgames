package pl.agntyp.boardgames.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.agntyp.boardgames.sec.UserInfoDto;
import pl.agntyp.boardgames.sec.UserRegistrationDto;
import pl.agntyp.boardgames.sec.UserService;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-panel")
    String userForm(@RequestParam String username, Model model) {
        Optional<UserInfoDto> user = userService.findUserByName(username);
        user.ifPresent(userInfoDto -> model.addAttribute("user", userInfoDto));

        return "user-panel";
    }

    @PostMapping("/edit_user")
    String edit(UserInfoDto userInfoDto) {
        userService.edit(userInfoDto);
//        return "redirect:/user-panel?username=" + userInfoDto.getUsername();
        return "redirect:/";
    }

//    @GetMapping("/confirmation")
//    String registrationConfirmation() {
//        return "registration-confirmation";
//    }
}
