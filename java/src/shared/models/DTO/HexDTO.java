package shared.models.DTO;

import shared.definitions.ResourceTypeOptional;
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
    private ResourceTypeOptional resource;
    /**
     * there is no number for desert or water tiles
     */
    private final Integer chit;

    public HexDTO(HexLocation newLocation, ResourceTypeOptional newResource,
            Integer newChit) {
        location = newLocation;
        resource = newResource;
        chit = newChit;
    }

    public HexLocation getLocation() {
        return location;
    }

    public void setLocation(HexLocation location) {
        this.location = location;
    }

    public ResourceTypeOptional getResource() {
        return resource;
    }

    public void setResource(ResourceTypeOptional resource) {
        this.resource = resource;
    }

    public Integer getNumber() {
        return chit;
    }

}
