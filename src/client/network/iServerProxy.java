/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.IOException;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public interface iServerProxy {
    
	/**
	 * 
	 * @param content
	 * @throws IOException
	 */
	void sendChat(String content) throws IOException;
	
	/**
	 * Sends request to server to accept a trade with another player. 
	 * @pre player has the ability to trade, whether on his turn or during the trader's turn.
	 * @pre player has the resources to trade
	 * @post the cards are distributed between the players and the model is updated
	 * @param willAccept
	 * @throws IOException
	 */
	void acceptTrade(boolean willAccept) throws IOException;
	
	/**
	 * Sends a request to the server to discard cards from the players ResourceList
	 * @pre the player has the cards to discard
	 * @post the cards are no longer within the player's ResourceList and the model is updated
	 * @param discardedCards
	 * @throws IOException
	 */
	void discardCards(ResourceList discardedCards) throws IOException; //ResourceList
	
	/**
	 * Sends a request to the server to roll a number
	 * @pre it is the beginning of the players turn
	 * @pre it is the correct player rolling the dice
	 * @post a number between 2-12 was randomly selected and the model is updated
	 */
	int rollNumber();
	
	/**
	 * Sends a reuqest to the server to build a road
	 * @pre the player has the resources to build a road
	 * @pre the location is valid to build a road
	 * @post a road is built and the model is updated
	 * @param free
	 * @param roadLocation
	 * @throws IOException
	 */
	void buildRoad(boolean free, EdgeLocation roadLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a settlement
	 * @pre player has the resources to build a settlement
	 * @pre this is a valid location to build a settlement
	 * @post a settlement is built and the model is updated
	 * @param free
	 * @param vertexLocation
	 * @throws IOException
	 */
	void buildSettlement(boolean free, VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a city
	 * @pre the player has the resources to build a city
	 * @pre there is a settlement at the location to be upgraded to a city
	 * @post a settlement is returned to the player and a city replaces it and the model is updated
	 * @param vertexLocation
	 * @throws IOException
	 */
	void buildCity(VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to offer a trade
	 * @pre It is the players turn or the player is offering to the player whose turn it currently is
	 * @pre the player has the resources they are offering
	 * @post the player is notified with the resources up for trade and the model is updated
	 * @param offer
	 * @param reciever
	 * @throws IOException
	 */
	void offerTrade(ResourceList offer, int reciever) throws IOException;
	
	/**
	 * Sends a request to the server to offer a maritimetrade
	 * @pre the player has a settlement on a port
	 * @pre the player has the necessary resources
	 * @post the player's resources are discarded and the player is given the traded resources and the model is updated
	 * @param ratio
	 * @param inputResource
	 * @param outputResource
	 * @throws IOException
	 */
	void maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException;
	
	/**
	 * Sends a request to the server to rob a player
	 * @pre a 7 was rolled
	 * @pre the robber was moved to the valid location
	 * @post a random resource is taken from the robbed player and the model is updated
	 * @param location
	 * @param victimIndex
	 * @throws IOException
	 */
	void robPlayer(HexLocation location, int victimIndex) throws IOException;
	
	/**
	 * Sends a request to the server to end a turn
	 * @pre it is the current player's turn
	 * @post the turn tracker is incremented to the next player and the model is updated
	 * @throws IOException
	 */
	void finishTurn() throws IOException;
	
	/**
	 * Sends a request to the server to buy a development card
	 * @pre the player has the resources to buy the development card
	 * @pre there are remaining development cards to be given
	 * @post the development card is given to the player and the model is updated
	 * @throws IOException
	 */
	void buyDevCard() throws IOException;
	
	/**
	 * Sends a request to the server to play a soldier
	 * @pre the player has a soldier in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post the robber is moved and a resource stolen from an appropriate player and the model is updated
	 * @param location
	 * @param victimIndex
	 * @throws IOException
	 */
	void playSoldier(HexLocation location, int victimIndex) throws IOException;
	
	/**
	 * Sends a request to the server to play the Year of Plenty development card
	 * @pre the player has a Year of Plenty in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post two chosen resources are given to the appropriate player and the model is updated
	 * @param resource1
	 * @param resource2
	 * @throws IOException
	 */
	void playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IOException;
	
	/**
	 * Sends a request to the server to play a Road Building development card
	 * @pre the player has a Road Building card in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post two roads are built and the model is updated
	 * @param spot1
	 * @param spot2
	 * @throws IOException
	 */
	void playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IOException;
	
	/**
	 * Sends a request to the server to play the monopoly development card
	 * @pre the player has a monopoly card in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post the selected resource is taken from the every player and given to the current player and the model is updated
	 * @param resource
	 * @throws IOException
	 */
	void playMonopoly(ResourceType resource) throws IOException;
	
	/**
	 * Sends a request to the server to play the monument card
	 * @pre the player has a monument card in his collection of development cards
	 * @post the players token count is incremented and the model is updated
	 * @throws IOException
	 */
	void playMonument() throws IOException;
	
	
	/**
	 * Sends a request to the server to login
	 * @pre valid username
	 * @pre valid password
	 * @post the player is logged in and assigned a color and the model is updated
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	void login(String username, String password) throws IOException;
	
	/**
	 * Sends a request to the server to register a new user
	 * @pre valid username
	 * @pre valid password
	 * @post user created and the model is updated
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	void registerNewUser(String username, String password) throws IOException;
	
	/**
	 * Sends a request to the server to list the games
	 * @pre there is at least one game
	 * @return GameContainer
	 * @throws IOException
	 */
	GameContainer listGames() throws IOException; 
	
	/**
	 * Sends a request to the server to create a game
	 * @pre valid game name
	 * @post a map is created with random tiles, numbers, and ports and the model is updated
	 * @param name
	 * @param randomTiles
	 * @param randomNumbers
	 * @param randomPorts
	 * @return
	 * @throws IOException
	 */
	Game createGames(String name, int randomTiles, int randomNumbers, int randomPorts) throws IOException;
	
	/**
	 * Sends a requesto to the server to join a game
	 * @pre valid user
	 * @pre game to join
	 * @post a player is added to the game and the model is updated
	 * @param gameId
	 * @param color
	 * @throws IOException
	 */
	void joinGame(int gameId, CatanColor color) throws IOException;
	
	/**
	 * Sends a request to the server to save a game
	 * @pre there is a game to save
	 * @post the game is saved
	 * @param gameId
	 * @param fileName
	 * @throws IOException
	 */
	void saveGames(int gameId, String fileName) throws IOException;
	
	/**
	 * Sends a request to the server to load a game
	 * @pre there is a game in the list of games
	 * @post a game is loaded and displayed
	 * @throws IOException
	 */
	void loadGame() throws IOException;
	
	/**
	 * Sends a request to the server to retrieve the current state of the game
	 * @pre there is a valid game
	 * @post if necessary, the model is updated
	 * @param versionNumber
	 * @throws IOException
	 */
	void retrieveCurrentState(int versionNumber) throws IOException;
	
	/**
	 * Sends a request to the server to reset a game
	 * @pre a valid game
	 * @post the game is rest to the initiation stage
	 * @throws IOException
	 */
	void resetGame() throws IOException;
	
	/**
	 * Sends a request to the server to get a list of the game commands
	 * @pre a user is logged in and has joined a game
	 * @post a list of commands is retrieved
	 * @throws IOException
	 */
	void getCommands() throws IOException;
	
	/**
	 * Sends a request to server to get a list of the post game commands
	 * @pre a user is logged in and has joined a game
	 * @post a list of commands is retrieved
	 * @throws IOException
	 */
	void postGameCommands() throws IOException;
	
	/**
	 * Sends a request to the server to list all Artificial intelligence types
	 * @post retrieves a list of ai players
	 * @throws IOException
	 */
	void listAITypes() throws IOException;
	
	/**
	 * Sends a request to the server to add an artificial intelligence player
	 * @pre user is logged in and joined a game
	 * @pre the game is not full
	 * @pre the AIType is valid
	 * @post an AIPlayer is added to the game and given a color and the model is updated
	 * @throws IOException
	 */
	void addAIPlayer() throws IOException;
	
	/**
	 * Sends a request to server to change the log level
	 * @pre the logging level is valid
	 * @post the server begins using logging level
	 * @param logLevel
	 * @throws IOException
	 */
	void changeLogLevel(int logLevel) throws IOException;
	
	
}
