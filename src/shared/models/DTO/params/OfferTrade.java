package shared.models.DTO.params;

import shared.models.DTO.ResourceListDTO;

public class OfferTrade extends MoveParams {

	private ResourceListDTO offer;
	public OfferTrade() {
		super("offerTrade");
		offer = null;
	}

	public OfferTrade(int playerIndex, ResourceListDTO offer) {
		super("offerTrade", playerIndex);
		setOffer(offer);
	}

	public ResourceListDTO getOffer() {
		return offer;
	}

	public void setOffer(ResourceListDTO offer) {
		this.offer = offer;
	}

}
