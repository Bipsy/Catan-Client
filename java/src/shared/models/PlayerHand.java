package shared.models;

import shared.definitions.DevCardType;
import shared.models.DTO.DevCardListDTO;
import shared.models.DTO.ResourceListDTO;

public class PlayerHand {

    private ResourceList resources;
    private DevCardList devCards;

    public PlayerHand() {
        // TODO Auto-generated constructor stub
    }

    public PlayerHand(ResourceListDTO resourceList, DevCardListDTO devCardList) {
        resources = new ResourceList(resourceList);
        devCards = new DevCardList(devCardList);
    }

    /**
     * This function is used to determine if a player has enough resources to
     * buy an object (road settlement, city) I don't currently know what object
     * would store the build requirements for each object, but that would be
     * passed in as a parameter
     *
     * @return whether the object passed in can be built.
     */
    public boolean canBuy() {
        /**
         * I'm assuming that there will be objects that have requirements on
         * what resources are needed to buy each object and that will be passed
         * in
         */
        return false;
    }

    /**
     * This function is used to buy an object I don't currently know what object
     * would store the build requirements for each object, but that would be
     * passed in as a parameter. If the requirements are met, the number of
     * resources would be removed from <code>resources</code>
     */
    public void buy() {

    }

    /**
     * This function is used to determine if the player can buy a dev card
     *
     * @return returns true if the player can, false otherwise
     */
    public boolean canBuyDevCard() {
        return false;
    }

    /**
     * This function is used to buy a dev card
     *
     * @pre <code>canBuyDevCard()</code> must return true
     */
    public void buyDevCard() {

    }

    /**
     * This function is used to determine if a dev card can be played
     *
     * @pre User must own dev card in order to play it
     * @param type the type of dev card
     * @return true if player owns dev card
     */
    public boolean canUseDevCard(DevCardType type) {
        return true;
    }

    /**
     * this function is used when a player plays a dev card to decrement the
     * type of dev card owned by the player
     *
     * @pre the function <code>canUseDevCard(DevCardType)</code> must return
     * true
     * @param type type of dev card played
     */
    public void useDevCard(DevCardType type) {

    }
}
