/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.MoveType;
import shared.definitions.PortType;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class MaritimeTradeDTO {

    public final MoveType type;
    public final int index;
    public final PortType ratio;
    public final String input;
    public final String output;

    public MaritimeTradeDTO(MoveType newType, int newIndex, PortType newRatio,
            String newInput, String newOutput) {
        type = newType;
        index = newIndex;
        ratio = newRatio;
        input = newInput;
        output = newOutput;
    }
}
