package client.map;

import java.io.IOException;
import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import shared.models.*;
import shared.models.DTO.params.Monopoly;
import shared.models.DTO.params.RoadBuilding;
import client.base.*;
import client.data.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

/**
 * Implementation for the map controller
 */
public class MapController extends Controller 
    implements IMapController, Observer {

    private IRobView robView;
    private MapState state;
    private String currState;
    private ModelFacade facade;
	private boolean roadBuildingTurn1 = false;
	private EdgeLocation edge1;
	private boolean roadBuildingTurn2 = false;
	private EdgeLocation edge2;

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
        	{
        			getView().placeRoad(road.getLocation(), facade.GetPlayerColor(road.getOwner()));
        	}
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

		state.placeRoad(edgeLoc);
		if (this.roadBuildingTurn1) {
    		this.edge1 = edgeLoc;
    	} else if (this.roadBuildingTurn2) {
    		this.edge2 = edgeLoc;
    	}

    }

    public void placeSettlement(VertexLocation vertLoc) {
    	state.placeSettlement(vertLoc);
    }

    public void placeCity(VertexLocation vertLoc) {
    	state.placeCity(vertLoc);
    }

    public void placeRobber(HexLocation hexLoc) {

    	getView().placeRobber(hexLoc);
    	state.setNewRobberLocation(hexLoc);
        getRobView().setPlayers(facade.getVictims(hexLoc, facade.getLocalPlayerIndex()));
        getRobView().showModal();
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

        getView().startDrop(pieceType, facade.GetPlayerColor(facade.getLocalPlayerIndex()), true);
    }

    public void cancelMove() {
    	getView().renewRoads();
        initFromModel(facade);
    }

    public void playSoldierCard() {
    	updateState("PlayingSoldierCard");
    }

    public void playRoadBuildingCard() {
    	
    	this.updateState("PlayingBuildRoadCard");
    }

    public void robPlayer(RobPlayerInfo victim) {
    	state.robPlayer(victim);
    }
    
    public void updateState(String currState) {
    	
    	boolean isPlayersturn = false;
    			try {
    				isPlayersturn = facade.isCurrentTurn(facade.getLocalPlayerIndex());
    			}
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    	if (!isPlayersturn)
    	{
			state = new MapState.Waiting();
			return;
    	}
    	
    	switch (currState) {
    		case "FirstRound":
    			state = new MapState.Setup1();
    			break;
    		case "SecondRound":
    			state = new MapState.Setup2();
    			break;
    		case "Rolling":
    		case "Discarding":	
    			state = new MapState.Waiting();
    			break;
    		case "Robbing":
    			state = new MapState.MoveRobber();
    			break;
    		case "Playing":
    			state = new MapState.Playing();
    			break;
    		case "PlayingBuildRoadCard":
    			state = new MapState.PlayingBuildRoadCard();
    			break;
    		case "PlayingSoldierCard":
    			state = new MapState.PlayingSoldierCard();
    			break;
			default:
				break;				
    	}
		state.showMapOverlay(getView());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            ModelFacade facade = (ModelFacade) arg;
            this.facade = facade;
            initFromModel(facade);
    		currState = facade.getState();
    		updateState(currState);
        }
        
    }

}
