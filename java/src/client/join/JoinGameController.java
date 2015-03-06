package client.join;

import java.io.IOException;
import java.util.List;

import shared.definitions.CatanColor;
import shared.models.DTO.params.CreateGameRequest;
import shared.models.DTO.params.JoinGameRequest;
import client.base.*;
import client.data.*;
import client.misc.*;
import client.network.ServerProxy;
import client.network.UserCookie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController,
        ActionListener {

    private INewGameView newGameView;
    private ISelectColorView selectColorView;
    private IMessageView messageView;
    private IAction joinAction;
    private static ServerProxy proxy = ServerProxy.getInstance();

    private List<GameInfo> games;
    private PlayerInfo localPlayer;
    private GameInfo localGame;
    private Timer pollingTimer;
    private final int MILLIS = 1000;
    private boolean pollingPaused = false;

    /**
     * JoinGameController constructor
     *
     * @param view Join game view
     * @param newGameView New game view
     * @param selectColorView Select color view
     * @param messageView Message view (used to display error messages that
     * occur while the user is joining a game)
     */
    public JoinGameController(IJoinGameView view, INewGameView newGameView,
            ISelectColorView selectColorView, IMessageView messageView) {

        super(view);
      
        setNewGameView(newGameView);
        setSelectColorView(selectColorView);
        setMessageView(messageView); 
        pollingTimer = new Timer(MILLIS, this);
        pollingTimer.setInitialDelay(MILLIS);
    }

    public IJoinGameView getJoinGameView() {

        return (IJoinGameView) super.getView();
    }

    /**
     * Returns the action to be executed when the user joins a game
     *
     * @return The action to be executed when the user joins a game
     */
    public IAction getJoinAction() {

        return joinAction;
    }

    /**
     * Sets the action to be executed when the user joins a game
     *
     * @param value The action to be executed when the user joins a game
     */
    public void setJoinAction(IAction value) {

        joinAction = value;
    }

    public INewGameView getNewGameView() {

        return newGameView;
    }

    public void setNewGameView(INewGameView newGameView) {

        this.newGameView = newGameView;
    }

    public ISelectColorView getSelectColorView() {

        return selectColorView;
    }

    public void setSelectColorView(ISelectColorView selectColorView) {

        this.selectColorView = selectColorView;
    }

    public IMessageView getMessageView() {

        return messageView;
    }

    public void setMessageView(IMessageView messageView) {

        this.messageView = messageView;
    }
    
    private void updateView() {
        try {
            games = proxy.listGames();
            IJoinGameView view = getJoinGameView();
            view.closeModal();
            view.setGames(games.toArray(new GameInfo[games.size()]), localPlayer);
            view.showModal();
        } catch (IOException ex) {
            System.err.println("Error in Starting Game");
            ex.printStackTrace();
        }
    }

    @Override
    public void start() {
        pollingTimer.start();
        pollingPaused = false;
        try {
            UserCookie uCookie = proxy.getUserCookie();

            games = proxy.listGames();

            IJoinGameView view = getJoinGameView();
            CatanColor color = CatanColor.RED;

            localPlayer = new PlayerInfo(uCookie.getPlayerID(), -1, uCookie.getName(), color);
            view.setGames(games.toArray(new GameInfo[games.size()]), localPlayer);
        } catch (IOException e) {
            System.err.println("Error in Starting Game");
        } finally {
            getJoinGameView().showModal();
        }
    }

    private void resetNewGameView() {
        INewGameView view = getNewGameView();
        view.setRandomlyPlaceHexes(false);
        view.setRandomlyPlaceNumbers(false);
        view.setTitle("");
        view.setUseRandomPorts(false);
    }

    @Override
    public void startCreateNewGame() {
        pollingPaused = true;
        getNewGameView().showModal();
    }

    @Override
    public void cancelCreateNewGame() {
        pollingPaused = false;
        getNewGameView().closeModal();
    }

    @Override
    public void createNewGame() {
        Boolean tiles = newGameView.getRandomlyPlaceHexes();
        Boolean nums = newGameView.getRandomlyPlaceNumbers();
        Boolean ports = newGameView.getUseRandomPorts();
        String title = newGameView.getTitle();
        
        if (title.isEmpty()) {
            messageView.setTitle("Create Game Error");
            messageView.setMessage("The name of the game cannot be blank");
            messageView.showModal();
            return;
        }

        JoinGameView view = (JoinGameView) this.getView();

        try {
            GameInfo newGame = proxy.createGames(new CreateGameRequest(tiles,
                    nums, ports, title));
            JoinGameRequest request = new JoinGameRequest(newGame.getId(),
                    localPlayer.getColor().toString().toLowerCase());
            proxy.joinGame(request);
            games = proxy.listGames();
            view.setGames(games.toArray(new GameInfo[games.size()]),
                    localPlayer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            pollingPaused = false;
            getNewGameView().closeModal();
        }
    }

    @Override
    public void startJoinGame(GameInfo game) {
        pollingPaused = true;
        localGame = game;
        List<PlayerInfo> players = game.getPlayers();
        for (PlayerInfo player : players) {
            if (player.getId() == localPlayer.getId()) {
                continue;
            }
            CatanColor playerColor = player.getColor();
            if (playerColor != null) {
                getSelectColorView().setColorEnabled(playerColor, false);
            }
        }
        getSelectColorView().showModal();
    }

    @Override
    public void cancelJoinGame() {
        pollingPaused = false;
        getSelectColorView().closeModal();
    }

    @Override
    public void joinGame(CatanColor color) {
        localPlayer.setColor(color);

        JoinGameRequest request = new JoinGameRequest(localGame.getId(),
                localPlayer.getColor().toString().toLowerCase());
        try {
            proxy.joinGame(request);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        pollingTimer.stop();

        // If join succeeded
        getSelectColorView().closeModal();
        getJoinGameView().closeModal();

        // join game to get cookie
        joinAction.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pollingPaused) {
            System.out.println("Not paused");
            updateView();
        }
    }
    
    

}
