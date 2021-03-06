package client.map;

import java.io.IOException;

import client.network.ServerProxy;
import client.network.UserCookie;
import shared.definitions.PieceType;
import shared.exceptions.NoCookieException;
import shared.locations.*;
import client.model.ModelFacade;
import client.model.Populator;
import shared.models.Road;
import shared.models.DTO.params.*;
import client.data.*;

public abstract class MapState {

    ServerProxy proxy = ServerProxy.getInstance();
    UserCookie uCookie = proxy.getUserCookie();
    ModelFacade facade = new ModelFacade();

    //default methods
    boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return false;
    }

    boolean canPlaceSettlement(VertexLocation vertLoc) {
        return false;
    }

    boolean canPlaceCity(VertexLocation vertLoc) {
        return false;
    }

    boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }

    void placeRoad(EdgeLocation edgeLoc) {
    }

    void placeSettlement(VertexLocation vertLoc) {
    }

    void placeCity(VertexLocation vertLoc) {
    }

    void robPlayer(RobPlayerInfo victim) {
    }

    void showMapOverlay(IMapView view) {
    }

    void setNewRobberLocation(HexLocation newRobberLocation) {
    }

    public static class Setup1 extends MapState {

        void showMapOverlay(IMapView view) {
            try {
                if ((facade.getObjectCount(facade.getLocalPlayerIndex(), "Road")) == 0) {
                    view.startDrop(PieceType.ROAD, facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
                } else if (facade.getObjectCount(facade.getLocalPlayerIndex(), "Road") == 1
                        && facade.getObjectCount(facade.getLocalPlayerIndex(), "Settlement") == 0) {
                    view.startDrop(PieceType.SETTLEMENT, facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        @Override
        boolean canPlaceRoad(EdgeLocation edgeLoc) {
            return facade.CanBuildRoad(new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), true));
        }

        @Override
        boolean canPlaceSettlement(VertexLocation vertLoc) {
            return facade.CanBuildSettlement(new BuildSettlement(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc), true));
        }

        void placeRoad(EdgeLocation edgeLoc) {
            BuildRoad roadMove = new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), true);
            try {
                Populator.getInstance().populateModel(proxy.buildRoad(roadMove), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }

        void placeSettlement(VertexLocation vertLoc) {
            BuildSettlement settlementMove = new BuildSettlement(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc), true);
            FinishTurn turn = new FinishTurn(facade.getCurrentPlayerIndex());
            try {
                Populator.getInstance().populateModel(proxy.buildSettlement(settlementMove), proxy.getLocalPlayerName());
                Populator.getInstance().populateModel(proxy.finishTurn(turn), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PlayingBuildRoadCard extends MapState {


    	private RoadLocation edge1 = null;
    	private IMapView view;
    	
        boolean canPlaceRoad(EdgeLocation edgeLoc) {
        	Road tempRoad = null;
        	if(edge1 != null) {
        		tempRoad = new Road();
        		tempRoad.setLocation(edge1.convertToEdgeLocation());
        		tempRoad.setOwner(facade.getLocalPlayerIndex());
        		facade.setTempRoad(tempRoad);
        	}
        	boolean result = facade.CanBuildRoad(new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), true));
        	if(edge1 != null) {
        		facade.removeTempRoad(tempRoad);
        	}
        	return result;
        	
        }
        
        void showMapOverlay(IMapView view) {     
        	this.view = view;
            view.startDrop(PieceType.ROAD, facade.GetPlayerColor(facade.getLocalPlayerIndex()), true);
        }

        void placeRoad(EdgeLocation edgeLoc) {

        	if (edge1 == null) {
        		edge1 = new RoadLocation(edgeLoc);
        		this.view.placeRoad(edgeLoc, facade.GetPlayerColor(facade.getLocalPlayerIndex()));
        		showMapOverlay(this.view);
        	}
        	else {
	        	RoadBuilding roadBuildingMove = new RoadBuilding(facade.getLocalPlayerIndex(), this.edge1, new RoadLocation(edgeLoc));
	        	
	        	try {
	        		Populator.getInstance().populateModel(proxy.playRoadBuilding(roadBuildingMove), proxy.getLocalPlayerName());
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		} 
        	}
        }
    }

    public static class Setup2 extends Setup1 {

        void showMapOverlay(IMapView view) {
            try {
                if ((facade.getObjectCount(facade.getLocalPlayerIndex(), "Road")) == 1) {
                    view.startDrop(PieceType.ROAD, facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
                } else if (facade.getObjectCount(facade.getLocalPlayerIndex(), "Road") == 2
                        && facade.getObjectCount(facade.getLocalPlayerIndex(), "Settlement") == 1) {
                    view.startDrop(PieceType.SETTLEMENT, facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static class Waiting extends MapState {

    }
    
    public static class MoveRobber extends MapState {

        private HexLocation newRobberLocation;

        public void setNewRobberLocation(HexLocation newRobberLocation) {
            this.newRobberLocation = newRobberLocation;
        }

        void showMapOverlay(IMapView view) {
            view.startDrop(PieceType.ROBBER, facade.GetPlayerColor(
                    facade.getLocalPlayerIndex()), false);
        }

        boolean canPlaceRobber(HexLocation hexLoc) {
            return facade.CanPlaceRobber(hexLoc);
        }

        void robPlayer(RobPlayerInfo victim) {
            RobPlayer robPlayer = new RobPlayer(facade.getLocalPlayerIndex(), 
                    victim.getPlayerIndex(), newRobberLocation);
            try {
                Populator.getInstance().populateModel(
                        proxy.robPlayer(robPlayer), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }

    }

    public static class Playing extends MapState {

        boolean canPlaceRoad(EdgeLocation edgeLoc) {
            BuildRoad road = new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), false);
            return facade.CanBuildRoad(road);
        }

        boolean canPlaceSettlement(VertexLocation vertLoc) {
            BuildSettlement settlement = new BuildSettlement(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc), false);
            return facade.CanBuildSettlement(settlement);
        }

        boolean canPlaceCity(VertexLocation vertLoc) {
            BuildCity city = new BuildCity(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc));
            return facade.CanBuildCity(city);
        }

        void placeRoad(EdgeLocation edgeLoc) {
            BuildRoad roadMove = new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), false);
            try {
                Populator.getInstance().populateModel(proxy.buildRoad(roadMove), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }

        void placeSettlement(VertexLocation vertLoc) {
            BuildSettlement settlementMove = new BuildSettlement(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc), false);
            try {
                Populator.getInstance().populateModel(proxy.buildSettlement(settlementMove), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }

        void placeCity(VertexLocation vertLoc) {
            BuildCity cityMove = new BuildCity(facade.getLocalPlayerIndex(), new VertexLocationDTO(vertLoc));
            try {
                Populator.getInstance().populateModel(proxy.buildCity(cityMove), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }
    }


    public static class PlayingSoldierCard extends MoveRobber {

        private HexLocation newRobberLocation;

        public void setNewRobberLocation(HexLocation newRobberLocation) {
            this.newRobberLocation = newRobberLocation;
        }

        void robPlayer(RobPlayerInfo victim) {
            Soldier soldier = new Soldier(facade.getLocalPlayerIndex(), victim.getPlayerIndex(), newRobberLocation);
            try {
                Populator.getInstance().populateModel(proxy.playSoldier(soldier), proxy.getLocalPlayerName());
            } catch (IOException | NoCookieException e) {
                e.printStackTrace();
            }
        }
    }

}
