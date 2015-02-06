package shared.models.DTO;

import shared.locations.HexLocation;
import shared.definitions.ResourceTypeOptional;
import shared.locations.EdgeLocation;
import shared.locations.EdgeDirection;

/**
 * This class stores the information needed to create a JSON string of a map,
 * and is used to facilitate the transfer of data between the server and client.
 *
 * @author Austin Von Wagner
 *
 */
public class MapDTO {

    /**
     * only includes land tiles
     */
    private HexDTO[] hexes;
    private EdgeValueDTO[] roads;
    private PortDTO[] ports;
    private VertexObjectDTO[] settlements;
    private VertexObjectDTO[] cities;
    /**
     * The radius of the map includes the center hex and the ocean hexes
     */
    private int radius;
    private HexLocation robber;

    public HexDTO[] getHexes() {
        return hexes;
    }

    public void setHexes(HexDTO[] hexes) {
        this.hexes = hexes;
    }

    public EdgeValueDTO[] getRoads() {
        return roads;
    }

    public void setRoads(EdgeValueDTO[] roads) {
        this.roads = roads;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public HexLocation getRobber() {
        return robber;
    }

    public void setRobber(HexLocation robber) {
        this.robber = robber;
    }

    public PortDTO[] getPorts() {
        return ports;
    }

    public void setPorts(PortDTO[] ports) {
        this.ports = ports;
    }

    public VertexObjectDTO[] getSettlements() {
        return settlements;
    }

    public void setSettlements(VertexObjectDTO[] settlements) {
        this.settlements = settlements;
    }

    public VertexObjectDTO[] getCities() {
        return cities;
    }

    public void setCities(VertexObjectDTO[] cities) {
        this.cities = cities;
    }
}
