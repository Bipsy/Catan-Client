/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.AIPlayer;
import shared.models.CommandContainer;
import shared.models.GameContainer;
import shared.models.ResourceList;
import shared.models.TradeOffer;
import shared.models.User;
import client.model.Serializer;

/**
 * ServerProxy is our implementation of the iServerProxy interface.
 * This class will be used to handle all communication with the server.
 * @author Peter Anderson <anderson.peter@byu.edu> 
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public class ServerProxy implements iServerProxy {

	private String serverHost = "localhost";
	private String serverPort = "8081";
	
	/**
	 * Class constructor
	 */
	public ServerProxy() {
		
	}
	
	/**
	 * Class constructor
	 */
	public ServerProxy(String serverHost, String serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	
	Serializer serializer = new Serializer();
	
	public String doGet(String urlPath) throws IOException {
		try {
			URL url = new URL("http://" + serverHost + ":" + serverPort + urlPath);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept","application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        StringBuilder out = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            out.append(line);
		        }
		        System.out.println(out.toString());
				
				return out.toString();
			}
			else {
				throw new IOException(String.format("doGet failed: %s (http code %d)",
											urlPath, connection.getResponseCode()));
			}
		}
		catch (IOException e) {
			throw new IOException(String.format("doGet failed: %s", e.getMessage()), e);
		}
	}
	

	public String doPost(String urlPath, String jsonString) throws IOException {
		try {
			URL url = new URL("http://" + serverHost + ":" + serverPort + urlPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//HttpURLConnection.setRequestProperty("Cookie", cookie);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/json");
			connection.setRequestProperty("Accept","application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			connection.connect();
			byte[] outputBytes = jsonString.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(outputBytes);

			os.close();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//Set cookies
				//send the result to the serializer
		        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        StringBuilder out = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            out.append(line);
		        }
		        System.out.println(out.toString());
				
				return out.toString();
			} else {
				throw new IOException(String.format("doPost failed: %s (http code %d)",
						urlPath, connection.getResponseCode()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}
	

    @Override
    public User login(String username, String password) throws IOException {
    	try {
	    	User user = new User(username, password);
	    	String params = serializer.serializeUser(user);
	        return serializer.deserializeUser(doPost("/user/login", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public User registerNewUser(String username, String password) throws IOException {
    	try {
	    	User user = new User(username, password);
	    	String params = serializer.serializeUser(user);
	        return serializer.deserializeUser(doPost("/user/login", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public GameContainer listGames() throws IOException {
		return null;
    	
    }

    @Override
    public String createGames(String name, int randomTiles, int randomNumbers, int randomPorts) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinGame(int gameId, CatanColor color) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String saveGames(int gameId, String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String loadGame() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String retrieveCurrentState(int versionNumber) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String resetGame() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCommands() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postGameCommands(CommandContainer commands) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listAITypes() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addAIPlayer(AIPlayer player) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String changeLogLevel(int logLevel) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
    @Override
    public String sendChat(String content) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String acceptTrade(boolean willAccept) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String discardCards(ResourceList discardedCards) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rollNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildRoad(boolean free, EdgeLocation roadLocation) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildSettlement(boolean free, VertexLocation vertexLocation) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildCity(VertexLocation vertexLocation) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String offerTrade(TradeOffer offer) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String robPlayer(HexLocation location, int victimIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String finishTurn() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buyDevCard() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String playSoldier(HexLocation location, int victimIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String playMonopoly(ResourceType resource) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String playMonument() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(final String[] args)
	{
    	ServerProxy test = new ServerProxy();
    	try {
			String user = test.doPost("/user/login", "{'username':'Pete','password':'pete'}");

			user = test.doGet("/games/list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}

