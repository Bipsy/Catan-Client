package client.domestic;

import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.ResourceList;
import shared.exceptions.NoCookieException;
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
    private IWaitView waitOverlay;
    private IAcceptTradeOverlay acceptOverlay;
    private PlayerInfo[] listOfPlayers;

    private int brickAmt;
    private int oreAmt;
    private int sheepAmt;
    private int wheatAmt;
    private int woodAmt;
    private int myBrick;
    private int myOre;
    private int mySheep;
    private int myWheat;
    private int myWood;

    private int currPlayer;
    private int playerTradingWith;
    private ResourceListDTO tradingResources = new ResourceListDTO();
    private boolean sendResources = false;
    private boolean recieveResources = false;
    private boolean playerTradingBool = false;
    private boolean sentTrade = false;
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
        for (int i = 0; i < 4; i++) {
            try {
                if (i != currPlayer) {
                    tempList.add(new PlayerInfo(facade.getPlayer(i).getID(), i, facade.getPlayer(i).getUsername(), facade.GetPlayerColor(i)));
                }
            } catch (InvalidPlayerIndex e) {
                e.printStackTrace();
            }
        }
        listOfPlayers = new PlayerInfo[3];
        for (int i = 0; i < 3; i++) {
            listOfPlayers[i] = tempList.get(i);
        }
        tradeOverlay.setPlayers(listOfPlayers);

        brickAmt = 0;
        oreAmt = 0;
        sheepAmt = 0;
        wheatAmt = 0;
        woodAmt = 0;
        myBrick = facade.getResourceCount(currPlayer, ResourceType.BRICK);
        myOre = facade.getResourceCount(currPlayer, ResourceType.ORE);
        mySheep = facade.getResourceCount(currPlayer, ResourceType.SHEEP);
        myWheat = facade.getResourceCount(currPlayer, ResourceType.WHEAT);
        myWood = facade.getResourceCount(currPlayer, ResourceType.WOOD);
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
    	setTradeResources();
        OfferTrade tradeOffer = new OfferTrade(currPlayer, tradingResources, playerTradingWith);
        try {
            proxy.offerTrade(tradeOffer);
            sentTrade = true;
        	System.out.println("sendTrade: proxyCall " + sentTrade);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setTradeResources() {
    	switch (receiveResource) {
        case BRICK:
        	brickAmt = -brickAmt;
            break;
        case ORE:
        	oreAmt = -oreAmt;
            break;
        case SHEEP:
            sheepAmt = -sheepAmt;
            break;
        case WHEAT:
            wheatAmt = -wheatAmt;
            break;
        case WOOD:
            woodAmt = -woodAmt;
            break;
        default:
            break;
    	}
    	tradingResources.setBrick(brickAmt);
    	tradingResources.setOre(oreAmt);
    	tradingResources.setSheep(sheepAmt);
    	tradingResources.setWheat(wheatAmt);
    	tradingResources.setWood(woodAmt);
    }

    @Override
    public void decreaseResourceAmount(ResourceType resource) {
        switch (resource) {
            case BRICK:
                brickAmt--;
                setChangeEnabled(brickAmt, myBrick, ResourceType.BRICK);
                break;
            case ORE:
                oreAmt--;
                setChangeEnabled(oreAmt, myOre, ResourceType.ORE);
                break;
            case SHEEP:
                sheepAmt--;
                setChangeEnabled(sheepAmt, mySheep, ResourceType.SHEEP);
                break;
            case WHEAT:
                wheatAmt--;
                setChangeEnabled(wheatAmt, myWheat, ResourceType.WHEAT);
                break;
            case WOOD:
                woodAmt--;
                setChangeEnabled(woodAmt, myWood, ResourceType.WOOD);
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
                setChangeEnabled(brickAmt, myBrick, ResourceType.BRICK);
                break;
            case ORE:
                oreAmt++;
                setChangeEnabled(oreAmt, myOre, ResourceType.ORE);
                break;
            case SHEEP:
                sheepAmt++;
                setChangeEnabled(sheepAmt, mySheep, ResourceType.SHEEP);
                break;
            case WHEAT:
                wheatAmt++;
                setChangeEnabled(wheatAmt, myWheat, ResourceType.WHEAT);
                break;
            case WOOD:
                woodAmt++;
                setChangeEnabled(woodAmt, myWood, ResourceType.WOOD);
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
        if (sendResource == resource) 
        	sendResource = null;
        
        switch (resource) {
            case BRICK:
            	brickAmt = 0;
            	resetAmounts(ResourceType.BRICK, myBrick);
                break;
            case ORE:
            	oreAmt = 0;
            	resetAmounts(ResourceType.ORE, myOre);
                break;
            case SHEEP:
            	sheepAmt = 0;
            	resetAmounts(ResourceType.SHEEP, mySheep);
                break;
            case WHEAT:
            	wheatAmt = 0;
            	resetAmounts(ResourceType.WHEAT, myWheat);
                break;
            case WOOD:
            	woodAmt = 0;
            	resetAmounts(ResourceType.WOOD, myWood);
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
        sendResource = resource;
        if (receiveResource == resource) 
        	receiveResource = null;

        switch (resource) {
            case BRICK:
            	brickAmt = 0;
            	resetAmounts(ResourceType.BRICK, myBrick);
                break;
            case ORE:
            	oreAmt = 0;
            	resetAmounts(ResourceType.ORE, myOre);
                break;
            case SHEEP:
            	sheepAmt = 0;
            	resetAmounts(ResourceType.SHEEP, mySheep);
                break;
            case WHEAT:
            	wheatAmt = 0;
            	resetAmounts(ResourceType.WHEAT, myWheat);
                break;
            case WOOD:
            	woodAmt = 0;
            	resetAmounts(ResourceType.WOOD, myWood);
                break;
            default:
                break;
        }
        sendResources = true;
        if (playerTradingBool && sendResources && recieveResources) {
            tradeOverlay.setTradeEnabled(true);
        }
    }
    
    public void resetAmounts(ResourceType resourceType, int myResource) {
    	tradeOverlay.setResourceAmount(resourceType, "0");
    	if (myResource == 0 && resourceType != receiveResource)
    		tradeOverlay.setResourceAmountChangeEnabled(resourceType, false, false);
    	else
    		tradeOverlay.setResourceAmountChangeEnabled(resourceType, true, false);
    }
    
    @Override
    public void unsetResource(ResourceType resource) {
        switch (resource) {
            case BRICK:
                if (tradingResources.getBrick() > 0) {
                    recieveResources = false;
                } else {
                    sendResources = false;
                }
                tradingResources.setBrick(0);
                break;
            case ORE:
                if (tradingResources.getBrick() > 0) {
                    recieveResources = false;
                } else {
                    sendResources = false;
                }
                tradingResources.setOre(0);
                break;
            case SHEEP:
                if (tradingResources.getBrick() > 0) {
                    recieveResources = false;
                } else {
                    sendResources = false;
                }
                tradingResources.setSheep(0);
                break;
            case WHEAT:
                if (tradingResources.getBrick() > 0) {
                    recieveResources = false;
                } else {
                    sendResources = false;
                }
                tradingResources.setWheat(0);
                break;
            case WOOD:
                if (tradingResources.getBrick() > 0) {
                    recieveResources = false;
                } else {
                    sendResources = false;
                }
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
        int localPlayer = facade.getLocalPlayerIndex();
        AcceptTrade accept = new AcceptTrade(localPlayer, willAccept);
        try {
        	try {
				Populator.getInstance().populateModel(proxy.acceptTrade(accept), proxy.getLocalPlayerName());
			} catch (NoCookieException e) {
				e.printStackTrace();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptOverlay.closeModal();
        acceptOverlay.reset();
        getWaitOverlay().closeModal();
    }

    public void accept(TradeOffer trade) {
    	
    	acceptOverlay.setPlayerName(facade.getCurrentPlayerName(trade.getSender()));
    	acceptOverlay.setAcceptEnabled(true);

        currPlayer = facade.getCurrentPlayerIndex();
        int rBrick = facade.getResourceCount(facade.getLocalPlayerIndex(), ResourceType.BRICK);
        int rOre = facade.getResourceCount(facade.getLocalPlayerIndex(), ResourceType.ORE);
        int rSheep = facade.getResourceCount(facade.getLocalPlayerIndex(), ResourceType.SHEEP);
        int rWheat = facade.getResourceCount(facade.getLocalPlayerIndex(), ResourceType.WHEAT);
        int rWood = facade.getResourceCount(facade.getLocalPlayerIndex(), ResourceType.WOOD);
        
    	assignAcceptResources(ResourceType.BRICK, trade.getResources().getBrick(), rBrick);
    	assignAcceptResources(ResourceType.ORE, trade.getResources().getOre(), rOre);
    	assignAcceptResources(ResourceType.SHEEP, trade.getResources().getSheep(), rSheep);
    	assignAcceptResources(ResourceType.WHEAT, trade.getResources().getWheat(), rWheat);
    	assignAcceptResources(ResourceType.WOOD, trade.getResources().getWood(), rWood);

        getAcceptOverlay().showModal();
    }
    
    public void assignAcceptResources(ResourceType type, int amount, int myResources) {
    	if (amount < 0) {
    		acceptOverlay.addGiveResource(type, -amount);
            if (-amount > myResources) {
            	acceptOverlay.setAcceptEnabled(false);
            }
    	} else if (amount > 0) {
    		acceptOverlay.addGetResource(type, amount);
    	} else {
    	}
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            ModelFacade facade = (ModelFacade) arg;
            this.facade = facade;
            if (facade.getTradeOffer() != null) {
            	if (facade.getTradeOffer().getReceiver() == facade.getLocalPlayerIndex()) {
            		accept(facade.getTradeOffer());
            	}
            } else {
            	if (sentTrade) {
                    getWaitOverlay().closeModal();
                    sentTrade = false;
            	}
            }
            if (facade.isLocalPlayerTurn()) {
                getTradeView().enableDomesticTrade(true);
            } else {
                getTradeView().enableDomesticTrade(false);
            }
            currState = facade.getState();
        }
    }

}
