package shared.models.DTO;

/**
 * @author Anna Sokolova
 *
 */
public class UserDTO {

    private String username;
    private String password;

    public UserDTO() {
        this.username = null;
        this.password = null;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
