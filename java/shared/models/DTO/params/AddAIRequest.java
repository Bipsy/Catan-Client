package shared.models.DTO.params;

public class AddAIRequest {
	
	private String AIType;

	public AddAIRequest() {
		AIType = null;
	}

	public AddAIRequest(String aIType) {
		super();
		AIType = aIType;
	}

	public String getAIType() {
		return AIType;
	}

	public void setAIType(String aIType) {
		AIType = aIType;
	}

}
