/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import shared.definitions.CatanColor;

/**
 * User class represents a user within a game. This class is essentially just
 * used to transport data. The field of this class are immutable.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class User {

    private CatanColor color = null;
    private String username;
    private String password;
    public CatanColor getColor() {
		return color;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getIndex() {
		return index;
	}

	public int getID() {
		return ID;
	}

	private int index = 0;
    private int ID = 0;

    /**
     * Default Constructor for User. This will generate a user with immutable
     * fields. If invalid parameters are given then system defaults will be
     * used.
     *
     * @param color Specifies the color of the player to be created. If this
     * color is already used then a color that is currently unused by the system
     * will be selected by the system.
     * @param name Specifies the name of the player to be created. Name cannot
     * be empty. If the name is empty then a randomly generated name will be
     * created.
     * @param playerIndex Specifies the index of the player to be created. Index
     * cannot be negative. If a negative value is given then a unique positive
     * index will be generated for use.
     * @param playerID Specifies the ID of the player. ID must be unique. If ID
     * is not unique then a unique ID will be generated.
     */
    public User(CatanColor color, String username, String password,
            int playerIndex, int playerID) {
        this.color = color;
        this.username = username;
        this.password = password;
        this.index = playerIndex;
        this.ID = playerID;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(CatanColor color2, int playerIndex, int playerID) {
        this.color = color2;
        this.index = playerIndex;
        this.ID = playerID;
    }
}
