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
public class BuildRoadDTO {

    public final MoveType type;
    public final int index;
    public final EdgeLocation location;
    public final boolean free;

    public BuildRoadDTO(MoveType newType, int newIndex, EdgeLocation newLocation,
            boolean newFree) {
        type = newType;
        index = newIndex;
        location = newLocation;
        free = newFree;
    }
}
