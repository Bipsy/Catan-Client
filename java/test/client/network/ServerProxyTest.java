package client.network;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import shared.locations.*;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.*;

public class ServerProxyTest {
	
	private static ServerProxy proxy;

	@Test
	public void testLogin() {
		proxy = new ServerProxy();
		UserCredentials user = new UserCredentials("Sam", "sam");
		try {
			proxy.login(user);
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
		} catch (IOException e) {
			fail("Did no succeed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterNewUser() {
//		UserCredentials tiger = new UserCredentials("tiger", "shark");
//		try {
//			proxy.registerNewUser(tiger);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void testListGames() {
		try {
			proxy.listGames();
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
		} catch (IOException e) {
			fail("Did no succeed");	
			e.printStackTrace();
		}
	}

	@Test
	public void testRetrieveCurrentState() {
		try {
			proxy.retrieveCurrentState(0);
		} catch (IOException e) {
			fail("Did no succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testSendChat() {
		SendChat message = new SendChat(0, "chat");
		try {
			proxy.sendChat(message);
		} catch (IOException e) {
			fail("Did no succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testOfferTrade() {
		ResourceListDTO list = new ResourceListDTO(1, 0, 0, 0, 2);
		OfferTrade trade = new OfferTrade(3, list, 2);
		try {
			proxy.offerTrade(trade);
		} catch (IOException e) {
			fail("Did no succeed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAcceptTrade() {
		AcceptTrade accept = new AcceptTrade(2, true);
		try {
			proxy.acceptTrade(accept);
		} catch (IOException e) {
			fail("Did not succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDiscardCards() {
		ResourceListDTO list = new ResourceListDTO(0, 1, 1, 3, 0);
		DiscardCards cards = new DiscardCards(0, list);
		try {
			proxy.discardCards(cards);
		} catch (IOException e) {
			fail("Did not succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testRollNumber() {
		RollNumber roll = new RollNumber(1,8);
		try {
			proxy.rollNumber(roll);
		} catch (IOException e) {
			fail("Did not succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testBuildRoad() {
		HexLocation loc = new HexLocation(-1, -1);
		EdgeLocation edge = new EdgeLocation(loc, EdgeDirection.NE);
		BuildRoad road = new BuildRoad(1, edge, false);
		try {
			proxy.buildRoad(road);
		} catch (IOException e) {
			fail("Did not succeed");
			e.printStackTrace();
		}
	}

	@Test
	public void testBuildSettlement() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaritimeTrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testRobPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinishTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuyDevCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaySoldier() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayYearOfPlenty() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayRoadBuilding() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayMonopoly() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayMonument() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAITypes() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAIPlayer() {
		fail("Not yet implemented");
	}

}
