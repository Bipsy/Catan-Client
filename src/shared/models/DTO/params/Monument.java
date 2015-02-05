package shared.models.DTO.params;

public class Monument extends MoveParams {

	public Monument() {
		super("Monument");
	}

	public Monument(int playerIndex) {
		super("Monument", playerIndex);
	}
}
