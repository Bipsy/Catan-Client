package client.communication;

import java.util.*;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller 
    implements IGameHistoryController, Observer {

    public GameHistoryController(IGameHistoryView view) {
        super(view);
        Populator.getInstance().addObserver(this);
        initFromModel(null);
    }

    @Override
    public IGameHistoryView getView() {
        return (IGameHistoryView) super.getView();
    }

    private void initFromModel(ModelFacade facade) {

        List<LogEntry> entries = new ArrayList<LogEntry>();
        
        if (facade != null) {
	        for (int i=0; i<facade.getLogObject().size(); i++) {
	        	String name = facade.getLogObject().get(i).getSource();
	        	entries.add(new LogEntry(facade.GetPlayerColor(name), facade.getLogObject().get(i).getMessage()));
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
