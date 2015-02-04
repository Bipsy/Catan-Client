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

import shared.models.Game;
import shared.models.User;
import client.model.Serializer;

import java.util.List;

import shared.definitions.MoveType;
import shared.definitions.ResourceType;
import shared.models.DTO.*;

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
    public void login(String username, String password) throws IOException {
    	try {
	    	UserDTO user = new UserDTO(username, password);
	    	String params = serializer.serializeUser(user);
	        doPost("/user/login", params);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public void registerNewUser(String username, String password) throws IOException {
    	try {
	    	UserDTO user = new UserDTO(username, password);
	    	String params = serializer.serializeUser(user);
	        doPost("/user/register", params);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public GameContainerDTO listGames() throws IOException {
    	try {
	        return serializer.deserializeGameContainer(doGet("/games/list"));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public GameDTO createGames(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IOException {
    	try {
    		GameToCreateDTO game = new GameToCreateDTO(name, randomTiles, randomNumbers, randomPorts);
	    	String params = serializer.serializeGameToCreate(game);
	        return serializer.deserializeGame(doPost("/games/create", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO retrieveCurrentState(int versionNumber) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(final String[] args) {
        ServerProxy test = new ServerProxy();
    	try {
            String user = test.doPost("/user/login", "{'username':'Pete','password':'pete'}");
            user = test.doGet("/games/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientModelDTO sendChat(MessageDTO message) throws IOException {
		return null;
//    	try {
//	    	ClientModelDTO model = new ClientModelDTO();
//	    	model.setChat(chat);
//	    	String params = serializer.serializeModel(message);
//	        return serializer.deserializeModel(doPost("/games/sendChat", params));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    		throw new IOException();
//    	}   
    }

    @Override
    public ClientModelDTO acceptTrade(MoveType acceptType, int playerIndex, boolean willAccept) throws IOException {
		return null;
//    	try {
//	    	ClientModelDTO model = new ClientModelDTO(username, password);
//	    	String params = serializer.serializeModel(model);
//	        return serializer.deserializeModel(doPost("/user/register", params));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    		throw new IOException();
//    	}    
    }

    @Override
    public ClientModelDTO discardCards(DiscardCardsDTO discardedCards) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO rollNumber(RollNumberDTO rollMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildRoad(BuildRoadDTO roadMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildSettlement(BuildStructureDTO settlementMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildCity(BuildStructureDTO cityMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO offerTrade(TradeOfferDTO tradeOffer) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO maritimeTrade(MaritimeTradeDTO maritimeMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO robPlayer(FigureDTO robMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO finishTurn(MoveType finishTurn, int playerIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buyDevCard(MoveType buyDevCard, int playerIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playSoldier(FigureDTO soldierMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playYearOfPlenty(YearOfPlentyDTO yearOfPlentyMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playRoadBuilding(RoadBuildingDTO roadBuildingMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playMonopoly(MoveType playMonopoly, ResourceType resource, int playerIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playMonument(MoveType playMonument, int playerIndex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void joinGame(GameDTO game) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGames(int gameId, String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadGame(String fileName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO resetGame() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CommandContainerDTO getCommands() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO postGameCommands(CommandContainerDTO commands) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AIPlayerDTO> listAITypes() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAIPlayer(AIPlayerDTO player) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeLogLevel(String logLevel) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

