package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildSettlement extends MoveParams {

    private VertexLocation vertexLocation;

    public BuildSettlement() {
        super("buildSettlement");
        this.vertexLocation = null;
    }

    public BuildSettlement(int playerIndex, VertexLocation vertexLocation) {
        super("buildSettlement", playerIndex);
        this.vertexLocation = vertexLocation;
    }

    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocation vertexLocation) {
        this.vertexLocation = vertexLocation;
    }
}
