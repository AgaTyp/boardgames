package pl.agntyp.boardgames.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.agntyp.boardgames.security.UserEditInfoDto;
import pl.agntyp.boardgames.security.UserInfoDto;
import pl.agntyp.boardgames.security.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-panel")
//    String userForm(@RequestParam String username, Model model) {
    String userForm(Model model, Principal principal) {
//        Optional<UserInfoDto> user = userService.findUserByName(username);
        Optional<UserInfoDto> user = userService.findUserByName(principal.getName());
        user.ifPresent(userInfoDto -> model.addAttribute("user", userInfoDto));

        return "user-panel";
    }

    @PostMapping("/edit_user")
    String edit(UserEditInfoDto userInfoDto, Principal principal) {
        userService.editInfo(userInfoDto);
//        return "redirect:/user-panel?username=" + userInfoDto.getUsername();
        return "redirect:/";
    }

    @PostMapping("/change_password")
    String changePassword(@RequestParam String newPassword, RedirectAttributes redirectAttributes) {
        userService.changeCurrentUserPassword(newPassword);
        return "redirect:logout?changed";
    }

//    @GetMapping("/confirmation")
//    String registrationConfirmation() {
//        return "registration-confirmation";
//    }
}
