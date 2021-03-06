package shared.models.DTO;

import com.google.gson.annotations.SerializedName;

/**
 * This class stores the information needed to create a JSON string of a game's
 * state, and is used to facilitate the transfer of data between the server and
 * client.
 *
 * @author Austin
 *
 */
public class ClientModelDTO {

    @SerializedName("bank")
    private ResourceListDTO resources;
    @SerializedName("deck")
    private DevCardListDTO devCards;
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
        this.version = version;
    }

    public ClientModelDTO() {
        // TODO Auto-generated constructor stub
    }

    public ClientModelDTO(ResourceListDTO resources, DevCardListDTO devCards,
            MessageListDTO chat, MessageListDTO log, MapDTO map,
            PlayerDTO[] players, TradeOfferDTO tradeOffer,
            TurnTrackerDTO turnTracker, int winner, int version) {
        this.resources = resources;
        this.devCards = devCards;
        this.chat = chat;
        this.log = log;
        this.map = map;
        this.players = players;
        this.tradeOffer = tradeOffer;
        this.turnTracker = turnTracker;
        this.winner = winner;
        this.version = version;
    }

    public void setBank(ResourceListDTO resources) {
        this.resources = resources;
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
        return devCards;
    }

    public void setDevCards(DevCardListDTO devCards) {
        this.devCards = devCards;
    }

    public ResourceListDTO getResources() {
        return resources;
    }

    public void setResources(ResourceListDTO resources) {
        this.resources = resources;
    }

}
