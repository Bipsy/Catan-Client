package client.network;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
		UserCredentials tiger = new UserCredentials("tiger", "shark");
		try {
			proxy.registerNewUser(tiger);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListGames() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateGames() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveCurrentState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendChat() {
		fail("Not yet implemented");
	}

	@Test
	public void testAcceptTrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testDiscardCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testRollNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildRoad() {
		fail("Not yet implemented");
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
	public void testOfferTrade() {
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
