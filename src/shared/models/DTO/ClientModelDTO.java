package shared.models.DTO;

public class ClientModelDTO {
	private ResourceListDTO bank;
	private MessageListDTO chat;
	private MessageListDTO log;
	private MapDTO map;
	private PlayerDTO[] players;
	/**
	 * optional
	 */
	private TradeOfferDTO tradeOffer;
	private TurnTrackerDTO turnTracker;
	/**
	 * gets incremented every time someone makes a move.
	 */
	private int version;
	/**
	 * if it is -1, there is no winner yet. Else if it is between 0 - 3, there
	 * is a winner
	 */
	private int winner;
	
	public ResourceListDTO getBank() {
		return bank;
	}
	
	public void setBank(ResourceListDTO bank) {
		this.bank = bank;
	}

	public MessageListDTO getChat() {
		return chat;
	}

	public void setChat(MessageListDTO chat) {
		this.chat = chat;
	}

	public MessageListDTO getLog() {
		return log;
	}

	public void setLog(MessageListDTO log) {
		this.log = log;
	}

	public MapDTO getMap() {
		return map;
	}

	public void setMap(MapDTO map) {
		this.map = map;
	}

	public PlayerDTO[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerDTO[] players) {
		this.players = players;
	}

	public TradeOfferDTO getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(TradeOfferDTO tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	public TurnTrackerDTO getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTrackerDTO turnTracker) {
		this.turnTracker = turnTracker;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	
	
}
