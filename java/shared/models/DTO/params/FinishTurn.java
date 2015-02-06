package shared.models.DTO.params;

public class FinishTurn extends MoveParams {

	public FinishTurn() {
		super("finishTurn");
	}

	public FinishTurn(int playerIndex) {
		super("finishTurn", playerIndex);
	}
}
