/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import java.util.List;

import shared.definitions.CatanColor;
import shared.models.DTO.params.BuildCity;
import shared.models.DTO.params.BuildRoad;
import shared.models.DTO.params.BuildSettlement;
import shared.models.DTO.params.BuyDevCard;
import shared.models.DTO.params.DiscardCards;
import shared.models.DTO.params.MaritimeTrade;
import shared.models.DTO.params.OfferTrade;

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

    public boolean CanOfferTrade(OfferTrade offerTrade) {
        return isCurrentPlayer(offerTrade.getPlayerIndex())
                && !isCurrentPlayer(offerTrade.getReceiver())
                && users.get(offerTrade.getPlayerIndex()).CanOfferTrade(offerTrade);
    }

    public boolean isCurrentPlayer(int playerIndex) {
        return turnTracker.matchesCurrent(playerIndex);
    }

    public Player getPlayer(int index) {
        if (index < 0 || index >= users.size()) {
            return null;
        }
        return users.get(index);
    }

    public int getNumPlayers() {
        return users.size();
    }
    
    public String getCurrentTurn() {
    	return turnTracker.getStatus();
    }
    
    public TurnTracker getTurnTracker() {
    	return turnTracker;
    }

    public void setCurrentUser(int index) {
        if (index >= 0 && index < 4) {
            turnTracker.setCurrentTurn(index);
        }
    }
    
    public CatanColor getPlayerColor(String player) {
    	for (int i=0; i<users.size(); i++) {
    		if (users.get(i).getUsername().equals(player)) {
    			Player currUser = users.get(i);
    			return currUser.getColor();
    		}
    	}
		return null;
    }

    public boolean CanMaritimeTrade(MaritimeTrade maritimeTrade) {
        return isCurrentPlayer(maritimeTrade.getPlayerIndex())
                && users.get(maritimeTrade.getPlayerIndex()).CanOfferMTrade(maritimeTrade);
    }

    public boolean CanBuildRoad(BuildRoad buildRoad) {
        return isCurrentPlayer(buildRoad.getPlayerIndex())
                && users.get(buildRoad.getPlayerIndex()).CanBuildRoad();
    }

    public boolean CanBuildSettlement(BuildSettlement buildSettlement) {
        return isCurrentPlayer(buildSettlement.getPlayerIndex())
                && users.get(buildSettlement.getPlayerIndex()).CanBuildSettlement();
    }

    public boolean CanBuildCity(BuildCity buildCity) {
        return isCurrentPlayer(buildCity.getPlayerIndex())
                && users.get(buildCity.getPlayerIndex()).CanBuildCity();
    }

    public boolean CanBuyDevCard(BuyDevCard buyDevCard) {
        return isCurrentPlayer(buyDevCard.getPlayerIndex())
                && users.get(buyDevCard.getPlayerIndex()).CanBuyDevCard(buyDevCard);
    }

    public void adjustUserPieces(int i, int r, int s, int c) {
        users.get(i).adjustUserPieces(r, s, c);
		// TODO Auto-generated method stub

    }

	public boolean CanBuildRoad(int playerIndex) {
		return isCurrentPlayer(playerIndex) && users.get(playerIndex).CanBuildRoad();
	}

	public boolean CanBuildSettlement(int playerIndex) {
		return isCurrentPlayer(playerIndex) && users.get(playerIndex).CanBuildSettlement();
	}
	
	public boolean CanBuildCity(int playerIndex) {
		return isCurrentPlayer(playerIndex) && users.get(playerIndex).CanBuildCity();
	}

	public int getLargestArmy() {
		return this.turnTracker.getLargestArmy();
	}

	public int getLongestRoad() {
		return this.turnTracker.getLongestRoad();
	}
	
	public Integer getLocalPlayerIndex(String name) {
		if(name != null) {
			for (int i = 0; i < this.users.size(); i++) {
				if(name.equals(users.get(i).getUsername())) {
					return users.get(i).getIndex();
				}
			}
		}
		return null;
	}

}
