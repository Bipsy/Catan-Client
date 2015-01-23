/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.IOException;

/**
 *
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public interface iServerProxy {
    
	void login(String username, String password) throws IOException;
	void registerNewUser(String username, String password) throws IOException;
	void listGames() throws IOException; 
	void createGames(String name, int randomTiles, int randomNumbers, int randomPorts) throws IOException;
	void joinGame(int gameId, String color) throws IOException;
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
