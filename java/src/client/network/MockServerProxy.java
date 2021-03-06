/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.data.GameInfo;
import client.model.Serializer;

import java.io.IOException;
import java.util.List;

import org.javatuples.Pair;

import shared.exceptions.NoCookieException;
import shared.models.DTO.ClientModelDTO;
import shared.models.DTO.params.*;

/**
 * MockServerProxy is a mock implementation of iServerProxy. This class is used
 * for testing purposes. Its methods return hard coded responses.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public class MockServerProxy implements iServerProxy {

    private final ClientModelDTO model;
    private final Serializer serializer;

    public MockServerProxy(Serializer newSerializer, String defaultModel) throws IOException {
        serializer = newSerializer;
        model = serializer.deserializeModel(defaultModel);
    }

    @Override
    public ClientModelDTO sendChat(SendChat message) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO acceptTrade(AcceptTrade accept) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO discardCards(DiscardCards discardedCards) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO rollNumber(RollNumber rollMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildRoad(BuildRoad roadMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildSettlement(BuildSettlement settlementMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buildCity(BuildCity cityMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO offerTrade(OfferTrade tradeOffer) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO maritimeTrade(MaritimeTrade maritimeMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO robPlayer(RobPlayer robMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO finishTurn(FinishTurn turn) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO buyDevCard(BuyDevCard card) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playSoldier(Soldier soldier) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playYearOfPlenty(YearOfPlenty yearOfPlentyMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playRoadBuilding(RoadBuilding roadBuildingMove) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playMonopoly(Monopoly monopoly) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientModelDTO playMonument(Monument monument) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pair<Boolean, Integer> login(UserCredentials user) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pair<Boolean, Integer> registerNewUser(UserCredentials user) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GameInfo> listGames() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameInfo createGames(CreateGameRequest game) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer joinGame(JoinGameRequest game) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void saveGames(int gameId, String fileName) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public void loadGame(String fileName) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public ClientModelDTO retrieveCurrentState(Integer version) throws IOException {
        if (version == null) {
            return null;
        } else if (version >= 1) {
            return null;
        } else {
            return model;
        }
    }

//    @Override
//    public ClientModelDTO resetGame() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public CommandContainerDTO getCommands() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public ClientModelDTO postGameCommands(CommandContainerDTO commands) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public List<String> listAITypes() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAIPlayer(AddAIRequest player) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public String getLocalPlayerName() throws NoCookieException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

//    @Override
//    public void changeLogLevel(String logLevel) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
