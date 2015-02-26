package shared.models.DTO;

import shared.exceptions.InvalidPlayerIndex;

/**
 * This class stores the information needed to create a JSON string of an edge
 * object (roads), and is used to facilitate the transfer of data between the
 * server and client.
 *
 * @author Austin
 *
 */
public class EdgeValueDTO {

    private int owner;
    private EdgeLocationDTO location;

    public EdgeValueDTO() {
        owner = -1;
        location = null;
    }

    public EdgeValueDTO(int newOwner, EdgeLocationDTO newLocation) {
        owner = newOwner;
        location = newLocation;
    }

    public int getOwner() {
        return owner;
    }

    /**
     * This sets the owner's player index
     *
     * @param owner the player index
     * @throws InvalidPlayerIndex this exception is thrown if the index is not
     * an integer in the range 0 - 3
     */
    public void setOwner(int owner) throws InvalidPlayerIndex {
        if (owner > 3 || owner < 0) {
            throw new InvalidPlayerIndex();
        }
        this.owner = owner;
    }

    public EdgeLocationDTO getLocation() {
        return location;
    }

    public void setLocation(EdgeLocationDTO location) {
        this.location = location;
    }

}
