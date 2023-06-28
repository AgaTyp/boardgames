package pl.agntyp.boardgames.security;

public class UserInfoDtoMapper {
    static UserInfoDto map(User user) {
        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(userRole -> userRole.getName().equals("ADMIN"));

        return new UserInfoDto(username, password, firstName, lastName, isAdmin);
    }

//    User map(UserInfoDto dto) {
//        User user = new User();
//        user.setUsername(dto.getUsername());
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        user.setPassword(dto.getPassword());
//
//        String username = dto.getUsername();
//        String firstName = dto.getFirstName();
//        String lastName = dto.getLastName();
//        String password = dto.getPassword();
//        boolean isAdmin = dto.isAdmin();
////                .stream()
////                .anyMatch(userRole -> userRole.getName().equals("ADMIN"));
//
//        return new UserInfoDto(username, password, firstName, lastName, isAdmin);
//    }
}
