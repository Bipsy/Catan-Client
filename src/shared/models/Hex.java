package shared.models;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.List;
import java.util.Map;

/**
 * Represents a hex on the board.
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Hex {
	private HexLocation location;
	private ResourceType resource;
	private int chit;
	private Map<EdgeLocation, Road> roadMap;
	private Harbor harbor;
	private Map<VertexLocation, VertexObject> communityMap;
	
	/**
	 * This function returns all the adjacent edges.
	 * @return list of edges
	 */
	public List<EdgeLocation> getAdjacentEdges() {
		return null;
	}
	
	/**
	 * This function returns all the adjacent vertices
	 * @return list of vertices
	 */
	public List<VertexLocation> getAdjacentVertices() {
		return null;
	}
	
	/**
	 * Returns the hex neighbors
	 * @return list of hexes
	 */
	public List<Hex> getNeighbors() {
		return null;
	}
	
	/**
	 * Determines whether or not a road can be build on it.
	 * @return true if it can, false if not.
	 */
	public boolean canBuildRoad() {
		return false;
	}
	
	/**
	 * Determines if a settlement can be built
	 * @return true if so, false if not
	 */
	public boolean canBuildSettlement() {
		return false;
	}
	
	/**
	 * Determines if a city can be built
	 * @return true if so, false if not
	 */
	public boolean canBuildCity() {
		return false;
	}
	
}
