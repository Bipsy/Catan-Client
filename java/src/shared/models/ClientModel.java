package shared.models;

import java.util.List;

import shared.definitions.ResourceType;
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

/**
 * Class serves as a wrapper for the model of the game or game instance. It can
 * be used as the single model instance for the server (iPopulator) and client
 * facades to write and read.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class ClientModel {
	
	private String localPlayerName;
	private Integer localPlayerIndex;

    private Bank bank;
    private Board board;
    private ChatObject chatObject;
    private UserManager userManager;
    private TradeOffer tradeOffer;
    private int version;
    //Player index of the game winner
    private int winner;

    public ClientModel() {
        winner = -1;
        version = 0;
        bank = null;
        board = null;
        chatObject = null;
        userManager = null;
        tradeOffer = null;
        localPlayerIndex = null;

    }

    /**
     * Getters and Setters for model wrapper
     */
    /**
     * BANK
     */
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * BOARD (Map)
     */
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * CHAT OBJECT
     */
    public List<Message> getLogObject() {
        return chatObject.log;
    }
    
    public List<Message> getChatObject() {
    	return chatObject.chat;
    }

    public void setChatObject(ChatObject chatObject) {
        this.chatObject = chatObject;
    }

    /**
     * USER MANAGER (player manager)
     */
    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    public String getStatus() {
    	return userManager.turnTracker.getStatus();
    }

    /**
     * TRADE OFFER
     */
    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    /**
     * VERSION
     */
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * WINNER
     */
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
     *
     * @param discardCards
     * @return
     */
    public boolean CanDiscardCards(DiscardCards discardCards) {
        return userManager.CanDiscardCards(discardCards);
    }

    /**
     * Checks that the player is the current player and that the roll number is
     * between 2 and 12
     *
     * @param rollNumber
     * @return
     */
    public boolean CanRollNumber(RollNumber rollNumber) {
        return rollNumber.getNumber() >= 2 && rollNumber.getNumber() <= 12
                && userManager.isCurrentPlayer(rollNumber.getPlayerIndex());
    }

    /**
     * Checks that the Player is the current player, the victim is a different
     * player and that the location is different than the robber's current
     * location
     *
     * @param robPlayer
     * @return
     */
    public boolean CanPlaceRobber(RobPlayer robPlayer) {
        return userManager.isCurrentPlayer(robPlayer.getPlayerIndex())
                && !userManager.isCurrentPlayer(robPlayer.getVictimIndex())
                && board.canPlaceRobber(robPlayer.getLocation());
    }

    /**
     * Checks that there isn't a road already there. Also checks that there is
     * an adjacent road owned by the player.
     *
     * @param buildRoad
     * @return
     */
    public boolean CanBuildRoad(BuildRoad buildRoad) {
        return (buildRoad.isFree() || userManager.CanBuildRoad(buildRoad))
                && board.canBuildRoad(buildRoad);
    }
    
    public boolean hasResourcesForRoad(int playerIndex) {
        return userManager.CanBuildRoad(playerIndex);
    }

    /**
     * Checks that the vertex is not taken, that there are no objects on
     * adjacent vertices, and that there is a road on an adjacent edge
     *
     * @param buildSettlement
     * @return
     */
    public boolean CanBuildSettlement(BuildSettlement buildSettlement) {
        return (buildSettlement.isFree() || userManager.CanBuildSettlement(buildSettlement))
                && board.canBuildSettlement(buildSettlement);
    }

    /**
     * Checks that a vertex is taken by the player and that the object is a
     * settlement
     *
     * @param buildCity
     * @return
     */
    public boolean CanBuildCity(BuildCity buildCity) {
        return userManager.CanBuildCity(buildCity)
                && board.canBuildCity(buildCity);
    }

    public boolean CanOfferTrade(OfferTrade offerTrade) {
        return userManager.CanOfferTrade(offerTrade);
    }

    /**
     * checks that the player is the current player, and that the resource
     * requested is available. Does not check that the ratio matches a player's
     * ownership of a port
     *
     * @param maritimeTrade
     * @return
     */
    public boolean CanMaritimeTrade(MaritimeTrade maritimeTrade) {
        return userManager.CanMaritimeTrade(maritimeTrade);
    }

    public boolean CanBuyDevCard(BuyDevCard buyDevCard) {
        return userManager.CanBuyDevCard(buyDevCard) && bank.hasDevCards();
    }

    public boolean CanUseYearOfPlenty(YearOfPlenty yearOfPlenty) {
        int index = yearOfPlenty.getPlayerIndex();
        Player player = userManager.getPlayer(index);
        ResourceType type1 = yearOfPlenty.getResource1();
        ResourceType type2 = yearOfPlenty.getResource1();
        boolean isCurrentUser = userManager.isCurrentPlayer(index);
        int resource1 = bank.getResources().getResourceNumber(type1);
        int resource2 = bank.getResources().getResourceNumber(type2);

        return isCurrentUser
                && player.canUseYearOfPlenty()
                && resource1 >= 1
                && resource2 >= 2;
    }

    public boolean CanUseRoadBuilder(RoadBuilding roadBuilding) {
        int index = roadBuilding.getPlayerIndex();
        Player player = userManager.getPlayer(index);
        boolean isCurrentUser = userManager.isCurrentPlayer(index);

        return isCurrentUser && player.canUseRoadBuilding();
    }

    public boolean CanUseSoldier(Soldier soldier) {
        int index = soldier.getPlayerIndex();
        Player player = userManager.getPlayer(index);
        boolean isCurrentUser = userManager.isCurrentPlayer(index);

        return isCurrentUser && player.canPlaySoldier();
    }

    public boolean CanUseMonopoly(Monopoly monopoly) {
        int index = monopoly.getPlayerIndex();
        Player player = userManager.getPlayer(index);
        boolean isCurrentUser = userManager.isCurrentPlayer(index);

        return isCurrentUser && player.canPlayMonopoly();
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
        boolean hasRolled = !"rolling".equals(turnTracker.getStatus().toLowerCase());
        boolean isTurn = userManager.isCurrentPlayer(finishTurn.getPlayerIndex());

        return isTurn && hasRolled;
    }

	public boolean CanBuildRoad(int playerIndex) {
		return userManager.CanBuildRoad(playerIndex);
	}

	public boolean CanBuildSettlement(int playerIndex) {
		return userManager.CanBuildRoad(playerIndex);
	}

	public boolean CanBuildCity(int playerIndex) {
		return userManager.CanBuildCity(playerIndex);
	}
	
	public int getMapRadius() {
		return this.board.getRadius();
	}

	public String getLocalPlayerName() {
		return localPlayerName;
	}

	public void setLocalPlayerName(String localPlayerName) {
		this.localPlayerName = localPlayerName;
		if(userManager != null)
			this.localPlayerIndex = userManager.getLocalPlayerIndex(localPlayerName);
	}
	
	public Integer getLocalPlayerIndex() {
		return this.localPlayerIndex;
	}
	
	public Player getPlayer(int index) {
		return this.userManager.getPlayer(index);
	}

	public int getLargestArmy() {
		return this.userManager.getLargestArmy();
	}

	public int getLongestRoad() {
		return this.userManager.getLongestRoad();
	}
}
