package client.model;

import shared.models.DTO.DevCardListDTO;
import shared.models.DTO.GameContainerDTO;
import shared.models.DTO.MapDTO;
import shared.models.DTO.MessageListDTO;
import shared.models.DTO.PlayerDTO;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.TradeOfferDTO;
import shared.models.DTO.TurnTrackerDTO;
import shared.models.DTO.UserDTO;
import shared.models.DTO.ClientModelDTO;

/**
 * 
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Populator implements iPopulator{
	private ClientModel	model;
	@Override
	public boolean populateModel(ClientModelDTO container) {
		/**
		 * Coming from container ->
		  ResourceListDTO bank;
		  MessageListDTO chat;
		  MessageListDTO log;
		  MapDTO map;
		  PlayerDTO[] players;
		  TradeOfferDTO tradeOffer;
		  TurnTrackerDTO turnTracker;
		 */
		
		//Why does getBank return ResourceListDTO?? It needs a DevCardListDTO as well or bankDTO
		populateBank(container.getResources(), container.getDevCards());
		
		//no list of ports in mapDTO, but there is a list of harbors in Board model
		populateBoard(container.getMap());
				
		//I'm getting a chat and a log but the chatObject only has a list of messages...
		populateChatObject(container.getChat());
			
		//
		populateUserManager(container.getPlayers(), container.getTurnTracker());
		
		//
		populateTradeOffer(container.getTradeOffer());

		model.setVersion(container.getVersion());
		model.setWinner(container.getWinner());
		
		return false;
	}

	private void populateTradeOffer(TradeOfferDTO tradeOffer) {
		//make new TradeOffer
		//new.sender sets -> tradeOffer.getSender()
		//new.receiver sets -> tradeOffer.getReceiver()
		//set resourceList from resourceListDTO
		
	}

	private void populateUserManager(PlayerDTO[] players,
			TurnTrackerDTO turnTracker) {
		
		
	}

	private void populateBoard(MapDTO map) {
		// needs a list of ports from mapDTO
		
	}

	private void populateBank(ResourceListDTO bank, DevCardListDTO devCardListDTO) {
		//needs resource list and a dev card list
	}
	
	private void populateChatObject(MessageListDTO messageListDTO) {
		
	}

	
	
	//Is this needed in the client model? I think no.
	@Override
	public boolean populateModel(UserDTO container) {
		// TODO Auto-generated method stub
		return false;
	}

	//Is this needed in the client model? I think no.
	@Override
	public boolean populateModel(GameContainerDTO container) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Getters and Setters
	 */
	public ClientModel getModel() {
		return model;
	}

	public void setModel(ClientModel model) {
		this.model = model;
	}

}