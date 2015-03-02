package client.turntracker;

import shared.definitions.CatanColor;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.Player;
import shared.models.DTO.params.FinishTurn;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller 
    implements ITurnTrackerController, Observer {

	private ServerProxy proxy = ServerProxy.getInstance();
	private ModelFacade facade;

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);
        Populator.getInstance().addObserver(this);
        initFromModel(null);
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {
    	FinishTurn endTurn = new FinishTurn(facade.getLocalPlayerIndex());
    	try {
			proxy.finishTurn(endTurn);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
    }

    private void initFromModel(ModelFacade facade) {
    	
    	if (facade == null || !facade.hasModel()) return;
    	
    	String username = facade.getLocalUserName();
    	
        //<temp>
    	CatanColor color = facade.GetPlayerColor(username);
    	
    	if(color != null)
    		getView().setLocalPlayerColor(color);
        int currentTurn = -1;
        
        for (int i = 0; i < 4; i++) {
			try {
				Player player = facade.getPlayer(i);
				if(facade.isCurrentTurn(i))
					currentTurn = i;
				getView().initializePlayer(i, player.getUsername(), player.getColor());
				getView().updatePlayer(i, player.getVictoryPoints(), facade.isCurrentTurn(i), 
						facade.getLargestArmy() == i, facade.getlongestRoad() == i);
			} catch (InvalidPlayerIndex e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        String state = facade.getState();
        boolean enabled = false;
		Player player = null;
		boolean isTurn = false;
		try {
			player = facade.getPlayer(currentTurn);
			isTurn = facade.isCurrentTurn(currentTurn);
		} catch (InvalidPlayerIndex e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        switch (state) {
		case "Rolling":
		case "Robbing":
			if(!isTurn) {
				state = "Waiting for Other Players";
			}
			break;
		case "Discarding":
		case "FirstRound":
			if(!isTurn) {
				state = "Waiting for Other Players";
			}
			else {
				if(player != null && player.getRoads() == 14 && player.getSettlements() == 4) {
					state = "Finish Turn";
					enabled = true;
				}
			}
			break;
		case "SecondRound":
			if(!isTurn) {
				state = "Waiting for Other Players";
			}
			else {
				if(player != null && player.getRoads() == 13 && player.getSettlements() == 3) {
					state = "Finish Turn";
					enabled = true;
				}
			}
			break;
		case "Playing":
			if(!isTurn) {
				state = "Waiting for Other Players";
			}
			else {
				state = "Finish Turn";
				enabled = true;
			}
			break;

		default:
			state = "Waiting for Other Players";
			enabled = false;
			break;
		}
        getView().updateGameState(state, enabled);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	facade = (ModelFacade) arg;
            initFromModel(facade);
        }
    }

}
