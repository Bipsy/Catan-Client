package shared.models;

/**
 * Model representation of the Catan board
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Board {
	private List<Hex> hexes;
	private List<Harbor> harbor;
	private List<Road> road;
	private List<VertexObject> settlements;
	private List<VertexObject> cities;
	private int radius;
	private Robber robber;
	
	/**
     * Determines from a hex provided if a road can be built
     * @param hex they want to determine if a road can be built on
     * @return true if they can build. false if they cannot
     */
	public boolean canBuildRoad(Hex hex){
		return false;
	}
	
	/**
     * Determines from a hex provided if a settlement can be built
     * @param hex they want to determine if a settlement can be built on
     * @return true if they can build. false if they cannot
     */
	public boolean canBuildSettlement(Hex hex){
		return false;
	}
	
	/**
     * Determines from a hex provided if a city can be built
     * @param hex they want to determine if a city can be built on
     * @return true if they can build. false if they cannot
     */
	public boolean canBuildCity(Hex hex){
		return false;
	}
	
	/**
     * Determines from a hex provided if a robber can be moved
     * @param hex they want to determine if a robber can be moved to
     * @return true if robber can be moved. false if not
     */
	public boolean canMoveRobber(Hex hex){
		return false;
	}
}
