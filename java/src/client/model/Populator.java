package client.model;

import java.util.ArrayList;
import java.util.List;

import shared.locations.HexLocation;
import shared.models.Bank;
import shared.models.Board;
import shared.models.ChatObject;
import shared.models.ClientModel;
import shared.models.DevCardList;
import shared.models.Harbor;
import shared.models.Hex;
import shared.models.Player;
import shared.models.ResourceList;
import shared.models.Road;
import shared.models.Robber;
import shared.models.TradeOffer;
import shared.models.TurnTracker;
import shared.models.UserManager;
import shared.models.VertexObject;
import shared.models.DTO.DevCardListDTO;
import shared.models.DTO.EdgeValueDTO;
import shared.models.DTO.GameContainerDTO;
import shared.models.DTO.HexDTO;
import shared.models.DTO.MapDTO;
import shared.models.DTO.MessageListDTO;
import shared.models.DTO.PlayerDTO;
import shared.models.DTO.PortDTO;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.TradeOfferDTO;
import shared.models.DTO.TurnTrackerDTO;
import shared.models.DTO.UserDTO;
import shared.models.DTO.ClientModelDTO;
import shared.models.DTO.VertexObjectDTO;

/**
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Populator implements iPopulator {

    private ClientModel model;
    
    public Populator() {
    	model = new ClientModel();
    }

    @Override
    public boolean populateModel(ClientModelDTO container) {

    	model = new ClientModel();
        populateBank(container.getResources(), container.getDevCards());
        System.out.println("I polulated the bank");

        populateBoard(container.getMap());
        
        populateChatObject(container.getChat(), container.getLog());

        populateUserManager(container.getPlayers(), container.getTurnTracker());

        if (container.getTradeOffer() != null)
        	model.setTradeOffer(new TradeOffer(container.getTradeOffer()));

        model.setVersion(container.getVersion());
        model.setWinner(container.getWinner());

        return true;
    }

    private void populateUserManager(PlayerDTO[] players,
            TurnTrackerDTO turnTracker) {
        List<Player> users = new ArrayList<Player>();
        for (int i = 0; i < players.length; i++) {
            users.add(new Player(players[i]));
        }

        model.setUserManager(new UserManager(users, new TurnTracker(turnTracker)));

    }

    private void populateBoard(MapDTO map) {

        Board board = new Board();

        board.setHexes(map.getHexes());

        board.setHarbor(map.getPorts());

        board.setRoads(map.getRoads());

        board.setSettlements(map.getSettlements());
        
        board.setCities(map.getCities());

        board.setRadius(map.getRadius());

        board.setRobber(new Robber(map.getRobber()));

        model.setBoard(board);

    }

    private void populateBank(ResourceListDTO resources, DevCardListDTO devCards) {
        ResourceList resourceList = new ResourceList(resources);
        DevCardList devCardList = new DevCardList(devCards);

        model.setBank(new Bank(resourceList, devCardList));
    }

    private void populateChatObject(MessageListDTO chat, MessageListDTO log) {

        ChatObject chatObject = new ChatObject(chat.getLines(), log.getLines());
        model.setChatObject(chatObject);
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
