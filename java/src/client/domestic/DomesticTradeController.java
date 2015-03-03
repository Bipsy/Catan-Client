package client.domestic;

import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.ResourceList;
import shared.models.TradeOffer;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.AcceptTrade;
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
	private ResourceType sendResource;
    
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
    		getTradeOverlay().showModal();
    	} else {
    		
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
    	if (resourceType == receiveResource) {
    		maxResources = 25;
    	}
		if (maxResources == 0) {
			if (resourceType == receiveResource) {
				tradeOverlay.setResourceAmountChangeEnabled(resourceType, true, false);
			} else {
				tradeOverlay.setResourceAmountChangeEnabled(resourceType, false, false);
			}
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
    	proxyCall();
        getTradeOverlay().closeModal();
        tradeOverlay.reset();
		getWaitOverlay().showModal();
    }

    @Override
    public void setPlayerToTradeWith(int playerIndex) {
    	playerTradingWith = playerIndex;
    	playerTradingBool = true;
    	if (playerTradingBool && sendResources && recieveResources) {
    		tradeOverlay.setTradeEnabled(true);
    	}
    }

    @Override
    public void setResourceToReceive(ResourceType resource) {
    	receiveResource = resource;
		setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
		setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
		setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
		setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
		setChangeEnabled(oreAmt, ore, ResourceType.ORE);
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
    	}
    }

    @Override
    public void setResourceToSend(ResourceType resource) {
    	receiveResource = null;
    	sendResource = resource;
		setChangeEnabled(woodAmt, wood, ResourceType.WOOD);
		setChangeEnabled(brickAmt, brick, ResourceType.BRICK);
		setChangeEnabled(sheepAmt, sheep, ResourceType.SHEEP);
		setChangeEnabled(wheatAmt, wheat, ResourceType.WHEAT);
		setChangeEnabled(oreAmt, ore, ResourceType.ORE);
		
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

    	currPlayer = facade.getCurrentPlayerIndex();
    	AcceptTrade accept = new AcceptTrade(currPlayer, willAccept);
    	try {
			proxy.acceptTrade(accept);
		} catch (IOException e) {
			e.printStackTrace();
		}
        getAcceptOverlay().closeModal();
    }
    
    public void accept() {
    	ResourceList rlistResource = new ResourceList();
    	ResourceType tempRType = null ;
    	int tempAmt = 0;
    	ResourceType tempRTypeR = null ;
    	int tempAmtR = 0;
    	rlistResource = facade.getTradeOffer().getResources();
    	if (rlistResource.getBrick() < 0) {
    		tempRType = ResourceType.BRICK;
    		tempAmt = rlistResource.getBrick();
    		System.out.println("111: "+tempRType);
    		System.out.println("2222: "+tempAmt);
    	} else if (rlistResource.getOre() < 0) {
    		tempRType = ResourceType.ORE;
    		tempAmt = rlistResource.getOre();
    		System.out.println("111: "+tempRType);
    		System.out.println("2222: "+tempAmt);
    	} else if (rlistResource.getSheep() < 0) {
    		tempRType = ResourceType.SHEEP;
    		tempAmt = rlistResource.getSheep();
    		System.out.println("111: "+tempRType);
    		System.out.println("2222: "+tempAmt);
    	} else if (rlistResource.getWheat() < 0) {
    		tempRType = ResourceType.WHEAT;
    		tempAmt = rlistResource.getWheat();
    		System.out.println("111: "+tempRType);
    		System.out.println("2222: "+tempAmt);
    	} else if (rlistResource.getWood() < 0) {
    		tempRType = ResourceType.WOOD;
    		tempAmt = rlistResource.getWood();
    		System.out.println("111: "+tempRType);
    		System.out.println("2222: "+tempAmt);
    	}
    	if (rlistResource.getBrick() > 0) {
    		tempRTypeR = ResourceType.BRICK;
    		tempAmtR = rlistResource.getBrick();
    	} else if (rlistResource.getOre() > 0) {
    		tempRTypeR = ResourceType.ORE;
    		tempAmtR = rlistResource.getOre();
    	} else if (rlistResource.getSheep() > 0) {
    		tempRTypeR = ResourceType.SHEEP;
    		tempAmtR = rlistResource.getSheep();
    	} else if (rlistResource.getWheat() > 0) {
    		tempRTypeR = ResourceType.WHEAT;
    		tempAmtR = rlistResource.getWheat();
    	} else if (rlistResource.getWood() > 0) {
    		tempRTypeR = ResourceType.WOOD;
    		tempAmtR = rlistResource.getWood();
    	}

//    	acceptOverlay.addGetResource(tempRType, tempAmt);
//    	acceptOverlay.addGiveResource(tempRTypeR, tempAmtR);
		getAcceptOverlay().showModal();

    	currPlayer = facade.getCurrentPlayerIndex();
    	brick = facade.getResourceCount(currPlayer, ResourceType.BRICK);
    	ore = facade.getResourceCount(currPlayer, ResourceType.ORE);
    	sheep = facade.getResourceCount(currPlayer, ResourceType.SHEEP);
    	wheat = facade.getResourceCount(currPlayer, ResourceType.WHEAT);
    	wood = facade.getResourceCount(currPlayer, ResourceType.WOOD);
    	
		
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
//            if (facade.getTradeOffer() != null && !currState.equals("Playing")) {
//            	//accept();
//            }	
    		currState = facade.getState();
//    		if (!currState.equals("Playing")) {
//    			if (facade.getTradeOffer() != null) {
//    				accept();
//    			}
//    		}
    		//System.out.println(currState);
    		updateState(currState);
        }
    }

}
