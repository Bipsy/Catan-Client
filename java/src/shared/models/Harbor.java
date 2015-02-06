package shared.models;

import shared.definitions.ResourceType;
import shared.models.DTO.PortDTO;

/**
 * Harbors allow players to trade resources more favorably. An instance of the
 * Harbor class represents which resource and at which ratio that resource may
 * be traded with at the bank.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Harbor {

    private ResourceType resource;
    private int ratio;

    public Harbor(PortDTO portDTO) {
        this.resource = portDTO.getResource();
        this.ratio = portDTO.getRatio();
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
