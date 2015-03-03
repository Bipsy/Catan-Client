package client.maritime;

import shared.definitions.*;
import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.models.DTO.params.MaritimeTrade;
import shared.models.Harbor;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller 
    implements IMaritimeTradeController, Observer {

    private IMaritimeTradeOverlay tradeOverlay;
    private ResourceType give;
    private ResourceType get;
    private int ratio;

    public MaritimeTradeController(IMaritimeTradeView tradeView, 
            IMaritimeTradeOverlay tradeOverlay) {

        super(tradeView);
        Populator.getInstance().addObserver(this);
        setTradeOverlay(tradeOverlay);
        ratio = 4;
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
    
    private void enableGives() {
        ModelFacade facade = new ModelFacade();        
        Map<ResourceType, Integer> playerHand = facade.getResources(
                facade.getLocalPlayerIndex());
        Map<PortType, Harbor> harbors = facade.getOwnedHarbors(
                facade.getLocalPlayerIndex());        
        List<ResourceType> enabledResources = new ArrayList<>();
        int resourceLimit = 4;
        if (harbors.containsKey(PortType.THREE)) {
            resourceLimit = 3;
        }
        for (Map.Entry<ResourceType, Integer> entry : playerHand.entrySet()) {
            PortType portType = PortType.convertToResource(entry.getKey());
            if (harbors.containsKey(portType) && entry.getValue() > 1) {
                enabledResources.add(entry.getKey());
            } else if (entry.getValue() >= resourceLimit) {
                enabledResources.add(entry.getKey());
            }
        }
        getTradeOverlay().showGiveOptions(enabledResources.toArray(
                new ResourceType[enabledResources.size()]));
    }
    
    private void enableGets() {
        ModelFacade facade = new ModelFacade();
        Map<ResourceType, Integer> bank = facade.getBank();
        List<ResourceType> resources = new ArrayList<>();
        for (Map.Entry<ResourceType, Integer> entry : bank.entrySet()) {
            if (entry.getValue() > 0) {
                resources.add(entry.getKey());
            }
        }
        getTradeOverlay().showGetOptions(
                        resources.toArray(new ResourceType[resources.size()]));
    }

    @Override
    public void startTrade() {
        enableGives();
        getTradeOverlay().setTradeEnabled(false);
        getTradeOverlay().showModal();
    }

    @Override
    public void makeTrade() {
        ModelFacade facade = new ModelFacade();
        ServerProxy proxy = ServerProxy.getInstance();
        int playerIndex = facade.getLocalPlayerIndex();
        if (get != null && give != null) {
            try {
                MaritimeTrade maritimeTrade = new MaritimeTrade(playerIndex,
                        ratio, give, get);
                proxy.maritimeTrade(maritimeTrade);
            } catch (IOException ex) {
                System.err.println("Error while making trade");
                ex.printStackTrace();
            }
        }
        getTradeOverlay().reset();
        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {
        getTradeOverlay().reset();
        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {
        ModelFacade facade = new ModelFacade();
        Map<ResourceType, Integer> bank = facade.getBank();
        if (bank.get(resource) > 0) {
            getTradeOverlay().selectGetOption(resource, 1);            
            getTradeOverlay().setTradeEnabled(true);
            get = resource;
        }
    }

    @Override
    public void setGiveResource(ResourceType resource) {
        ModelFacade facade = new ModelFacade();
        int localIndex = facade.getLocalPlayerIndex();
        Map<ResourceType, Integer> playerHand = facade.getResources(localIndex);
        Map<PortType, Harbor> harbors = facade.getOwnedHarbors(localIndex);
        PortType portType = PortType.convertToResource(resource);
        if (harbors.containsKey(portType) && playerHand.get(resource) >= 2) {            
            getTradeOverlay().selectGiveOption(resource, 2);
            ratio = 2;
        } else if (harbors.containsKey(PortType.THREE) 
                && playerHand.get(resource) >= 3) {
            getTradeOverlay().selectGiveOption(resource, 3);
            ratio = 3;
        } else if (playerHand.get(resource) >= 4) {
            getTradeOverlay().selectGiveOption(resource, 4);
            ratio = 4;
        }
        give = resource;
        enableGets();
    }

    @Override
    public void unsetGetValue() {
        get = null;
        enableGets();
        getTradeOverlay().setTradeEnabled(false);
    }

    @Override
    public void unsetGiveValue() {
        give = null;
        enableGives();
        getTradeOverlay().setTradeEnabled(false);

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
