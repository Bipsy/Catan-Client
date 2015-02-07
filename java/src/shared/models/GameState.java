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

    public boolean CanDiscardCards(DiscardCards discardCards) {
		// TODO check the player's hand to make sure that the number of 
        // resources discarded is half of the player's hand, and that the cards
        // discarded are available to be discarded

        // This will get passed to the UserManager and then down to the user
        return false;
    }

    public boolean CanRollNumber(RollNumber rollNumber) {
		// TODO Check that the player index is the current player's index and 
        // that the number rolled is between 2 and 12

        // This will be passed to the UserManager
        return false;
    }

    public boolean CanPlaceRobber(RobPlayer robPlayer) {
		// TODO Check that the player index is the current player and the 
        // location is not the robber's current location

        // This will get data from the UsrManager and the map
        return false;
    }

    public boolean CanBuildRoad(BuildRoad buildRoad) {
        // TODO check that the location is free and the player has a road 
        // adjacent to that location
        return false;
    }

    public boolean CanBuildSettlement(BuildSettlement buildSettlement) {
        // TODO check that the location is free, the player has a road adjacent
        // to that location, and that there is not any other building on a 
        // nearby vertex
        return false;
    }

    public boolean CanBuildCity(BuildCity buildCity) {
        // TODO check that the location has a settlement owned by the player at
        //the specified location
        return false;
    }

    public boolean CanOfferTrade(OfferTrade offerTrade) {
        // TODO Check that the player is the current player index, the 
        // other player is not the current player, the current player has the 
        // necessary resources, and that a resource being offered is not one 
        // being requested
        return false;
    }

    public boolean CanMaritimeTrade(MaritimeTrade maritimeTrade) {
        // TODO check that the player is the current player, the ratio reflects
        // the player's ownership of ports, the resource offered has the right
        // ratio, and that the resource requested is available
        return false;
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
        // TODO is current user, has dev card, has enough monuments and victory
        // points to win
        return false;
    }

    public boolean CanFinishTurn(FinishTurn finishTurn) {
        // TODO user has rolled dice and discarded if neccesary
        return false;
    }
}
