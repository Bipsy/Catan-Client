package client.data;

import shared.definitions.*;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 *
 */
public class PlayerInfo {

    private int id;
    private int playerIndex;
    private String name;
    private CatanColor color;

    public PlayerInfo(int id, int index, String name, CatanColor color) {
        this.id = id;
        this.playerIndex = index;
        this.name = name;
        this.color = color;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PlayerID: " + id + "\n");
        builder.append("Player Index: " + playerIndex + "\n");
        builder.append("Player Name: " + name + "\n");
        builder.append("Player Color: " + color + "\n");
        return builder.toString();
    }

    public PlayerInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatanColor getColor() {
        return color;
    }

    public void setColor(CatanColor color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return 31 * this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerInfo other = (PlayerInfo) obj;
//        System.out.println("this id: " + this.id +
//                " other id " + other.id);
        return this.id == other.id;
    }
}
