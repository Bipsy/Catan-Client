package client.join;

import client.base.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.main.Catan;
import client.network.ServerProxy;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shared.models.DTO.params.AddAIRequest;

/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {
	
    private ServerProxy proxy;
    private IPlayerWaitingView view;

    public PlayerWaitingController(IPlayerWaitingView view) {

        super(view);
    }

    @Override
    public IPlayerWaitingView getView() {

        return (IPlayerWaitingView) super.getView();
    }

    private int setViewPlayers(IPlayerWaitingView view, ServerProxy proxy) {
        int numPlayers = 0;
    	try {
            int gameNumber = proxy.getGameNumber();
            List<GameInfo> games = proxy.listGames();
            for (GameInfo game : games) {
                if (game.getId() == gameNumber) {
                    List<PlayerInfo> players = game.getPlayers();
                    numPlayers = players.size();
                    view.setPlayers(players.toArray(new PlayerInfo[players.size()]));
                    break;
                }
            }
        } catch (IOException ex) {
            System.err.println("Exception while starting view controller");
            ex.printStackTrace();
        }
    	return numPlayers;
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
        this.proxy = ServerProxy.getInstance();
        this.view = getView();
        int numPlayers = setViewPlayers(view, proxy);
        setViewAIs(view, proxy);
        view.showModal();
        if (numPlayers < 4) {
            final Timer timer = new Timer();
            final WaitForPlayersTask waiting = new WaitForPlayersTask(this, proxy, timer);
            timer.schedule(waiting, 0, 2000);
        } else if (numPlayers == 4) {
            view.closeModal();
            Catan.startPoller(2000);
        }
    }
    

    @Override
    public void addAI() {
    	/*
    	AddAIRequest player = new AddAIRequest(view.getSelectedAI());
    	try {
			proxy.addAIPlayer(player);
		} catch (IOException e) {
			System.err.println("Error creating AIPlayer");
			e.printStackTrace();
		}
		*/
    	
    }

    class WaitForPlayersTask extends TimerTask {
    	private PlayerWaitingController parent;
    	private ServerProxy proxy;
    	private Timer timer;
    	private int players = 0;
    	
    	public WaitForPlayersTask(PlayerWaitingController parent, ServerProxy proxy, Timer timer) {
            this.parent = parent;
            this.proxy = proxy;
            this.timer = timer;
    	}
    	
    	@Override
    	public void run() {
            if (players < 4) {
                players = parent.setViewPlayers(parent.getView(), proxy);
                parent.getView().showModal();
            } else {
                parent.getView().closeModal();
                Catan.startPoller(2000);
                timer.cancel();
                timer.purge();
            }
    	}
    	
    }
}
