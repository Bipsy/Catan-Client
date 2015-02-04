package shared.models.DTO.params;

public class MoveParams {
	
	private String type;
	private int playerIndex;

	public MoveParams(String type) {
		setType(type);
		playerIndex = -1;
	}

	public MoveParams(String type, int playerIndex) {
		setType(type);
		setPlayerIndex(playerIndex);
	}

	public String getType() {
		return type;
	}

	public boolean setType(String type) {
		if(this.type == null) {
			this.type = type;
			return true;
		}
		return false;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
}
