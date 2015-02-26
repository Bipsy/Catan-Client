package shared.locations;

import shared.models.DTO.EdgeLocationDTO;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation {

    private HexLocation roadLocation;
    private EdgeDirection direction;

    public EdgeLocation(HexLocation roadLocation, EdgeDirection direction) {
        setHexLoc(roadLocation);
        setDir(direction);
    }

    public EdgeLocation(EdgeLocationDTO location) {
    	roadLocation = new HexLocation(location.getX(), location.getY());
    	direction = location.getDirection();
	}

	public HexLocation getHexLoc() {
        return roadLocation;
    }

    private void setHexLoc(HexLocation roadLocation) {
        if (roadLocation == null) {
            throw new IllegalArgumentException("hexLoc cannot be null");
        }
        this.roadLocation = roadLocation;
    }

    public EdgeDirection getDir() {
        return direction;
    }

    private void setDir(EdgeDirection direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "EdgeLocation [roadLocation=" + roadLocation + ", direction=" + direction + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((roadLocation == null) ? 0 : roadLocation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EdgeLocation other = (EdgeLocation) obj;
        if (direction != other.direction) {
            return false;
        }
        if (roadLocation == null) {
            if (other.roadLocation != null) {
                return false;
            }
        } else if (!roadLocation.equals(other.roadLocation)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a canonical (i.e., unique) value for this edge location. Since
     * each edge has two different locations on a map, this method converts a
     * hex location to a single canonical form. This is useful for using hex
     * locations as map keys.
     *
     * @return Normalized hex location
     */
    public EdgeLocation getNormalizedLocation() {

        // Return an EdgeLocation that has direction NorthWest, North, or NorthEast
        switch (direction) {
            case NorthWest:
            case North:
            case NorthEast:
                return this;
            case SouthWest:
            case South:
            case SouthEast:
                return new EdgeLocation(roadLocation.getNeighborLoc(direction),
                        direction.getOppositeDirection());
            default:
                assert false;
                return null;
        }
    }

    public EdgeLocation[] getAdjacentEdges() {
        EdgeLocation[] adjEdges = new EdgeLocation[4];
        EdgeLocation temp = getNormalizedLocation();
        switch (temp.getDir()) {
            case NorthWest:
                adjEdges[0] = new EdgeLocation(roadLocation, EdgeDirection.North);
                adjEdges[1] = new EdgeLocation(roadLocation, EdgeDirection.SouthWest);
                adjEdges[2] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.South);
                adjEdges[3] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.NorthEast);
                break;
            case North:
                adjEdges[0] = new EdgeLocation(roadLocation, EdgeDirection.NorthEast);
                adjEdges[1] = new EdgeLocation(roadLocation, EdgeDirection.NorthWest);
                adjEdges[2] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.SouthWest);
                adjEdges[3] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.SouthEast);
                break;
            case NorthEast:
                adjEdges[0] = new EdgeLocation(roadLocation, EdgeDirection.North);
                adjEdges[1] = new EdgeLocation(roadLocation, EdgeDirection.SouthEast);
                adjEdges[2] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.South);
                adjEdges[3] = new EdgeLocation(roadLocation.getNeighborLoc(temp.getDir()),
                        EdgeDirection.NorthWest);
                break;
            default:
                assert false;
                return null;
        }
        return adjEdges;
    }
}
