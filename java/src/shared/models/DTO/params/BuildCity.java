package shared.models.DTO.params;

import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.DTO.VertexLocationDTO;

public class BuildCity extends MoveParams {

    private VertexLocationDTO vertexLocation;

    public BuildCity() {
        super("buildCity");
        this.vertexLocation = null;
    }

    public BuildCity(int playerIndex, VertexLocation vertexLocation) {
        super("buildCity", playerIndex);
        this.vertexLocation = new VertexLocationDTO(vertexLocation.getHexLoc().getX(), vertexLocation.getHexLoc().getY(), vertexLocation.getDir());
    }

    public VertexLocationDTO getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocationDTO vertexLocation) {
        this.vertexLocation = vertexLocation;
    }

}
