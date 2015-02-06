/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.MoveType;
import shared.locations.HexLocation;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class FigureDTO {

    public final MoveType type;
    public final int playerIndex;
    public final int victimIndex;
    public final HexLocation location;

    public FigureDTO(MoveType newType, int newPlayerIndex,
            int newVictimIndex, HexLocation newLocation) {
        type = newType;
        playerIndex = newPlayerIndex;
        victimIndex = newVictimIndex;
        location = newLocation;
    }
}
