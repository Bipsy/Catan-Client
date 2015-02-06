package shared.models.DTO.params;

import shared.definitions.ResourceType;

public class YearOfPlenty extends MoveParams {
	
	private ResourceType resource1;
	private ResourceType resource2;

	public YearOfPlenty() {
		super("Year_of_Plenty");
	}

	public YearOfPlenty(int playerIndex) {
		super("Year_of_Plenty", playerIndex);
	}

	public ResourceType getResource1() {
		return resource1;
	}

	public void setResource1(ResourceType resource1) {
		this.resource1 = resource1;
	}

	public ResourceType getResource2() {
		return resource2;
	}

	public void setResource2(ResourceType resource2) {
		this.resource2 = resource2;
	}

	
}
