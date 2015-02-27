package client.communication;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller 
    implements IChatController, Observer {

    public ChatController(IChatView view) {
        super(view);
        Populator.getInstance().addObserver(this);
        initFromModel(null);
    }

    @Override
    public IChatView getView() {
        return (IChatView) super.getView();
    }

    @Override
    public void sendMessage(String message) {

    }
    private void initFromModel(ModelFacade facade) {

        List<LogEntry> entries = new ArrayList<LogEntry>();
        
    	if (facade == null) {
           entries.add(new LogEntry(CatanColor.WHITE, "There are no messages yet"));
    	} else {
	        for (int i=1; i<facade.getChatObject().size(); i++) {
	        	String name = facade.getChatObject().get(i).getSource();
	        	entries.add(new LogEntry(facade.GetPlayerColor(name), facade.getChatObject().get(i).getMessage()));
	        }
    	}
        
        getView().setEntries(entries);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            ModelFacade facade = (ModelFacade) arg;
            initFromModel(facade);
        }
    }

}
