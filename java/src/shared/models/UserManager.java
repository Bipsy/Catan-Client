/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import java.util.List;

import shared.models.DTO.TurnTrackerDTO;
import shared.models.DTO.params.DiscardCards;

/**
 * UserManager stores the meta information regarding the users. This includes
 * the updated Turn Tracker and the current list of Users.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class UserManager {

    private List<Player> users;
    TurnTracker turnTracker;

    public UserManager(List<Player> userList, TurnTracker turnTracker2) {
        this.users = userList;
        this.turnTracker = turnTracker2;
    }

	public boolean CanDiscardCards(DiscardCards discardCards) {
		// should check that player index is valid
		return users.get(discardCards.getPlayerIndex()).CanDiscardCards(discardCards);
	}

	public boolean isCurrentPlayer(int playerIndex) {
		return turnTracker.matchesCurrent(playerIndex);
	}
}
