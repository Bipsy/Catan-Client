package shared.models.DTO;

import shared.exceptions.InvalidPlayerIndex;

/**
 * This class stores the information needed to create a JSON string of a vertex
 * object (cities and settlements), and is used to facilitate the transfer of
 * data between the server and client.
 *
 * @author Austin
 *
 */
public class VertexObjectDTO {

    private int owner;
    private VertexLocationDTO location;

    public VertexObjectDTO(int owner, VertexLocationDTO location) {
        this.owner = owner;
        this.location = location;
    }

    public int getOwner() {
        return owner;
    }

    /**
     * This function sets the player index of the owner
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

    public VertexLocationDTO getLocation() {
        return location;
    }

    public void setLocation(VertexLocationDTO location) {
        this.location = location;
    }

}
