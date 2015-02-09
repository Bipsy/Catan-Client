package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.mocks.ModelMock;
import client.model.Populator;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.*;

public class ModelFacadeTest {
	
	static private ModelFacade modelFacade;
	private static ClientModel model;
	
	/**
	 * These tests use the ModelMock to grab a Client state and it is used
	 * to test all the can do functions.
	 * @throws Exception
	 */
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

	/**
	 * In this test, Pete is discarding four cards, all of which he has.
	 * Mark is trying to discard cards that he doesn't own.
	 */
	@Test
	public void testCanDiscardCards() {
		
		DiscardCards pete = new DiscardCards(2, new ResourceListDTO(0,-2,0,-1,-1));
		DiscardCards mark = new DiscardCards(3, new ResourceListDTO(-1,-1,-1,-1,-1));
		assertTrue(modelFacade.CanDiscardCards(pete));

		assertFalse(modelFacade.CanDiscardCards(mark));
	}

	/**
	 * In this test, it is Sam's turn, so he is able to roll the dice.
	 * Mark is trying to roll, but it is not his turn.
	 */
	@Test
	public void testCanRollNumber() {
		RollNumber sam = new RollNumber(0, 6);
		RollNumber mark = new RollNumber(3,8);
		assertTrue(modelFacade.CanRollNumber(sam));

		assertFalse(modelFacade.CanRollNumber(mark));
	}

	/**
	 * Brooke has enough resources to build a road, and is building it in
	 * an available spot. Sam doesn't have enough resources.
	 */
	@Test
	public void testCanBuildRoad() {
		model.getUserManager().setCurrentUser(1);
		BuildRoad brooke = new BuildRoad(1, new EdgeLocation(new HexLocation(-1,0), EdgeDirection.NorthEast), false);
		assertTrue(modelFacade.CanBuildRoad(brooke));

		model.getUserManager().setCurrentUser(0);
		BuildRoad sam = new BuildRoad(0, new EdgeLocation(new HexLocation(0,1), EdgeDirection.NorthEast), false);
		assertFalse(modelFacade.CanBuildRoad(sam));
	}

	/**
	 *  Pete has enough resources to build a city.
	 *  
	 *  Sam does not.
	 */
	@Test
	public void testCanBuildSettlement() {
		model.getUserManager().setCurrentUser(2);
		BuildSettlement pete = new BuildSettlement(2, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assertTrue(modelFacade.CanBuildSettlement(pete));

		model.getUserManager().setCurrentUser(0);
		BuildSettlement sam = new BuildSettlement(0, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assertFalse(modelFacade.CanBuildSettlement(sam));
	}

	/**
	 * Pete has enough resources to build a city. He is placing it where one
	 * of his settlements was.
	 * 
	 * Brooke doesn't have enough resources.
	 */
	@Test
	public void testCanBuildCity() {
		model.getUserManager().setCurrentUser(2);
		BuildCity pete = new BuildCity(2, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assertTrue(modelFacade.CanBuildCity(pete));
		
		model.getUserManager().setCurrentUser(1);
		BuildCity brooke = new BuildCity(1, new VertexLocation(new HexLocation(0,0), VertexDirection.SouthWest));
		assertFalse(modelFacade.CanBuildCity(brooke));
	}

	/**
	 * Mark is offering a valid trade.
	 * 
	 * Brooke is trying to trade with herself.
	 */
	@Test
	public void testCanOfferTrade() {
		model.getUserManager().setCurrentUser(2);
		OfferTrade pete = new OfferTrade(2, new ResourceListDTO(1,-1,-1, 0, 0), 1);
		assertTrue(modelFacade.CanOfferTrade(pete));

		model.getUserManager().setCurrentUser(1);
		OfferTrade mark = new OfferTrade(1, new ResourceListDTO(0, 1, -1, 0, 1), 1);
		assertFalse(modelFacade.CanOfferTrade(mark));
	}

	/**
	 * Pete has enough resources to trade at a special maritime trade
	 * 
	 * Sam does not have enough resources
	 */
	@Test
	public void testCanMaritimeTrade() {
		model.getUserManager().setCurrentUser(2);
		MaritimeTrade pete = new MaritimeTrade(2, 3, ResourceType.ORE, ResourceType.WOOD);
		assertTrue(modelFacade.CanMaritimeTrade(pete));

		model.getUserManager().setCurrentUser(0);
		MaritimeTrade sam = new MaritimeTrade(0, 3, ResourceType.ORE, ResourceType.WOOD);
		assertFalse(modelFacade.CanMaritimeTrade(sam));
	}

	
	/**
	 * Sam cannot finish his turn because he is still rolling
	 */
	@Test
	public void testCanFinishTurn() {
		FinishTurn sam = new FinishTurn(0);
		assertFalse(modelFacade.CanFinishTurn(sam));
	}
	
	/**
	 * Sam has the resources to buy a dev card
	 * Mark doesn't
	 */
	@Test
	public void testCanBuyDevCard() {
		model.getUserManager().setCurrentUser(0);
		BuyDevCard sam = new BuyDevCard(0);
		assertTrue(modelFacade.CanBuyDevCard(sam));

		model.getUserManager().setCurrentUser(3);
		BuyDevCard mark = new BuyDevCard(3);
		assertFalse(modelFacade.CanBuyDevCard(mark));
	}

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

	/**
	 * mark is placing the robber in a new location
	 * sam is trying to place the robber in the same location
	 */
	@Test
	public void testCanPlaceRobber() {
		model.getUserManager().setCurrentUser(3);
		RobPlayer mark = new RobPlayer(3, 2, new HexLocation(1,-1));
		assertTrue(modelFacade.CanPlaceRobber(mark));
		
		model.getUserManager().setCurrentUser(0);
		RobPlayer sam = new RobPlayer(0, 1, new HexLocation(0, -2));
		assertFalse(modelFacade.CanPlaceRobber(sam));
	}
}
