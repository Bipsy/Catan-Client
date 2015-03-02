package client.model;

import java.util.List;

import shared.models.ClientModel;
import shared.models.Harbor;
import shared.models.Hex;
import shared.models.Message;
import shared.models.Player;
import shared.models.PlayerHand;
import shared.models.Road;
import shared.models.Robber;
import shared.models.UserManager;
import shared.models.VertexObject;
import shared.models.DTO.params.*;
import shared.definitions.*;
import shared.exceptions.InvalidPlayerIndex;
import client.storage.Data;

public class ModelFacade {

    private ClientModel models;

    public ModelFacade() {
        models = Data.getCurentModelInstance();
    }

    public void updateModels(ClientModel cm) {
        models = cm;
    }

    public String getState() {
        return models.getStatus();
    }

    public List<Message> getLogObject() {
        return models.getLogObject();
    }

    public List<Message> getChatObject() {
        return models.getChatObject();
    }

    public boolean CanDiscardCards(DiscardCards discardCards) {
        return models != null && models.CanDiscardCards(discardCards);
    }

    public boolean CanRollNumber(RollNumber rollNumber) {
        return models != null && models.CanRollNumber(rollNumber);
    }

    public boolean CanBuildRoad(BuildRoad buildRoad) { // check number of roads
        return models != null && models.CanBuildRoad(buildRoad);
    }

    public boolean CanBuildSettlement(BuildSettlement buildSettlement) { // check number of settlements
        return models != null && models.CanBuildSettlement(buildSettlement);
    }

    public boolean CanBuildCity(BuildCity buildCity) { // check number of cities
        return models != null && models.CanBuildCity(buildCity);
    }

    public boolean CanOfferTrade(OfferTrade offerTrade) {
        return models != null && models.CanOfferTrade(offerTrade);
    }

    public boolean CanMaritimeTrade(MaritimeTrade maritimeTrade) {
        return models != null && models.CanMaritimeTrade(maritimeTrade);
    }

    public boolean CanFinishTurn(FinishTurn finishTurn) {
        return models != null && models.CanFinishTurn(finishTurn);
    }

    public boolean CanBuyDevCard(BuyDevCard buyDevCard) {
        return models != null && models.CanBuyDevCard(buyDevCard);
    }

    public boolean CanUseYearOfPlenty(YearOfPlenty yearOfPlenty) {
        return models != null && models.CanUseYearOfPlenty(yearOfPlenty);
    }

    public boolean CanUseRoadBuilder(RoadBuilding roadBuilding) {
        return models != null && models.CanUseRoadBuilder(roadBuilding);
    }

    public boolean CanUseSoldier(Soldier soldier) {
        return models != null && models.CanUseSoldier(soldier);
    }

    public boolean CanUseMonopoly(Monopoly monopoly) {
        return models != null && models.CanUseMonopoly(monopoly);
    }

    public boolean CanUseMonument(Monument monument) {
        return models != null && models.CanUseMonument(monument);
    }

    public boolean CanPlaceRobber(RobPlayer robPlayer) {
        return models != null && models.CanPlaceRobber(robPlayer);
    }

    public boolean hasModel() {
        return (models != null);
    }

    //map data getters
    public int NumberOfHexes() {
        if (models.getBoard().getHexes() != null) {
            return models.getBoard().getHexes().size();
        } else {
            return 0;
        }
    }

    public UserManager getUserManager() {
        if (models.getUserManager() != null) {
            return models.getUserManager();
        } else {
            return null;
        }
    }

    public int getCurrentPlayerIndex() {
        UserManager um = this.getUserManager();
        int index = um.getTurnTracker().getCurrentTurn();
        return index;
    }

    public Hex GetHexAt(int i) {
        if (i < models.getBoard().getHexes().size() && i >= 0) {
            return models.getBoard().getHexes().get(i);
        } else {
            return null;
        }
    }

    public int NumberOfRoads() {
        if (models.getBoard().getRoads() != null) {
            return models.getBoard().getRoads().size();
        } else {
            return 0;
        }
    }

    public Road GetRoadAt(int i) {
        if (i < models.getBoard().getRoads().size() && i >= 0) {
            return models.getBoard().getRoads().get(i);
        } else {
            return null;
        }
    }

    public int NumberOfSettlements() {
        if (models.getBoard().getSettlements() != null) {
            return models.getBoard().getSettlements().size();
        } else {
            return 0;
        }
    }

    public VertexObject GetSettlementAt(int i) {
        if (i < models.getBoard().getSettlements().size() && i >= 0) {
            return models.getBoard().getSettlements().get(i);
        } else {
            return null;
        }
    }

    public int NumberOfCities() {
        if (models.getBoard().getCities() != null) {
            return models.getBoard().getCities().size();
        } else {
            return 0;
        }
    }

    public VertexObject GetCityAt(int i) {
        if (i < models.getBoard().getCities().size() && i >= 0) {
            return models.getBoard().getCities().get(i);
        } else {
            return null;
        }
    }

    public Robber GetRobber() {
        return models.getBoard().getRobber();
    }

    public int NumberOfHarbors() {
        if (models.getBoard().getHarbor() != null) {
            return models.getBoard().getHarbor().size();
        } else {
            return 0;
        }
    }

    public Harbor GetHarborAt(int i) {
        if (i < models.getBoard().getHarbor().size() && i >= 0) {
            return models.getBoard().getHarbor().get(i);
        } else {
            return null;
        }
    }

    //player data getters
    public CatanColor GetPlayerColor(int playerIndex) {
        if (models.getUserManager().getNumPlayers() > playerIndex && 0 <= playerIndex) {
            return models.getUserManager().getPlayer(playerIndex).getColor();
        } else {
            return null;
        }
    }

    public boolean canBuildRoad(int playerIndex) {
        return models.CanBuildRoad(playerIndex);
    }

    public boolean canBuildSettlement(int playerIndex) {
        return models.CanBuildSettlement(playerIndex);
    }

    public boolean canBuildCity(int playerIndex) {
        return models.CanBuildCity(playerIndex);
    }

    public Player getPlayer(int index) throws InvalidPlayerIndex {
        if (index >= 0 && index < 4) {
            return this.models.getPlayer(index);
        } else {
            throw new InvalidPlayerIndex();
        }
    }

    public CatanColor GetPlayerColor(String player) {
        return models.getUserManager().getPlayerColor(player);
    }

    public int getMapRadius() {
        return this.models.getMapRadius();
    }

    public String getLocalUserName() {
        return this.models.getLocalPlayerName();
    }

    public boolean isCurrentTurn(int index) throws InvalidPlayerIndex {
        if (index >= 0 && index < 4) {
            return this.getCurrentPlayerIndex() == index;
        } else {
            throw new InvalidPlayerIndex();
        }
    }

    public int getLargestArmy() {
        return this.models.getLargestArmy();
    }

    public int getlongestRoad() {
        return this.models.getLongestRoad();
    }

    public Integer getLocalPlayerIndex() {
        if (models != null) {
            return models.getLocalPlayerIndex();
        }
        return null;
    }

    public boolean isLocalPlayerTurn() {
        if (models != null) {
            try {
                return isCurrentTurn(models.getLocalPlayerIndex());
            } catch (InvalidPlayerIndex e) {
                System.err.println(e.toString());
            }
        }
        return false;
    }

    public int getResourceCount(int playerIndex, ResourceType type) {
        Player player = models.getPlayer(playerIndex);
        PlayerHand cards = player.getResources();
        return cards.getResourceCount(type);
    }
    
    public PlayerHand getResources(int playerIndex) {
        Player currentPlayer = models.getPlayer(playerIndex);
        PlayerHand hand = currentPlayer.getResources();
        return hand;
    }

    public int getWinner() {
        int winner = -1;
        if (models != null) {
            winner = models.getWinner();
        }
        return winner;
    }

    public boolean canBuyDevCard(int playerIndex) {
        return models.CanBuyDevCard(playerIndex);
    }

    public int soldierCount(int playerIndex) {
        Player player = models.getPlayer(playerIndex);
        return player.getSoldiers();
    }
}
