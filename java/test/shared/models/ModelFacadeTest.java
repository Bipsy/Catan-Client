package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.mocks.ModelMock;
import client.model.ModelFacade;
import client.model.Populator;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.DTO.EdgeLocationDTO;
import shared.models.DTO.EdgeValueDTO;
import shared.models.DTO.ResourceListDTO;
import shared.models.DTO.params.*;

public class ModelFacadeTest {

    static private ModelFacade modelFacade;
    private static ClientModel model;

    /**
     * These tests use the ModelMock to grab a Client state and it is used to
     * test most of the can do functions. The dev cards are tested in
     * PlayerTest.java
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Populator pop = Populator.getInstance();
        pop.populateModel(ModelMock.getModelDTO(), null);
        modelFacade = null;
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test 0 has less than 7 cards, doesn't need to discard cards Test 1 number
     * of cards is less than half test 2 cards being discarded aren't owned test
     * 3 has more than 7, half are selected, and all are available
     */
    @Test
    public void testCanDiscardCards() {

        DiscardCards pete = new DiscardCards(2, new ResourceListDTO(0, -2, 0, -1, -1));
        DiscardCards mark = new DiscardCards(3, new ResourceListDTO(-1, -1, -1, -1, -1));
        assertTrue("A player can discard cards that (s)he owns", modelFacade.CanDiscardCards(pete));

        assertFalse("A player cannot discard cards that (s)he doesn'town", modelFacade.CanDiscardCards(mark));
    }

    /**
     * Test 0 not turn Test 1 invalid number test 2 valid number and is turn
     */
    @Test
    public void testCanRollNumber() {
        RollNumber sam = new RollNumber(0, 6);
        RollNumber mark = new RollNumber(3, 8);
        model.getUserManager().setCurrentUser(0);
        assertTrue("A player can roll on their turn", modelFacade.CanRollNumber(sam));

        assertFalse("A player cannot roll when it is not their turn", modelFacade.CanRollNumber(mark));
    }

    /**
     * @pre if road is free, just check board to make sure location is
     * available. If not free, player must have resources necessary, must be
     * player's turn, and location must be available Test0: not user's turn
     * Test1: road is free, and location is available Test2: road is free,
     * location is not available Test3: road is not free, user has resources,
     * location is available Test4: road is not free, user does not have
     * resources, location is available Test5: road is not free, user has
     * resources, location is not available Test6: user doesn't have any more
     * road pieces
     */
    @Test
    public void testCanBuildRoad() {
        model.getUserManager().setCurrentUser(0);
    	//Our model always has player 0 as the current player

        //Test 0 - even though brooke has the resources and the location is available, it is not her turn
        BuildRoad brooke = new BuildRoad(1, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthEast), false);
        assertFalse("A player can not build a road if it is not their turn", modelFacade.CanBuildRoad(brooke));

        //Test 1 - even though mark doesn't have the necessary resources, the road is free, and the location is available
        BuildRoad mark = new BuildRoad(3, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthEast), true);
        model.getUserManager().setCurrentUser(3);
        assertTrue("A player can build a road if the road is free, and the spot is available", modelFacade.CanBuildRoad(mark));

        //Test 2 - even though the road is free, the location is not available
        mark = new BuildRoad(3, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), true);
        model.getUserManager().setCurrentUser(3);
        assertFalse("A player can not build a road if the road is free, but the spot is not available", modelFacade.CanBuildRoad(mark));

        //Test 3 - brooke has the resources, and the location is available
        model.getUserManager().setCurrentUser(1);
        assertTrue("A player can build a road if they have enough resources", modelFacade.CanBuildRoad(brooke));

        //Test 4 - even though mark doesn't have the necessary resources, the road is free, and the location is available
        mark = new BuildRoad(3, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.South), false);
        model.getUserManager().setCurrentUser(3);
        assertFalse("A player can build a road if they have enough resources", modelFacade.CanBuildRoad(mark));

        //Test 5 - brooke has the resources, but the location is not available
        brooke = new BuildRoad(1, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), false);
        model.getUserManager().setCurrentUser(3);
        assertFalse("A player can build a road if they have enough resources", modelFacade.CanBuildRoad(mark));

        //Test 6 - brooke has the resources, but has no more road pieces
        model.getUserManager().adjustUserPieces(3, 0, 5, 4); // player index, roads, settlements, cities
        brooke = new BuildRoad(1, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), false);
        model.getUserManager().setCurrentUser(3);
        assertFalse("A player can build a road if they have enough resources", modelFacade.CanBuildRoad(mark));

    }

    /**
     * @pre if settlement is free, just check board to make sure location is
     * available. If not free, player must have resources necessary, must be
     * player's turn, and location must be available Test0: not user's turn
     * Test1: settlement is free, and location is available Test2: settlement is
     * free, location is not available Test3: settlement is not free, user has
     * resources, location is available Test4: settlement is not free, user does
     * not have resources, location is available Test5: settlement is not free,
     * user has resources, location is not available Test6: user doesn't have
     * any more settlement pieces
     */
    @Test
    public void testCanBuildSettlement() {
        Road peteRoad = new Road(new EdgeValueDTO(2, new EdgeLocationDTO(-1, -1, EdgeDirection.NorthEast)));
        Road samRoad = new Road(new EdgeValueDTO(0, new EdgeLocationDTO(-2, 3, EdgeDirection.NorthEast)));
        model.getBoard().addRoad(peteRoad);
        model.getBoard().addRoad(samRoad);

        //Test 0
        model.getUserManager().setCurrentUser(0);
        BuildSettlement pete = new BuildSettlement(2, new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthEast), false);
        assertFalse("A player can not build a settlement if it is not their turn", modelFacade.CanBuildSettlement(pete));

        //Test 1
        BuildSettlement sam = new BuildSettlement(0, new VertexLocation(new HexLocation(-2, 3), VertexDirection.East), true);
        assertTrue("A player can build a settlement if the settlement is free and location available",
                modelFacade.CanBuildSettlement(sam));

        //Test 2
        model.getUserManager().setCurrentUser(2);
        pete = new BuildSettlement(2, new VertexLocation(new HexLocation(-1, -1), VertexDirection.East), true);
        assertFalse("A player can not build a settlement if the settlement is free, but the location is not available",
                modelFacade.CanBuildSettlement(pete));

        //Test 3
        pete = new BuildSettlement(2, new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthEast), false);
        assertTrue("A player can build a settlement if they have the resources, and the location is available",
                modelFacade.CanBuildSettlement(pete));

        //Test 4
        model.getUserManager().setCurrentUser(0);
        sam = new BuildSettlement(0, new VertexLocation(new HexLocation(-2, 3), VertexDirection.East), false);
        assertFalse("A player can not build a settlement if they have the resources, but the location is not available",
                modelFacade.CanBuildSettlement(sam));

        //Test 5
        model.getUserManager().setCurrentUser(2);
        pete = new BuildSettlement(2, new VertexLocation(new HexLocation(-1, -1), VertexDirection.East), false);
        assertFalse("A player can not build a settlement if they do not have the resources, but the location is available",
                modelFacade.CanBuildSettlement(pete));

        //Test 6
        model.getUserManager().adjustUserPieces(2, 15, 0, 4); // player index, roads, settlements, cities
        pete = new BuildSettlement(2, new VertexLocation(new HexLocation(-1, -1), VertexDirection.East), false);
        assertFalse("A player can not build a settlement if they have no more pieces",
                modelFacade.CanBuildSettlement(pete));
    }

    /**
     * @pre if city is free, just check board to make sure location is
     * available. If not free, player must have resources necessary, must be
     * player's turn, and location must be available Test0: not user's turn
     * Test1: city is not free, user has resources, location is available Test2:
     * city is not free, user does not have resources, location is available
     * Test3: city is not free, user has resources, location is not available
     * Test4: user doesn't have any more city pieces
     */
    @Test
    public void testCanBuildCity() {
        BuildCity pete = new BuildCity(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest));
        BuildCity brooke = new BuildCity(1, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest));

        //Test 0
        model.getUserManager().setCurrentUser(0);
        assertFalse("A player can not build a city if it is not their turn", modelFacade.CanBuildCity(pete));

        //Test 1
        model.getUserManager().setCurrentUser(2);
        assertTrue("A player can build a city if they have the resources, and the location is available",
                modelFacade.CanBuildCity(pete));

        //Test 2
        model.getUserManager().setCurrentUser(1);
        assertFalse("A player can not build a settlement if they have the resources, but the location is not available",
                modelFacade.CanBuildCity(brooke));

        //Test 3
        model.getUserManager().setCurrentUser(2);
        pete = new BuildCity(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthEast));
        assertFalse("A player can not build a settlement if they have the resources, but the location is not available",
                modelFacade.CanBuildCity(pete));

        //Test 4
        model.getUserManager().adjustUserPieces(2, 15, 5, 0); // player index, roads, settlements, cities
        pete = new BuildCity(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest));
        assertFalse("A player cannot build a city if they don't have the pieces",
                modelFacade.CanBuildCity(pete));
    }

    /**
     * Test 0 not turn, can't offer trade Test 1 Offering invalid resources Test
     * 2 Trading with self Test 3 Offering valid trade
     */
    @Test
    public void testCanOfferTrade() {
        model.getUserManager().setCurrentUser(2);
        OfferTrade pete = new OfferTrade(2, new ResourceListDTO(1, -1, -1, 0, 0), 1);
        assertTrue("A player can trade with other players if they have the resources", modelFacade.CanOfferTrade(pete));

        model.getUserManager().setCurrentUser(1);
        OfferTrade mark = new OfferTrade(1, new ResourceListDTO(0, 1, -1, 0, 1), 1);
        assertFalse("A player can not trade with them selves", modelFacade.CanOfferTrade(mark));
    }

    /**
     * Test 0, not turn, cant maritime trade Has port, correct ratio
     */
    @Test
    public void testCanMaritimeTrade() {
        model.getUserManager().setCurrentUser(2);
        MaritimeTrade pete = new MaritimeTrade(2, 3, ResourceType.ORE, ResourceType.WOOD);
        assertTrue("A player can maritime trade if they have the resources", modelFacade.CanMaritimeTrade(pete));

        model.getUserManager().setCurrentUser(0);
        MaritimeTrade sam = new MaritimeTrade(0, 3, ResourceType.ORE, ResourceType.WOOD);
        assertFalse("A player cannot maritime trade if they don\'t have the resources", modelFacade.CanMaritimeTrade(sam));
    }

    /**
     * Sam cannot finish his turn because he is still rolling
     */
    @Test
    public void testCanFinishTurn() {
        FinishTurn sam = new FinishTurn(0);
        assertFalse("A player cannot finish their turn if they are rolling", modelFacade.CanFinishTurn(sam));
    }

    /**
     * Sam has the resources to buy a dev card Mark doesn't
     */
    @Test
    public void testCanBuyDevCard() {
        model.getUserManager().setCurrentUser(0);
        BuyDevCard sam = new BuyDevCard(0);
        assertTrue("a player can buy dev cards if they have the resources on their turn", modelFacade.CanBuyDevCard(sam));

        model.getUserManager().setCurrentUser(3);
        BuyDevCard mark = new BuyDevCard(3);
        assertFalse("a player can not buy dev cards if they don't have the resources", modelFacade.CanBuyDevCard(mark));
    }

    /**
     * mark is placing the robber in a new location sam is trying to place the
     * robber in the same location
     */
    @Test
    public void testCanPlaceRobber() {
        model.getUserManager().setCurrentUser(3);
        RobPlayer mark = new RobPlayer(3, 2, new HexLocation(1, -1));
        assertTrue("the robber can be placed on a different tile", modelFacade.CanPlaceRobber(mark));

        model.getUserManager().setCurrentUser(0);
        RobPlayer sam = new RobPlayer(0, 1, new HexLocation(0, -2));
        assertFalse("the robber cannot be placed on the same tile", modelFacade.CanPlaceRobber(sam));
    }
}
