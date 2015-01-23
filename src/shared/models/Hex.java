package shared.models;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class Hex {
	private HexLocation location;
	private ResourceType resource;
	private int chit;
	private Map<EdgeLocation, Road> roadMap;
	private Harbor harbor;
	private Map<VertexLocation, VertexObject> communityMap;
	
	public List<EdgeLocation> getAdjacentEdges() {
		return null;
	}
	
	public List<VertexLocation> getAdjacentVertices() {
		return null;
	}
	
	public List<Hex> getNeighbors() {
		return null;
	}
	
	public boolean canBuildRoad() {
		return false;
	}
	
	public boolean canBuildSettlement() {
		return false;
	}
	
	public boolean canBuildCity() {
		return false;
	}
	
}
