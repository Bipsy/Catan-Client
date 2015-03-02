package shared.models.DTO.params;

import shared.locations.EdgeLocation;
import shared.models.DTO.EdgeLocationDTO;

public class BuildRoad extends MoveParams {

    private EdgeLocationDTO roadLocation;
    private boolean free;

    public BuildRoad() {
        super("buildRoad");
        this.roadLocation = null;
        this.free = false;
        // TODO Auto-generated constructor stub
    }

    public BuildRoad(int playerIndex, EdgeLocation edgeLocation, boolean free) {
        super("buildRoad", playerIndex);
        this.roadLocation = new EdgeLocationDTO(edgeLocation.getHexLoc().getX(), edgeLocation.getHexLoc().getY(), edgeLocation.getDir());
        this.free = free;
    }

    public EdgeLocationDTO getRoadLocation() {
        return roadLocation;
    }

    public void setRoadLocation(EdgeLocationDTO roadLocation) {
        this.roadLocation = roadLocation;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

}
