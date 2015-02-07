package shared.models.DTO;

import shared.exceptions.InvalidPlayerIndex;
import shared.locations.VertexLocation;

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
    private VertexLocation location;
    
    public VertexObjectDTO() {
    	owner = -1;
    	location = null;
    }

    public VertexObjectDTO(int owner, VertexLocation location) {
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

    public VertexLocation getLocation() {
        return location;
    }

    public void setLocation(VertexLocation location) {
        this.location = location;
    }

}
