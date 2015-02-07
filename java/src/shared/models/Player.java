package shared.models;

import shared.definitions.CatanColor;
import shared.models.DTO.PlayerDTO;
import shared.models.DTO.params.BuildCity;
import shared.models.DTO.params.BuildRoad;
import shared.models.DTO.params.BuildSettlement;
import shared.models.DTO.params.DiscardCards;
import shared.models.DTO.params.MaritimeTrade;
import shared.models.DTO.params.OfferTrade;

public class Player extends User {

    private boolean discarded;
    private int victoryPoints;

    //Structure Fields
    private int cities;
    private int roads;
    private int settlements;
    private int soldiers;

    //Dev Card Fields
    private DevCardList newDevCards;
    private DevCardList oldDevCards;
    private boolean playedDevCard;

    private PlayerHand resources;

    /**
     * This will create a player with a color, name, index, and id
     *
     * @param color
     * @param name
     * @param playerIndex
     * @param playerID
     */
    public Player(CatanColor color, String name, int playerIndex, int playerID,
            String username, String password) {
        super(color, username, password, playerIndex, playerID);
    }

    public Player(PlayerDTO playerDTO) {
        super(playerDTO.getColor(), playerDTO.getPlayerIndex(), playerDTO.getPlayerID());
        this.setCities(playerDTO.getCities());
        this.setRoads(playerDTO.getRoads());
        this.setSettlements(playerDTO.getSettlements());
        this.setSoldiers(playerDTO.getSoldiers());
        this.setNewDevCards(new DevCardList(playerDTO.getNewDevCards()));
        this.setOldDevCards(new DevCardList(playerDTO.getOldDevCards()));
        this.resources = new PlayerHand(playerDTO.getResources(), playerDTO.getNewDevCards());
    }

    /**
     * canPlayDevCard determines if the selected player is able to play a dev
     * card. If the player does not have a dev card, has already played a dev
     * card (and couldn't win that turn by playing multiple monument cards),
     * etc. then this method will return false, otherwise it will return true;
     *
     * @return True if the player can play the dev card, false otherwise.
     */
    public boolean canPlayDevCard() {
        return false;
    }
    
    /**
     * canPlayDevCard determines if the selected player is able to play a 
     * monument. If # of monuments player has + current # of victory points
     * is >= # of points needed to win.
     *
     * @return True if the player can play the dev card, false otherwise.
     */
    public boolean canPlayMonument() {
    	DevCardList devCards = this.resources.getDevCards();
    	int monuments = devCards.getMonument();
    	boolean canPlay = monuments + this.victoryPoints >= 10;
        
    	return canPlay;
    }
    
    

    /**
     * Determines if a player can build a road. If the player has the resources
     * and the road structure available then this method returns true, otherwise
     * it returns false.
     *
     * @return true if the player can build a road, false otherwise.
     */
    public boolean canBuildRoad() {
        return false;
    }

    /**
     * Determines if a player can build a settlement. If the player has the
     * resources and the settlement structure is available then this method
     * returns true, otherwise it returns false.
     *
     * @return true if the player can build a settlement, false otherwise.
     */
    public boolean canBuildSettlement() {
        return false;
    }

    /**
     * Determines if a player can build a city. If the player has the resources
     * and the city structure available then this method returns true, otherwise
     * it returns false.
     *
     * @return true if the player can build a city, false otherwise.
     */
    public boolean canBuildCity() {
        return false;
    }

    /**
     * Determines if a player can buy a dev card. If the player has the
     * resources to purchase the dev card then this method returns true,
     * otherwise false.
     *
     * @return true if the player can buy a dev card, false otherwise
     */
    public boolean canBuyDevCard() {
        return false;
    }

	public boolean CanDiscardCards(DiscardCards discardCards) {
		if(discarded || resources.getNumResourceCards() < 7) 
    		return false;
    	return resources.CanUpdateResourceCards(discardCards.getDiscardedCards());
	}

	public boolean CanOfferTrade(OfferTrade offerTrade) {
		return resources.CanUpdateResourceCards(offerTrade.getOffer());
	}
	
	public boolean CanOfferMTrade(MaritimeTrade maritimeTrade) {
		return resources.canMTrade(maritimeTrade);
	}

	public boolean CanBuildRoad(BuildRoad buildRoad) {
		return resources.canBuildRoad(buildRoad);
	}

	public boolean CanBuildSettlement(BuildSettlement buildSettlement) {
		return resources.canBuildSettlement(buildSettlement);
	}

	public boolean CanBuildCity(BuildCity buildCity) {
		return resources.canBuildCity(buildCity);
	}
		
	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}

	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	public void setOldDevCards(DevCardList oldDevCards) {
		this.oldDevCards = oldDevCards;
	}

	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	public void setNewDevCards(DevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}

	public int getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	public int getSettlements() {
		return settlements;
	}

	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}

	public int getRoads() {
		return roads;
	}

	public void setRoads(int roads) {
		this.roads = roads;
	}

	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

}
