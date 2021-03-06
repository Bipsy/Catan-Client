package shared.models;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.models.DTO.PortDTO;

/**
 * Harbors allow players to trade resources more favorably. An instance of the
 * Harbor class represents which resource and at which ratio that resource may
 * be traded with at the bank.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Harbor {

    private PortType resource;
    private int ratio;
    private EdgeLocation location;

    public Harbor(PortDTO portDTO) {
    	if(portDTO.getResource() == null) {
    		portDTO.setResource(PortType.THREE);
    	}
        this.resource = portDTO.getResource();
        this.ratio = portDTO.getRatio();
        this.location = new EdgeLocation(portDTO.getLocation(), portDTO.getDirection());
    }

    public PortType getResource() {
        return resource;
    }

    public void setResource(PortType resource) {
        this.resource = resource;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public EdgeLocation getLocation() {
        return location;
    }

    public void setLocation(EdgeLocation location) {
        this.location = location;
    }
}
