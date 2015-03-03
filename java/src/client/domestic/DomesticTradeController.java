package client.domestic;

import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.model.ModelFacade;
import client.model.Populator;

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
    private PlayerInfo[] listOfPlayers;
    
    private int brickAmt;
    private int oreAmt;
    private int sheepAmt;
    private int wheatAmt;
    private int woodAmt;
	private int brick;
	private int ore;
	private int sheep;
	private int wheat;
	private int wood;
    
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
    			listOfPlayers[i] = new PlayerInfo(facade.getPlayer(i).getID(), i, facade.getPlayer(i).getUsername(), facade.GetPlayerColor(i));
			} catch (InvalidPlayerIndex e) {
				e.printStackTrace();
			}
    	}
    	tradeOverlay.setPlayers(listOfPlayers);
    	
        brickAmt = 0;
        oreAmt = 0;
        sheepAmt = 0;
        wheatAmt = 0;
        woodAmt = 0;
    	
    	int currPlayer = facade.getCurrentPlayerIndex();
    	brick = facade.getResourceCount(currPlayer, ResourceType.BRICK);
    	ore = facade.getResourceCount(currPlayer, ResourceType.ORE);
    	sheep = facade.getResourceCount(currPlayer, ResourceType.SHEEP);
    	wheat = facade.getResourceCount(currPlayer, ResourceType.WHEAT);
    	wood = facade.getResourceCount(currPlayer, ResourceType.WOOD);
    	
    	if (currState.equals("Playing")) {
    		System.out.println("currstate equals playing");
    		
    		setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
    		setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
    		setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
    		setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
    		setChangeEnabled(oreAmt, ore, ResourceType.ORE);
    	    tradeOverlay.setResourceSelectionEnabled(true);
    		
    		getTradeOverlay().showModal();

    		//tradeOverlay.setTradeEnabled(true);
    		//send proxy call
    		//sendTradeOffer();
    		
    	}
    	//state.startTrade();
    }

    @Override
    public void decreaseResourceAmount(ResourceType resource) {
    	System.out.println("I am decreasing");

    	switch (resource) {
		case BRICK:
			brickAmt--;
			setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
			break;
		case ORE:
			oreAmt--;
			setChangeEnabled(oreAmt, ore, ResourceType.ORE);
			break;
		case SHEEP:
			sheepAmt--;
			setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
			break;
		case WHEAT:
			wheatAmt--;
			setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
			break;
		case WOOD:
			woodAmt--;
			setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
			break;
		default:
			break;
    	}
    }
    
    @Override
    public void increaseResourceAmount(ResourceType resource) {
    	System.out.println("I am increasing");

    	switch (resource) {
		case BRICK:
			brickAmt++;
			setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
			break;
		case ORE:
			oreAmt++;
			setChangeEnabled(oreAmt, ore, ResourceType.ORE);
			break;
		case SHEEP:
			sheepAmt++;
			setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
			break;
		case WHEAT:
			wheatAmt++;
			setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
			break;
		case WOOD:
			woodAmt++;
			setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
			break;
		default:
			break;
    	}
    }

    public void setChangeEnabled(int resource, int currResource, ResourceType resourceType) {
		if (currResource == 0) {
			tradeOverlay.setResourceAmountChangeEnabled(resourceType, false, false);
		} else if (resource == currResource) {
    		tradeOverlay.setResourceAmountChangeEnabled(resourceType, false, true);
    	} else if (resource == 0) {
    		tradeOverlay.setResourceAmountChangeEnabled(resourceType, true, false);
    	} else {
    		tradeOverlay.setResourceAmountChangeEnabled(resourceType, true, true);
    	}
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
    	switch (resource) {
		case BRICK:
			brickAmt++;
			break;
		case ORE:
			oreAmt++;
			break;
		case SHEEP:
			sheepAmt++;
			break;
		case WHEAT:
			wheatAmt++;
			break;
		case WOOD:
			woodAmt++;
			break;
		default:
			break;
    	}
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
