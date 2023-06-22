package pl.agntyp.boardgames.sec;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findCredentialsByName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    private UserDetails createUserDetails(UserCredentialsDto userCredentialsDto) {
        return User.builder()
                .username(userCredentialsDto.getUsername())
                .password(userCredentialsDto.getPassword())
                .roles(userCredentialsDto.getRoles().toArray(String[]::new))
                .build();
    }
}
