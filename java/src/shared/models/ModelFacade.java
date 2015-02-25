package shared.models;

import shared.models.DTO.params.*;
import shared.models.DTO.*;
import shared.definitions.*;
import shared.locations.*;
import shared.models.*;
import client.storage.Data;


public class ModelFacade {

    private ClientModel models;

    public ModelFacade(ClientModel cm) {
        models = cm;
    }

    public void updateModels(ClientModel cm) {
        models = cm;
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
    
    //map data getters
    
    public int NumberOfHexes() {
    	return models.getBoard().getHexes().size();
    }
    
    public Hex GetHexAt(int i) {
    	if (i < models.getBoard().getHexes().size() && i >=0)
    		return models.getBoard().getHexes().get(i);
    	else
    		return null;
    }
    
    public int NumberOfRoads() {
    	return models.getBoard().getRoad().size();
    }
    
    public Road GetRoadAt(int i) {
    	if (i < models.getBoard().getRoad().size() && i >= 0)
    		return models.getBoard().getRoad().get(i);
    	else
    		return null;
    }
    
    public int NumberOfSettlements() {
    	return models.getBoard().getSettlements().size();
    }
    
    public VertexObject GetSettlementAt(int i) {
    	if (i < models.getBoard().getSettlements().size() && i >= 0)
    		return models.getBoard().getSettlements().get(i);
    	else
    		return null;
    }
    
    public int NumberOfCities() {
    	return models.getBoard().getCities().size();
    }
    
    public VertexObject GetCityAt(int i) {
    	if (i < models.getBoard().getCities().size() && i >= 0)
    		return models.getBoard().getCities().get(i);
    	else
    		return null;
    }
    
    public Robber GetRobber() {
    	return models.getBoard().getRobber();
    }
    
    public int NumberOfHarbors() {
    	return models.getBoard().getHarbor().size();
    }
    
    public Harbor GetHarborAt(int i) {
    	if (i < models.getBoard().getHarbor().size() && i >= 0)
    		return models.getBoard().getHarbor().get(i);
    	else
    		return null;
    }
    
    //player data getters
    
    public CatanColor GetPlayerColor (int playerIndex) {
    	if (models.getUserManager().getNumPlayers() > playerIndex && 0 <= playerIndex) 
    		return models.getUserManager().getPlayer(playerIndex).getColor();
    	else
    		return null;
    			
    }
    
}
