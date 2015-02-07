package client.mocks;


import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.definitions.ResourceTypeOptional;
import shared.exceptions.InvalidPlayerIndex;
import shared.locations.HexLocation;
/**
 * @author Anna Sokolova
 *
 */
import shared.models.DTO.*;

public class ModelMock {
	
	private ClientModelDTO JSONmodel;

    public ModelMock() {
    	createJSONModel();
    	//createClientModel();
    }

    public static String getJSON() {
        return "{ \"deck\": { \"yearOfPlenty\": 2, \"monopoly\": 2, \"soldier\": 14, \"roadBuilding\": 2, \"monument\": 5 }, \"map\": { \"hexes\": [ { \"location\": { \"x\": 0, \"y\": -2 } }, { \"resource\": \"brick\", \"location\": { \"x\": 1, \"y\": -2 }, \"number\": 4 }, { \"resource\": \"wood\", \"location\": { \"x\": 2, \"y\": -2 }, \"number\": 11 }, { \"resource\": \"brick\", \"location\": { \"x\": -1, \"y\": -1 }, \"number\": 8 }, { \"resource\": \"wood\", \"location\": { \"x\": 0, \"y\": -1 }, \"number\": 3 }, { \"resource\": \"ore\", \"location\": { \"x\": 1, \"y\": -1 }, \"number\": 9 }, { \"resource\": \"sheep\", \"location\": { \"x\": 2, \"y\": -1 }, \"number\": 12 }, { \"resource\": \"ore\", \"location\": { \"x\": -2, \"y\": 0 }, \"number\": 5 }, { \"resource\": \"sheep\", \"location\": { \"x\": -1, \"y\": 0 }, \"number\": 10 }, { \"resource\": \"wheat\", \"location\": { \"x\": 0, \"y\": 0 }, \"number\": 11 }, { \"resource\": \"brick\", \"location\": { \"x\": 1, \"y\": 0 }, \"number\": 5 }, { \"resource\": \"wheat\", \"location\": { \"x\": 2, \"y\": 0 }, \"number\": 6 }, { \"resource\": \"wheat\", \"location\": { \"x\": -2, \"y\": 1 }, \"number\": 2 }, { \"resource\": \"sheep\", \"location\": { \"x\": -1, \"y\": 1 }, \"number\": 9 }, { \"resource\": \"wood\", \"location\": { \"x\": 0, \"y\": 1 }, \"number\": 4 }, { \"resource\": \"sheep\", \"location\": { \"x\": 1, \"y\": 1 }, \"number\": 10 }, { \"resource\": \"wood\", \"location\": { \"x\": -2, \"y\": 2 }, \"number\": 6 }, { \"resource\": \"ore\", \"location\": { \"x\": -1, \"y\": 2 }, \"number\": 3 }, { \"resource\": \"wheat\", \"location\": { \"x\": 0, \"y\": 2 }, \"number\": 8 } ], \"roads\": [], \"cities\": [], \"settlements\": [], \"radius\": 3, \"ports\": [ { \"ratio\": 2, \"resource\": \"brick\", \"direction\": \"NE\", \"location\": { \"x\": -2, \"y\": 3 } }, { \"ratio\": 3, \"direction\": \"NW\", \"location\": { \"x\": 2, \"y\": 1 } }, { \"ratio\": 2, \"resource\": \"sheep\", \"direction\": \"NW\", \"location\": { \"x\": 3, \"y\": -1 } }, { \"ratio\": 3, \"direction\": \"SW\", \"location\": { \"x\": 3, \"y\": -3 } }, { \"ratio\": 2, \"resource\": \"wood\", \"direction\": \"NE\", \"location\": { \"x\": -3, \"y\": 2 } }, { \"ratio\": 2, \"resource\": \"ore\", \"direction\": \"S\", \"location\": { \"x\": 1, \"y\": -3 } }, { \"ratio\": 3, \"direction\": \"SE\", \"location\": { \"x\": -3, \"y\": 0 } }, { \"ratio\": 3, \"direction\": \"N\", \"location\": { \"x\": 0, \"y\": 3 } }, { \"ratio\": 2, \"resource\": \"wheat\", \"direction\": \"S\", \"location\": { \"x\": -1, \"y\": -2 } } ], \"robber\": { \"x\": 0, \"y\": -2 } }, \"players\": [ { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, \"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, \"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, \"playedDevCard\": false, \"discarded\": false, \"playerID\": 0, \"playerIndex\": 0, \"name\": \"Sam\", \"color\": \"orange\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, \"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, \"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, \"playedDevCard\": false, \"discarded\": false, \"playerID\": 1, \"playerIndex\": 1, \"name\": \"Brooke\", \"color\": \"blue\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, \"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, \"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, \"playedDevCard\": false, \"discarded\": false, \"playerID\": 10, \"playerIndex\": 2, \"name\": \"Pete\", \"color\": \"red\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, \"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, \"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, \"playedDevCard\": false, \"discarded\": false, \"playerID\": 11, \"playerIndex\": 3, \"name\": \"Mark\", \"color\": \"green\" } ], \"log\": { \"lines\": [] }, \"chat\": { \"lines\": [] }, \"bank\": { \"brick\": 24, \"wood\": 24, \"sheep\": 24, \"wheat\": 24, \"ore\": 24 }, \"turnTracker\": { \"status\": \"FirstRound\", \"currentTurn\": 0, \"longestRoad\": -1, \"largestArmy\": -1 }, \"winner\": -1, \"version\": 0 }";
    }

    public static ClientModelDTO getModelDTO() {
        ModelMock m = new ModelMock();
        return m.JSONmodel;
    }
    
    private void createJSONModel() {
    	
    	JSONmodel = new ClientModelDTO();
    	
        JSONmodel.setWinner(-1);
        JSONmodel.setVersion(0);
        JSONmodel.setTradeOffer(null);
        
        ResourceListDTO bank = new ResourceListDTO();
        bank.setBrick(24);
        bank.setOre(24);
        bank.setSheep(24);
        bank.setWheat(24);
        bank.setWood(24);
        JSONmodel.setBank(bank);
                   	    
        createPlayers();
        createMap();
        
    }
    
    private void createMap() {
    	
    	MapDTO map = new MapDTO();
        map.setRadius(3);
        map.setRobber(new HexLocation(0,-2));
	    map = initializeHexes(map);
	    map = initializePorts(map);
	    JSONmodel.setMap(map);
    }

	private MapDTO initializeHexes(MapDTO map) {
		HexDTO[] hexes = new HexDTO[20];
	    hexes[0] = new HexDTO(new HexLocation(0, -2), ResourceTypeOptional.NONE, null);
	    hexes[1] = new HexDTO(new HexLocation(1, -2), ResourceTypeOptional.BRICK, 4);
	    hexes[2] = new HexDTO(new HexLocation(2, -2), ResourceTypeOptional.WOOD, 11);
	    hexes[3] = new HexDTO(new HexLocation(-1, -1), ResourceTypeOptional.BRICK, 8);
	    hexes[4] = new HexDTO(new HexLocation(0, -1), ResourceTypeOptional.WOOD, 3);
	    hexes[5] = new HexDTO(new HexLocation(1, 1), ResourceTypeOptional.ORE, 9);
	    hexes[6] = new HexDTO(new HexLocation(2, -1), ResourceTypeOptional.SHEEP, 12);
	    hexes[7] = new HexDTO(new HexLocation(-2, 0), ResourceTypeOptional.ORE, 5);
	    hexes[8] = new HexDTO(new HexLocation(-1, 0), ResourceTypeOptional.SHEEP, 10);
	    hexes[9] = new HexDTO(new HexLocation(0, 0), ResourceTypeOptional.WHEAT, 11);
	    hexes[10] = new HexDTO(new HexLocation(1, 0), ResourceTypeOptional.BRICK, 5);
	    hexes[11] = new HexDTO(new HexLocation(2, 0), ResourceTypeOptional.WHEAT, 6);
	    hexes[12] = new HexDTO(new HexLocation(-2, 1), ResourceTypeOptional.WHEAT, 2);
	    hexes[13] = new HexDTO(new HexLocation(-1, 1), ResourceTypeOptional.SHEEP, 9);
	    hexes[14] = new HexDTO(new HexLocation(0, 1), ResourceTypeOptional.WOOD, 4);
	    hexes[15] = new HexDTO(new HexLocation(1, 1), ResourceTypeOptional.SHEEP, 10);
	    hexes[16] = new HexDTO(new HexLocation(-2, 2), ResourceTypeOptional.WOOD, 6);
	    hexes[17] = new HexDTO(new HexLocation(-1, 2), ResourceTypeOptional.ORE, 3);
	    hexes[18] = new HexDTO(new HexLocation(0, 2), ResourceTypeOptional.WHEAT, 8);
	    hexes[19] = new HexDTO(new HexLocation(0, 0), ResourceTypeOptional.WHEAT, 11);
	    map.setHexes(hexes);
	    return map;
	}
	
	private MapDTO initializePorts(MapDTO map) {
		
		PortDTO[] ports = new PortDTO[9];
		ports[0] = new PortDTO(ResourceType.BRICK, new HexLocation(-2,3), "NE", 2);
		ports[1] = new PortDTO(null, new HexLocation(2,1), "NW", 3);
		ports[2] = new PortDTO(ResourceType.SHEEP, new HexLocation(3,-1), "NW", 2);
		ports[3] = new PortDTO(null, new HexLocation(3,-3), "SW", 3);
		ports[4] = new PortDTO(ResourceType.WOOD, new HexLocation(-3,2), "NE", 2);
		ports[5] = new PortDTO(ResourceType.ORE, new HexLocation(1,-3), "S", 2);
		ports[6] = new PortDTO(null, new HexLocation(-3,0), "SE", 3);
		ports[7] = new PortDTO(null, new HexLocation(0,3), "N", 3);
		ports[8] = new PortDTO(ResourceType.WHEAT, new HexLocation(-1,-2), "S", 2);
	    map.setPorts(ports);
	    return map;
	}
    
    
    private void createPlayers() {
    	PlayerDTO[] players = new PlayerDTO[4];
    	PlayerDTO pl1 = createPlayer(4, CatanColor.ORANGE, false, 0, "Sam", 0, false, 0, 15, 5, 0, 0);
    	PlayerDTO pl2 = createPlayer(4, CatanColor.BLUE, false, 0, "Brooke", 1, false, 1, 15, 5, 0, 0);
    	PlayerDTO pl3 = createPlayer(4, CatanColor.RED, false, 0, "Pete", 2, false, 10, 15, 5, 0, 0);
    	PlayerDTO pl4 = createPlayer(4, CatanColor.GREEN, false, 0, "Mark", 3, false, 11, 15, 5, 0, 0);
    	players[0] = pl1;
    	players[1] = pl2;
    	players[2] = pl3;
    	players[3] = pl4;
    	JSONmodel.setPlayers(players);
    }

    private PlayerDTO createPlayer(int cities, CatanColor color, boolean discarded, int monuments, String name, int playerIndex, 
    		boolean playedDevCard, int playerID, int roads, int settlements, int soldiers, int victoryPoints) {
    	PlayerDTO pl1 = new PlayerDTO();
        pl1.setCities(cities);
    	pl1.setColor(color);
    	pl1.setDiscarded(discarded);
    	pl1.setMonuments(monuments);
    	pl1.setName(name);
    	try {
			pl1.setPlayerIndex(playerIndex);
		} catch (InvalidPlayerIndex e) {
			e.printStackTrace();
		}
    	pl1.setPlayedDevCard(playedDevCard);
    	pl1.setPlayerID(playerID);
    	pl1.setRoads(roads);
    	pl1.setSettlements(settlements);
    	pl1.setSoldiers(soldiers);
    	pl1.setVictoryPoints(victoryPoints);
    	
    	return pl1;
    }
   

}
