package shared.models.DTO.params;

import shared.locations.*;

public class RoadLocation {
	
	private int x;
	private int y;
	private EdgeDirection direction;
	
	public RoadLocation(EdgeLocation loc) {
		x = loc.getHexLoc().getX();
		y = loc.getHexLoc().getY();
		direction = loc.getDir();
	}
	
	public RoadLocation(int x, int y, EdgeDirection dir) {
		this.x = x;
		this.y = y;
		direction = dir;
	}
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public EdgeDirection getDirection() {
		return direction;
	}
	public void setDirection(EdgeDirection direction) {
		this.direction = direction;
	}
	
	public EdgeLocation getEdgeLocation() {
		return new EdgeLocation(new HexLocation(x,y), direction);
	}
}
