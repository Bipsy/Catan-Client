package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import shared.models.*;
import client.base.*;
import client.data.*;
import client.storage.*;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {

    private IRobView robView;
    private ModelFacade facade;
    private ClientModel model;

    public MapController(IMapView view, IRobView robView) {

        super(view);
        model = Data.getCurrentModel();
        facade = new ModelFacade(model);
        

        setRobView(robView);

        initFromModel();
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

    protected void initFromModel() {
                
        for (Hex hex : model.getBoard().getHexes()) {
        	getView().addHex(hex.getLocation(), hex.getResource()); 
            getView().addNumber(hex.getLocation(), hex.getChit());

        }
        
        for (Road road : model.getBoard().getRoad()) {
        	getView().placeRoad(road.getLocation(), model.getUserManager().getPlayer(road.getOwner()).getColor()); //make sure index is correct
        }
        
        for (VertexObject obj :  model.getBoard().getSettlements()) {
        	if (obj.getType() == PieceType.SETTLEMENT) {
                getView().placeSettlement(obj.getLocation(), model.getUserManager().getPlayer(obj.getOwner()).getColor());
        	}
        	else {
                getView().placeCity(obj.getLocation(), model.getUserManager().getPlayer(obj.getOwner()).getColor());
        	}
        }
        
        for (Harbor port : model.getBoard().getHarbor()) {
            getView().addPort(port.getLocation(), port.getResource()); 
        }
        
        getView().placeRobber(model.getBoard().getRobber().getLocation());

    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc) {

        return true;
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc) {

        return true;
    }

    public boolean canPlaceCity(VertexLocation vertLoc) {

        return true;
    }

    public boolean canPlaceRobber(HexLocation hexLoc) {

        return true;
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

}
