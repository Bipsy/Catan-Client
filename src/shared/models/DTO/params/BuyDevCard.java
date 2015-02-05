package shared.models.DTO.params;

public class BuyDevCard extends MoveParams {

	public BuyDevCard() {
		super("buyDevCard");
	}
	
	public BuyDevCard(int playerIndex) {
		super("buyDevCard", playerIndex);
	}

}
