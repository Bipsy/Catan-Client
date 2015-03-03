package shared.definitions;

import com.google.gson.annotations.SerializedName;

public enum PortType {

    @SerializedName("wood")
    WOOD, 
    @SerializedName("brick")
    BRICK, 
    @SerializedName("sheep")
    SHEEP, 
    @SerializedName("wheat")
    WHEAT, 
    @SerializedName("ore")
    ORE, 
    @SerializedName("three")
    THREE;
    
    public static PortType convertToResource(ResourceType resource) {    
        switch (resource) {
            case BRICK :
                return PortType.BRICK;
            case ORE :
                return PortType.ORE;
            case SHEEP :
                return PortType.SHEEP;
            case WHEAT :
                return PortType.WHEAT;
            case WOOD :
                return PortType.WOOD;
            default :
                return null;
        }
    }
}
