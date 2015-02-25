package client.roll;

import client.base.*;
import client.model.Populator;
import java.util.Observable;
import java.util.Observer;
import shared.models.ModelFacade;

/**
 * Implementation for the roll controller
 */
public class RollController extends Controller 
    implements IRollController, Observer {

    private IRollResultView resultView;

    /**
     * RollController constructor
     *
     * @param view Roll view
     * @param resultView Roll result view
     */
    public RollController(IRollView view, IRollResultView resultView) {

        super(view);

        setResultView(resultView);
    }

    public IRollResultView getResultView() {
        return resultView;
    }

    public void setResultView(IRollResultView resultView) {
        this.resultView = resultView;
    }

    public IRollView getRollView() {
        return (IRollView) getView();
    }

    @Override
    public void rollDice() {
    	//implement timer
    	//"Rolling automatically in (timer) seconds"
    	//
        getResultView().showModal();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
