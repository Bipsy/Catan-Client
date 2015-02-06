/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import shared.definitions.CatanColor;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class GamePlayerDTO {

    public final CatanColor color;
    public final String name;
    public final int ID;

    public GamePlayerDTO(CatanColor newColor, String newName, int newID) {
        color = newColor;
        name = newName;
        ID = newID;
    }
}
