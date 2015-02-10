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


	/*
	 * This method tests that the model gets populated correctly using DTO objects. 
	 * For JSON to DTO serialization see serializerTest
	 * 
	 */
	@Test
	public void testModelPopulation() {
		iSerializer s = new Serializer();
		Populator p = new Populator();
		try {
			boolean result = p.populateModel(s.deserializeModel(ModelMock.getJSON()));
			
			assertTrue("populator returned true", result);
			shared.models.ClientModel m = p.getModel();
			assertTrue("winner field should be populated correctly", m.getWinner() == ModelMock.getModelDTO().getWinner());
	        assertTrue("version field should be populated correctly", m.getVersion() == ModelMock.getModelDTO().getVersion());
	        assertTrue("player's cities field has to be set correctly", m.getUserManager().getPlayer(0).getCities() == 
	        		ModelMock.getModelDTO().getPlayers()[0].getCities());
	        assertTrue("the number of players has to be set correctly", m.getUserManager().getNumPlayers() == ModelMock.getModelDTO().getPlayers().length);
	        assertTrue("the number of cities should be populated correctly", m.getBoard().getCities().size() == ModelMock.getModelDTO().getMap().getCities().length);
	        assertTrue("the map radius should be populated correctly", m.getBoard().getRadius() == ModelMock.getModelDTO().getMap().getRadius());
	        assertTrue("the robber location should be populated correctly", m.getBoard().getRobber().getLocation().getX() == ModelMock.getModelDTO().getMap().getRobber().getX());
	        
		}
		catch (Exception e) {
			fail("the populator failed");
		}
	}

}
