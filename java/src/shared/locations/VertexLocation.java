package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation {

    private HexLocation hexLoc;
    private VertexDirection dir;

    public VertexLocation(HexLocation hexLoc, VertexDirection dir) {
        setHexLoc(hexLoc);
        setDir(dir);
    }

    public HexLocation getHexLoc() {
        return hexLoc;
    }

    private void setHexLoc(HexLocation hexLoc) {
        if (hexLoc == null) {
            throw new IllegalArgumentException("hexLoc cannot be null");
        }
        this.hexLoc = hexLoc;
    }

    public VertexDirection getDir() {
        return dir;
    }

    private void setDir(VertexDirection direction) {
        this.dir = direction;
    }

    @Override
    public String toString() {
        return "VertexLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
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
        if (dir != other.dir) {
            return false;
        }
        if (hexLoc == null) {
            if (other.hexLoc != null) {
                return false;
            }
        } else if (!hexLoc.equals(other.hexLoc)) {
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
        switch (dir) {
            case NorthWest:
            case NorthEast:
                return this;
            case West:
                return new VertexLocation(
                        hexLoc.getNeighborLoc(EdgeDirection.SouthWest),
                        VertexDirection.NorthEast);
            case SouthWest:
                return new VertexLocation(
                        hexLoc.getNeighborLoc(EdgeDirection.South),
                        VertexDirection.NorthWest);
            case SouthEast:
                return new VertexLocation(
                        hexLoc.getNeighborLoc(EdgeDirection.South),
                        VertexDirection.NorthEast);
            case East:
                return new VertexLocation(
                        hexLoc.getNeighborLoc(EdgeDirection.SouthEast),
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
				adjEdges[0] = new VertexLocation(hexLoc, VertexDirection.NorthEast);
				adjEdges[1] = new VertexLocation(hexLoc, VertexDirection.West);
				adjEdges[2] = new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.North),
						VertexDirection.West);
				break;
			case NorthEast:
				adjEdges[0] = new VertexLocation(hexLoc, VertexDirection.NorthWest);
				adjEdges[1] = new VertexLocation(hexLoc, VertexDirection.East);
				adjEdges[2] = new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.North),
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
				adjEdges[0] = new EdgeLocation(hexLoc, EdgeDirection.North);
				adjEdges[1] = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
				adjEdges[2] = new EdgeLocation(hexLoc.getNeighborLoc(EdgeDirection.North),
						EdgeDirection.SouthWest);
				break;
			case NorthEast:
				adjEdges[0] = new EdgeLocation(hexLoc, EdgeDirection.North);
				adjEdges[1] = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
				adjEdges[2] = new EdgeLocation(hexLoc.getNeighborLoc(EdgeDirection.North),
						EdgeDirection.SouthEast);
				break;
			default:
				assert false;
				return null;
		}
		return adjEdges;	
	}
}
