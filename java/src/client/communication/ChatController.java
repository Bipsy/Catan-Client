package client.communication;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.models.DTO.params.SendChat;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller 
    implements IChatController, Observer {

	ServerProxy proxy = ServerProxy.getInstance();

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
    	SendChat chat = new SendChat(message);
    	proxy.sendChat(chat);
    }
    private void initFromModel(ModelFacade facade) {
    	System.out.println("DO I EVEN MAKE IT HERE?");
    	
        List<LogEntry> entries = new ArrayList<LogEntry>();
        
    	if (facade == null) {
           entries.add(new LogEntry(CatanColor.WHITE, "There are no messages yet"));
    	} else {
	        for (int i=1; i<facade.getChatObject().size(); i++) {
	        	System.out.println("ChatLog size: " + facade.getChatObject().size());
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
