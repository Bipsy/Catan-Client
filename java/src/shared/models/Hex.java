package shared.models;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.DTO.HexDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.definitions.HexType;

/**
 * Represents a hex on the board.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Hex {

    private HexLocation location;
    private HexType resource;
    private Integer chit;
    private Map<EdgeLocation, Road> roadMap;
    private Harbor harbor;
    private Map<VertexLocation, VertexObject> communityMap;

    public Hex(HexDTO hexDTO) {
        this.location = new HexLocation(hexDTO.getLocation());
        this.resource = hexDTO.getResource();
        if(this.resource == null)
        	this.resource = HexType.DESERT;
        this.chit = hexDTO.getNumber();
    }

    /**
     * This function returns all six adjacent edges with normalized locations
     *
     * @return list of edges
     */
    public List<EdgeLocation> getAdjacentEdges() {
    	List<EdgeLocation> edges = new ArrayList<EdgeLocation>();
    	edges.add(new EdgeLocation(this.location, EdgeDirection.North).getNormalizedLocation());
    	edges.add(new EdgeLocation(this.location, EdgeDirection.NorthEast).getNormalizedLocation());
    	edges.add(new EdgeLocation(this.location, EdgeDirection.NorthWest).getNormalizedLocation());
    	edges.add(new EdgeLocation(this.location, EdgeDirection.South).getNormalizedLocation());
    	edges.add(new EdgeLocation(this.location, EdgeDirection.SouthEast).getNormalizedLocation());
    	edges.add(new EdgeLocation(this.location, EdgeDirection.SouthWest).getNormalizedLocation());
        return edges;
    }

    /**
     * This function returns all six adjacent vertices with normalized locations
     *
     * @return list of vertices
     */
    public List<VertexLocation> getAdjacentVertices() {
    	
    	List<VertexLocation> verticies = new ArrayList<VertexLocation>();
    	verticies.add(new VertexLocation(this.location, VertexDirection.East).getNormalizedLocation());
    	verticies.add(new VertexLocation(this.location, VertexDirection.NorthEast).getNormalizedLocation());
    	verticies.add(new VertexLocation(this.location, VertexDirection.NorthWest).getNormalizedLocation());
    	verticies.add(new VertexLocation(this.location, VertexDirection.West).getNormalizedLocation());
    	verticies.add(new VertexLocation(this.location, VertexDirection.SouthEast).getNormalizedLocation());
    	verticies.add(new VertexLocation(this.location, VertexDirection.SouthWest).getNormalizedLocation());

        return verticies;
    }

    /**
     * Returns the hex neighbors
     *
     * @return list of hexes
     */
    public List<Hex> getNeighbors() {
        return null;
    }

    /**
     * Determines whether or not a road can be build on it.
     *
     * @return true if it can, false if not.
     */
    public boolean canBuildRoad() {
        return false;
    }

    /**
     * Determines if a settlement can be built
     *
     * @return true if so, false if not
     */
    public boolean canBuildSettlement() {
        return false;
    }

    /**
     * Determines if a city can be built
     *
     * @return true if so, false if not
     */
    public boolean canBuildCity() {
        return false;
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

    public void setNumber(Integer number) {
        this.chit = number;
    }

    public Map<EdgeLocation, Road> getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(Map<EdgeLocation, Road> roadMap) {
        this.roadMap = roadMap;
    }

    public Harbor getHarbor() {
        return harbor;
    }

    public void setHarbor(Harbor harbor) {
        this.harbor = harbor;
    }

    public Map<VertexLocation, VertexObject> getCommunityMap() {
        return communityMap;
    }

    public void setCommunityMap(Map<VertexLocation, VertexObject> communityMap) {
        this.communityMap = communityMap;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chit == null) ? 0 : chit.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hex other = (Hex) obj;
		if (chit == null) {
			if (other.chit != null)
				return false;
		} else if (!chit.equals(other.chit))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (resource != other.resource)
			return false;
		return true;
	}
    

}
