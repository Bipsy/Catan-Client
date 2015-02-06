/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.IOException;
import java.util.List;

import shared.models.DTO.ClientModelDTO;
import shared.models.DTO.params.AcceptTrade;
import shared.models.DTO.params.*;

/**
 *
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public interface iServerProxy {

    /**
     * Sends a request to the server to send a chat
     *
     * @post the chat contains the message or an error message
     * @param content
     * @throws IOException
     */
    ClientModelDTO sendChat(SendChat message) throws IOException;

    /**
     * Sends request to server to accept a trade with another player.
     *
     * @pre player has the ability to trade, whether on his turn or during the
     * trader's turn.
     * @pre player has the resources to trade
     * @post the cards are distributed between the players and the model is
     * updated if accepted
     * @post the trade offer is discarded if not accepted or an error message
     * @param willAccept
     * @throws IOException
     */
    ClientModelDTO acceptTrade(AcceptTrade accept) throws IOException;

    /**
     * Sends a request to the server to discard cards from the players
     * ResourceList
     *
     * @pre the player has over 7 cards
     * @pre the status is discarding and the player has chosen cards to discard
     * @post the cards are no longer within the player's ResourceList and the
     * model is updated or an error message
     * @param discardedCards
     * @throws IOException
     */
    ClientModelDTO discardCards(DiscardCards discardedCards) throws IOException; //ResourceList

    /**
     * Sends a request to the server to roll a number
     *
     * @throws IOException
     * @pre it is the beginning of the players turn
     * @post a number between 2-12 was randomly selected and the model is
     * updated or an error message
     */
    ClientModelDTO rollNumber(RollNumber rollMove) throws IOException;

    /**
     * Sends a request to the server to build a road
     *
     * @pre the player has the resources to build a road
     * @pre the location is valid to build a road
     * @post a road is built and the model is updated or an error message
     * @param free
     * @param roadLocation
     * @throws IOException
     */
    ClientModelDTO buildRoad(BuildRoad roadMove) throws IOException;

    /**
     * Sends a request to the server to build a settlement
     *
     * @pre player has the resources to build a settlement
     * @pre this is a valid location to build a settlement
     * @post a settlement is built and the model is updated or an error message
     * @param free
     * @param vertexLocation
     * @throws IOException
     */
    ClientModelDTO buildSettlement(BuildSettlement settlementMove) throws IOException;

    /**
     * Sends a request to the server to build a city
     *
     * @pre the player has the resources to build a city
     * @pre there is a settlement at the location to be upgraded to a city
     * @post a settlement is returned to the player and a city replaces it and
     * the model is updated or an error message
     * @param vertexLocation
     * @throws IOException
     */
    ClientModelDTO buildCity(BuildCity cityMove) throws IOException;

    /**
     * Sends a request to the server to offer a trade
     *
     * @pre It is the players turn or the player is offering to the player whose
     * turn it currently is
     * @pre the player has the resources they are offering
     * @post the player is notified with the resources up for trade and the
     * model is updated or an error message
     * @param tradeOffer the information about the offer
     * @throws IOException
     */
    ClientModelDTO offerTrade(OfferTrade tradeOffer) throws IOException;

    /**
     * Sends a request to the server to offer a maritimetrade
     *
     * @pre the player has a settlement on a port
     * @pre the player has the necessary resources
     * @post the player's resources are discarded and the player is given the
     * traded resources and the model is updated or an error message
     * @param ratio
     * @param inputResource
     * @param outputResource
     * @throws IOException
     */
    ClientModelDTO maritimeTrade(MaritimeTrade maritimeMove) throws IOException;

    /**
     * Sends a request to the server to rob a player
     *
     * @pre a 7 was rolled
     * @pre the robber was moved to the valid location and the resources were
     * stolen
     * @post a random resource is taken from the robbed player and the model is
     * updated or an error message
     * @param location
     * @param victimIndex
     * @throws IOException
     */
    ClientModelDTO robPlayer(RobPlayer robMove) throws IOException;

    /**
     * Sends a request to the server to end a turn
     *
     * @post the turn tracker is incremented to the next player and the model is
     * updated or an error message
     * @throws IOException
     */
    ClientModelDTO finishTurn(FinishTurn turn) throws IOException;

    /**
     * Sends a request to the server to buy a development card
     *
     * @pre the player has the resources to buy the development card
     * @pre there are remaining development cards to be given
     * @post the development card is given to the player and the model is
     * updated or an error message
     * @throws IOException
     */
    ClientModelDTO buyDevCard(BuyDevCard card) throws IOException;

    /**
     * Sends a request to the server to play a soldier
     *
     * @pre the player has a soldier in his collection of development cards
     * @pre the player has not already played a development card
     * @post the card is discarded
     * @post the robber is moved and a resource stolen from an appropriate
     * player and the model is updated or an error message
     * @param location
     * @param victimIndex
     * @throws IOException
     */
    ClientModelDTO playSoldier(Soldier soldier) throws IOException;

    /**
     * Sends a request to the server to play the Year of Plenty development card
     *
     * @pre the player has a Year of Plenty in his collection of development
     * cards
     * @pre the player has not already played a development card
     * @pre there are the two resources in the bank
     * @post the card is discarded
     * @post two chosen resources are given to the appropriate player and the
     * model is updated or an error message
     * @param resource1
     * @param resource2
     * @throws IOException
     */
    ClientModelDTO playYearOfPlenty(YearOfPlenty yearOfPlentyMove) throws IOException;

    /**
     * Sends a request to the server to play a Road Building development card
     *
     * @pre the player has a Road Building card in his collection of development
     * cards
     * @pre the player has not already played a development card
     * @pre the player has the roads to build
     * @post the card is discarded
     * @post two roads are built and the model is updated or an error message
     * @param spot1
     * @param spot2
     * @throws IOException
     */
    ClientModelDTO playRoadBuilding(RoadBuilding roadBuildingMove) throws IOException;

    /**
     * Sends a request to the server to play the monopoly development card
     *
     * @pre the player has a monopoly card in his collection of development
     * cards
     * @pre the player has not already played a development card
     * @post the card is discarded
     * @post the selected resource is taken from the every player and given to
     * the current player and the model is updated or an error message
     * @param resource
     * @throws IOException
     */
    ClientModelDTO playMonopoly(Monopoly monopoly) throws IOException;

    /**
     * Sends a request to the server to play the monument card
     *
     * @pre the player has a monument card in his collection of development
     * cards
     * @pre the player has enough to win the game
     * @post the players victory points are incremented and the model is updated
     * or an error message
     * @throws IOException
     */
    ClientModelDTO playMonument(Monument monument) throws IOException;

    /**
     * Sends a request to the server to login
     *
     * @pre valid username
     * @pre valid password
     * @post the player is logged in and assigned a color and the model is
     * updated or an error message
     * @param username
     * @param password
     * @throws IOException
     */
    void login(UserCredentials user) throws IOException;

    /**
     * Sends a request to the server to register a new user
     *
     * @pre valid username
     * @pre valid password
     * @post user created and the model is updated or an error message
     * @param username
     * @param password
     * @throws IOException
     */
    void registerNewUser(UserCredentials user) throws IOException;

    /**
     * Sends a request to the server to list the games
     *
     * @post retrieves a list of games or an error message
     * @return GameContainer
     * @throws IOException
     */
    ClientModelDTO listGames() throws IOException;

    /**
     * Sends a request to the server to create a game
     *
     * @pre valid game name
     * @pre tiles, numbers and ports have valid numbers
     * @post a map is created with random tiles, numbers, and ports and the
     * model is updated or an error message
     * @param name
     * @param randomTiles
     * @param randomNumbers
     * @param randomPorts
     * @return
     * @throws IOException
     */
    ClientModelDTO createGames(CreateGameRequest game) throws IOException;

    /**
     * Sends a request to the server to join a game
     *
     * @pre valid user that has logged in
     * @pre game to join with valid id
     * @pre valid color to be assigned
     * @post a player is added to the game with the specified color and the
     * model is updated or an error message
     * @param game DTO that specifies the game the user would like to join.
     * @throws IOException
     */
    void joinGame(JoinGameRequest game) throws IOException;

    /**
     * Sends a request to the server to save a game
     *
     * @pre game name is valid
     * @pre file name is valid
     * @post the game is saved and the model updated or an error message
     * @param gameId the id of the game that the user would like saved
     * @param fileName the file location where the game should be saved
     * @throws IOException
     */
    //void saveGames(int gameId, String fileName) throws IOException;
    /**
     * Sends a request to the server to load a game
     *
     * @pre there is a version of the game previously saved
     * @post a game is loaded and displayed and the model updated or an error
     * message
     * @param fileName the file that stores the saved state of a game
     * @throw; fileName) throws IOException;
     */
//	void loadGame(String fileName) throws IOException;
    /**
     * Sends a request to the server to retrieve the current state of the game
     *
     * @return The updated model from the server, null if not available
     * @pre there is a valid game
     * @pre the version number is valid
     * @post if necessary, the model is updated or an error message
     * @param versionNumber
     * @throws IOException
     */
    ClientModelDTO retrieveCurrentState(Integer versionNumber) throws IOException;

    /**
     * Sends a request to the server to reset a game
     *
     * @return the model of the game at the reset state
     * @pre a valid game and user logged in
     * @post the game is rest to the initiation stage while keeping the players
     * intact or an error message
     * @throws IOException
     */
    //ClientModelDTO resetGame() throws IOException;
    /**
     * Sends a request to the server to get a list of the game commands
     *
     * @pre a user is logged in and has joined a game
     * @post a list of commands is retrieved or an error message
     * @throws IOException
     */
//	CommandContainerDTO getCommands() throws IOException;
    /**
     * Sends a request to server with a list of the game commands
     *
     * @pre a user is logged in and has joined a game
     * @post a list of commands is sent to the server or an error message
     * @throws IOException
     */
    //ClientModelDTO postGameCommands(CommandContainerDTO commands) throws IOException;
    /**
     * Sends a request to the server to list all Artificial intelligence types
     *
     * @post retrieves a list of ai players or an error message
     * @throws IOException
     */
    List<AddAIRequest> listAITypes() throws IOException;

    /**
     * Sends a request to the server to add an artificial intelligence player
     *
     * @pre user is logged in and joined a game
     * @pre the game is not full
     * @pre the AIType is valid
     * @post an AIPlayer is added to the game and given a color and the model is
     * updated or an error message
     * @throws IOException
     */
    void addAIPlayer(AddAIRequest player) throws IOException;

    /**
     * Sends a request to server to change the log level
     *
     * @pre the logging level is valid
     * @post the server begins using logging level or an error message
     * @param logLevel
     * @throws IOException
     */
	//void changeLogLevel(String logLevel) throws IOException;
}
