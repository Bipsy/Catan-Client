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
        boolean currentTurn = false;
        
        for (int i = 0; i < 4; i++) {
			Player player;
			try {
				player = facade.getPlayer(i);
				if(!currentTurn)
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
        if(!currentTurn) {
        	state = "Waiting for Other Players";
        }
        
        boolean enable = currentTurn && (state.equals("Playing") || 
        			state.equals("FirstRound") || state.equals("SecondRound"));
        getView().updateGameState((state.equals("Playing")?"Finish Turn":state), enable);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	facade = (ModelFacade) arg;
            initFromModel(facade);
        }
    }

}
