package shared.models.DTO.params;

public class Soldier extends MoveParams {
	
	private int victimIndex;

	public Soldier() {
		super("Soldier");
		victimIndex = -1;
	}

	public Soldier(int playerIndex, int victimIndex) {
		super("Soldier", playerIndex);
		this.victimIndex = victimIndex;
		
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}
}
