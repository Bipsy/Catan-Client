package shared.models.DTO.params;

import shared.definitions.ResourceType;

public class Monopoly extends MoveParams {
	
	private ResourceType resource;

	public Monopoly() {
		super("Monopoly");
		resource = null;
	}

	public Monopoly(int playerIndex, ResourceType resource) {
		super("Monopoly", playerIndex);
		this.resource = resource;
	}

	public ResourceType getResource() {
		return resource;
	}

	public void setResource(ResourceType resource) {
		this.resource = resource;
	}

}
