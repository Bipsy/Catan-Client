package client.points;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;

import java.util.Observable;
import java.util.Observer;

import shared.exceptions.InvalidPlayerIndex;
import shared.models.Player;

/**
 * Implementation for the points controller
 */
public class PointsController extends Controller 
    implements IPointsController, Observer {

    private IGameFinishedView finishedView;
    
    private ModelFacade facade;

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
    	if(facade == null || !facade.hasModel())
    		return;
    	int winnerIndex = facade.getWinner();
    	if(winnerIndex != -1) {
    		Player winner;
			try {
				winner = facade.getPlayer(winnerIndex);
				finishedView.setWinner(winner.getUsername(), facade.getLocalPlayerIndex() == winnerIndex);
				finishedView.showModal();
			} catch (InvalidPlayerIndex e) {
				System.err.println(e.toString());
			}
    	}
    	int points = 0;
    	try {
			Player player = facade.getPlayer(facade.getLocalPlayerIndex());
			points = player.getVictoryPoints();
		} catch (InvalidPlayerIndex e) {
			System.err.println(e.toString());
		}
        getPointsView().setPoints(points);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            facade = (ModelFacade)arg;
            initFromModel();
        }
    }

}
