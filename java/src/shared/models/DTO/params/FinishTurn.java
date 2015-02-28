package shared.models.DTO.params;

public class FinishTurn extends MoveParams {

    public FinishTurn(int playerIndex) {
        super("finishTurn", playerIndex);
    }
}
