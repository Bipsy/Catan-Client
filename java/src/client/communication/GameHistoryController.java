package client.communication;

import java.util.*;
import java.util.List;

import client.base.*;
import client.model.Populator;
import shared.definitions.*;
import shared.models.ModelFacade;

/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller 
    implements IGameHistoryController, Observer {

    public GameHistoryController(IGameHistoryView view) {

        super(view);
        Populator.getInstance().addObserver(this);
        initFromModel();
    }

    @Override
    public IGameHistoryView getView() {

        return (IGameHistoryView) super.getView();
    }

    private void initFromModel() {

        //<temp>
        List<LogEntry> entries = new ArrayList<LogEntry>();
        entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
        entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
        entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
        entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
        entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
        entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
        entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
        entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));

        getView().setEntries(entries);

        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
