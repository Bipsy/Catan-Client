package client.join;

import java.io.IOException;
import java.util.List;

import shared.definitions.CatanColor;
import shared.models.DTO.GameContainerDTO;
import client.base.*;
import client.data.*;
import client.misc.*;
import client.network.ServerProxy;

/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

    private INewGameView newGameView;
    private ISelectColorView selectColorView;
    private IMessageView messageView;
    private IAction joinAction;

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

    @Override
    public void start() {
<<<<<<< HEAD
        getJoinGameView().showModal();
=======
    	try {
    		ServerProxy proxy = new ServerProxy();

			GameInfo[] games = (GameInfo[]) proxy.listGames().toArray();

			JoinGameView view = (JoinGameView) this.getView();

			//TOD: proxy.getCookie()
			view.setGames(games, new PlayerInfo());
		} catch (IOException e) {
			
		} finally {
			getJoinGameView().showModal();
		}
>>>>>>> 64705fd51c4d15fd7b04cb25ef1a6be484dddf79
    }

    @Override
    public void startCreateNewGame() {

        getNewGameView().showModal();
    }

    @Override
    public void cancelCreateNewGame() {

        getNewGameView().closeModal();
    }

    @Override
    public void createNewGame() {

        getNewGameView().closeModal();
    }

    @Override
    public void startJoinGame(GameInfo game) {

        getSelectColorView().showModal();
    }

    @Override
    public void cancelJoinGame() {

        getJoinGameView().closeModal();
    }

    @Override
    public void joinGame(CatanColor color) {

        // If join succeeded
        getSelectColorView().closeModal();
        getJoinGameView().closeModal();
        joinAction.execute();
    }

}
