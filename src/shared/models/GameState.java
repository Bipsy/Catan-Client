package shared.models;

public class GameState {
	private Bank bank;
	private ChatObject chat;
	private Map map;
	private UserManager userManager;
	private TradeOffer tradeOffer;
	private int version;
	private int winner;
	public GameState() {
		// TODO Auto-generated constructor stub
		winner = -1;
		version = 0;
	}

	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public ChatObject getChat() {
		return chat;
	}
	public void setChat(ChatObject chat) {
		this.chat = chat;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
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
