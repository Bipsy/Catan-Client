package shared.models.DTO.params;

public class SendChat extends MoveParams {
	
	private String content;

	public SendChat() {
		super("sendChat");
		content = null;
		// TODO Auto-generated constructor stub
	}

	public SendChat(int playerIndex, String content) {
		super("sendChat", playerIndex);
		this.content = content;		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
