package client.join;

import client.base.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.model.Populator;
import client.network.ServerProxy;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import shared.models.ModelFacade;

/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller 
    implements IPlayerWaitingController, Observer {

    public PlayerWaitingController(IPlayerWaitingView view) {
        super(view);
        Populator.getInstance().addObserver(this);
    }

    @Override
    public IPlayerWaitingView getView() {

        return (IPlayerWaitingView) super.getView();
    }

    private void setViewPlayers(IPlayerWaitingView view, ServerProxy proxy) {
        try {
            int gameNumber = proxy.getGameNumber();
            System.out.println("Game ID: " + gameNumber);
            List<GameInfo> games = proxy.listGames();
            for (GameInfo game : games) {
                if (game.getId() == gameNumber) {
                    List<PlayerInfo> players = game.getPlayers();
                    for (int i = 0; i < players.size(); i++) {
						System.out.println(players.get(i).getName() + players.get(i).getColor());
					}
                    view.setPlayers(players.toArray(new PlayerInfo[players.size()]));
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Exception while starting view controller");
            ex.printStackTrace();
        }
    }

    private void setViewAIs(IPlayerWaitingView view, ServerProxy proxy) {
        try {
            List<String> AIs = proxy.listAITypes();
            String[] AITypes = new String[AIs.size()];
            for (int i = 0; i < AIs.size(); i++) {
                AITypes[i] = AIs.get(i).toString();
            }
            view.setAIChoices(AITypes);
        } catch (IOException ex) {
            System.err.println("Exception occurred while setting AIs in view");
            ex.printStackTrace();
        }
    }

    @Override
    public void start() {
        ServerProxy proxy = ServerProxy.getInstance();
        IPlayerWaitingView view = getView();
        setViewPlayers(view, proxy);
        setViewAIs(view, proxy);
        view.showModal();
    }

    @Override
    public void addAI() {
    }
    
        @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
            
        }
    }

}
