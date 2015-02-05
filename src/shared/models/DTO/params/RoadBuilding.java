package shared.models.DTO.params;

import shared.locations.EdgeLocation;

public class RoadBuilding extends MoveParams {

	private EdgeLocation spot1;
	private EdgeLocation spot2;
	
	public RoadBuilding() {
		super("Road_Building");
		spot1 = null;
		spot2 = null;
	}

	public RoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		super("Road_Building", playerIndex);
		this.spot1 = spot1;
		this.spot2 = spot2;
	}

	public EdgeLocation getSpot1() {
		return spot1;
	}

	public void setSpot1(EdgeLocation spot1) {
		this.spot1 = spot1;
	}

	public EdgeLocation getSpot2() {
		return spot2;
	}

	public void setSpot2(EdgeLocation spot2) {
		this.spot2 = spot2;
	}
	

}
