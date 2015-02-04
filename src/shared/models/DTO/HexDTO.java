package shared.models.DTO;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;

/**
 * This class stores the information needed to create a JSON string of a hex, 
 * and is used to facilitate the transfer of data between the server and client.
 * @author Austin
 *
 */
public class HexDTO {
	private HexLocation location;
	/**
	 * Must be: 'Wood', 'Brick', 'Sheep', 'Wheat', or 'Ore', optional
	 */
	private ResourceType resource;
	/**
	 * there is no number for desert or water tiles
	 */
	private Integer number;
	
	public HexLocation getLocation() {
		return location;
	}
	
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	public ResourceType getResource() {
		return resource;
	}
	
	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
}
