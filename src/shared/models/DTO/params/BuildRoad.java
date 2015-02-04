package shared.models.DTO.params;

import javax.management.relation.Relation;

import shared.locations.EdgeLocation;

public class BuildRoad extends MoveParams {

	private EdgeLocation roadLocation;
	private boolean free;
	public BuildRoad() {
		super("buildRoad");
		this.roadLocation = null;
		this.free = false;
		// TODO Auto-generated constructor stub
	}

	public BuildRoad(int playerIndex, EdgeLocation roadLocation, boolean free) {
		super("buildRoad", playerIndex);
		this.roadLocation = roadLocation;
		this.free = free;
	}

	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
