package shared.models.DTO;

import shared.exceptions.InvalidPlayerIndex;

/**
 * This class stores the information needed to create a JSON string of the turn
 * tracker information, and is used to facilitate the transfer of data between
 * the server and client.
 *
 * @author Austin
 *
 */
public class TurnTrackerDTO {

    private int currentTurn;
    private String status;
    private Integer longestRoad;
    private Integer largestArmy;

    /**
     * This constructor creates a default <code>TurnTrackerDTO</code> object. It
     * assumes the start of the game where the first player is player index 0.
     */
    public TurnTrackerDTO() {
        currentTurn = 0;
        status = null;
        /**
         * index of the player with the longest road, optional
         */
        longestRoad = null;
        /**
         * index of the player with the largest army, optional
         */
        largestArmy = null;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets the index of the player who's turn it is.
     *
     * @pre this index can only be the numbers 0 - 3 because there are only four
     * players
     * @param currentTurn the index of the player who is currently taking their
     * turn
     * @throws InvalidPlayerIndex this exception is thrown if the index is not
     * an integer in the range 0 - 3
     */
    public void setCurrentTurn(int currentTurn) throws InvalidPlayerIndex {
        if (currentTurn > 3 || currentTurn < 0) {
            throw new InvalidPlayerIndex();
        }
        this.currentTurn = currentTurn;
    }

    public String getStatus() {
        return status;
    }

    /**
     * This function sets the status of the turn
     *
     * @pre The string passed in must be 'Rolling', 'Robbing', 'Playing',
     * 'Discarding', 'FirstRound', or 'SecondRound'
     * @param status the new status string
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(Integer longestRoad) {
        this.longestRoad = longestRoad;
    }

    public Integer getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(Integer largestArmy) {
        this.largestArmy = largestArmy;
    }
}
