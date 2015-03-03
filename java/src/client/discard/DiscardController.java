package client.discard;

import shared.definitions.*;
import client.base.*;
import client.misc.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import shared.exceptions.InvalidPlayerIndex;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.DiscardCards;
import shared.models.Player;

/**
 * Discard controller implementation
 */
public class DiscardController extends Controller 
    implements IDiscardController, Observer {

    private IWaitView waitView;
    private final Map<ResourceType, Integer> discardSet;
    private Map<ResourceType, Integer> hand;
    private boolean discarding;
    private final String discardMessage = "%d/%d";

    /**
     * DiscardController constructor
     *
     * @param view View displayed to let the user select cards to discard
     * @param waitView View displayed to notify the user that they are waiting
     * for other players to discard
     */
    public DiscardController(IDiscardView view, IWaitView waitView) {

        super(view);
        Populator.getInstance().addObserver(this);
        this.waitView = waitView;
        discardSet = new HashMap<>();
        hand = new HashMap<>();
        discardSet.put(ResourceType.BRICK, 0);
        discardSet.put(ResourceType.ORE, 0);
        discardSet.put(ResourceType.SHEEP, 0);
        discardSet.put(ResourceType.WHEAT, 0);
        discardSet.put(ResourceType.WOOD, 0);
        hand.put(ResourceType.BRICK, 0);
        hand.put(ResourceType.ORE, 0);
        hand.put(ResourceType.SHEEP, 0);
        hand.put(ResourceType.WHEAT, 0);
        hand.put(ResourceType.WOOD, 0);
        discarding = false;
        getDiscardView().setDiscardButtonEnabled(false);
    }

    public IDiscardView getDiscardView() {
        return (IDiscardView) super.getView();
    }

    public IWaitView getWaitView() {
        return waitView;
    }

    @Override
    public void increaseAmount(ResourceType resource) {
        if (hand == null || discardSet == null) return;
        IDiscardView view = getDiscardView();
        if (discardSet.containsKey(resource)) {
            int oldVal = discardSet.get(resource);
            if (oldVal < hand.get(resource)) {
                int newVal = oldVal+1;
                discardSet.put(resource, newVal);
                view.setResourceDiscardAmount(resource, newVal);
                if (newVal == hand.get(resource)) {
                    view.setResourceAmountChangeEnabled(resource, false, true);
                } else {
                    view.setResourceAmountChangeEnabled(resource, true, true);
                }
                if (getTotalDiscarding() >= getTotalHand() / 2) {
                    view.setDiscardButtonEnabled(true);
                    capArrows();
                    view.setStateMessage("Discard");
                } else {                
                    String buttonLabel = String.format(discardMessage, 
                            getTotalDiscarding(), getTotalHand()/2);
                    view.setStateMessage(buttonLabel);
                }
            }
        }
    }

    @Override
    public void decreaseAmount(ResourceType resource) {
        if (hand == null || discardSet == null) return;
        IDiscardView view = getDiscardView();
        if (discardSet.containsKey(resource)) {
            int oldVal = discardSet.get(resource);
            if (oldVal > 0) {
                int newVal = oldVal-1;
                discardSet.put(resource, newVal);
                view.setResourceDiscardAmount(resource, newVal);          
                if (newVal == 0) {
                    view.setResourceAmountChangeEnabled(resource, true, false);                
                } else {
                    view.setResourceAmountChangeEnabled(resource, true, true);
                }                
                if (getTotalDiscarding() < getTotalHand() / 2) {
                    view.setDiscardButtonEnabled(false);
                    String buttonLabel = String.format(discardMessage, 
                            getTotalDiscarding(), getTotalHand()/2);
                    uncapArrows();
                    view.setStateMessage(buttonLabel);
                }
            }
        }
    }
    
    private int getTotalHand() {
        if (hand == null) return 0;
        int sum = 0;
        for (Map.Entry<ResourceType, Integer> entry : hand.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
    
    private int getTotalDiscarding() {
        if (discardSet == null) return 0;
        int sum = 0;
        for (Map.Entry<ResourceType, Integer> entry : discardSet.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
    
    private ResourceListDTO makeDiscardSet() {
        int brick = discardSet.get(ResourceType.BRICK);
        int ore = discardSet.get(ResourceType.ORE);
        int sheep = discardSet.get(ResourceType.SHEEP);
        int wheat = discardSet.get(ResourceType.WHEAT);
        int wood = discardSet.get(ResourceType.WOOD);
        
        return new ResourceListDTO(brick, ore, sheep, wheat, wood);
    }

    @Override
    public void discard() {
        try {
            ServerProxy proxy = ServerProxy.getInstance();
            ModelFacade facade = new ModelFacade();           
            ResourceListDTO bundle = makeDiscardSet();
            int playerIndex = facade.getCurrentPlayerIndex();
            DiscardCards discardingSet = new DiscardCards(playerIndex, bundle);
            proxy.discardCards(discardingSet);
            updateDiscarding();
            discarding = false;
            if (getDiscardView().isModalShowing()) {
                getDiscardView().closeModal();
            }
        } catch (IOException ex) {
            System.err.println("Error while tring to discard cards");
        }
    }
    
    private void updateView() {
        if (hand == null) return;
        IDiscardView view = getDiscardView();        
        for (Map.Entry<ResourceType, Integer> entry : hand.entrySet()) {
            view.setResourceMaxAmount(entry.getKey(), entry.getValue());
        }
    }
    
    private void capArrows() {
        if (hand == null || discardSet == null) return;
        IDiscardView view = getDiscardView();
        for (Map.Entry<ResourceType, Integer> entry : hand.entrySet()) {
            if (discardSet.get(entry.getKey()) == 0) {
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        false, false); 
            } else if (entry.getValue() > 0) { 
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        false, true);
            } else {
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        false, false);
            }
        }
    }
    
    private void uncapArrows() {
        if (hand == null || discardSet == null) return;
        IDiscardView view = getDiscardView();
        for (Map.Entry<ResourceType, Integer> entry : hand.entrySet()) {
            int discardVal = discardSet.get(entry.getKey());
            if (entry.getValue() - discardVal > 0 && discardVal > 0) {
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        true, true); 
            } else if (entry.getValue() - discardVal > 0) { 
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        true, false);
            } else if (discardVal > 0) {
                view.setResourceAmountChangeEnabled(entry.getKey(),
                        false, true);
            } else {
                view.setResourceAmountChangeEnabled(entry.getKey(), 
                        false, false);
            }
        }
    }
    
    private void initArrows() {
        if (hand == null) return;
        IDiscardView view = getDiscardView();
        for (Map.Entry<ResourceType, Integer> entry : hand.entrySet()) {
            if (entry.getValue() > 0) {
                view.setResourceAmountChangeEnabled(entry.getKey(), true, false);
            } else {
                view.setResourceAmountChangeEnabled(entry.getKey(), false, false);
            }
        }
    }
    
    private void updateDiscarding() {
        for (Map.Entry<ResourceType, Integer> entry : discardSet.entrySet()) {
            discardSet.put(entry.getKey(), 0);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            try {
                ModelFacade facade = (ModelFacade) arg;
                int localIndex = facade.getLocalPlayerIndex();
                Player localPlayer = facade.getPlayer(localIndex);
                Map<ResourceType, Integer> playerHand = facade.getResources(localIndex);
                hand = playerHand;
                System.out.println(facade.getState());
                if (facade.getState().equals("Discarding") 
                        && this.getTotalHand() > 7
                        && localPlayer.getDiscarded() == false) {
                            if (!discarding) {
                                discarding = true;
                                updateView();
                                initArrows();
                            }
                            if (getDiscardView().isModalShowing() == false) {
                                getDiscardView().showModal();
                            }
                }
            } catch (InvalidPlayerIndex ex) {
               System.out.println("Error while obtaining player index");
               ex.printStackTrace();
            }
        }
    }

}
