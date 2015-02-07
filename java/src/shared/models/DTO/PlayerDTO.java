package shared.models.DTO;

import shared.definitions.CatanColor;
import shared.exceptions.InvalidPlayerIndex;

/**
 * This class stores the information needed to create a JSON string of a player,
 * and is used to facilitate the transfer of data between the server and client.
 *
 * @author Austin
 *
 */
public class PlayerDTO {

    private int cities;
    private CatanColor color;
    /**
     * flag for whether or not a player has discarded cards during a discard
     * phase
     */
    private boolean discarded;
    /**
     * number of monument cards owned/played by player
     */
    private int monuments;
    private String name;
    /**
     * new dev cards bought this turn
     */
    private DevCardListDTO newDevCards = new DevCardListDTO();
    /**
     * the cards a player had when the turn started
     */
    private DevCardListDTO oldDevCards = new DevCardListDTO();;
    /**
     * a number between 0 and 3
     */
    private int playerIndex;
    /**
     * This is a flag for if a player has played a dev card this turn
     */
    private boolean playedDevCard;
    /**
     * The playerId is the unique playerId it is used to pick the player apart
     * from the others. It is also used in the cookie object which hasn't been
     * implemented yet.
     */
    private int playerID;
    private ResourceListDTO resources = new ResourceListDTO();
    private int roads;
    private int settlements;
    private int soldiers;
    private int victoryPoints;

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    public int getMonuments() {
        return monuments;
    }

    public void setMonuments(int monuments) {
        this.monuments = monuments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DevCardListDTO getNewDevCards() {
        return newDevCards;
    }

    public void setNewDevCards(DevCardListDTO newDevCards) {
        this.newDevCards = newDevCards;
    }

    public DevCardListDTO getOldDevCards() {
        return oldDevCards;
    }

    public void setOldDevCards(DevCardListDTO oldDevCards) {
        this.oldDevCards = oldDevCards;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Sets the player index
     *
     * @pre the player index must be in the range 0 -3
     * @param playerIndex the player index
     * @throws InvalidPlayerIndex this exception is thrown if the index is not
     * an integer in the range 0 - 3
     */
    public void setPlayerIndex(int playerIndex) throws InvalidPlayerIndex {
        if (playerIndex > 3 || playerIndex < 0) {
            throw new InvalidPlayerIndex();
        }
        this.playerIndex = playerIndex;
    }

    public boolean isPlayedDevCard() {
        return playedDevCard;
    }

    public void setPlayedDevCard(boolean playedDevCard) {
        this.playedDevCard = playedDevCard;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public ResourceListDTO getResources() {
        return resources;
    }

    public void setResources(ResourceListDTO resources) {
        this.resources = resources;
    }

    public int getRoads() {
        return roads;
    }

    public void setRoads(int roads) {
        this.roads = roads;
    }

    public int getSettlements() {
        return settlements;
    }

    public void setSettlements(int settlements) {
        this.settlements = settlements;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public CatanColor getColor() {
        return color;
    }

    public void setColor(CatanColor color) {
        this.color = color;
    }

}
