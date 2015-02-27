package shared.models.DTO;

import shared.definitions.PortType;
import shared.exceptions.InvalidRatio;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

/**
 * This class stores the information needed to create a JSON string of a port,
 * and is used to facilitate the transfer of data between the server and client.
 *
 * @author Austin
 *
 */
public class PortDTO {

    /**
     * Must be: 'Wood', 'Brick', 'Sheep', 'Wheat', or 'Ore', optional
     */
    private PortType resource;
    private HexLocation location;
    /**
     * Must be: 'NW', 'N', 'NE', 'SW', 'S', or 'SE'
     */
    private EdgeDirection direction;
    /**
     * either 2 or 3 depending on if there is a resource
     */
    private int ratio;

    /**
     * This constructor inputs default values. The ratio defaults to 3, because
     * the resource is set to null;
     */
    public PortDTO() {
        this.resource = null;
        this.location = null;
        this.direction = null;
        this.ratio = 3;
    }

    public PortDTO(int ratio, String resource, HexLocation hexLocation, EdgeDirection direction) {
        this.ratio = ratio;
        this.location = hexLocation;
        this.direction = direction;
        if (resource != null) {
            this.resource = PortType.valueOf(resource.toUpperCase());
        }
    }

    public PortType getResource() {
        return resource;
    }

    public void setResource(PortType resource) {
        this.resource = resource;
    }

    public HexLocation getLocation() {
        return location;
    }

    public void setLocation(HexLocation location) {
        this.location = location;
    }

    public EdgeDirection getDirection() {
        return direction;
    }

    public void setDirection(EdgeDirection direction) {
        this.direction = direction;
    }

    public int getRatio() {
        return ratio;
    }

    /**
     * sets the ratio
     *
     * @pre the ratio must be 2 or 3
     * @param ratio
     * @throws InvalidRatio this is thrown if the ratio input is not 2 or 3
     */
    public void setRatio(int ratio) throws InvalidRatio {
        if (ratio != 2 || ratio != 3) {
            throw new InvalidRatio();
        }
        this.ratio = ratio;
    }

}
