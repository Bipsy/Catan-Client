package shared.models.DTO.params;

import shared.locations.*;

public class VertexLocationDTO {
	
	private int x;
	private int y;
	private VertexDirection direction;
	
	public VertexLocationDTO(VertexLocation loc) {
		x = loc.getHexLoc().getX();
		y = loc.getHexLoc().getY();
		direction = loc.getDir();
	}
	
	public VertexLocationDTO(int x, int y, VertexDirection dir) {
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
	
	public VertexDirection getDirection() {
		return direction;
	}
	
	public void setDirection(VertexDirection direction) {
		this.direction = direction;
	}
	
	public VertexLocation getVertexLocation() {
		return new VertexLocation(new HexLocation(x,y), direction);
	}
}
