package shared.models;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

public class Bank {

    private ResourceList resources;
    private DevCardList devCards;

    public Bank() {
        // TODO Auto-generated constructor stub
    }

    public Bank(ResourceList resourceList, DevCardList devCardList) {
        this.resources = resourceList;
        this.devCards = devCardList;
    }

    /**
     * This function gets called to determine if there are enough cards to be
     * distributed when drawing resource cards
     *
     * @param type the type of resource card to check
     * @param amount the number of resource cards to draw
     * @return true if they can be drawn, false otherwise
     */
    public boolean canDrawResources(ResourceType type, int amount) {
        return false;

    }

    /**
     * This function gets called when resources are drawn to buy roads,
     * settlements, cities, and dev cards or when a maritime trade takes place.
     *
     * @pre <code>canDrawResources(ResourceType, int)</code> must return true.
     * @param type the type of resource
     * @param amount the amount to draw
     */
    public void drawResources(ResourceType type, int amount) {

    }

    /**
     * This function gets called when resources are discarded to buy roads,
     * settlements, cities, and dev cards or when a maritime trade takes place.
     *
     * @param type type of resource
     * @param amount amount of resource
     */
    public void discardResources(ResourceType type, int amount) {

    }

    /**
     * this function is called when a user wants to buy a dev card. There must
     * be at least one card in the devCardList
     *
     * @return true if there is at least one dev card, false otherwise
     */
    public boolean canBuyDevCard() {
        return false;

    }

    /**
     * draws a random dev card and returns the type
     *
     * @pre <code>canBuyDevCard()</code> must return true
     * @return
     */
    public DevCardType buyDevCard() {
        return null;

    }
}
