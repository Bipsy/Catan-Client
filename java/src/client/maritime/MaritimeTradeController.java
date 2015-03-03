package client.maritime;

import shared.definitions.*;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.Harbor;
import shared.models.Player;
import shared.models.PlayerHand;

/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller 
    implements IMaritimeTradeController, Observer {

    private IMaritimeTradeOverlay tradeOverlay;

    public MaritimeTradeController(IMaritimeTradeView tradeView, 
            IMaritimeTradeOverlay tradeOverlay) {

        super(tradeView);
        Populator.getInstance().addObserver(this);
        setTradeOverlay(tradeOverlay);
    }

    public IMaritimeTradeView getTradeView() {

        return (IMaritimeTradeView) super.getView();
    }

    public IMaritimeTradeOverlay getTradeOverlay() {
        return tradeOverlay;
    }

    public final void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
        this.tradeOverlay = tradeOverlay;
    }
    
    private Map<ResourceType, Integer> handToMap(PlayerHand hand) {
        Map<ResourceType, Integer> map = new HashMap<>();
        if (hand != null) {
            map.put(ResourceType.BRICK, hand.getResourceCount(ResourceType.BRICK));
            map.put(ResourceType.ORE, hand.getResourceCount(ResourceType.ORE));
            map.put(ResourceType.SHEEP, hand.getResourceCount(ResourceType.SHEEP));
            map.put(ResourceType.WHEAT, hand.getResourceCount(ResourceType.WHEAT));
            map.put(ResourceType.WOOD, hand.getResourceCount(ResourceType.WOOD));
        }
        return map;
    }

    @Override
    public void startTrade() {
        try {
            ModelFacade facade = new ModelFacade();
            int localIndex = facade.getLocalPlayerIndex();
            Player localPlayer = facade.getPlayer(localIndex);
            PlayerHand playerHand = localPlayer.getResources();
            Map<ResourceType, Integer> map = handToMap(playerHand);
            List<Harbor> harbors = facade.getOwnedHarbors(localIndex);
            for (Map.Entry<ResourceType, Integer> entry : map.entrySet()) {
                
            }
            getTradeOverlay().showModal();
        } catch (InvalidPlayerIndex ex) {
            System.err.println("Error while getting player");
            ex.printStackTrace();
        }
    }

    @Override
    public void makeTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {

    }

    @Override
    public void setGiveResource(ResourceType resource) {

    }

    @Override
    public void unsetGetValue() {

    }

    @Override
    public void unsetGiveValue() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            ModelFacade facade = (ModelFacade) arg;
            if (facade.isLocalPlayerTurn()) {
                getTradeView().enableMaritimeTrade(true);
            } else {
                getTradeView().enableMaritimeTrade(false);
            }
        }
    }

}
