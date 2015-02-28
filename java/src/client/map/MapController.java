package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import shared.models.*;
import client.base.*;
import client.data.*;
import client.model.ModelFacade;
import client.model.Populator;

/**
 * Implementation for the map controller
 */
public class MapController extends Controller 
    implements IMapController, Observer {

    private IRobView robView;
    private MapState state;
    private String currState;

    public MapController(IMapView view, IRobView robView) {

        super(view);
        Populator.getInstance().addObserver(this);
        setRobView(robView);

        initFromModel(null);
    }


	public IMapView getView() {

        return (IMapView) super.getView();
    }

    private IRobView getRobView() {
        return robView;
    }

    private void setRobView(IRobView robView) {
        this.robView = robView;
    }

    protected void initFromModel(ModelFacade facade) {
        if (facade == null || !facade.hasModel()) return;
                
        for (int i = 0; i < facade.NumberOfHexes(); i++) {

            Hex hex = facade.GetHexAt(i);
            if (hex != null) {
        		getView().addHex(hex.getLocation(), hex.getResource());
                if(hex.getNumber() != null)
                	getView().addNumber(hex.getLocation(), hex.getNumber());
            }

        }
        
        //initialize water hexes
        int radius = facade.getMapRadius();
        for(int x = 0; x < radius; x++) {
        	getView().addHex(new HexLocation(-x, radius), HexType.WATER);
        	getView().addHex(new HexLocation(-radius, radius - x), HexType.WATER);
        	getView().addHex(new HexLocation(x - radius, - x), HexType.WATER);
        	getView().addHex(new HexLocation(x, -radius), HexType.WATER);
        	getView().addHex(new HexLocation(radius, x - radius), HexType.WATER);
        	getView().addHex(new HexLocation(radius - x, x), HexType.WATER);
        }
        
        // need to draw water tiles
        
        for (int i = 0; i < facade.NumberOfRoads(); i++) {
        	Road road = facade.GetRoadAt(i);
        	if (road !=null)
        		getView().placeRoad(road.getLocation(), facade.GetPlayerColor(road.getOwner()));
        }
        
        for (int i = 0; i < facade.NumberOfCities(); i++) {
        	VertexObject city = facade.GetCityAt(i); 
        	if (city !=null)       	
        		getView().placeCity(city.getLocation(), facade.GetPlayerColor(city.getOwner()));
        }
        
        for (int i = 0; i < facade.NumberOfSettlements(); i++) {
        	VertexObject settlement = facade.GetSettlementAt(i);
        	if (settlement !=null)
        		getView().placeSettlement(settlement.getLocation(), facade.GetPlayerColor(settlement.getOwner()));
        }
        
        
        for (int i = 0; i < facade.NumberOfHarbors(); i++) {
        	Harbor port = facade.GetHarborAt(i); 
        	if (port !=null) {
        		getView().addPort(port.getLocation(), port.getResource());
        	}
        }
        
        Robber robber = facade.GetRobber();
        if (robber != null)
        	getView().placeRobber(robber.getLocation());
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc) {

        return state.canPlaceRoad(edgeLoc);
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc) {

        return state.canPlaceSettlement(vertLoc);
    }

    public boolean canPlaceCity(VertexLocation vertLoc) {

        return state.canPlaceCity(vertLoc);
    }

    public boolean canPlaceRobber(HexLocation hexLoc) {

        return state.canPlaceRobber(hexLoc);
    }

    public void placeRoad(EdgeLocation edgeLoc) {

        getView().placeRoad(edgeLoc, CatanColor.ORANGE);
    }

    public void placeSettlement(VertexLocation vertLoc) {

        getView().placeSettlement(vertLoc, CatanColor.ORANGE);
    }

    public void placeCity(VertexLocation vertLoc) {

        getView().placeCity(vertLoc, CatanColor.ORANGE);
    }

    public void placeRobber(HexLocation hexLoc) {

        getView().placeRobber(hexLoc);

        getRobView().showModal();
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

        getView().startDrop(pieceType, CatanColor.ORANGE, true);
    }

    public void cancelMove() {

    }

    public void playSoldierCard() {

    }

    public void playRoadBuildingCard() {

    }

    public void robPlayer(RobPlayerInfo victim) {

    }
    
    public void updateState(String currState) {
    	switch (currState) {
    		case "FirstRound":
    			state = new MapState.Setup();
    			break;
    		case "SecondRound":
    			state = new MapState.Setup();
    			break;
    		case "Rolling":
    			state = new MapState.Rolling();
    			break;
    		case "Robbing":
    			state = new MapState.MoveRobber();
    			break;
    		case "Playing":
    			state = new MapState.Playing();
    			break;
    		case "Discarding":
    			state = new MapState.Discarding();
    			break;
			default:
				break;
    		
    	}
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            ModelFacade facade = (ModelFacade) arg;
            initFromModel(facade);
    		currState = facade.getState();
    		updateState(currState);
        }
        
    }

}
