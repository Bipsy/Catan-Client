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

		// Return location that has direction NW or NE
        switch (direction) {
            case NW:
            case NE:
                return this;
            case W:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.SW),
                        VertexDirection.NE);
            case SW:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.S),
                        VertexDirection.NW);
            case SE:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.S),
                        VertexDirection.NE);
            case E:
                return new VertexLocation(
                        vertexLocation.getNeighborLoc(EdgeDirection.SE),
                        VertexDirection.NW);
            default:
                assert false;
                return null;
        }
    }

	public VertexLocation[] getAdjacentVertexes() {
		VertexLocation[] adjEdges = new VertexLocation[3];
		VertexLocation temp = getNormalizedLocation();
		switch (temp.getDir()) {
			case NW:
				adjEdges[0] = new VertexLocation(vertexLocation, VertexDirection.NE);
				adjEdges[1] = new VertexLocation(vertexLocation, VertexDirection.W);
				adjEdges[2] = new VertexLocation(vertexLocation.getNeighborLoc(EdgeDirection.N),
						VertexDirection.W);
				break;
			case NE:
				adjEdges[0] = new VertexLocation(vertexLocation, VertexDirection.NW);
				adjEdges[1] = new VertexLocation(vertexLocation, VertexDirection.E);
				adjEdges[2] = new VertexLocation(vertexLocation.getNeighborLoc(EdgeDirection.N),
						VertexDirection.E);
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
			case NW:
				adjEdges[0] = new EdgeLocation(vertexLocation, EdgeDirection.N);
				adjEdges[1] = new EdgeLocation(vertexLocation, EdgeDirection.NW);
				adjEdges[2] = new EdgeLocation(vertexLocation.getNeighborLoc(EdgeDirection.N),
						EdgeDirection.SW);
				break;
			case NE:
				adjEdges[0] = new EdgeLocation(vertexLocation, EdgeDirection.N);
				adjEdges[1] = new EdgeLocation(vertexLocation, EdgeDirection.NE);
				adjEdges[2] = new EdgeLocation(vertexLocation.getNeighborLoc(EdgeDirection.N),
						EdgeDirection.SE);
				break;
			default:
				assert false;
				return null;
		}
		return adjEdges;	
	}
}
