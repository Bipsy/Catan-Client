package client.devcards;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.exceptions.NoCookieException;
import shared.models.DTO.params.BuyDevCard;
import shared.models.DTO.params.Monopoly;
import shared.models.DTO.params.Monument;
import shared.models.DTO.params.RoadBuilding;
import shared.models.DTO.params.YearOfPlenty;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller 
    implements IDevCardController, Observer {

    private IBuyDevCardView buyCardView;
    private IAction soldierAction;
    private IAction roadAction;

    /**
     * DevCardController constructor
     *
     * @param view "Play dev card" view
     * @param buyCardView "Buy dev card" view
     * @param soldierAction Action to be executed when the user plays a soldier
     * card. It calls "mapController.playSoldierCard()".
     * @param roadAction Action to be executed when the user plays a road
     * building card. It calls "mapController.playRoadBuildingCard()".
     */
    public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView,
            IAction soldierAction, IAction roadAction) {

        super(view);
        Populator.getInstance().addObserver(this);
        this.buyCardView = buyCardView;
        this.soldierAction = soldierAction;
        this.roadAction = roadAction;
    }

    public IPlayDevCardView getPlayCardView() {
        return (IPlayDevCardView) super.getView();
    }

    public IBuyDevCardView getBuyCardView() {
        return buyCardView;
    }

    @Override
    public void startBuyCard() {
    	
        getBuyCardView().showModal();
    }

    @Override
    public void cancelBuyCard() {

        getBuyCardView().closeModal();
    }

    @Override
    public void buyCard() {
    	ModelFacade model = new ModelFacade();
    	ServerProxy proxy = ServerProxy.getInstance();
    	int playerIndex = model.getCurrentPlayerIndex();
    	BuyDevCard buyDevCard = new BuyDevCard(playerIndex);
    	
    	try {
			Populator.getInstance().populateModel(proxy.buyDevCard(buyDevCard), proxy.getLocalPlayerName());
			System.out.println("hi");
		} catch (IOException | NoCookieException e) {

			e.printStackTrace();
		} 
        getBuyCardView().closeModal();
    }

    @Override
    public void startPlayCard() {
    	IPlayDevCardView view = getPlayCardView();
    	ModelFacade model = new ModelFacade();
    	int playerIndex = model.getCurrentPlayerIndex();
    	
    	boolean canPlaySoldierCard = model.canPlaySoldier(playerIndex);
    	boolean canUseYearOfPlenty = model.canUseYearOfPlenty(playerIndex);
    	boolean canPlayMonopoly = model.canPlayMonopoly(playerIndex);
    	boolean canUseRoadBuilding = model.canUseRoadBuilding(playerIndex);
    	boolean canPlayMonument = model.canPlayMonument(playerIndex);
    	
    	int soldierCount = model.getSoldierCount(playerIndex);
    	int yopCount = model.getYearOfPlentyCount(playerIndex);
    	int monopolyCount = model.getMonopolyCount(playerIndex);
    	int rodBuildCount = model.getRoadBuildCount(playerIndex);
    	int monumentCount = model.getMonumentCount(playerIndex);
    	
    	view.setCardEnabled(DevCardType.SOLDIER, canPlaySoldierCard);
    	view.setCardEnabled(DevCardType.YEAR_OF_PLENTY, canUseYearOfPlenty);
    	view.setCardEnabled(DevCardType.MONOPOLY, canPlayMonopoly);
    	view.setCardEnabled(DevCardType.ROAD_BUILD, canUseRoadBuilding);
    	view.setCardEnabled(DevCardType.MONUMENT, canPlayMonument);
    	
    	view.setCardAmount(DevCardType.SOLDIER, soldierCount);
    	view.setCardAmount(DevCardType.YEAR_OF_PLENTY, yopCount);
    	view.setCardAmount(DevCardType.MONOPOLY, monopolyCount);
    	view.setCardAmount(DevCardType.ROAD_BUILD, rodBuildCount);
    	view.setCardAmount(DevCardType.MONUMENT, monumentCount);
    	
    	
    	view.showModal();
    }

    @Override
    public void cancelPlayCard() {

        getPlayCardView().closeModal();
    }

    @Override
    public void playMonopolyCard(ResourceType resource) {
    	ModelFacade model = new ModelFacade();
    	ServerProxy proxy = ServerProxy.getInstance();
    	int playerIndex = model.getCurrentPlayerIndex();
    	Monopoly monopoly = new Monopoly(playerIndex, resource);
    	
    	try {
			proxy.playMonopoly(monopoly);
		} catch (IOException e) {

			e.printStackTrace();
		} 
    	getPlayCardView().closeModal();
    }

    @Override
    public void playMonumentCard() {
    	ModelFacade model = new ModelFacade();
    	ServerProxy proxy = ServerProxy.getInstance();
    	int playerIndex = model.getCurrentPlayerIndex();
    	Monument monument = new Monument(playerIndex);
    	
    	try {
			proxy.playMonument(monument);
		} catch (IOException e) {

			e.printStackTrace();
		} 
    	getPlayCardView().closeModal();
    }

    @Override
    public void playRoadBuildCard() {

        roadAction.execute();
    }

    @Override
    public void playSoldierCard() {

        soldierAction.execute();
    }

    @Override
    public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
    	ModelFacade model = new ModelFacade();
    	ServerProxy proxy = ServerProxy.getInstance();
    	int playerIndex = model.getCurrentPlayerIndex();
    	YearOfPlenty yearOfPlentyMove = new YearOfPlenty(playerIndex, resource1, resource2);
    	
    	try {
			proxy.playYearOfPlenty(yearOfPlentyMove);
		} catch (IOException e) {

			e.printStackTrace();
		} 
    	getPlayCardView().closeModal();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
