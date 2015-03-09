package shared.models.DTO.params;

import shared.locations.EdgeLocation;

public class RoadBuilding extends MoveParams {

    private RoadLocation spot1;
    private RoadLocation spot2;

    public RoadBuilding() {
        super("Road_Building");
        spot1 = null;
        spot2 = null;
    }

    public RoadBuilding(int playerIndex, RoadLocation spot1, RoadLocation spot2) {
        super("Road_Building", playerIndex);
        this.spot1 = spot1;
        this.spot2 = spot2;
    }

    public RoadLocation getSpot1() {
        return spot1;
    }

    public void setSpot1(RoadLocation spot1) {
        this.spot1 = spot1;
    }

    public RoadLocation getSpot2() {
        return spot2;
    }

    public void setSpot2(RoadLocation spot2) {
        this.spot2 = spot2;
    }

}
