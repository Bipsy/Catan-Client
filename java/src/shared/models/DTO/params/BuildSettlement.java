package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildSettlement extends MoveParams {
	
	private VertexLocation vertexLocation;
	private boolean free;

	public BuildSettlement() {
		super("buildSettlement");
		this.vertexLocation = null;
		this.free = false;
	}

	public BuildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) {
		super("buildSettlement", playerIndex);
		this.vertexLocation = vertexLocation;
		this.free = free;
	}

	public VertexLocation getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
