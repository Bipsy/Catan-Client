package shared.models.DTO.params;

import shared.locations.HexLocation;

public class Soldier extends MoveParams {

    private static final String SOLDIER = "Soldier";
    private int victimIndex;
    private HexLocation location;

    public Soldier() {
        super(SOLDIER);
        victimIndex = -1;
        setLocation(null);
    }

    public Soldier(int playerIndex, int victimIndex, HexLocation location) {
        super(SOLDIER, playerIndex);
        this.victimIndex = victimIndex;
        this.setLocation(location);

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
