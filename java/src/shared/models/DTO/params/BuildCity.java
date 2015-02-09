package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildCity extends MoveParams {
	
	private VertexLocation vertexLocation;

	public BuildCity() {
		super("buildCity");
		this.vertexLocation = null;
	}

	public BuildCity(int playerIndex, VertexLocation vertexLocation) {
		super("buildCity", playerIndex);
		this.vertexLocation = vertexLocation;
	}

	public VertexLocation getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

}