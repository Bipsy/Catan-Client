package shared.models;

import shared.locations.HexLocation;

/**
 * If the production number for the hex containing the robber is rolled, the
 * owners of adjacent settlements and cities do not receive resources. The
 * robber prevents it.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Robber {

    private HexLocation location;
    private Number number;

    public Robber(HexLocation location) {
        // TODO Auto-generated constructor stub
        this.setLocation(location);
    }

    /**
     * This method updates the hex location of the robber.
     *
     * @param location should be a valid hex location on the Catan map
     * representing the space which the robber occupies.
     */
    public void MoveTo(HexLocation location) {

    }

    public HexLocation getLocation() {
        return location;
    }

    public void setLocation(HexLocation location) {
        this.location = location;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

}
