package shared.models.DTO.params;

import shared.locations.HexLocation;

public class Soldier extends MoveParams {

    private static final String SOLDIER = "Soldier";
    private int victimIndex;
    private HexLocation location;

    public Soldier() {
        super(SOLDIER);
        victimIndex = -1;
        location = null;
    }

    public Soldier(int playerIndex, int victimIndex, HexLocation location) {
        super(SOLDIER, playerIndex);
        this.victimIndex = victimIndex;
        this.location = location;

    }

    public int getVictimIndex() {
        return victimIndex;
    }

    public void setVictimIndex(int victimIndex) {
        this.victimIndex = victimIndex;
    }
}
