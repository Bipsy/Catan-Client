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
	private String myCookie = "";
	
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
			connection.setRequestProperty("Cookie", myCookie);
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
				String headerName = null;
				for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) {
					if (headerName.equals("Set-Cookie")) {
						String unsplit = connection.getHeaderField(i);
						String[] split = unsplit.split("=");
						if (split[0] == "catan.user") {
							myCookie = connection.getHeaderField(i);
						} else  {
							myCookie.concat("; " + connection.getHeaderField(i));
						}
					}
				}
				
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
    public void login(UserDTO user) throws IOException {
    	try {
	    	String params = serializer.serializeUser(user);
	        doPost("/user/login", params);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public void registerNewUser(UserDTO user) throws IOException {
    	try {
	    	String params = serializer.serializeUser(user);
	        doPost("/user/register", params);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO listGames() throws IOException {
    	try {
	        return (ClientModelDTO) serializer.deserialize(doGet("/games/list"));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO createGames(GameToCreateDTO game) throws IOException {
    	try {
	    	String params = serializer.serialize(game);
	        return (ClientModelDTO) serializer.deserialize(doPost("/games/create", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO retrieveCurrentState(ClientModelDTO model) throws IOException {
    	try {
	    	String params = serializer.serialize(model);
	        return (ClientModelDTO) serializer.deserialize(doPost("/games/retrieveCurrentState", params)); ////url
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO sendChat(MessageDTO message) throws IOException {
    	try {
	    	String params = serializer.serialize(message);
	        return (ClientModelDTO) serializer.deserialize(doPost("/games/sendChat", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}   
    }

    @Override
    public ClientModelDTO acceptTrade(MoveType acceptType, int playerIndex, boolean willAccept) throws IOException {
		return null;
//    	try {
//	    	ClientModelDTO model = new ClientModelDTO(username, password);
//	    	String params = serializer.serializeModel(model);
//	        return serializer.deserializeModel(doPost("/moves/acceptTrade", params));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    		throw new IOException();
//    	}    
    }

    @Override
    public ClientModelDTO discardCards(DiscardCardsDTO discardedCards) throws IOException {
    	try {
	    	String params = serializer.serialize(discardedCards);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/discardedCards", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}    }

    @Override
    public ClientModelDTO rollNumber(RollNumberDTO rollMove) throws IOException {
    	try {
	    	String params = serializer.serialize(rollMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/rollNumber", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}   
    }

    @Override
    public ClientModelDTO buildRoad(BuildRoadDTO roadMove) throws IOException {
    	try {
	    	String params = serializer.serialize(roadMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/move/buildRoad", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO buildSettlement(BuildStructureDTO settlementMove) throws IOException {
    	try {
	    	String params = serializer.serialize(settlementMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/buildSettlement", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO buildCity(BuildStructureDTO cityMove) throws IOException {
    	try {
	    	String params = serializer.serialize(cityMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/buildCity", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO offerTrade(TradeOfferDTO tradeOffer) throws IOException {
    	try {
	    	String params = serializer.serialize(tradeOffer);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/offerTrade", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO maritimeTrade(MaritimeTradeDTO maritimeMove) throws IOException {
    	try {
	    	String params = serializer.serialize(maritimeMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/maritimeTrade", params)); ////url
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO robPlayer(FigureDTO robMove) throws IOException {
    	try {
	    	String params = serializer.serialize(robMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/robPlayer", params)); ////url
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
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
    	try {
	    	String params = serializer.serialize(soldierMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/Soldier", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO playYearOfPlenty(YearOfPlentyDTO yearOfPlentyMove) throws IOException {
    	try {
	    	String params = serializer.serialize(yearOfPlentyMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/Year_of_Plenty", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public ClientModelDTO playRoadBuilding(RoadBuildingDTO roadBuildingMove) throws IOException {
    	try {
	    	String params = serializer.serialize(roadBuildingMove);
	        return (ClientModelDTO) serializer.deserialize(doPost("/moves/Road_Building", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
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
    	try {
	    	String params = serializer.serialize(game);
	        serializer.deserialize(doPost("/games/join", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
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
    	try {
	    	String params = serializer.serialize(commands);
	        return (ClientModelDTO) serializer.deserialize(doPost("/games/commands", params));
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public List<AIPlayerDTO> listAITypes() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAIPlayer(AIPlayerDTO player) throws IOException {
    	try {
	    	String params = serializer.serialize(player);
	        serializer.deserialize(doPost("/games/sendChat", params)); ////url
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IOException();
    	}
    }

    @Override
    public void changeLogLevel(String logLevel) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

//  public static void main(final String[] args) {
//      ServerProxy test = new ServerProxy();
//  	try {
//          String user = test.doPost("/user/login", "{'username':'Pete','password':'pete'}");
//          user = test.doGet("/games/list");
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//  }
    
}

