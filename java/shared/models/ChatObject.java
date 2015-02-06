package shared.models;

import java.util.ArrayList;
import java.util.List;

import shared.models.DTO.MessageLineDTO;

public class ChatObject {

    List<Message> log;
    List<Message> chat;

    public ChatObject() {
        // TODO Auto-generated constructor stub
    }

    public ChatObject(MessageLineDTO[] chat, MessageLineDTO[] log) {
        this.log = convertToMessages(chat);
        this.log = convertToMessages(log);
    }

    private List<Message> convertToMessages(MessageLineDTO[] lines) {
        List<Message> temp = new ArrayList<Message>();

        for (int i = 0; i < lines.length; i++) {
            temp.add(new Message(lines[i]));
        }

        return temp;
    }

}
