package shared.models.DTO.params;

import shared.definitions.ResourceType;

public class YearOfPlenty extends MoveParams {

    private static final String YEAR_OF_PLENTY = "Year_of_Plenty";
    private ResourceType resource1;
    private ResourceType resource2;

    public YearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
        super(YEAR_OF_PLENTY, playerIndex);
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    public YearOfPlenty() {
        super(YEAR_OF_PLENTY);
    }

    public YearOfPlenty(int playerIndex) {
        super(YEAR_OF_PLENTY, playerIndex);
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
