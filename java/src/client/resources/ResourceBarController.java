package client.resources;

import java.util.*;

import shared.definitions.ResourceType;
import shared.exceptions.InvalidPlayerIndex;
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
    	
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	ModelFacade model = (ModelFacade) arg;
        	int playerIndex = model.getCurrentPlayerIndex();

            boolean canBuildRoad = model.canBuildRoad(playerIndex) && model.getState().equals("Playing");
            boolean canBuildSettlement = model.canBuildSettlement(playerIndex) && model.getState().equals("Playing");
            boolean canBuildCity = model.canBuildCity(playerIndex) && model.getState().equals("Playing");
            boolean canBuyCard = model.canBuyDevCard(playerIndex) && model.getState().equals("Playing");
            boolean canPlayDevCard = model.canPlayDevCard(playerIndex) && model.getState().equals("Playing");
            
            


            int woodAmount = model.getResourceCount(playerIndex, ResourceType.WOOD);
            int brickAmount = model.getResourceCount(playerIndex, ResourceType.BRICK);
            int sheepAmount = model.getResourceCount(playerIndex, ResourceType.SHEEP);
            int wheatAmount = model.getResourceCount(playerIndex, ResourceType.WHEAT);
            int oreAmount = model.getResourceCount(playerIndex, ResourceType.ORE);
            int roadAmount = 0, settlementAmount = 0, cityAmount = 0;
			try {
				roadAmount = model.getPlayer(playerIndex).getRoads();
				settlementAmount = model.getPlayer(playerIndex).getSettlements();
				cityAmount = model.getPlayer(playerIndex).getCities();
			} catch (InvalidPlayerIndex e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            int soliderCount = model.soldierCount(playerIndex);
            
            this.getView().setElementAmount(ResourceBarElement.WOOD, woodAmount);
            this.getView().setElementAmount(ResourceBarElement.BRICK, brickAmount);
            this.getView().setElementAmount(ResourceBarElement.SHEEP, sheepAmount);
            this.getView().setElementAmount(ResourceBarElement.WHEAT, wheatAmount);
            this.getView().setElementAmount(ResourceBarElement.ORE, oreAmount);

			this.getView().setElementEnabled(ResourceBarElement.ROAD, canBuildRoad);
			this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, canBuildSettlement);
			this.getView().setElementEnabled(ResourceBarElement.CITY, canBuildCity);
			this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, canBuyCard);
			this.getView().setElementEnabled(ResourceBarElement.PLAY_CARD, canPlayDevCard);
			
			this.getView().setElementAmount(ResourceBarElement.ROAD, roadAmount);
			this.getView().setElementAmount(ResourceBarElement.SETTLEMENT, settlementAmount);
			this.getView().setElementAmount(ResourceBarElement.CITY, cityAmount);
			this.getView().setElementAmount(ResourceBarElement.SOLDIERS, soliderCount);
        }
    }

}
