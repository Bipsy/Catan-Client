package shared.models;

/**
 * This class stores information about a game.
 *
 * @author Austin
 *
 */
public class Game {

    private String title;
    private int id;
    private User[] players;
    private boolean randomTiles;
    private boolean randomNumbers;
    private boolean randomPorts;

    public Game(String title, boolean randomTiles,
            boolean randomNumbers, boolean randomPorts) {
        super();
        this.title = title;
        this.randomTiles = randomTiles;
        this.randomNumbers = randomNumbers;
        this.randomPorts = randomPorts;
    }

    public Game() {
        // TODO Auto-generated constructor stub
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User[] getPlayers() {
        return players;
    }

    public void setPlayers(User[] players) {
        this.players = players;
    }

    public boolean isRandomTiles() {
        return randomTiles;
    }

    public void setRandomTiles(boolean randomTiles) {
        this.randomTiles = randomTiles;
    }

    public boolean isRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(boolean randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public boolean isRandomPorts() {
        return randomPorts;
    }

    public void setRandomPorts(boolean randomPorts) {
        this.randomPorts = randomPorts;
    }

    /**
     * This function will look at the current array of Players to determine if
     * another player can be added.
     *
     * @pre <code>players</code> array needs to be initialized
     * @return If there are less than four players, this function will return
     * true. Otherwise, it will return false.
     */
    public boolean canAddPlayer() {
        return false;
    }

    /**
     * This function will be used to add a player to a game.
     *
     * @pre <code>canAddPlayer()</code> must return true;
     * @post If a player can be added, the players container will include the
     * new player
     * @param player the new <code>User</code> object to be added
     */
    public void addPlayer(User player) {

    }
}
