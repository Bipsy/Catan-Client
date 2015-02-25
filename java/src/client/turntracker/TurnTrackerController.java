package client.turntracker;

import shared.definitions.CatanColor;
import client.base.*;
import client.model.Populator;
import java.util.Observable;
import java.util.Observer;
import shared.models.ModelFacade;

/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller 
    implements ITurnTrackerController, Observer {

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {

    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
