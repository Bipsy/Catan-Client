package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.models.DTO.params.*;

public class ModelFacadeTest {
	
	static private ModelFacade modelFacade;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// instantiate modelFacade
		Bank bank;
		Board board;
		ChatObject chatObject;
		UserManager userManager;
		TradeOffer tradeOffer;
		int version;
		int winner;
		ClientModel cm = new ClientModel();
		modelFacade = new ModelFacade();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		modelFacade = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanDiscardCards() {
		DiscardCards pass = new DiscardCards();
		DiscardCards fail = new DiscardCards();
		assertTrue(modelFacade.CanDiscardCards(pass));

		assertFalse(modelFacade.CanDiscardCards(fail));
	}

	@Test
	public void testCanRollNumber() {
		RollNumber pass = new RollNumber();
		RollNumber fail = new RollNumber();
		assertTrue(modelFacade.CanRollNumber(pass));

		assertFalse(modelFacade.CanRollNumber(fail));
	}

	@Test
	public void testCanBuildRoad() {
		BuildRoad pass = new BuildRoad();
		BuildRoad fail = new BuildRoad();
		assertTrue(modelFacade.CanBuildRoad(pass));

		assertFalse(modelFacade.CanBuildRoad(fail));
	}

	@Test
	public void testCanBuildSettlement() {
		BuildSettlement pass = new BuildSettlement();
		BuildSettlement fail = new BuildSettlement();
		assertTrue(modelFacade.CanBuildSettlement(pass));

		assertFalse(modelFacade.CanBuildSettlement(fail));
	}

	@Test
	public void testCanBuildCity() {
		BuildCity pass = new BuildCity();
		BuildCity fail = new BuildCity();
		assertTrue(modelFacade.CanBuildCity(pass));

		assertFalse(modelFacade.CanBuildCity(fail));
	}

	@Test
	public void testCanOfferTrade() {
		OfferTrade pass = new OfferTrade();
		OfferTrade fail = new OfferTrade();
		assertTrue(modelFacade.CanOfferTrade(pass));

		assertFalse(modelFacade.CanOfferTrade(fail));
	}

	@Test
	public void testCanMaritimeTrade() {
		MaritimeTrade pass = new MaritimeTrade();
		MaritimeTrade fail = new MaritimeTrade();
		assertTrue(modelFacade.CanMaritimeTrade(pass));

		assertFalse(modelFacade.CanMaritimeTrade(fail));
	}

	@Test
	public void testCanFinishTurn() {
		FinishTurn pass = new FinishTurn();
		FinishTurn fail = new FinishTurn();
		assertTrue(modelFacade.CanFinishTurn(pass));

		assertFalse(modelFacade.CanFinishTurn(fail));
	}

	@Test
	public void testCanBuyDevCard() {
		BuyDevCard pass = new BuyDevCard();
		BuyDevCard fail = new BuyDevCard();
		assertTrue(modelFacade.CanBuyDevCard(pass));

		assertFalse(modelFacade.CanBuyDevCard(fail));
	}

	@Test
	public void testCanUseYearOfPlenty() {
		YearOfPlenty pass = new YearOfPlenty();
		YearOfPlenty fail = new YearOfPlenty();
		assertTrue(modelFacade.CanUseYearOfPlenty(pass));

		assertFalse(modelFacade.CanUseYearOfPlenty(fail));
	}

	@Test
	public void testCanUseRoadBuilder() {
		RoadBuilding pass = new RoadBuilding();
		RoadBuilding fail = new RoadBuilding();
		assertTrue(modelFacade.CanUseRoadBuilder(pass));

		assertFalse(modelFacade.CanUseRoadBuilder(fail));
	}

	@Test
	public void testCanUseSoldier() {
		Soldier pass = new Soldier();
		Soldier fail = new Soldier();
		assertTrue(modelFacade.CanUseSoldier(pass));

		assertFalse(modelFacade.CanUseSoldier(fail));
	}

	@Test
	public void testCanUseMonopoly() {
		Monopoly pass = new Monopoly();
		Monopoly fail = new Monopoly();
		assertTrue(modelFacade.CanUseMonopoly(pass));

		assertFalse(modelFacade.CanUseMonopoly(fail));
	}

	@Test
	public void testCanUseMonument() {
		Monument pass = new Monument();
		Monument fail = new Monument();
		assertTrue(modelFacade.CanUseMonument(pass));

		assertFalse(modelFacade.CanUseMonument(fail));
	}

	@Test
	public void testCanPlaceRobber() {
		RobPlayer pass = new RobPlayer();
		RobPlayer fail = new RobPlayer();
		assertTrue(modelFacade.CanPlaceRobber(pass));

		assertFalse(modelFacade.CanPlaceRobber(fail));
	}

}
