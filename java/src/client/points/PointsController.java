package client.points;

import client.base.*;
import client.model.Populator;
import java.util.Observable;
import java.util.Observer;
import shared.models.ModelFacade;

/**
 * Implementation for the points controller
 */
public class PointsController extends Controller 
    implements IPointsController, Observer {

    private IGameFinishedView finishedView;

    /**
     * PointsController constructor
     *
     * @param view Points view
     * @param finishedView Game finished view, which is displayed when the game
     * is over
     */
    public PointsController(IPointsView view, IGameFinishedView finishedView) {

        super(view);
        Populator.getInstance().addObserver(this);
        setFinishedView(finishedView);

        initFromModel();
    }

    public IPointsView getPointsView() {

        return (IPointsView) super.getView();
    }

    public IGameFinishedView getFinishedView() {
        return finishedView;
    }

    public void setFinishedView(IGameFinishedView finishedView) {
        this.finishedView = finishedView;
    }

    private void initFromModel() {
        //<temp>		
        getPointsView().setPoints(5);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
