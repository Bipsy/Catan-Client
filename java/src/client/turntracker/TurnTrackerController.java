package client.turntracker;

import shared.definitions.CatanColor;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.Player;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

import java.util.Observable;
import java.util.Observer;

/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller 
    implements ITurnTrackerController, Observer {

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

    }

    private void initFromModel(ModelFacade facade) {
    	
    	if (facade == null || !facade.hasModel()) return;
    	
    	String username = facade.getLocalUserName();
    	
        //<temp>
    	CatanColor color = facade.GetPlayerColor(username);
    	
    	if(color != null)
    		getView().setLocalPlayerColor(color);
        boolean currentTurn = false;
        
        for (int i = 0; i < 4; i++) {
			Player player;
			try {
				player = facade.getPlayer(i);
				currentTurn = username.equals(player.getUsername()) && facade.isCurrentTurn(i);
				getView().initializePlayer(i, player.getUsername(), player.getColor());
				getView().updatePlayer(i, player.getVictoryPoints(), facade.isCurrentTurn(i), 
						facade.getLargestArmy() == i, facade.getlongestRoad() == i);
			} catch (InvalidPlayerIndex e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        String state = facade.getState();
        getView().updateGameState(state, currentTurn);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	ModelFacade facade = (ModelFacade) arg;
            initFromModel(facade);
        }
    }

}
