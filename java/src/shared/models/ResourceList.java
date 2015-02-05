package shared.models;

import shared.definitions.ResourceType;
import shared.models.DTO.ResourceListDTO;

/**
 * This class will only store a list of resources. It is used by the
 * <code>PlayerHand</code> and the <code>Bank</code> classes to store resources.
 *
 * @author Austin
 *
 */
public class ResourceList {

    private int brick;
    private int ore;
    private int sheep;
    private int wheat;
    private int wood;

    public ResourceList() {
        // TODO Auto-generated constructor stub
    }

    public ResourceList(ResourceListDTO resources) {
        this.brick = resources.getBrick();
        this.ore = resources.getOre();
        this.sheep = resources.getSheep();
        this.wheat = resources.getWheat();
        this.wood = resources.getWood();
    }

    /**
     * This function is used to add resources to the list
     *
     * @param type the type of resource
     * @param amount the number of cards
     */
    public void addCards(ResourceType type, int amount) {

    }

    /**
     * This function is used to remove resources from the list
     *
     * @pre the type and amount of resources needs to be available in the list
     * before they can be removed
     * @param type the type of resource
     * @param amount the amount of resources
     */
    public void removeCards(ResourceType type, int amount) {

    }

    /**
     * This function will return the number of cards of a specified resource
     * type in the <code>ResourceList</code>
     *
     * @param type type of resource to check
     * @return the number of cards in the list
     */
    public int getResourceNumber(ResourceType type) {
        return 0;
    }
}
