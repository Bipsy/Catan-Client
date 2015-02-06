/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.MoveType;
import shared.definitions.ResourceType;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class YearOfPlentyDTO {

    public final MoveType type;
    public final int index;
    ResourceType resourceOne;
    ResourceType resourceTwo;

    public YearOfPlentyDTO(MoveType newType, int newIndex,
            ResourceType newResourceOne, ResourceType newResourceTwo) {
        type = newType;
        index = newIndex;
        resourceOne = newResourceOne;
        resourceTwo = newResourceTwo;
    }
}
