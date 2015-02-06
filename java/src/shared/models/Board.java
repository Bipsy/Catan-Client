package shared.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.DTO.EdgeValueDTO;
import shared.models.DTO.HexDTO;
import shared.models.DTO.PortDTO;
import shared.models.DTO.VertexObjectDTO;
import shared.models.DTO.params.BuildRoad;

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
    

    private Map<EdgeLocation, Road> roadMap;
    private Map<VertexLocation, VertexObject> communityMap;

    public Board() {
    	roadMap = new HashMap<EdgeLocation, Road>();
    	communityMap = new HashMap<VertexLocation, VertexObject>();

    }

    /**
     * Determines from a hex provided if a road can be built
     *
     * @param hex they want to determine if a road can be built on
     * @return true if they can build. false if they cannot
     */
    public boolean canBuildRoad(Hex hex) {
        return false;
    }

    /**
     * Determines from a hex provided if a settlement can be built
     *
     * @param hex they want to determine if a settlement can be built on
     * @return true if they can build. false if they cannot
     */
    public boolean canBuildSettlement(Hex hex) {
        return false;
    }

    /**
     * Determines from a hex provided if a city can be built
     *
     * @param hex they want to determine if a city can be built on
     * @return true if they can build. false if they cannot
     */
    public boolean canBuildCity(Hex hex) {
        return false;
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
    
    public boolean canBuildRoad(BuildRoad buildRoad) {
    	EdgeLocation road = buildRoad.getRoadLocation();
    	if(roadMap.containsKey(road.getNormalizedLocation())) {
    		return false;
    	}
    	else {
    		EdgeLocation[] adjRoads = road.getAdjacentEdges();
    		for (int i = 0; i < adjRoads.length; i++) {
				if(roadMap.containsKey(adjRoads[i].getNormalizedLocation()) &&
						roadMap.get(adjRoads[i].getNormalizedLocation()).getOwner() ==
						buildRoad.getPlayerIndex()) {
					return true;
				}
			}
    		return false;
    	}
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

    public void setHarbor(List<Harbor> harbor) {
        this.harbors = harbor;
    }

    public List<Road> getRoad() {
        return roads;
    }

    public void setRoad(List<Road> road) {
        this.roads = road;
        for (int i = 0; i < road.size(); i++) {
			roadMap.put(roads.get(i).getLocation().getNormalizedLocation(), roads.get(i));
		}
    }

    public List<VertexObject> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<VertexObject> settlements) {
        this.settlements = settlements;
        for (int i = 0; i < settlements.size(); i++) {
        	communityMap.put(settlements.get(i).getLocation(), settlements.get(i));
		}
    }

    public List<VertexObject> getCities() {
        return cities;
    }

    public void setCities(List<VertexObject> cities) {
        this.cities = cities;
        for (int i = 0; i < cities.size(); i++) {
			communityMap.put(cities.get(i).getLocation(), cities.get(i));
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
        for (int i = 0; i < hexArray.length; i++) {
            temp.add(new Hex(hexArray[i]));
        }
        this.hexes = temp;
    }

    public void setHarbor(PortDTO[] ports) {
        List<Harbor> temp = new ArrayList<Harbor>();
        for (int i = 0; i < ports.length; i++) {
            temp.add(new Harbor(ports[i]));
        }
        this.harbors = temp;
    }

    public void setRoads(EdgeValueDTO[] roadArray) {
        List<Road> temp = new ArrayList<Road>();
        for (int i = 0; i < roadArray.length; i++) {
            temp.add(new Road(roadArray[i]));
        }
        this.roads = temp;
    }

    public void setSettlements(VertexObjectDTO[] settlementArray) {
        List<VertexObject> temp = new ArrayList<VertexObject>();
        for (int i = 0; i < settlementArray.length; i++) {
            temp.add(new VertexObject(settlementArray[i], PieceType.SETTLEMENT));
        }
        this.settlements = temp;

    }

    public void setCities(VertexObjectDTO[] cityArray) {
        List<VertexObject> temp = new ArrayList<VertexObject>();
        for (int i = 0; i < cityArray.length; i++) {
            temp.add(new VertexObject(cityArray[i], PieceType.CITY));
        }
        this.cities = temp;

    }

}
