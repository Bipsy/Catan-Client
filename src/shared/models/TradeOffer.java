package shared.models;

public class TradeOffer {
	
	private Player sender;
	private Player receiver;
	private ResourceList resources;
	
	public TradeOffer() {
		// TODO Auto-generated constructor stub
	}

	public Player getSender() {
		return sender;
	}

	public void setSender(Player sender) {
		this.sender = sender;
	}

	public Player getReceiver() {
		return receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

	public ResourceList getResources() {
		return resources;
	}

	public void setResources(ResourceList resources) {
		this.resources = resources;
	}
	
	

}
