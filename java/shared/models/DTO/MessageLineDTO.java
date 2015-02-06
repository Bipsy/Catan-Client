package shared.models.DTO;

/**
 * This class stores the information needed to create a JSON string of a message
 * line, and is used to facilitate the transfer of data between the server and
 * client.
 *
 * @author Austin
 *
 */
public class MessageLineDTO {

    private String message;
    private String source;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
