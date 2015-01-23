package shared.models;

import shared.definitions.PieceType;
import shared.definitions.PortType;

/**
 * Vertex Objects represent pieces that exist on the vertices of hexes. E.G. Settlements and Cities.
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
