/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

import java.util.List;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class GameDTO {

    public final String title;
    public final int ID;
    public final List<GamePlayerDTO> players;

    GameDTO(String newTitle, int newID, List<GamePlayerDTO> newPlayers) {
        title = newTitle;
        ID = newID;
        players = newPlayers;
    }
}
