package client.network;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import org.junit.BeforeClass;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.*;
import shared.models.DTO.ClientModelDTO;
import shared.models.DTO.GameContainerDTO;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.*;

public class ServerProxyTest {

    private static ServerProxy proxy;

    @BeforeClass
    public static void setupClass() {
        proxy = new ServerProxy();
//        UserCredentials user = new UserCredentials("Sam", "sam");
//        try {
//            proxy.login(user);
//        } catch (IOException e) {
//            System.err.println("Login Failed");
//        }
    }

    @Test
    public void testLogin() {

        UserCredentials user = new UserCredentials("Sam", "sam");
        try {
            proxy.login(user);
            assertTrue("the user will be logged in", true);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testJoinGame() {
        JoinGameRequest game = new JoinGameRequest(0, "orange");
        try {
            proxy.joinGame(game);
            assertTrue("The specified user has been added to a game", true);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

//    @Test
//    public void testRegisterNewUser() {
//        UserCredentials tiger = new UserCredentials("tiger", "shark");
//        try {
//            proxy.registerNewUser(tiger);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    @Test
    public void testListGames() {
        GameContainerDTO game = new GameContainerDTO();
        try {
            proxy.listGames();
            assertTrue("The list of games was retrieved", game != null);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateGames() {
        CreateGameRequest game = new CreateGameRequest(true, true, true, "FirstGame");
        try {
            proxy.createGames(game);
            assertTrue("A game has been created", true);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testRetrieveCurrentState() {
        ClientModelDTO model = new ClientModelDTO();
        try {
            proxy.retrieveCurrentState(null);
            assertTrue("Either the model is up to date or the current version"
                    + "of the model is retrieved", model != null);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testSendChat() {
        ClientModelDTO model = new ClientModelDTO();
        SendChat message = new SendChat(0, "chat");
        try {
            model = proxy.sendChat(message);
            assertTrue("A chat message was displayed", model != null);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testOfferTrade() {
        ClientModelDTO model = new ClientModelDTO();
        ResourceListDTO list = new ResourceListDTO(1, 0, 0, 0, 2);
        OfferTrade trade = new OfferTrade(3, list, 2);
        try {
            proxy.offerTrade(trade);
            assertTrue("A trade was offered toward another player", model != null);
        } catch (IOException e) {
            fail("Did no succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptTrade() {
        ClientModelDTO model = new ClientModelDTO();
        AcceptTrade accept = new AcceptTrade(2, true);
        try {
            proxy.acceptTrade(accept);
            assertTrue("A trade was accepted toward another player", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testDiscardCards() {
        ClientModelDTO model = new ClientModelDTO();
        ResourceListDTO list = new ResourceListDTO(0, 1, 1, 3, 0);
        DiscardCards cards = new DiscardCards(0, list);
        try {
            proxy.discardCards(cards);
            assertTrue("The specified cards are discarded", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testRollNumber() {
        ClientModelDTO model = new ClientModelDTO();
        RollNumber roll = new RollNumber(1, 8);
        try {
            proxy.rollNumber(roll);
            assertTrue("A number has been rolled", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testBuildRoad() {
        ClientModelDTO model = new ClientModelDTO();
        HexLocation loc = new HexLocation(-1, -1);
        EdgeLocation edge = new EdgeLocation(loc, EdgeDirection.NorthEast);
        BuildRoad road = new BuildRoad(1, edge, false);
        try {
            proxy.buildRoad(road);
            assertTrue("A road has been built in the specified location", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testBuildSettlement() {
        ClientModelDTO model = new ClientModelDTO();
        HexLocation hex = new HexLocation(-1, -1);
        VertexLocation vert = new VertexLocation(hex, VertexDirection.NorthEast);
        BuildSettlement sett = new BuildSettlement(2, vert);
        try {
            proxy.buildSettlement(sett);
            assertTrue("A settlement has been built in the specified location", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testBuildCity() {
        ClientModelDTO model = new ClientModelDTO();
        HexLocation hex = new HexLocation(-1, -1);
        VertexLocation vert = new VertexLocation(hex, VertexDirection.NorthEast);
        BuildCity city = new BuildCity(2, vert);
        try {
            proxy.buildCity(city);
            assertTrue("A city has been built in the specified location", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testMaritimeTrade() {
        ClientModelDTO model = new ClientModelDTO();
        MaritimeTrade trade = new MaritimeTrade(2, 2, ResourceType.BRICK, ResourceType.ORE);
        try {
            proxy.maritimeTrade(trade);
            assertTrue("A maritime trade has taken place", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testRobPlayer() {
        ClientModelDTO model = new ClientModelDTO();
        HexLocation hex = new HexLocation(-1, -1);
        RobPlayer rob = new RobPlayer(2, 0, hex);
        try {
            proxy.robPlayer(rob);
            assertTrue("The specified player has been robbed", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testFinishTurn() {
        ClientModelDTO model = new ClientModelDTO();
        FinishTurn turn = new FinishTurn(2);
        try {
            proxy.finishTurn(turn);
            assertTrue("The turn has been finished", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testBuyDevCard() {
        ClientModelDTO model = new ClientModelDTO();
        BuyDevCard card = new BuyDevCard(2);
        try {
            proxy.buyDevCard(card);
            assertTrue("The player has bought a development card", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testPlaySoldier() {
        ClientModelDTO model = new ClientModelDTO();
        Soldier sold = new Soldier(2, 0);
        try {
            proxy.playSoldier(sold);
            assertTrue("The Soldier developmetn card has been played", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayYearOfPlenty() {
        ClientModelDTO model = new ClientModelDTO();
        YearOfPlenty card = new YearOfPlenty(3, ResourceType.ORE, ResourceType.SHEEP);
        try {
            proxy.playYearOfPlenty(card);
            assertTrue("The Year of Plenty development card has been played", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayRoadBuilding() {
        ClientModelDTO model = new ClientModelDTO();
        HexLocation hex = new HexLocation(0, 0);
        HexLocation hexTwo = new HexLocation(1, -1);
        EdgeLocation edge = new EdgeLocation(hex, EdgeDirection.NorthEast);
        EdgeLocation edgeTwo = new EdgeLocation(hexTwo, EdgeDirection.South);
        RoadBuilding card = new RoadBuilding(0, edge, edgeTwo);
        try {
            proxy.playRoadBuilding(card);
            assertTrue("The Road Building development card has been played", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayMonopoly() {
        ClientModelDTO model = new ClientModelDTO();
        Monopoly monopoly = new Monopoly(0, ResourceType.BRICK);
        try {
            proxy.playMonopoly(monopoly);
            assertTrue("The Monopoloy development card has been played", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayMonument() {
        ClientModelDTO model = new ClientModelDTO();
        Monument monument = new Monument(1);
        try {
            proxy.playMonument(monument);
            assertTrue("A monument development card has been played", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testListAITypes() {
        try {
            proxy.listAITypes();
            assertTrue("The list of AI players has been retrieved", true);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAIPlayer() {
        ClientModelDTO model = new ClientModelDTO();
        AddAIRequest ai = new AddAIRequest("2");
        try {
            proxy.addAIPlayer(ai);
            assertTrue("An AI player has been added", model != null);
        } catch (IOException e) {
            fail("Did not succeed");
            e.printStackTrace();
        }
    }

}
