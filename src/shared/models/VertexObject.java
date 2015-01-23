package shared.models;

/**
 * 
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class VertexObject {
	private int owner;
	private PieceType type;
	private int resourceRatio;
	private PortType harbor;
	
	/**
     * Convenience method to determine if object harbor benefits available to it.
     * @return Boolean representing whether (true) or not (false) object resides
     * on a harbor.
     */
	public boolean onHarbor() {
		return false;
	}
}
