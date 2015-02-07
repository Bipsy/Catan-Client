package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation {

    private HexLocation vertexLocation;
    private VertexDirection direction;

    public VertexLocation(HexLocation vertexLocation, VertexDirection direction) {
        setHexLoc(vertexLocation);
        setDir(direction);
    }

    public HexLocation getHexLoc() {
        return vertexLocation;
    }

    private void setHexLoc(HexLocation vertexLocation) {
        if (vertexLocation == null) {
            throw new IllegalArgumentException("vertexLocation cannot be null");
        }
        this.vertexLocation = vertexLocation;
    }

    public VertexDirection getDir() {
        return direction;
    }

    private void setDir(VertexDirection direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "VertexLocation [vertexLocation=" + vertexLocation + ", direction=" + direction + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((vertexLocation == null) ? 0 : vertexLocation.hashCode());
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
        VertexLocation other = (VertexLocation) obj;
        if (direction != other.direction) {
            return false;
        }
        if (vertexLocation == null) {
            if (other.vertexLocation != null) {
                return false;
            }
        } else if (!vertexLocation.equals(other.vertexLocation)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a canonical (i.e., unique) value for this vertex location. Since
     * each vertex has three different locations on a map, this method converts
     * a vertex location to a single canonical form. This is useful for using
     * vertex locations as map keys.
     *
     * @return Normalized vertex location
     */
    public VertexLocation getNormalizedLocation() {

		// Return location that has direction NorthWest or NorthEast
        switch (direction) {
            case NorthWest:
            case NorthEast:
                return this;
            case West:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.SouthWest),
                        VertexDirection.NorthEast);
            case SouthWest:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.South),
                        VertexDirection.NorthWest);
            case SouthEast:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.South),
                        VertexDirection.NorthEast);
            case East:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.SouthEast),
                        VertexDirection.NorthWest);
            default:
                assert false;
                return null;
        }
    }

	public VertexLocation[] getAdjacentVertexes() {
		VertexLocation[] adjEdges = new VertexLocation[3];
		VertexLocation temp = getNormalizedLocation();
		switch (temp.getDir()) {
			case NorthWest:
				adjEdges[0] = new VertexLocation(vertexLocation, VertexDirection.NorthEast);
				adjEdges[1] = new VertexLocation(vertexLocation, VertexDirection.West);
				adjEdges[2] = new VertexLocation(vertexLocation.getNeighborLoc(EdgeDirection.North),
						VertexDirection.West);
				break;
			case NorthEast:
				adjEdges[0] = new VertexLocation(vertexLocation, VertexDirection.NorthWest);
				adjEdges[1] = new VertexLocation(vertexLocation, VertexDirection.East);
				adjEdges[2] = new VertexLocation(vertexLocation.getNeighborLoc(EdgeDirection.North),
						VertexDirection.East);
				break;
			default:
				assert false;
				return null;
		}
		return adjEdges;	
	}
	
	public EdgeLocation[] getAdjacentEdges() {
		EdgeLocation[] adjEdges = new EdgeLocation[3];
		VertexLocation temp = getNormalizedLocation();
		switch (temp.getDir()) {
			case NorthWest:
				adjEdges[0] = new EdgeLocation(vertexLocation, EdgeDirection.North);
				adjEdges[1] = new EdgeLocation(vertexLocation, EdgeDirection.NorthWest);
				adjEdges[2] = new EdgeLocation(vertexLocation.getNeighborLoc(EdgeDirection.North),
						EdgeDirection.SouthWest);
				break;
			case NorthEast:
				adjEdges[0] = new EdgeLocation(vertexLocation, EdgeDirection.North);
				adjEdges[1] = new EdgeLocation(vertexLocation, EdgeDirection.NorthEast);
				adjEdges[2] = new EdgeLocation(vertexLocation.getNeighborLoc(EdgeDirection.North),
						EdgeDirection.SouthEast);
				break;
			default:
				assert false;
				return null;
		}
		return adjEdges;	
	}
}
