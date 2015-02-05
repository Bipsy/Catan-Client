package shared.models.DTO;

import java.util.ArrayList;
import java.util.List;

import shared.models.Game;

/**
 * This model contains the list of games that are currently running.
 *
 * @author Austin
 *
 */
public class GameContainerDTO {

    private List<Game> games;

    public GameContainerDTO() {
        setGames(new ArrayList<Game>());
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    /**
     * This function will add a game to the collection of games.
     *
     * @pre the game's title cannot be null, and the random flags need to be set
     * @param game the game to be added to the container
     * @return if a game does not meet the pre conditions, it will return false,
     * otherwise, it will return true.
     */
    public boolean addGame(Game game) {
        return false;
    }
}
