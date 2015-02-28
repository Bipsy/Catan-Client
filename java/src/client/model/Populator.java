package client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import shared.models.Bank;
import shared.models.Board;
import shared.models.ChatObject;
import shared.models.ClientModel;
import shared.models.DevCardList;
import shared.models.Player;
import shared.models.ResourceList;
import shared.models.Robber;
import shared.models.TradeOffer;
import shared.models.TurnTracker;
import shared.models.UserManager;
import shared.models.DTO.DevCardListDTO;
import shared.models.DTO.MapDTO;
import shared.models.DTO.MessageListDTO;
import shared.models.DTO.PlayerDTO;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.TurnTrackerDTO;
import shared.models.DTO.ClientModelDTO;
import client.storage.*;

/**
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Populator extends Observable implements iPopulator {

    private ClientModel model;
    private static Populator instance;
    private ModelFacade facade;
    
    public static Populator getInstance() {
        if (instance == null) {
            instance = new Populator();
        }
        return instance;
    }

    private Populator() {
        model = Data.getCurentModelInstance();
        facade = new ModelFacade();
    }

    @Override
    public boolean populateModel(ClientModelDTO container, String username) {
    	
        populateBank(container.getResources(), container.getDevCards());

        populateBoard(container.getMap());

        populateChatObject(container.getChat(), container.getLog());
        
        populateUserManager(container.getPlayers(), container.getTurnTracker());

        if (container.getTradeOffer() != null) {
            model.setTradeOffer(new TradeOffer(container.getTradeOffer()));
        }

        model.setVersion(container.getVersion());
        model.setWinner(container.getWinner());

        model.setLocalPlayerName(username);
        
        setChanged();
        notifyObservers(facade);

        return true;
    }

    private void populateUserManager(PlayerDTO[] players,
            TurnTrackerDTO turnTracker) {
        List<Player> users = new ArrayList<>();
        for (PlayerDTO player : players) {
        	if(player != null)
        		users.add(new Player(player));
        }

        model.setUserManager(new UserManager(users, new TurnTracker(turnTracker)));

    }

    private void populateBoard(MapDTO map) {

        Board board = new Board();
        
        board.setRadius(map.getRadius());

        board.setHexes(map.getHexes());

        board.setHarbor(map.getPorts());

        board.setRoads(map.getRoads());

        board.setSettlements(map.getSettlements());

        board.setCities(map.getCities());

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

}
