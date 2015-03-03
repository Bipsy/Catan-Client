package client.domestic;

import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.TradeOffer;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.OfferTrade;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.io.IOException;
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
	
	private int currPlayer;
	private int playerTradingWith;
	private ResourceListDTO tradingResources = new ResourceListDTO();
	private boolean sendResources = false;
	private boolean recieveResources = false;
	private boolean playerTradingBool = false;
	private ResourceType receiveResource;
    
    private String currState;
    private ModelFacade facade;
	ServerProxy proxy = ServerProxy.getInstance();

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

    	currPlayer = facade.getCurrentPlayerIndex();
    	List<PlayerInfo> tempList = new ArrayList<PlayerInfo>();
    	for (int i=0; i<4; i++) {
    		try {
    			if (i != currPlayer) {
    				tempList.add(new PlayerInfo(facade.getPlayer(i).getID(), i, facade.getPlayer(i).getUsername(), facade.GetPlayerColor(i)));
    			}
			} catch (InvalidPlayerIndex e) {
				e.printStackTrace();
			}
    	}
    	listOfPlayers = new PlayerInfo[3];
    	for (int i=0; i<3; i++) {
    		listOfPlayers[i] = tempList.get(i);
    	}
    	tradeOverlay.setPlayers(listOfPlayers);
    	
    	
        brickAmt = 0;
        oreAmt = 0;
        sheepAmt = 0;
        wheatAmt = 0;
        woodAmt = 0;
    	brick = facade.getResourceCount(currPlayer, ResourceType.BRICK);
    	ore = facade.getResourceCount(currPlayer, ResourceType.ORE);
    	sheep = facade.getResourceCount(currPlayer, ResourceType.SHEEP);
    	wheat = facade.getResourceCount(currPlayer, ResourceType.WHEAT);
    	wood = facade.getResourceCount(currPlayer, ResourceType.WOOD);
    	tradingResources.setBrick(0);
    	tradingResources.setWood(0);
    	tradingResources.setSheep(0);
    	tradingResources.setWheat(0);
    	tradingResources.setOre(0);
    	
    	if (currState.equals("Playing")) {
    		System.out.println("currState is Playing");
    		setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
    		setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
    		setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
    		setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
    		setChangeEnabled(oreAmt, ore, ResourceType.ORE);
    		
    		getTradeOverlay().showModal();
    	}
    	//state.startTrade();
    }

    public void proxyCall() {
    	OfferTrade tradeOffer = new OfferTrade(currPlayer, tradingResources, playerTradingWith);
		try {
			proxy.offerTrade(tradeOffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void decreaseResourceAmount(ResourceType resource) {
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
		int maxResources = currResource;
    	if (resourceType == receiveResource)
    		maxResources = 25;
		if (maxResources == 0) {
			tradeOverlay.setResourceAmountChangeEnabled(resourceType, false, false);
		} else if (resource == maxResources) {
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
        tradeOverlay.reset();
//		getWaitOverlay().showModal();
    }

    @Override
    public void setPlayerToTradeWith(int playerIndex) {
    	playerTradingWith = playerIndex;
    	playerTradingBool = true;
    	if (playerTradingBool && sendResources && recieveResources) {
    		tradeOverlay.setTradeEnabled(true);
    		proxyCall();
    	}
    }

    @Override
    public void setResourceToReceive(ResourceType resource) {
    	receiveResource = resource;
    	switch (resource) {
		case BRICK:
			tradingResources.setBrick(brickAmt);
			break;
		case ORE:
			tradingResources.setOre(oreAmt);
			break;
		case SHEEP:
			tradingResources.setSheep(sheepAmt);
			break;
		case WHEAT:
			tradingResources.setWheat(wheatAmt);
			break;
		case WOOD:
			tradingResources.setWood(woodAmt);
			break;
		default:
			break;
    	}
    	recieveResources = true;
    	if (playerTradingBool && sendResources && recieveResources) {
    		tradeOverlay.setTradeEnabled(true);
    		proxyCall();
    	}
    }

    @Override
    public void setResourceToSend(ResourceType resource) {
    	switch (resource) {
		case BRICK:
			tradingResources.setBrick(-brickAmt);
			break;
		case ORE:
			tradingResources.setOre(-oreAmt);
			break;
		case SHEEP:
			tradingResources.setSheep(-sheepAmt);
			break;
		case WHEAT:
			tradingResources.setWheat(-wheatAmt);
			break;
		case WOOD:
			tradingResources.setWood(-woodAmt);
			break;
		default:
			break;
    	}
    	sendResources = true;
    	if (playerTradingBool && sendResources && recieveResources) {
    		tradeOverlay.setTradeEnabled(true);
    		proxyCall();
    	}
    }

    @Override
    public void unsetResource(ResourceType resource) {
    	switch (resource) {
		case BRICK:
			if (tradingResources.getBrick() > 0)
				recieveResources = false;
			else 
				sendResources = false;
			tradingResources.setBrick(0);
			break;
		case ORE:
			if (tradingResources.getBrick() > 0)
				recieveResources = false;
			else 
				sendResources = false;
			tradingResources.setOre(0);
			break;
		case SHEEP:
			if (tradingResources.getBrick() > 0)
				recieveResources = false;
			else 
				sendResources = false;
			tradingResources.setSheep(0);
			break;
		case WHEAT:
			if (tradingResources.getBrick() > 0)
				recieveResources = false;
			else 
				sendResources = false;
			tradingResources.setWheat(0);
			break;
		case WOOD:
			if (tradingResources.getBrick() > 0)
				recieveResources = false;
			else 
				sendResources = false;
			tradingResources.setWood(0);
			break;
		default:
			break;
    	}
    }

    @Override
    public void cancelTrade() {
    	tradeOverlay.reset();
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
