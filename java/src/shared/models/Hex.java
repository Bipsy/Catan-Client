package shared.models;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.DTO.HexDTO;

import java.util.List;
import java.util.Map;
import shared.definitions.ResourceTypeOptional;

/**
 * Represents a hex on the board.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Hex {

    private HexLocation location;
    private ResourceTypeOptional resource;
    private int chit;
    private Map<EdgeLocation, Road> roadMap;
    private Harbor harbor;
    private Map<VertexLocation, VertexObject> communityMap;

    public Hex(HexDTO hexDTO) {
        this.location = new HexLocation(hexDTO.getLocation());
        this.resource = hexDTO.getResource();
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

    public ResourceTypeOptional getResource() {
        return resource;
    }

    public void setResource(ResourceTypeOptional resource) {
        this.resource = resource;
    }

    public int getChit() {
        return chit;
    }

    public void setChit(int chit) {
        this.chit = chit;
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
