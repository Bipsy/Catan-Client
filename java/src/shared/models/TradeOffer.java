package shared.models;

import shared.models.DTO.TradeOfferDTO;

public class TradeOffer {

    private int sender;
    private int receiver;
    private ResourceList resources;

    public TradeOffer() {
        // TODO Auto-generated constructor stub
    }

    public TradeOffer(TradeOfferDTO tradeOffer) {
        this.setSender(tradeOffer.getSender());
        this.setReceiver(tradeOffer.getReceiver());
        this.resources = new ResourceList(tradeOffer.getOffer());
    }

    public ResourceList getResources() {
        return resources;
    }

    public void setResources(ResourceList resources) {
        this.resources = resources;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

}
