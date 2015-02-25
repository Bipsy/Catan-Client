package client.communication;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

import java.util.Observable;
import java.util.Observer;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller 
    implements IChatController, Observer {

    public ChatController(IChatView view) {
        super(view);
        Populator.getInstance().addObserver(this);
    }

    @Override
    public IChatView getView() {
        return (IChatView) super.getView();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
