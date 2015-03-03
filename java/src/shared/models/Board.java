package shared.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.DTO.EdgeValueDTO;
import shared.models.DTO.HexDTO;
import shared.models.DTO.PortDTO;
import shared.models.DTO.VertexObjectDTO;
import shared.models.DTO.params.BuildCity;
import shared.models.DTO.params.BuildRoad;
import shared.models.DTO.params.BuildSettlement;

/**
 * Model representation of the Catan board
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Board {

    private List<Hex> hexes;
    private List<Harbor> harbors;
    private List<Road> roads;
    private List<VertexObject> settlements;
    private List<VertexObject> cities;
    private int radius;
    private Robber robber;

    private Map<HexLocation, Hex> hexMap;
    private Map<EdgeLocation, Road> roadMap;
    private Map<VertexLocation, VertexObject> communityMap;

    public Board() {
        roadMap = new HashMap<>();
        communityMap = new HashMap<>();

    }

    /**
     * Determines from a hex provided if a robber can be moved
     *
     * @param hex they want to determine if a robber can be moved to
     * @return true if robber can be moved. false if not
     */
    public boolean canPlaceRobber(HexLocation hex) {
        return robber.isNewLocation(hex);
    }

    public boolean canBuildRoad(BuildRoad buildRoad, String state) {
        EdgeLocation road = buildRoad.getRoadLocation().getEdgeLocation();
        if (roadMap.containsKey(road.getNormalizedLocation())) {
            return false;
        } 
        else if (!hexMap.containsKey(road.getHexLoc()) && !hexMap.containsKey(road.getHexLoc().getNeighborLoc(road.getDir()))) {
        	return false;
        }
        else if (state.contains("Round")) {
        	VertexLocation[] adjVertex = road.getAdjacentVertices();
        	System.out.println(adjVertex == null);
        	boolean result = false;
        	for (VertexLocation vertexLocation : adjVertex) {
        		if(communityMap.containsKey(vertexLocation.getNormalizedLocation()))
        				return false;
        		boolean buildable = true;
				VertexLocation[]adjVertecies = vertexLocation.getAdjacentVertexes();
				for (VertexLocation vertexLocation2 : adjVertecies) {
					if(communityMap.containsKey(vertexLocation2.getNormalizedLocation())) {
						buildable = false;
						break;
					}
				}
				if(buildable) {
					result = true;
					break;
				}
			}
        	return result;
        }else {
            EdgeLocation[] adjRoads = road.getAdjacentEdges();
            for (int i = 0; i < adjRoads.length; i++) {
                if (roadMap.containsKey(adjRoads[i].getNormalizedLocation())
                        && roadMap.get(adjRoads[i].getNormalizedLocation()).getOwner()
                        == buildRoad.getPlayerIndex()) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean canBuildSettlement(BuildSettlement buildSettlement) {
        boolean result = false;
        VertexLocation vertex = buildSettlement.getVertexLocation().getVertexLocation();

        if (!communityMap.containsKey(vertex.getNormalizedLocation())) {
            VertexLocation[] adjVertex = vertex.getAdjacentVertexes();
            boolean found = false;
            for (int i = 0; i < adjVertex.length; i++) {
                if (communityMap.containsKey(adjVertex[i].getNormalizedLocation())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                EdgeLocation[] adjRoads = vertex.getAdjacentEdges();
                for (int i = 0; i < adjRoads.length; i++) {
                    if (roadMap.containsKey(adjRoads[i].getNormalizedLocation())
                            && roadMap.get(adjRoads[i].getNormalizedLocation()).getOwner()
                            == buildSettlement.getPlayerIndex()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean canBuildCity(BuildCity buildCity) {
        VertexLocation vertex = buildCity.getVertexLocation().getVertexLocation().getNormalizedLocation();

        if (communityMap.containsKey(vertex)) {
            VertexObject vertexObj = communityMap.get(vertex);
            if (vertexObj.getType() == PieceType.SETTLEMENT && vertexObj.getOwner()
                    == buildCity.getPlayerIndex()) {
                return true;
            }
        }
        return false;
    }

    public List<Hex> getHexes() {
        return hexes;
    }

    public void setHexes(List<Hex> hexes) {
        this.hexes = hexes;
    }

    public List<Harbor> getHarbor() {
        return harbors;
    }
    
    public Map<PortType, Harbor> getOwnedHarbors(int playerIndex) {
        Map<PortType, Harbor> ownedHarbors = new HashMap<>();
        if (harbors != null) {
            for (Harbor harbor : harbors) {
                EdgeLocation harborEdge = harbor.getLocation();
                VertexLocation[] adjacentVertices = 
                        harborEdge.getAdjacentVertices();
                for (VertexLocation vertex : adjacentVertices) {
                    if (communityMap.containsKey(vertex.getNormalizedLocation())) {
                        VertexObject community = 
                                communityMap.get(vertex.getNormalizedLocation());
                        if (community.getOwner() == playerIndex) {
                            ownedHarbors.put(harbor.getResource(), harbor);
                        }
                    }
                }
            }
        }
        return ownedHarbors;
    }

    public void setHarbor(List<Harbor> harbor) {
        this.harbors = harbor;
    }

    public List<Road> getRoads() {
        return roads;
    }
    
    
    public void setRoads(EdgeValueDTO[] roadArray) {
    	List<Road> temp = new ArrayList<>();
    	for (int i = 0; i < roadArray.length; i++) {
    		temp.add(new Road(roadArray[i]));        		
    	}
    	setRoads(temp);
    }
    
    public void setRoads(List<Road> road) {
    	this.roads = road;
    	for (int i = 0; i < road.size(); i++) {
    		roadMap.put(road.get(i).getLocation().getNormalizedLocation(), road.get(i));
    	}
    }

    public List<VertexObject> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<VertexObject> settlements) {
        this.settlements = settlements;
        for (int i = 0; i < settlements.size(); i++) {
            communityMap.put(settlements.get(i).getLocation().getNormalizedLocation(), settlements.get(i));
        }
    }

    public List<VertexObject> getCities() {
        return cities;
    }

    public void setCities(List<VertexObject> cities) {
        this.cities = cities;
        for (int i = 0; i < cities.size(); i++) {
            communityMap.put(cities.get(i).getLocation().getNormalizedLocation(), cities.get(i));
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Robber getRobber() {
        return robber;
    }

    public void setRobber(Robber robber) {
        this.robber = robber;
    }

    public void setHexes(HexDTO[] hexArray) {
        List<Hex> temp = new ArrayList<Hex>();
        hexMap = new HashMap<HexLocation, Hex>();
//        for (Hex hex : hexes) {
//			hexMap.put(hex.getLocation(), hex);
//		}
        for (int i = 0; i < hexArray.length; i++) {
        	Hex hex = new Hex(hexArray[i]);

            temp.add(hex);
            hexMap.put(hex.getLocation(), hex);
        }
        this.hexes = temp;
    }

    public void setHarbor(PortDTO[] ports) {
        List<Harbor> temp = new ArrayList<>();
        for (int i = 0; i < ports.length; i++) {
            temp.add(new Harbor(ports[i]));
        }
        this.harbors = temp;
    }

    public void setSettlements(VertexObjectDTO[] settlementArray) {
        List<VertexObject> temp = new ArrayList<>();
        for (int i = 0; i < settlementArray.length; i++) {
            temp.add(new VertexObject(settlementArray[i], PieceType.SETTLEMENT));
        }
        setSettlements(temp);

    }

    public void setCities(VertexObjectDTO[] cityArray) {
        List<VertexObject> temp = new ArrayList<>();
        for (int i = 0; i < cityArray.length; i++) {
            temp.add(new VertexObject(cityArray[i], PieceType.CITY));
        }
        setCities(temp);

    }

    public void addRoad(Road road) {
        this.roads.add(road);
        this.roadMap.put(road.getLocation().getNormalizedLocation(), road);
    }
    
    /*
    public List<VertexObject> getBuildingsAt(Hex hex) {
    	List<VertexObject> buildings = new ArrayList<VertexObject>();
    	for (VertexLocation vertex : hex.getAdjacentVertices()) {
    		if (communityMap[vertex])
    	}
    }
    */

}