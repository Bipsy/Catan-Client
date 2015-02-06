package shared.models.DTO;

public class MessageListDTO {

    private MessageLineDTO[] lines;

    public MessageListDTO() {
        lines = null;
    }

    public MessageLineDTO[] getLines() {
        return lines;
    }

    public void setLines(MessageLineDTO[] lines) {
        this.lines = lines;
    }

}
