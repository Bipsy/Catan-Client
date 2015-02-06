/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.MoveType;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class RollNumberDTO {

    public final MoveType type;
    public final int index;
    public final int number;

    public RollNumberDTO(MoveType newType, int newIndex, int newNumber) {
        type = newType;
        index = newIndex;
        number = newNumber;
    }
}
