package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.mocks.ModelMock;
import client.model.Populator;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.*;

public class ModelFacadeTest {
	
	static private ModelFacade modelFacade;
	private static ClientModel model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Populator pop = new Populator();
		pop.populateModel(ModelMock.getModelDTO());
		model = pop.getModel();
		modelFacade = new ModelFacade(pop.getModel());
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
		
		DiscardCards pete = new DiscardCards(2, new ResourceListDTO(0,-2,0,-1,-1));
		DiscardCards mark = new DiscardCards(3, new ResourceListDTO(-1,-1,-1,-1,-1));
		assertTrue(modelFacade.CanDiscardCards(pete));

		assertFalse(modelFacade.CanDiscardCards(mark));
	}

	@Test
	public void testCanRollNumber() {
		RollNumber sam = new RollNumber(0, 6);
		RollNumber mark = new RollNumber(3,8);
		assertTrue(modelFacade.CanRollNumber(sam));

		assertFalse(modelFacade.CanRollNumber(mark));
	}

	@Test
	public void testCanBuildRoad() {
		model.getUserManager().setCurrentUser(1);
		BuildRoad brooke = new BuildRoad(1, new EdgeLocation(new HexLocation(-1,0), EdgeDirection.NorthEast), false);
		assertTrue(modelFacade.CanBuildRoad(brooke));

		model.getUserManager().setCurrentUser(0);
		BuildRoad sam = new BuildRoad(0, new EdgeLocation(new HexLocation(0,1), EdgeDirection.NorthEast), false);
		assertFalse(modelFacade.CanBuildRoad(sam));
	}

//	@Test
//	public void testCanBuildSettlement() {
//		BuildSettlement pass = new BuildSettlement();
//		BuildSettlement fail = new BuildSettlement();
//		assertTrue(modelFacade.CanBuildSettlement(pass));
//
//		assertFalse(modelFacade.CanBuildSettlement(fail));
//	}
//
//	@Test
//	public void testCanBuildCity() {
//		BuildCity pass = new BuildCity();
//		BuildCity fail = new BuildCity();
//		assertTrue(modelFacade.CanBuildCity(pass));
//
//		assertFalse(modelFacade.CanBuildCity(fail));
//	}
//
//	@Test
//	public void testCanOfferTrade() {
//		OfferTrade pete = new OfferTrade();
//		OfferTrade mark = new OfferTrade();
//		assertTrue(modelFacade.CanOfferTrade(pete));
//
//		assertFalse(modelFacade.CanOfferTrade(mark));
//	}
//
//	@Test
//	public void testCanMaritimeTrade() {
//		MaritimeTrade pass = new MaritimeTrade();
//		MaritimeTrade fail = new MaritimeTrade();
//		assertTrue(modelFacade.CanMaritimeTrade(pass));
//
//		assertFalse(modelFacade.CanMaritimeTrade(fail));
//	}
//
//	@Test
//	public void testCanFinishTurn() {
//		FinishTurn pass = new FinishTurn();
//		FinishTurn fail = new FinishTurn();
//		assertTrue(modelFacade.CanFinishTurn(pass));
//
//		assertFalse(modelFacade.CanFinishTurn(fail));
//	}
//
//	@Test
//	public void testCanBuyDevCard() {
//		BuyDevCard pass = new BuyDevCard();
//		BuyDevCard fail = new BuyDevCard();
//		assertTrue(modelFacade.CanBuyDevCard(pass));
//
//		assertFalse(modelFacade.CanBuyDevCard(fail));
//	}
//
//	@Test
//	public void testCanUseYearOfPlenty() {
//		YearOfPlenty pass = new YearOfPlenty();
//		YearOfPlenty fail = new YearOfPlenty();
//		assertTrue(modelFacade.CanUseYearOfPlenty(pass));
//
//		assertFalse(modelFacade.CanUseYearOfPlenty(fail));
//	}
//
//	@Test
//	public void testCanUseRoadBuilder() {
//		RoadBuilding pass = new RoadBuilding();
//		RoadBuilding fail = new RoadBuilding();
//		assertTrue(modelFacade.CanUseRoadBuilder(pass));
//
//		assertFalse(modelFacade.CanUseRoadBuilder(fail));
//	}
//
//	@Test
//	public void testCanUseSoldier() {
//		Soldier pass = new Soldier();
//		Soldier fail = new Soldier();
//		assertTrue(modelFacade.CanUseSoldier(pass));
//
//		assertFalse(modelFacade.CanUseSoldier(fail));
//	}
//
//	@Test
//	public void testCanUseMonopoly() {
//		Monopoly pass = new Monopoly();
//		Monopoly fail = new Monopoly();
//		assertTrue(modelFacade.CanUseMonopoly(pass));
//
//		assertFalse(modelFacade.CanUseMonopoly(fail));
//	}
//
//	@Test
//	public void testCanUseMonument() {
//		Monument pass = new Monument();
//		Monument fail = new Monument();
//		assertTrue(modelFacade.CanUseMonument(pass));
//
//		assertFalse(modelFacade.CanUseMonument(fail));
//	}
//
//	@Test
//	public void testCanPlaceRobber() {
//		RobPlayer pass = new RobPlayer();
//		RobPlayer fail = new RobPlayer();
//		assertTrue(modelFacade.CanPlaceRobber(pass));
//
//		assertFalse(modelFacade.CanPlaceRobber(fail));
//	}

}
