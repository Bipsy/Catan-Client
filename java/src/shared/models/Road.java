package shared.models;

import shared.locations.EdgeLocation;
import shared.models.DTO.EdgeValueDTO;

/**
 * The roads connect player's settlements and cities. An instance of a road
 * class will have an integer representation of the player that 'owns' it.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Road {

    private int owner;
    private EdgeLocation location;

    public Road(EdgeValueDTO edgeValueDTO) {
        this.owner = edgeValueDTO.getOwner();
        this.location = new EdgeLocation(edgeValueDTO.getLocation());
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public EdgeLocation getLocation() {
        return location;
    }

    public void setLocation(EdgeLocation location) {
        this.location = location;
    }

}
