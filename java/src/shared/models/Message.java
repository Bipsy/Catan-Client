package shared.models;

import shared.models.DTO.MessageLineDTO;

public class Message {

    private String message;
    private String source;

    public Message(MessageLineDTO line) {
        this.message = line.getMessage();
        this.source = line.getSource();
    }

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
