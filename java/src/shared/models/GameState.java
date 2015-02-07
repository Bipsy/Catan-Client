package shared.models;

import shared.models.DTO.params.BuildCity;
import shared.models.DTO.params.BuildRoad;
import shared.models.DTO.params.BuildSettlement;
import shared.models.DTO.params.BuyDevCard;
import shared.models.DTO.params.DiscardCards;
import shared.models.DTO.params.FinishTurn;
import shared.models.DTO.params.MaritimeTrade;
import shared.models.DTO.params.Monopoly;
import shared.models.DTO.params.Monument;
import shared.models.DTO.params.OfferTrade;
import shared.models.DTO.params.RoadBuilding;
import shared.models.DTO.params.RobPlayer;
import shared.models.DTO.params.RollNumber;
import shared.models.DTO.params.Soldier;
import shared.models.DTO.params.YearOfPlenty;

public class GameState {

    private Bank bank;
    private ChatObject chat;
    private Board map;
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

    public Board getMap() {
        return map;
    }

    public void setMap(Board map) {
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

    /**
     * This function will check that a player has not already discarded this
     * turn, that the number of cards to be discarded is half of their total
     * cards, that they have 7 or more cards in their hand, and that the cards
     * to be discarded is not greater than the number of cards owned for each
     * type
     * @param discardCards
     * @return
     */
    public boolean CanDiscardCards(DiscardCards discardCards) {
        return userManager.CanDiscardCards(discardCards);
    }

    /**
     * Checks that the player is the current player and that the roll number is
     * between 2 and 12
     * @param rollNumber
     * @return
     */
    public boolean CanRollNumber(RollNumber rollNumber) {
        return rollNumber.getNumber() >= 2 && rollNumber.getNumber() <=  12 && 
        		userManager.isCurrentPlayer(rollNumber.getPlayerIndex());
    }

    /**
     * Checks that the Player is the current player, the victim is a different
     * player and that the location is different than the robber's current
     * location
     * @param robPlayer
     * @return
     */
    public boolean CanPlaceRobber(RobPlayer robPlayer) {
    	return userManager.isCurrentPlayer(robPlayer.getPlayerIndex()) &&
    			!userManager.isCurrentPlayer(robPlayer.getVictimIndex()) &&
    			map.canPlaceRobber(robPlayer.getLocation());
    }

    /**
     * Checks that there isn't a road already there. Also checks that there is
     * an adjacent road owned by the player.
     * @param buildRoad
     * @return
     */
    public boolean CanBuildRoad(BuildRoad buildRoad) {
    	return userManager.CanBuildRoad(buildRoad) &&
    			map.canBuildRoad(buildRoad);
    }

    /**
     * Checks that the vertex is not taken, that there are no objects on adjacent
     * vertices, and that there is a road on an adjacent edge
     * @param buildSettlement
     * @return
     */
    public boolean CanBuildSettlement(BuildSettlement buildSettlement) {
    	return userManager.CanBuildSettlement(buildSettlement) &&
    			map.canBuildSettlement(buildSettlement);
    }

    /**
     * Checks that a vertex is taken by the player and that the object is a
     * settlement
     * @param buildCity
     * @return
     */
    public boolean CanBuildCity(BuildCity buildCity) {
    	return userManager.CanBuildCity(buildCity) &&
    			map.canBuildCity(buildCity);
    }

    public boolean CanOfferTrade(OfferTrade offerTrade) {
    	return userManager.CanOfferTrade(offerTrade);
    }

    /**
     * checks that the player is the current player, and that the resource 
     * requested is available. Does not check that the ratio matches a player's
     * ownership of a port
     * @param maritimeTrade
     * @return
     */
    public boolean CanMaritimeTrade(MaritimeTrade maritimeTrade) {
    	return userManager.CanMaritimeTrade(maritimeTrade);
    }

    public boolean CanBuyDevCard(BuyDevCard buyDevCard) {
		// TODO check that the player is the current player, and has the 
        // necessary resources to buy a dev card
        return false;
    }

    public boolean CanUseYearOfPlenty(YearOfPlenty yearOfPlenty) {
        // TODO is current user, has dev card, hasn't played any other dev cards
        return false;
    }

    public boolean CanUseRoadBuilder(RoadBuilding roadBuilding) {
		// TODO is current user, has dev card, hasn't played any other dev cards
        // has roads
        return false;
    }

    public boolean CanUseSoldier(Soldier soldier) {
        // TODO is current user, has dev card, hasn't played any other dev cards
        return false;
    }

    public boolean CanUseMonopoly(Monopoly monopoly) {
        // TODO is current user, has dev card, hasn't played any other dev cards
        return false;
    }

    public boolean CanUseMonument(Monument monument) {
    	int index = monument.getPlayerIndex();
    	Player player = userManager.getPlayer(index);
    	boolean isCurrentUser = userManager.isCurrentPlayer(index);
    	
    	return isCurrentUser && player.canPlayMonument();
    }

    public boolean CanFinishTurn(FinishTurn finishTurn) {
        // TODO track initialization stages ("FirstRound" or "SecondRound")
    	TurnTracker turnTracker = userManager.turnTracker;
    	boolean hasRolled = turnTracker.getStatus().toLowerCase() != "rolling";
    	boolean isTurn = userManager.isCurrentPlayer(finishTurn.getPlayerIndex());
    	
    	
        return isTurn && hasRolled;
    }
}
