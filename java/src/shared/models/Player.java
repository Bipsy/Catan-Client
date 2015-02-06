package shared.models;

import shared.definitions.CatanColor;
import shared.models.DTO.PlayerDTO;

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
        this.cities = playerDTO.getCities();
        this.roads = playerDTO.getRoads();
        this.settlements = playerDTO.getSettlements();
        this.soldiers = playerDTO.getSoldiers();
        this.newDevCards = new DevCardList(playerDTO.getNewDevCards());
        this.oldDevCards = new DevCardList(playerDTO.getOldDevCards());
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

}
