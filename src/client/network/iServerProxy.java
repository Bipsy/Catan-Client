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
import shared.models.Game;
import shared.models.GameContainer;
import shared.models.ResourceList;

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
	 * @post the cards are distributed between the players and the model updated
	 * @param willAccept
	 * @throws IOException
	 */
	void acceptTrade(boolean willAccept) throws IOException;
	
	/**
	 * Sends a request to the server to discard cards from the players ResourceList
	 * @pre the player has the cards to discard
	 * @post the cards are no longer within the player's ResourceList and the model updated
	 * @param discardedCards
	 * @throws IOException
	 */
	void discardCards(ResourceList discardedCards) throws IOException; //ResourceList
	
	/**
	 * Sends a request to the server to roll a number
	 * @pre it is the beginning of the players turn
	 * @pre it is the correct player rolling the dice
	 * @post a number between 2-12 was randomly selected and the model updated
	 */
	int rollNumber();
	
	/**
	 * Sends a reuqest to the server to build a road
	 * @pre the player has the resources to build a road
	 * @pre the location is valid to build a road
	 * @post a road is built and the model updated
	 * @param free
	 * @param roadLocation
	 * @throws IOException
	 */
	void buildRoad(boolean free, EdgeLocation roadLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a settlement
	 * @pre player has the resources to build a settlement
	 * @pre this is a valid location to build a settlement
	 * @post a settlement is built and the model updated
	 * @param free
	 * @param vertexLocation
	 * @throws IOException
	 */
	void buildSettlement(boolean free, VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a city
	 * @pre the player has the resources to build a city
	 * @pre there is a settlement at the location to be upgraded to a city
	 * @post a settlement is returned to the player and a city replaces it and the model updated
	 * @param vertexLocation
	 * @throws IOException
	 */
	void buildCity(VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to offer a trade
	 * @pre It is the players turn or the player is offering to the player whose turn it currently is
	 * @pre the player has the resources they are offering
	 * @post the player is notified with the resources up for trade
	 * @param offer
	 * @param reciever
	 * @throws IOException
	 */
	void offerTrade(ResourceList offer, int reciever) throws IOException;
	void maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException;
	void robPlayer(HexLocation location, int victimIndex) throws IOException; //
	void finishTurn() throws IOException;
	void buyDevCard() throws IOException;
	void playSoldier(HexLocation location, int victimIndex) throws IOException;
	void playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IOException;
	void playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IOException;
	void playMonopoly(ResourceType resource) throws IOException;
	void playMonument() throws IOException;
	
	void login(String username, String password) throws IOException;
	void registerNewUser(String username, String password) throws IOException;
	GameContainer listGames() throws IOException; 
	Game createGames(String name, int randomTiles, int randomNumbers, int randomPorts) throws IOException;
	void joinGame(int gameId, CatanColor color) throws IOException;
	void saveGames(int gameId, String fileName) throws IOException;
	void loadGame() throws IOException;
	void retrieveCurrentState(int versionNumber) throws IOException;
	void resetGame() throws IOException;
	void getCommands() throws IOException;
	void postGameCommands() throws IOException;
	void listAITypes() throws IOException;
	void addAIPlayer() throws IOException;
	void changeLogLevel(int logLevel) throws IOException;	
}
