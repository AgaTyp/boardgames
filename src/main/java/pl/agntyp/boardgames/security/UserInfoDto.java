package pl.agntyp.boardgames.security;

public class UserInfoDto {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean isAdmin;

    public UserInfoDto(String username, String password, String firstName, String lastName, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
