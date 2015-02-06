package shared.models.DTO.params;

import shared.models.DTO.ResourceListDTO;

public class OfferTrade extends MoveParams {

	private ResourceListDTO offer;
	int receiver;
	
	public OfferTrade() {
		super("offerTrade");
		offer = null;
		receiver = -1;
	}

	public OfferTrade(int playerIndex, ResourceListDTO offer, int receiver) {
		super("offerTrade", playerIndex);
		setOffer(offer);
		setReceiver(receiver);
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public ResourceListDTO getOffer() {
		return offer;
	}

	public void setOffer(ResourceListDTO offer) {
		this.offer = offer;
	}

}
