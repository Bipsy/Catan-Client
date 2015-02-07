package shared.models.DTO;

import shared.models.TurnTracker;

/**
 * This class stores the information needed to create a JSON string of a game's
 * state, and is used to facilitate the transfer of data between the server and
 * client.
 *
 * @author Austin
 *
 */
public class ClientModelDTO {

    private ResourceListDTO bank;
    private DevCardListDTO deck;
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

    /**
     * Constructor with version number
     *
     * @param version
     */
    public ClientModelDTO(int version) {
        super();
        bank = new ResourceListDTO();
        deck = new DevCardListDTO();
        chat = new MessageListDTO();
        log = new MessageListDTO();
        map = new MapDTO();
        turnTracker = new TurnTrackerDTO();
        this.version = version;
        winner = -1;
    }

    public ClientModelDTO() {
    	bank = new ResourceListDTO();
        deck = new DevCardListDTO();
        chat = new MessageListDTO();
        log = new MessageListDTO();
        map = new MapDTO();
        turnTracker = new TurnTrackerDTO();
        version = 0;
        winner = -1;
    }

    public void setBank(ResourceListDTO resources) {
        this.bank = resources;
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

    public DevCardListDTO getDevCards() {
        return deck;
    }

    public void setDevCards(DevCardListDTO devCards) {
        this.deck = devCards;
    }

    public ResourceListDTO getResources() {
        return bank;
    }

    public void setResources(ResourceListDTO resources) {
        this.bank = resources;
    }

}
