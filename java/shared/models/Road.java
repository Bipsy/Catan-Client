package shared.models;

import shared.models.DTO.EdgeValueDTO;

/**
 * The roads connect player's settlements and cities. An instance of a road
 * class will have an integer representation of the player that 'owns' it.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Road {

    private int owner;

    public Road(EdgeValueDTO edgeValueDTO) {
        this.owner = edgeValueDTO.getOwner();
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

}
