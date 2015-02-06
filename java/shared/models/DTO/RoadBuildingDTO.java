/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.MoveType;
import shared.locations.EdgeLocation;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class RoadBuildingDTO {

    public final MoveType type;
    public final int index;
    public final EdgeLocation locationOne;
    public final EdgeLocation locationTwo;

    public RoadBuildingDTO(MoveType newType, int newIndex,
            EdgeLocation newLocationOne, EdgeLocation newLocationTwo) {
        type = newType;
        index = newIndex;
        locationOne = newLocationOne;
        locationTwo = newLocationTwo;
    }
}
