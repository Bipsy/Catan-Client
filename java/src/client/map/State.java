package client.map;

import java.io.IOException;

import client.network.ServerProxy;
import client.network.UserCookie;
import shared.locations.*;
import shared.models.ModelFacade;
import shared.models.DTO.params.*;

public abstract class State {
	
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
	
	void placeRobber(HexLocation hexLoc) {}
	
	
	
	class Setup extends State {
		//Show Edit Map
		void placeRoad(EdgeLocation edgeLoc) {
			BuildRoad roadMove = new BuildRoad(uCookie.getPlayerID(), edgeLoc, true);
			try {
				proxy.buildRoad(roadMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		void placeSettlement(VertexLocation vertLoc) {
			BuildSettlement settlementMove = new BuildSettlement(uCookie.getPlayerID(), vertLoc, true);
			try {
				proxy.buildSettlement(settlementMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Rolling extends State {
	    //show normal map
		//show rolling modal
	}
	
	class MoveRobber extends State {
		//Show Edit Map
		
		boolean canPlaceRobber(HexLocation hexLoc) {
			//NEED VICTIM INDEX
			RobPlayer robPlayer = new RobPlayer(uCookie.getPlayerID(), 0, hexLoc);
			return facade.CanPlaceRobber(robPlayer);
		}
		
		void placeRobber(HexLocation hexLoc) {
			RobPlayer robPlayer = new RobPlayer(uCookie.getPlayerID(), 0, hexLoc);
			facade.CanPlaceRobber(robPlayer);
		}
		//show Normal Map
	}
	
	class Building extends State {
		//show Edit Map
		
		boolean canPlaceRoad(EdgeLocation edgeLoc) {
			BuildRoad road = new BuildRoad(uCookie.getPlayerID(), edgeLoc, false);
			return facade.CanBuildRoad(road);
		}
		
		boolean canPlaceSettlement(VertexLocation vertLoc) {
			BuildSettlement settlement = new BuildSettlement(uCookie.getPlayerID(), vertLoc, false);
			return facade.CanBuildSettlement(settlement);
		}
		
		boolean canPlaceCity(VertexLocation vertLoc) {
			BuildCity city = new BuildCity(uCookie.getPlayerID(), vertLoc);
			return facade.CanBuildCity(city);
		}

		void placeRoad(EdgeLocation edgeLoc) {
			BuildRoad roadMove = new BuildRoad(uCookie.getPlayerID(), edgeLoc, false);
			try {
				proxy.buildRoad(roadMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		void placeSettlement(VertexLocation vertLoc) {
			BuildSettlement settlementMove = new BuildSettlement(uCookie.getPlayerID(), vertLoc, false);
			try {
				proxy.buildSettlement(settlementMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		void placeCity(VertexLocation vertLoc) {
			BuildCity cityMove = new BuildCity(uCookie.getPlayerID(), vertLoc);
			try {
				proxy.buildCity(cityMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
		
		//Show normal Updated Map
	}
	
	class Discarding extends State {
		
	}

}
