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
import shared.models.*;

/**
 *
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public interface iServerProxy {
    
	/**
	 * Sends a request to the server to send a chat
	 * @post the chat contains the message or an error message
	 * @param content
	 * @throws IOException
	 */
	String sendChat(String content) throws IOException;
	
	/**
	 * Sends request to server to accept a trade with another player. 
	 * @pre player has the ability to trade, whether on his turn or during the trader's turn.
	 * @pre player has the resources to trade
	 * @post the cards are distributed between the players and the model is updated if accepted
	 * @post the trade offer is discarded if not accepted or an error message
	 * @param willAccept
	 * @throws IOException
	 */
	String acceptTrade(boolean willAccept) throws IOException;
	
	/**
	 * Sends a request to the server to discard cards from the players ResourceList
	 * @pre the player has over 7 cards
	 * @pre the status is discarding and the player has chosen cards to discard
	 * @post the cards are no longer within the player's ResourceList and the model is updated or an error message
	 * @param discardedCards
	 * @throws IOException
	 */
	String discardCards(ResourceList discardedCards) throws IOException; //ResourceList
	
	/**
	 * Sends a request to the server to roll a number
	 * @pre it is the beginning of the players turn
	 * @post a number between 2-12 was randomly selected and the model is updated or an error message
	 */
	String rollNumber();
	
	/**
	 * Sends a request to the server to build a road
	 * @pre the player has the resources to build a road
	 * @pre the location is valid to build a road
	 * @post a road is built and the model is updated or an error message
	 * @param free
	 * @param roadLocation
	 * @throws IOException
	 */
	String buildRoad(boolean free, EdgeLocation roadLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a settlement
	 * @pre player has the resources to build a settlement
	 * @pre this is a valid location to build a settlement
	 * @post a settlement is built and the model is updated or an error message
	 * @param free
	 * @param vertexLocation
	 * @throws IOException
	 */
	String buildSettlement(boolean free, VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to build a city
	 * @pre the player has the resources to build a city
	 * @pre there is a settlement at the location to be upgraded to a city
	 * @post a settlement is returned to the player and a city replaces it and the model is updated or an error message
	 * @param vertexLocation
	 * @throws IOException
	 */
	String buildCity(VertexLocation vertexLocation) throws IOException;
	
	/**
	 * Sends a request to the server to offer a trade
	 * @pre It is the players turn or the player is offering to the player whose turn it currently is
	 * @pre the player has the resources they are offering
	 * @post the player is notified with the resources up for trade and the model is updated or an error message
	 * @param tradeOffer the information about the offer
	 * @throws IOException
	 */
	String offerTrade(TradeOffer tradeOffer) throws IOException;
	
	/**
	 * Sends a request to the server to offer a maritimetrade
	 * @pre the player has a settlement on a port
	 * @pre the player has the necessary resources
	 * @post the player's resources are discarded and the player is given the traded resources and the model is updated or an error message
	 * @param ratio
	 * @param inputResource
	 * @param outputResource
	 * @throws IOException
	 */
	String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException;
	
	/**
	 * Sends a request to the server to rob a player
	 * @pre a 7 was rolled
	 * @pre the robber was moved to the valid location and the resources were stolen
	 * @post a random resource is taken from the robbed player and the model is updated or an error message
	 * @param location
	 * @param victimIndex
	 * @throws IOException
	 */
	String robPlayer(HexLocation location, int victimIndex) throws IOException;
	
	/**
	 * Sends a request to the server to end a turn
	 * @post the turn tracker is incremented to the next player and the model is updated or an error message
	 * @throws IOException
	 */
	String finishTurn() throws IOException;
	
	/**
	 * Sends a request to the server to buy a development card
	 * @pre the player has the resources to buy the development card
	 * @pre there are remaining development cards to be given
	 * @post the development card is given to the player and the model is updated or an error message
	 * @throws IOException
	 */
	String buyDevCard() throws IOException;
	
	/**
	 * Sends a request to the server to play a soldier
	 * @pre the player has a soldier in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post the robber is moved and a resource stolen from an appropriate player and the model is updated or an error message
	 * @param location
	 * @param victimIndex
	 * @throws IOException
	 */
	String playSoldier(HexLocation location, int victimIndex) throws IOException;
	
	/**
	 * Sends a request to the server to play the Year of Plenty development card
	 * @pre the player has a Year of Plenty in his collection of development cards
	 * @pre the player has not already played a development card
	 * @pre there are the two resources in the bank
	 * @post the card is discarded
	 * @post two chosen resources are given to the appropriate player and the model is updated or an error message
	 * @param resource1
	 * @param resource2
	 * @throws IOException
	 */
	String playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IOException;
	
	/**
	 * Sends a request to the server to play a Road Building development card
	 * @pre the player has a Road Building card in his collection of development cards
	 * @pre the player has not already played a development card
	 * @pre the player has the roads to build
	 * @post the card is discarded
	 * @post two roads are built and the model is updated or an error message
	 * @param spot1
	 * @param spot2
	 * @throws IOException
	 */
	String playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IOException;
	
	/**
	 * Sends a request to the server to play the monopoly development card
	 * @pre the player has a monopoly card in his collection of development cards
	 * @pre the player has not already played a development card
	 * @post the card is discarded
	 * @post the selected resource is taken from the every player and given to the current player and the model is updated or an error message
	 * @param resource
	 * @throws IOException
	 */
	String playMonopoly(ResourceType resource) throws IOException;
	
	/**
	 * Sends a request to the server to play the monument card
	 * @pre the player has a monument card in his collection of development cards
	 * @pre the player has enough to win the game
	 * @post the players victory points are incremented and the model is updated or an error message
	 * @throws IOException
	 */
	String playMonument() throws IOException;
	
	
	/**
	 * Sends a request to the server to login
	 * @pre valid username
	 * @pre valid password
	 * @post the player is logged in and assigned a color and the model is updated or an error message
	 * @param username
	 * @param password
	 * @return user 
	 * @throws IOException
	 */
	User login(String username, String password) throws IOException;
	
	/**
	 * Sends a request to the server to register a new user
	 * @pre valid username
	 * @pre valid password
	 * @post user created and the model is updated or an error message
	 * @param username
	 * @param password
	 * @return user 
	 * @throws IOException
	 */
	User registerNewUser(String username, String password) throws IOException;
	
	/**
	 * Sends a request to the server to list the games
	 * @post retrieves a list of games or an error message
	 * @return GameContainer
	 * @throws IOException
	 */
	GameContainer listGames() throws IOException; 
	
	/**
	 * Sends a request to the server to create a game
	 * @pre valid game name
	 * @pre tiles, numbers and ports have valid numbers
	 * @post a map is created with random tiles, numbers, and ports and the model is updated or an error message
	 * @param name
	 * @param randomTiles
	 * @param randomNumbers
	 * @param randomPorts
	 * @return
	 * @throws IOException
	 */
	String createGames(String name, int randomTiles, int randomNumbers, int randomPorts) throws IOException;
	
	/**
	 * Sends a requesto to the server to join a game
	 * @pre valid user that has logged in
	 * @pre game to join with valid id
	 * @pre valid color to be assigned
	 * @post a player is added to the game with the specified color and the model is updated or an error message
	 * @param gameId
	 * @param color
	 * @throws IOException
	 */
	String joinGame(int gameId, CatanColor color) throws IOException;
	
	/**
	 * Sends a request to the server to save a game
	 * @pre game name is valid
	 * @pre file name is valid
	 * @post the game is saved and the model updated or an error message
	 * @param gameId
	 * @param fileName
	 * @throws IOException
	 */
	String saveGames(int gameId, String fileName) throws IOException;
	
	/**
	 * Sends a request to the server to load a game
	 * @pre there is a version of the game previously saved
	 * @post a game is loaded and displayed and the model updated or an error message
	 * @throws IOException
	 */
	String loadGame() throws IOException;
	
	/**
	 * Sends a request to the server to retrieve the current state of the game
	 * @pre there is a valid game
	 * @pre the version number is valid
	 * @post if necessary, the model is updated or an error message
	 * @param versionNumber
	 * @throws IOException
	 */
	String retrieveCurrentState(int versionNumber) throws IOException;
	
	/**
	 * Sends a request to the server to reset a game
	 * @pre a valid game and user logged in
	 * @post the game is rest to the initiation stage while keeping the players intact or an error message
	 * @throws IOException
	 */
	String resetGame() throws IOException;
	
	/**
	 * Sends a request to the server to get a list of the game commands
	 * @pre a user is logged in and has joined a game
	 * @post a list of commands is retrieved or an error message
	 * @throws IOException
	 */
	String getCommands() throws IOException;
	
	/**
	 * Sends a request to server with a list of the game commands
	 * @pre a user is logged in and has joined a game
	 * @pre 
	 * @post a list of commands is sent to the server or an error message
	 * @throws IOException 
	 */
	String postGameCommands(CommandContainer commands) throws IOException;
	
	/**
	 * Sends a request to the server to list all Artificial intelligence types
	 * @post retrieves a list of ai players or an error message
	 * @throws IOException
	 */
	String listAITypes() throws IOException;
	
	/**
	 * Sends a request to the server to add an artificial intelligence player
	 * @pre user is logged in and joined a game
	 * @pre the game is not full
	 * @pre the AIType is valid
	 * @post an AIPlayer is added to the game and given a color and the model is updated or an error message
	 * @throws IOException
	 */
	String addAIPlayer(AIPlayer player) throws IOException;
	
	/**
	 * Sends a request to server to change the log level
	 * @pre the logging level is valid
	 * @post the server begins using logging level or an error message
	 * @param logLevel
	 * @throws IOException
	 */
	String changeLogLevel(int logLevel) throws IOException;
}
