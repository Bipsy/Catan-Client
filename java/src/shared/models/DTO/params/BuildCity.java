package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildCity extends MoveParams {

    private VertexLocationDTO vertexLocation;

    public BuildCity() {
        super("buildCity");
        this.vertexLocation = null;
    }

    public BuildCity(int playerIndex, VertexLocationDTO vertexLocation) {
        super("buildCity", playerIndex);
        this.vertexLocation = vertexLocation;
    }

    public VertexLocationDTO getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocationDTO vertexLocation) {
        this.vertexLocation = vertexLocation;
    }

}
