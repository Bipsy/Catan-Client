package shared.models;

import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.VertexLocation;
import shared.models.DTO.VertexObjectDTO;

/**
 * Vertex Objects represent pieces that exist on the vertices of hexes. E.G.
 * Settlements and Cities.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class VertexObject {

    private int owner;
    private PieceType type;
    private int resourceRatio;
    private PortType harbor;
    private VertexLocation location;

    public VertexObject(VertexObjectDTO location, PieceType type) {
        this.owner = location.getOwner();
        this.location = new VertexLocation(location.getLocation());
        this.type = type;
        if (type == PieceType.SETTLEMENT) {
            this.resourceRatio = 2;
        } else {
            this.resourceRatio = 1;
        }
    }

    /**
     * Convenience method to determine if object harbor benefits available to
     * it.
     *
     * @return Boolean representing whether (true) or not (false) object resides
     * on a harbor.
     */
    public boolean onHarbor() {
        return false;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public int getResourceRatio() {
        return resourceRatio;
    }

    public void setResourceRatio(int resourceRatio) {
        this.resourceRatio = resourceRatio;
    }

    public PortType getHarbor() {
        return harbor;
    }

    public void setHarbor(PortType harbor) {
        this.harbor = harbor;
    }

    public VertexLocation getLocation() {
        return location;
    }

    public void setLocation(VertexLocation location) {
        this.location = location;
    }
}
