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
public class MessageDTO {

    public final MoveType type;
    public final int destID;
    public final String content;

    public MessageDTO(MoveType newType, int newDestID, String newContent) {
        type = newType;
        destID = newDestID;
        content = newContent;
    }
}
