package shared.models.DTO.params;

import shared.definitions.ResourceType;

public class MaritimeTrade extends MoveParams {
	
	private int ratio;
	private ResourceType inputResource;
	private ResourceType outputResource;

	public MaritimeTrade() {
		super("maritimeTrade");
		ratio = 0;
		inputResource = null;
		outputResource = null;
	}

	public MaritimeTrade(int playerIndex, int ratio, 
			ResourceType inputResource, ResourceType outputResource) {
		super("maritimeTrade", playerIndex);
		setRatio(ratio);
		setInputResource(inputResource);
		setOutputResource(outputResource);
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public ResourceType getInputResource() {
		return inputResource;
	}

	public void setInputResource(ResourceType inputResource) {
		this.inputResource = inputResource;
	}

	public ResourceType getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(ResourceType outputResource) {
		this.outputResource = outputResource;
	}

}
