package shared.models.DTO;

import shared.exceptions.ExcessiveDevCards;

/**
 * This class stores the information needed to create a JSON string of a
 * Development Card list, and is used to facilitate the transfer of data between
 * the server and client.
 *
 * @author Austin
 *
 */
public class DevCardListDTO {

    private int monopoly;
    private int roadBuilding;
    private int yearOfPlenty;
    private int monument;
    private int soldier;

    private static int MAX_SOLDIER = 14;
    private static int MAX_MONUMENT = 5;
    private static int MAX_PROGRESS = 2;

    public int getMonopoly() {
        return monopoly;
    }

    /**
     * Sets the number of monopoly cards in card list
     *
     * @param monopoly number of monopoly cards
     * @throws ExcessiveDevCards thrown when the number of cards exceeds the
     * <code>MAX_PROGRESS</code> constant
     */
    public void setMonopoly(int monopoly) throws ExcessiveDevCards {
        if (monopoly > MAX_PROGRESS) {
            throw new ExcessiveDevCards("Excessive monopoly cards");
        }
        this.monopoly = monopoly;
    }

    public int getRoadBuilding() {
        return roadBuilding;
    }

    /**
     * Sets the number of road building cards in card list
     *
     * @param roadBuilding number of road building cards
     * @throws ExcessiveDevCards thrown when the number of cards exceeds the
     * <code>MAX_PROGRESS</code> constant
     */
    public void setRoadBuilding(int roadBuilding) throws ExcessiveDevCards {
        if (roadBuilding > MAX_PROGRESS) {
            throw new ExcessiveDevCards("Excessive road building cards");
        }
        this.roadBuilding = roadBuilding;
    }

    public int getYearOfPlenty() {
        return yearOfPlenty;
    }

    /**
     * Sets the number of road building cards in card list
     *
     * @param yearOfPlenty the number of year of plenty cards
     * @throws ExcessiveDevCards thrown when the number of cards exceeds the
     * <code>MAX_PROGRESS</code> constant
     */
    public void setYearOfPlenty(int yearOfPlenty) throws ExcessiveDevCards {
        if (yearOfPlenty > MAX_PROGRESS) {
            throw new ExcessiveDevCards("Excessive year of plenty cards");
        }
        this.yearOfPlenty = yearOfPlenty;
    }

    public int getMonument() {
        return monument;
    }

    /**
     * Sets the number of monument cards in card list
     *
     * @param monument the number of monument cards
     * @throws ExcessiveDevCards thrown when the number of cards exceeds the
     * <code>MAX_MONUMENT</code> constant
     */
    public void setMonument(int monument) throws ExcessiveDevCards {
        if (monument > MAX_MONUMENT) {
            throw new ExcessiveDevCards("Excessive monument cards");
        }
        this.monument = monument;
    }

    public int getSoldier() {
        return soldier;
    }

    /**
     * Sets the number of soldier cards in card list
     *
     * @param soldier the number of soldier cards
     * @throws ExcessiveDevCards thrown when the number of cards exceeds the
     * <code>MAX_SOLDIER</code> constant
     */
    public void setSoldier(int soldier) throws ExcessiveDevCards {
        if (soldier > MAX_SOLDIER) {
            throw new ExcessiveDevCards("Excessive soldier cards");
        }
        this.soldier = soldier;
    }
}
