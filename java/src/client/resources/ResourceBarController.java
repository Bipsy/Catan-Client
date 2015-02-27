package client.resources;

import java.util.*;

import shared.locations.EdgeLocation;
import shared.models.DTO.params.BuildRoad;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller 
    implements IResourceBarController, Observer {

    private Map<ResourceBarElement, IAction> elementActions;

    public ResourceBarController(IResourceBarView view) {

        super(view);
        Populator.getInstance().addObserver(this);
        elementActions = new HashMap<ResourceBarElement, IAction>();
    }

    @Override
    public IResourceBarView getView() {
        return (IResourceBarView) super.getView();
    }

    /**
     * Sets the action to be executed when the specified resource bar element is
     * clicked by the user
     *
     * @param element The resource bar element with which the action is
     * associated
     * @param action The action to be executed
     */
    public void setElementAction(ResourceBarElement element, IAction action) {

        elementActions.put(element, action);
    }

    @Override
    public void buildRoad() {
        executeElementAction(ResourceBarElement.ROAD);
    }

    @Override
    public void buildSettlement() {
        executeElementAction(ResourceBarElement.SETTLEMENT);
    }

    @Override
    public void buildCity() {
        executeElementAction(ResourceBarElement.CITY);
    }

    @Override
    public void buyCard() {
        executeElementAction(ResourceBarElement.BUY_CARD);
    }

    @Override
    public void playCard() {
        executeElementAction(ResourceBarElement.PLAY_CARD);
    }

    private void executeElementAction(ResourceBarElement element) {

        if (elementActions.containsKey(element)) {

            IAction action = elementActions.get(element);
            action.execute();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
//    	System.out.println("observing from resource bar");
    	
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	ModelFacade model = (ModelFacade) arg;
        	int playerIndex = model.getCurrentPlayerIndex();

            boolean canBuildRoad = model.canBuildRoad(playerIndex);
            boolean canBuildSettlement = model.canBuildSettlement(playerIndex);
            boolean canBuildCity = model.canBuildCity(playerIndex);
//            boolean canBuyCard = model.hasResourcesForRoad(playerIndex);
//            boolean canPlayCard = model.hasResourcesForRoad(playerIndex);
//            boolean canSoldiers = model.hasResourcesForRoad(playerIndex);
//            System.out.println(canBuildRoad);
//            System.out.println(canBuildSettlement);
//            System.out.println(canBuildCity);

			this.getView().setElementEnabled(ResourceBarElement.ROAD, canBuildRoad);
			this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, canBuildSettlement);
			this.getView().setElementEnabled(ResourceBarElement.CITY, canBuildCity);
			this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			this.getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
			this.getView().setElementEnabled(ResourceBarElement.SOLDIERS, false);
        }
    }

}
