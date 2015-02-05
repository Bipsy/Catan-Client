package shared.models.DTO.params;

import shared.models.DTO.ResourceListDTO;

public class DiscardCards extends MoveParams {

	private ResourceListDTO discardedCards;
	
	public DiscardCards() {
		super("discardCards");
		this.discardedCards = null;
	}

	public DiscardCards(int playerIndex, ResourceListDTO discardedCards) {
		super("discardCards", playerIndex);
		this.discardedCards = discardedCards;
	}

	public ResourceListDTO getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(ResourceListDTO discardedCards) {
		this.discardedCards = discardedCards;
	}

}
