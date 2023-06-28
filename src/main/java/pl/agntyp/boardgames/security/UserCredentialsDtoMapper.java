package pl.agntyp.boardgames.security;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    static UserCredentialsDto map(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(username, password, roles);
    }
}
