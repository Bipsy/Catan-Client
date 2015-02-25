package shared.models.DTO;

import shared.definitions.HexType;
import shared.locations.HexLocation;

/**
 * This class stores the information needed to create a JSON string of a hex,
 * and is used to facilitate the transfer of data between the server and client.
 *
 * @author Austin
 *
 */
public class HexDTO {

    private HexLocation location;
    /**
     * Must be: 'Wood', 'Brick', 'Sheep', 'Wheat', or 'Ore', optional
     */
    private HexType resource;
    /**
     * there is no number for desert or water tiles
     */
    private final Integer chit;

    public HexDTO(HexLocation newLocation, String resource,
            Integer newChit) {
        location = newLocation;
        this.resource = (resource != null) ? HexType.valueOf(resource.toUpperCase()) : null;
        chit = newChit;
    }

    public HexLocation getLocation() {
        return location;
    }

    public void setLocation(HexLocation location) {
        this.location = location;
    }

    public HexType getResource() {
        return resource;
    }

    public void setResource(HexType resource) {
        this.resource = resource;
    }

    public Integer getNumber() {
        return chit;
    }

}
