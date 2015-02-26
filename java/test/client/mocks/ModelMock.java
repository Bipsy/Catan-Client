package client.mocks;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
/**
 * @author Anna Sokolova
 *
 */
import shared.models.DTO.*;

public class ModelMock {

    public ModelMock() {
    }

    public static String getJSON() {
        return "{ \"deck\": { \"yearOfPlenty\": 2, \"monopoly\": 2, \"soldier\": 14, \"roadBuilding\": 2, "
                + "\"monument\": 5 }, \"map\": { \"hexes\": [ { \"location\": { \"x\": 0, \"y\": -2 } }, "
                + "{ \"resource\": \"brick\", \"location\": { \"x\": 1, \"y\": -2 }, \"number\": 4 }, "
                + "{ \"resource\": \"wood\", \"location\": { \"x\": 2, \"y\": -2 }, \"number\": 11 }, "
                + "{ \"resource\": \"brick\", \"location\": { \"x\": -1, \"y\": -1 }, \"number\": 8 }, "
                + "{ \"resource\": \"wood\", \"location\": { \"x\": 0, \"y\": -1 }, \"number\": 3 }, "
                + "{ \"resource\": \"ore\", \"location\": { \"x\": 1, \"y\": -1 }, \"number\": 9 }, "
                + "{ \"resource\": \"sheep\", \"location\": { \"x\": 2, \"y\": -1 }, \"number\": 12 }, "
                + "{ \"resource\": \"ore\", \"location\": { \"x\": -2, \"y\": 0 }, \"number\": 5 }, "
                + "{ \"resource\": \"sheep\", \"location\": { \"x\": -1, \"y\": 0 }, \"number\": 10 }, "
                + "{ \"resource\": \"wheat\", \"location\": { \"x\": 0, \"y\": 0 }, \"number\": 11 }, "
                + "{ \"resource\": \"brick\", \"location\": { \"x\": 1, \"y\": 0 }, \"number\": 5 }, "
                + "{ \"resource\": \"wheat\", \"location\": { \"x\": 2, \"y\": 0 }, \"number\": 6 }, "
                + "{ \"resource\": \"wheat\", \"location\": { \"x\": -2, \"y\": 1 }, \"number\": 2 }, "
                + "{ \"resource\": \"sheep\", \"location\": { \"x\": -1, \"y\": 1 }, \"number\": 9 }, "
                + "{ \"resource\": \"wood\", \"location\": { \"x\": 0, \"y\": 1 }, \"number\": 4 }, "
                + "{ \"resource\": \"sheep\", \"location\": { \"x\": 1, \"y\": 1 }, \"number\": 10 }, "
                + "{ \"resource\": \"wood\", \"location\": { \"x\": -2, \"y\": 2 }, \"number\": 6 }, "
                + "{ \"resource\": \"ore\", \"location\": { \"x\": -1, \"y\": 2 }, \"number\": 3 }, "
                + "{ \"resource\": \"wheat\", \"location\": { \"x\": 0, \"y\": 2 }, \"number\": 8 } ], \"roads\": [], "
                + "\"cities\": [], \"settlements\": [], \"radius\": 3, \"ports\": [ { \"ratio\": 2, "
                + "\"resource\": \"brick\", \"direction\": \"NE\", \"location\": { \"x\": -2, \"y\": 3 } }, "
                + "{ \"ratio\": 3, \"direction\": \"NW\", \"location\": { \"x\": 2, \"y\": 1 } }, { \"ratio\": 2, "
                + "\"resource\": \"sheep\", \"direction\": \"NW\", \"location\": { \"x\": 3, \"y\": -1 } }, "
                + "{ \"ratio\": 3, \"direction\": \"SW\", \"location\": { \"x\": 3, \"y\": -3 } }, { \"ratio\": 2, "
                + "\"resource\": \"wood\", \"direction\": \"NE\", \"location\": { \"x\": -3, \"y\": 2 } }, "
                + "{ \"ratio\": 2, \"resource\": \"ore\", \"direction\": \"S\", \"location\": { \"x\": 1, "
                + "\"y\": -3 } }, { \"ratio\": 3, \"direction\": \"SE\", \"location\": { \"x\": -3, \"y\": 0 } }, "
                + "{ \"ratio\": 3, \"direction\": \"N\", \"location\": { \"x\": 0, \"y\": 3 } }, { \"ratio\": 2, "
                + "\"resource\": \"wheat\", \"direction\": \"S\", \"location\": { \"x\": -1, \"y\": -2 } } ], "
                + "\"robber\": { \"x\": 0, \"y\": -2 } }, \"players\": [ { \"resources\": { \"brick\": 0, \"wood\": 0, "
                + "\"sheep\": 0, \"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, "
                + "\"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, "
                + "\"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, "
                + "\"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, "
                + "\"playedDevCard\": false, \"discarded\": false, \"playerID\": 0, \"playerIndex\": 0, \"name\": "
                + "\"Sam\", \"color\": \"orange\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, "
                + "\"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, "
                + "\"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, "
                + "\"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, "
                + "\"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, "
                + "\"playedDevCard\": false, \"discarded\": false, \"playerID\": 1, \"playerIndex\": 1, \"name\": "
                + "\"Brooke\", \"color\": \"blue\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, "
                + "\"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, "
                + "\"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, "
                + "\"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, "
                + "\"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, "
                + "\"playedDevCard\": false, \"discarded\": false, \"playerID\": 10, \"playerIndex\": 2, \"name\": "
                + "\"Pete\", \"color\": \"red\" }, { \"resources\": { \"brick\": 0, \"wood\": 0, \"sheep\": 0, "
                + "\"wheat\": 0, \"ore\": 0 }, \"oldDevCards\": { \"yearOfPlenty\": 0, \"monopoly\": 0, "
                + "\"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"newDevCards\": { \"yearOfPlenty\": 0, "
                + "\"monopoly\": 0, \"soldier\": 0, \"roadBuilding\": 0, \"monument\": 0 }, \"roads\": 15, "
                + "\"cities\": 4, \"settlements\": 5, \"soldiers\": 0, \"victoryPoints\": 0, \"monuments\": 0, "
                + "\"playedDevCard\": false, \"discarded\": false, \"playerID\": 11, \"playerIndex\": 3, "
                + "\"name\": \"Mark\", \"color\": \"green\" } ], \"log\": { \"lines\": [] }, \"chat\": "
                + "{ \"lines\": [] }, \"bank\": { \"brick\": 23, \"wood\": 21, \"sheep\": 20, \"wheat\": 22, "
                + "\"ore\": 22 }, \"turnTracker\": { \"status\": \"FirstRound\", \"currentTurn\": 0, "
                + "\"longestRoad\": -1, \"largestArmy\": -1 }, \"winner\": -1, \"version\": 0 }";
    }

    public static ClientModelDTO getModelDTO() {
        DevCardListDTO devCards = new DevCardListDTO(2, 2, 14, 2, 5);
        HexDTO[] hexes = {
            new HexDTO(new HexLocation(0, -2), null, null),
            new HexDTO(new HexLocation(1, -2), "brick", 4),
            new HexDTO(new HexLocation(2, -2), "wood", 11),
            new HexDTO(new HexLocation(-1, -1), "brick", 8),
            new HexDTO(new HexLocation(0, -1), "wood", 3),
            new HexDTO(new HexLocation(1, -1), "ore", 9),
            new HexDTO(new HexLocation(2, -1), "sheep", 12),
            new HexDTO(new HexLocation(-2, 0), "ore", 5),
            new HexDTO(new HexLocation(-1, 0), "sheep", 10),
            new HexDTO(new HexLocation(0, 0), "wheat", 11),
            new HexDTO(new HexLocation(1, 0), "brick", 5),
            new HexDTO(new HexLocation(2, 0), "wheat", 6),
            new HexDTO(new HexLocation(-2, 1), "wheat", 2),
            new HexDTO(new HexLocation(-1, 1), "sheep", 9),
            new HexDTO(new HexLocation(0, 1), "wood", 4),
            new HexDTO(new HexLocation(1, 1), "sheep", 10),
            new HexDTO(new HexLocation(-2, 2), "wood", 6),
            new HexDTO(new HexLocation(-1, 2), "ore", 3),
            new HexDTO(new HexLocation(0, 2), "wheat", 8)
        };
        EdgeValueDTO[] roads = {
            new EdgeValueDTO(2, new EdgeLocationDTO(1, -1, EdgeDirection.South)),
            new EdgeValueDTO(3, new EdgeLocationDTO(2, -2, EdgeDirection.SouthWest)),
            new EdgeValueDTO(0, new EdgeLocationDTO(0, 1, EdgeDirection.South)),
            new EdgeValueDTO(1, new EdgeLocationDTO(-2, 1, EdgeDirection.SouthWest)),
            new EdgeValueDTO(2, new EdgeLocationDTO(0, 0, EdgeDirection.South)),
            new EdgeValueDTO(0, new EdgeLocationDTO(2, 0, EdgeDirection.SouthWest)),
            new EdgeValueDTO(1, new EdgeLocationDTO(-1, -1, EdgeDirection.South)),
            new EdgeValueDTO(3, new EdgeLocationDTO(-1, 1, EdgeDirection.SouthWest))
        };
        VertexObjectDTO[] cities = new VertexObjectDTO[0];
        VertexObjectDTO[] settlements = {
            new VertexObjectDTO(3, new VertexLocation(new HexLocation(1, -2), VertexDirection.SouthEast)),
            new VertexObjectDTO(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest)),
            new VertexObjectDTO(2, new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest)),
            new VertexObjectDTO(1, new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthEast)),
            new VertexObjectDTO(0, new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast)),
            new VertexObjectDTO(1, new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest)),
            new VertexObjectDTO(0, new VertexLocation(new HexLocation(2, 0), VertexDirection.SouthWest)),
            new VertexObjectDTO(3, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest))
        };
        PortDTO[] ports = {
            new PortDTO(3, null, new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest)),
            new PortDTO(2, "ore", new EdgeLocation(new HexLocation(1, -3), EdgeDirection.South)),
            new PortDTO(3, null, new EdgeLocation(new HexLocation(2, 1), EdgeDirection.NorthWest)),
            new PortDTO(2, "wheat", new EdgeLocation(new HexLocation(-1, -2), EdgeDirection.South)),
            new PortDTO(2, "brick", new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.NorthEast)),
            new PortDTO(2, "wood", new EdgeLocation(new HexLocation(-3, 2), EdgeDirection.NorthEast)),
            new PortDTO(2, "sheep", new EdgeLocation(new HexLocation(3, -1), EdgeDirection.NorthWest)),
            new PortDTO(3, null, new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast)),
            new PortDTO(3, null, new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North))
        };
        HexLocation robber = new HexLocation(0, -2);
        MapDTO map = new MapDTO(hexes, roads, cities, settlements, 3, ports, robber);
        PlayerDTO[] players = {
            new PlayerDTO(new ResourceListDTO(0, 1, 1, 1, 0), new DevCardListDTO(0, 0, 0, 0, 0),
            new DevCardListDTO(0, 0, 0, 0, 0), 13, 4, 3, 0, 2, 0, false, false, 0, 0, "Sam", "orange"),
            new PlayerDTO(new ResourceListDTO(1, 0, 1, 0, 1), new DevCardListDTO(0, 0, 0, 0, 0),
            new DevCardListDTO(0, 0, 0, 0, 0), 13, 4, 3, 0, 2, 0, false, false, 1, 1, "Brooke", "blue"),
            new PlayerDTO(new ResourceListDTO(1, 3, 1, 2, 1), new DevCardListDTO(0, 0, 0, 0, 0),
            new DevCardListDTO(0, 0, 0, 0, 0), 13, 4, 3, 0, 2, 0, false, false, 10, 2, "Pete", "red"),
            new PlayerDTO(new ResourceListDTO(0, 1, 1, 0, 1), new DevCardListDTO(0, 0, 0, 0, 0),
            new DevCardListDTO(0, 0, 0, 0, 0), 13, 4, 3, 0, 2, 0, false, false, 11, 3, "Mark", "green")
        };

        MessageLineDTO[] lines = {
            new MessageLineDTO("Sam", "Sam built a road"),
            new MessageLineDTO("Sam", "Sam built a settlement"),
            new MessageLineDTO("Sam", "Sam's turn just ended"),
            new MessageLineDTO("Brooke", "Brooke built a road"),
            new MessageLineDTO("Brooke", "Brooke built a settlement"),
            new MessageLineDTO("Brooke", "Brooke's turn just ended"),
            new MessageLineDTO("Pete", "Pete built a road"),
            new MessageLineDTO("Pete", "Pete built a settlement"),
            new MessageLineDTO("Pete", "Pete's turn just ended"),
            new MessageLineDTO("Mark", "Mark built a road"),
            new MessageLineDTO("Mark", "Mark built a settlement"),
            new MessageLineDTO("Mark", "Mark's turn just ended"),
            new MessageLineDTO("Mark", "Mark built a road"),
            new MessageLineDTO("Mark", "Mark built a settlement"),
            new MessageLineDTO("Mark", "Mark's turn just ended"),
            new MessageLineDTO("Pete", "Pete built a road"),
            new MessageLineDTO("Pete", "Pete built a settlement"),
            new MessageLineDTO("Pete", "Pete's turn just ended"),
            new MessageLineDTO("Brooke", "Brooke built a road"),
            new MessageLineDTO("Brooke", "Brooke built a settlement"),
            new MessageLineDTO("Brooke", "Brooke's turn just ended"),
            new MessageLineDTO("Sam", "Sam built a road"),
            new MessageLineDTO("Sam", "Sam built a settlement"),
            new MessageLineDTO("Sam", "Sam's turn just ended")
        };
        MessageListDTO log = new MessageListDTO();
        log.setLines(lines);

        MessageListDTO chat = new MessageListDTO();
        chat.setLines(new MessageLineDTO[0]);

        ResourceListDTO resources = new ResourceListDTO(23, 21, 20, 22, 22);

        TradeOfferDTO tradeOffer = null;

        TurnTrackerDTO turnTracker = new TurnTrackerDTO("Rolling", 0, -1, -1);

        ClientModelDTO model = new ClientModelDTO(resources, devCards, chat, log, map, players, tradeOffer, turnTracker, -1, 0);
        return model;
    }

}
