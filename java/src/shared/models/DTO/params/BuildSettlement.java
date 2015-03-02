package shared.models.DTO.params;

import shared.locations.VertexLocation;

public class BuildSettlement extends MoveParams {

    private static final String BUILD_SETTLEMENT = "buildSettlement";
    private VertexLocationDTO vertexLocation;
    private boolean free;

    public BuildSettlement() {
        super(BUILD_SETTLEMENT);
        this.vertexLocation = null;
        free = false;
    }

    public BuildSettlement(int playerIndex, VertexLocationDTO vertexLocation,
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

    public VertexLocationDTO getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocationDTO vertexLocation) {
        this.vertexLocation = vertexLocation;
    }
}
