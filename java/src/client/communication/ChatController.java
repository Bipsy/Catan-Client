package client.communication;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.models.DTO.params.SendChat;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller 
    implements IChatController, Observer {

	ServerProxy proxy = ServerProxy.getInstance();
	private int localIndex;

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
    	try {
    		SendChat chat = new SendChat(localIndex, message);
			proxy.sendChat(chat);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void initFromModel(ModelFacade facade) {
    	
        List<LogEntry> entries = new ArrayList<LogEntry>();
        
    	if (facade != null) {
    		if (facade.getLocalPlayerIndex() != null) {
	        	localIndex = facade.getLocalPlayerIndex();
    		}
	        for (int i=0; i<facade.getChatObject().size(); i++) {
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
