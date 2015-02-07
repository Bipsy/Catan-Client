package client.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.models.DTO.ClientModelDTO;
import client.mocks.ModelMock;
import client.model.Serializer;
import client.model.Populator;
import shared.models.*;

public class PopulatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * This methods tests that the model gets populated correctly using json
	 * 
	 */
	@Test
	public void test() {
		iSerializer s = new Serializer();
		Populator p = new Populator();
		try {
			boolean result = p.populateModel(s.deserializeModel(ModelMock.getJSON()));
			
			assertTrue("populator returned true", result);
			shared.models.ClientModel m = p.getModel();
			assertTrue("winner field should be populated correctly", m.getWinner() == ModelMock.getModelDTO().getWinner());
	        assertTrue("version field should be populated correctly", m.getVersion() == ModelMock.getModelDTO().getVersion());
	        assertTrue("map radius field should be populated correctly", m.getBoard().getRadius() == ModelMock.getModelDTO().getMap().getRadius());
	        assertTrue("player's cities field has to be set correctly", m.getUserManager().getUsers().get(0).getCities() == 
	        		ModelMock.getModelDTO().getPlayers()[0].getCities());
	        assertTrue("the number of players has to be set correctly", m.getUserManager().getUsers().size() == ModelMock.getModelDTO().getPlayers().length);
			
		}
		catch (Exception e) {
			fail("the populator failed");
		}
	}

}
