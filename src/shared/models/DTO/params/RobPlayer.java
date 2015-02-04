package shared.models.DTO.params;

import shared.locations.HexLocation;

public class RobPlayer extends MoveParams {
	private int victimIndex;
	private HexLocation location;

	public RobPlayer() {
		super("robPlayer");
		victimIndex = -1;
		location = null;
	}

	public RobPlayer(int playerIndex, int victimIndex, HexLocation location) {
		super("robPlayer", playerIndex);
		this.victimIndex = victimIndex;
		this.location = location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}
}
