package shared.models;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.DTO.HexDTO;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

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
//    	System.out.println(hexDTO.getLocation());
//    	System.out.println(hexDTO.getNumber());
//    	System.out.println(hexDTO.getResource());
        this.location = new HexLocation(hexDTO.getLocation());
        this.resource = hexDTO.getResource();
        if(this.resource == null)
        	this.resource = HexType.DESERT;
        this.chit = hexDTO.getNumber();
    }

    /**
     * This function returns all the adjacent edges.
     *
     * @return list of edges
     */
    public List<EdgeLocation> getAdjacentEdges() {
        return null;
    }

    /**
     * This function returns all the adjacent vertices
     *
     * @return list of vertices
     */
    public List<VertexLocation> getAdjacentVertices() {
        return null;
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

}
