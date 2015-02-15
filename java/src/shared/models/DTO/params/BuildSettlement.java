package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildSettlement extends MoveParams {

    private static final String BUILD_SETTLEMENT = "buildSettlement";
    private VertexLocation vertexLocation;
    private boolean free;

    public BuildSettlement() {
        super(BUILD_SETTLEMENT);
        this.vertexLocation = null;
        free = false;
    }

    public BuildSettlement(int playerIndex, VertexLocation vertexLocation,
            boolean free) {
        super(BUILD_SETTLEMENT, playerIndex);
        this.vertexLocation = vertexLocation;
        this.free = free;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocation vertexLocation) {
        this.vertexLocation = vertexLocation;
    }
}
