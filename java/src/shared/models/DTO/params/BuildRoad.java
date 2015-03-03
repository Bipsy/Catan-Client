package shared.models.DTO.params;


public class BuildRoad extends MoveParams {

    private RoadLocation roadLocation;
    private boolean free;

    public BuildRoad() {
        super("buildRoad");
        this.roadLocation = null;
        this.free = false;
        // TODO Auto-generated constructor stub
    }

    public BuildRoad(int playerIndex, RoadLocation roadLocation, boolean free) {
        super("buildRoad", playerIndex);
        this.roadLocation = roadLocation;
        this.free = free;
    }

    public RoadLocation getRoadLocation() {
        return roadLocation;
    }

    public void setRoadLocation(RoadLocation roadLocation) {
        this.roadLocation = roadLocation;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

}
