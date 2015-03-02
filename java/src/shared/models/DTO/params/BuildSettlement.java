package shared.models.DTO.params;

import shared.locations.VertexLocation;
import shared.models.DTO.VertexLocationDTO;

public class BuildSettlement extends MoveParams {

    private static final String BUILD_SETTLEMENT = "buildSettlement";
    private VertexLocationDTO vertexLocation;
    private boolean free;

    public BuildSettlement() {
        super(BUILD_SETTLEMENT);
        this.vertexLocation = null;
        free = false;
    }

    public BuildSettlement(int playerIndex, VertexLocation vertexLocation,
            boolean free) {
        super(BUILD_SETTLEMENT, playerIndex);
        this.vertexLocation = new VertexLocationDTO(vertexLocation.getHexLoc().getX(), vertexLocation.getHexLoc().getY(), vertexLocation.getDir());
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
