package pl.agntyp.boardgames.sec;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_AUTHORITY = "ROLE_ADMIN";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserCredentialsDto> findCredentialsByName(String username) {
        return userRepository.findByUsername(username)
                .map(UserCredentialsDtoMapper::map);
    }

    public Optional<UserInfoDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserInfoDtoMapper::map);
    }

    public Optional<UserInfoDto> findUserByName(String username) {
        return userRepository.findByUsername(username)
                .map(UserInfoDtoMapper::map);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserInfoDto> findAllUsers() {
        return userRepository.findAll()
                .stream().map(UserInfoDtoMapper::map)
                .toList();
    }

    public List<String> findAllUsersNames() {
        return userRepository.findAllByRoles_Name(USER_ROLE)
                .stream()
                .map(User::getUsername)
                .toList();
    }

    @Transactional
    public void deleteUserByName(String username) {
        if (isCurrentUserAdmin() && isUser(username)) {
            userRepository.deleteByUsername(username);
        }
    }

    private boolean isUser(String username) {
        return findAllUsersNames()
                .stream()
                .anyMatch(user -> user.equals(username));
    }

    private boolean isCurrentUserAdmin() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(ADMIN_AUTHORITY));
    }

    @Transactional
    public void register(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setUsername(registration.getUsername());
        String passwordHash = passwordEncoder.encode(registration.getPassword());
        user.setPassword(passwordHash);
        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> user.getRoles().add(role),
                () -> { throw new NoSuchElementException();
                }
        );
        userRepository.save(user);
    }

    @Transactional
    public void edit(UserInfoDto edition) {
        Optional<User> user = findUserByUsername(edition.getUsername());
        user.ifPresent(
                u -> {
                    u.setUsername(edition.getUsername());
                    u.setFirstName(edition.getFirstName());
                    u.setLastName(edition.getLastName());
                    u.setPassword(passwordEncoder.encode(edition.getPassword()));
                    userRepository.save(u);
                }
        );

//        user.setFirstName(registration.getFirstName());
//        user.setLastName(registration.getLastName());
//        user.setUsername(registration.getUsername());
//        String passwordHash = passwordEncoder.encode(registration.getPassword());
//        user.setPassword(passwordHash);
//        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
//        userRole.ifPresentOrElse(
//                role -> user.getRoles().add(role),
//                () -> { throw new NoSuchElementException();
//                }
//        );
//        userRepository.save(user);
    }
}
