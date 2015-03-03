package client.map;

import java.io.IOException;

import client.network.ServerProxy;
import client.network.UserCookie;
import shared.definitions.PieceType;
import shared.exceptions.NoCookieException;
import shared.locations.*;
import client.model.ModelFacade;
import client.model.Populator;
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
	
	void placeRoad(EdgeLocation edgeLoc) {}
	
	void placeSettlement(VertexLocation vertLoc) {}
	
	void placeCity(VertexLocation vertLoc) {}
	
	void robPlayer(RobPlayerInfo victim) {}
	void showMapOverlay(IMapView view) {}
	

	public static class Setup1 extends MapState {
		

		void showMapOverlay(IMapView view) {
			try {
				if ((facade.getObjectCount(facade.getLocalPlayerIndex(), "Road")) == 0)
					view.startDrop(PieceType.ROAD,facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
				else if (facade.getObjectCount(facade.getLocalPlayerIndex(), "Road") == 1 &&
							facade.getObjectCount(facade.getLocalPlayerIndex(), "Settlement") == 0) {
					view.startDrop(PieceType.SETTLEMENT,facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
				}
			}
			catch(Exception e) {
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
			try {
				Populator.getInstance().populateModel(proxy.buildSettlement(settlementMove), proxy.getLocalPlayerName());
			} catch (IOException | NoCookieException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static class PlayingBuildRoadCard extends MapState {
//		IMapView view = new IMapView();
		boolean round1 = true;
		boolean canPlaceRoad(EdgeLocation edgeLoc) {
			System.out.println("marbles everywhere!");
			return true;
		}
		void placeRoad(EdgeLocation edgeLoc) {
			
			BuildRoad roadMove = new BuildRoad(facade.getLocalPlayerIndex(), new RoadLocation(edgeLoc), false);
			if (round1) {
				try {
					Populator.getInstance().populateModel(proxy.buildRoad(roadMove), proxy.getLocalPlayerName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoCookieException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	}
	
public static class Setup2 extends Setup1 {
		
		void showMapOverlay(IMapView view) {
			try {
				if ((facade.getObjectCount(facade.getLocalPlayerIndex(), "Road")) == 1)
					view.startDrop(PieceType.ROAD,facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
				else if (facade.getObjectCount(facade.getLocalPlayerIndex(), "Road") == 2 &&
							facade.getObjectCount(facade.getLocalPlayerIndex(), "Settlement") == 1) {
					view.startDrop(PieceType.SETTLEMENT,facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);
				}
			}
			catch(Exception e) {
				e.printStackTrace(System.out);
			}				
		}
	}
	
	public static class Rolling extends MapState {
		
		
	}
	
	public static class MoveRobber extends MapState {
		
		void showMapOverlay(IMapView view) {			
			view.startDrop(PieceType.ROBBER,facade.GetPlayerColor(facade.getLocalPlayerIndex()), false);				
		}
		
		boolean canPlaceRobber(HexLocation hexLoc) {
			return facade.CanPlaceRobber(hexLoc);
		}
		
		void robPlayer(RobPlayerInfo victim) {
			RobPlayer robPlayer = new RobPlayer(facade.getLocalPlayerIndex(), victim.getPlayerIndex(), null);
			try {
				Populator.getInstance().populateModel(proxy.robPlayer(robPlayer), proxy.getLocalPlayerName());
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
	
	public static class Discarding extends MapState {
	
		
	}

}
