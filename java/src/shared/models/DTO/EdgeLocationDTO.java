package shared.models.DTO;

import shared.locations.EdgeDirection;

public class EdgeLocationDTO {
	private int x;
	private int y;
	private EdgeDirection direction;
	
	public EdgeLocationDTO(int x, int y, EdgeDirection direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
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
}
