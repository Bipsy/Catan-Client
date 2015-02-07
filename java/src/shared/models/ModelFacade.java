package shared.models;

import shared.models.DTO.params.*;

public class ModelFacade {
	
	private GameState models;

	public ModelFacade() {
		models = null;
	}
	
	public ModelFacade(GameState gs) {
		models = gs;
	}
	
	public void updateModels(GameState gs) {
		models = gs;
	}
	
	public boolean CanDiscardCards (DiscardCards discardCards) {
		return models != null && models.CanDiscardCards(discardCards);
	}
	
	public boolean CanRollNumber (RollNumber rollNumber) {
		return models != null && models.CanRollNumber(rollNumber);
	}
	
	public boolean CanBuildRoad (BuildRoad buildRoad) {
		return models != null && models.CanBuildRoad(buildRoad);
	}
	
	public boolean CanBuildSettlement (BuildSettlement buildSettlement) {
		return models != null && models.CanBuildSettlement(buildSettlement);
	}
	
	public boolean CanBuildCity (BuildCity buildCity) {
		return models != null && models.CanBuildCity(buildCity);
	}
	
	public boolean CanOfferTrade (OfferTrade offerTrade) {
		return models != null && models.CanOfferTrade(offerTrade);
	}
	
	public boolean CanMaritimeTrade (MaritimeTrade maritimeTrade) {
		return models != null && models.CanMaritimeTrade(maritimeTrade);
	}
	
	public boolean CanFinishTurn (FinishTurn finishTurn) {
		return models != null && models.CanFinishTurn(finishTurn);
	}
	
	public boolean CanBuyDevCard (BuyDevCard buyDevCard) {
		return models != null && models.CanBuyDevCard(buyDevCard);
	}
	
	public boolean CanUseYearOfPlenty (YearOfPlenty yearOfPlenty) {
		return models != null && models.CanUseYearOfPlenty(yearOfPlenty);
	}
	
	public boolean CanUseRoadBuilder (RoadBuilding roadBuilding) {
		return models != null && models.CanUseRoadBuilder(roadBuilding);
	}
	
	public boolean CanUseSoldier (Soldier soldier) {
		return models != null && models.CanUseSoldier(soldier);
	}
	
	public boolean CanUseMonopoly (Monopoly monopoly) {
		return models != null && models.CanUseMonopoly(monopoly);
	}
	
	public boolean CanUseMonument (Monument monument) {
		return models != null && models.CanUseMonument(monument);
	}
	
	public boolean CanPlaceRobber (RobPlayer robPlayer) {
		return models != null && models.CanPlaceRobber(robPlayer);
	}
}
