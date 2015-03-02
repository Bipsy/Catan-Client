package client.domestic;

import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.Player;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.model.ModelFacade;
import client.model.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller 
    implements IDomesticTradeController, Observer {

    private IDomesticTradeOverlay tradeOverlay;
    private DomesticTradeState state;
    private IWaitView waitOverlay;
    private IAcceptTradeOverlay acceptOverlay;
    private PlayerInfo localPlayer;
    private PlayerInfo[] listOfPlayers;

    private String currState;
    private ModelFacade facade;

    /**
     * DomesticTradeController constructor
     *
     * @param tradeView Domestic trade view (i.e., view that contains the
     * "Domestic Trade" button)
     * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user
     * propose a domestic trade)
     * @param waitOverlay Wait overlay used to notify the user they are waiting
     * for another player to accept a trade
     * @param acceptOverlay Accept trade overlay which lets the user accept or
     * reject a proposed trade
     */
    public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
            IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

        super(tradeView);
        Populator.getInstance().addObserver(this);
        setTradeOverlay(tradeOverlay);
        setWaitOverlay(waitOverlay);
        setAcceptOverlay(acceptOverlay);
    }

    public IDomesticTradeView getTradeView() {

        return (IDomesticTradeView) super.getView();
    }

    public IDomesticTradeOverlay getTradeOverlay() {
        return tradeOverlay;
    }

    public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
        this.tradeOverlay = tradeOverlay;
    }

    public IWaitView getWaitOverlay() {
        return waitOverlay;
    }

    public void setWaitOverlay(IWaitView waitView) {
        this.waitOverlay = waitView;
    }

    public IAcceptTradeOverlay getAcceptOverlay() {
        return acceptOverlay;
    }

    public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
        this.acceptOverlay = acceptOverlay;
    }

    @Override
    public void startTrade() {
    	System.out.println("showing modal");
    	
    	listOfPlayers = new PlayerInfo[4];
    	for (int i=0; i<4; i++) {
    		try {
    			System.out.println(facade.getPlayer(i).getID());
    			System.out.println(i);
    			System.out.println(facade.getPlayer(i).getUsername());
    			System.out.println(facade.GetPlayerColor(i));
    			listOfPlayers[i] = new PlayerInfo(facade.getPlayer(i).getID(), i, facade.getPlayer(i).getUsername(), facade.GetPlayerColor(i));
			} catch (InvalidPlayerIndex e) {
				e.printStackTrace();
			}
    	}
    	
    	if (currState.equals("Playing")) {
    		System.out.println("currstate equals playing");
    		getTradeOverlay().showModal();
    		
    	}
    	//state.startTrade();
    }

    @Override
    public void decreaseResourceAmount(ResourceType resource) {
    	
    }

    @Override
    public void increaseResourceAmount(ResourceType resource) {
    	
    }

    @Override
    public void sendTradeOffer() {

        getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
    }

    @Override
    public void setPlayerToTradeWith(int playerIndex) {
    	
    }

    @Override
    public void setResourceToReceive(ResourceType resource) {

    }

    @Override
    public void setResourceToSend(ResourceType resource) {

    }

    @Override
    public void unsetResource(ResourceType resource) {

    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void acceptTrade(boolean willAccept) {

        getAcceptOverlay().closeModal();
    }

    public void updateState(String currState) {
    	
    	switch (currState) {
    		case "Playing":
    			state = new DomesticTradeState.Playing();
    			break;
			default:
				break;
    		
    	}
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	ModelFacade facade = (ModelFacade) arg;
            this.facade = facade;
            if (this.facade == null) {
            }
    		currState = facade.getState();
    		updateState(currState);
        }
    }

}
