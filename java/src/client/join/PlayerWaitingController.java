package client.join;

import client.base.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.network.ServerProxy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import shared.models.DTO.params.AddAIRequest;

/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

    public PlayerWaitingController(IPlayerWaitingView view) {

        super(view);
    }

    @Override
    public IPlayerWaitingView getView() {

        return (IPlayerWaitingView) super.getView();
    }

    private void setViewPlayers(IPlayerWaitingView view, ServerProxy proxy) {
        try {
            int gameNumber = proxy.getGameNumber();
            List<GameInfo> games = proxy.listGames();
            List<PlayerInfo> players = new ArrayList();
            for (GameInfo game : games) {
                if (game.getId() == gameNumber) {
                    players = game.getPlayers();
                    view.setPlayers((PlayerInfo[]) players.toArray());
                }
            }
            view.setPlayers((PlayerInfo[]) players.toArray());

            getView().showModal();
        } catch (IOException ex) {
            System.out.println("Exception while starting view controller");
            ex.printStackTrace();
        }
    }

    private void setViewAIs(IPlayerWaitingView view, ServerProxy proxy) {
        try {
            List<AddAIRequest> AIs = proxy.listAITypes();
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
    }

    @Override
    public void addAI() {

        // TEMPORARY
        getView().closeModal();
    }

}
