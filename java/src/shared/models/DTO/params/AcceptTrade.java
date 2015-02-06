package shared.models.DTO.params;

public class AcceptTrade extends MoveParams {

	private boolean willAccept;
	
	public AcceptTrade() {
		super("acceptTrade");
		setWillAccept(false);
	}

	public AcceptTrade(int playerIndex, boolean willAccept) {
		super("acceptTrade", playerIndex);
		this.willAccept = willAccept;
	}

	public boolean isWillAccept() {
		return willAccept;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}	
}
