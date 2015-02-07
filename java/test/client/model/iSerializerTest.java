/**
 *
 */
package client.model;

import static org.junit.Assert.*;

import org.junit.Test;
import com.google.gson.*;

import client.mocks.ModelMock;
import shared.models.DTO.*;
import client.model.iSerializer;

/**
 * @author Anna Sokolova
 *
 */
public class iSerializerTest {

    private iSerializer s = new Serializer();

    @Test
    public void testDeserializeModel() {
        try {
            ClientModelDTO modelObj = s.deserializeModel(ModelMock.getJSON());
            checkModelFields(modelObj);
        } catch (Exception e) {
            fail("JSON does not match the ClientModelDTO object");
        }
    }

    @Test
    public void testSerializeModel() {
    	
    	JsonParser parser = new JsonParser(); 
    	ClientModelDTO modelObj = ModelMock.getModelDTO(); 
    	String JSON = s.serializeModel(modelObj);
    	JsonElement jsonEl = parser.parse(JSON);
    	JsonElement expectedJsonEl = parser.parse(ModelMock.getJSON());
    	
    	//compare root level of the client model json to the expected one    	
    	assertTrue("JSON needs to have a correct version", 
    			jsonEl.getAsJsonObject().get("version").getAsString().equals(expectedJsonEl.getAsJsonObject().get("version").getAsString()));
    	assertTrue("JSON needs to have a correct winner", 
    			jsonEl.getAsJsonObject().get("winner").getAsString().equals(expectedJsonEl.getAsJsonObject().get("winner").getAsString()));    	
    	assertTrue("JSON needs to have a correct number of players", 
    			jsonEl.getAsJsonObject().get("players").getAsJsonArray().size() ==
    			expectedJsonEl.getAsJsonObject().get("players").getAsJsonArray().size());
    	
    	//compare players
    	JsonArray players = jsonEl.getAsJsonObject().get("players").getAsJsonArray();
    	JsonArray expectedPlayers = expectedJsonEl.getAsJsonObject().get("players").getAsJsonArray();
    	assertTrue("JSON player has to have a correct name", 
    			players.get(0).getAsJsonObject().get("name").getAsString().equals(expectedPlayers.get(0).getAsJsonObject().get("name").getAsString()));
    	assertTrue("JSON player has to have a correct name", 
    			players.get(2).getAsJsonObject().get("name").getAsString().equals(expectedPlayers.get(2).getAsJsonObject().get("name").getAsString()));
    	
    	//compare maps
    	JsonObject map = jsonEl.getAsJsonObject().get("map").getAsJsonObject();
    	JsonObject expectedMap = expectedJsonEl.getAsJsonObject().get("map").getAsJsonObject();
    	assertTrue("Map has to have a correct radius", 
    			map.get("radius").getAsInt() ==  expectedMap.get("radius").getAsInt());
    }
   
    
    private void checkModelFields(ClientModelDTO modelObj) {
    	
        ClientModelDTO expectedModelObj = ModelMock.getModelDTO();
        assertTrue("winner field should be deserialized correctly", modelObj.getWinner() == expectedModelObj.getWinner());
        assertTrue("version field should be deserialized correctly", modelObj.getVersion() == expectedModelObj.getVersion());
        assertTrue("map radius field should be deserialized correctly", modelObj.getMap().getRadius() == expectedModelObj.getMap().getRadius());
        assertTrue("player's name field has to be set correctly", modelObj.getPlayers()[0].getName().equals(expectedModelObj.getPlayers()[0].getName()));
        assertTrue("the number of players has to be set correctly", modelObj.getPlayers().length == expectedModelObj.getPlayers().length);
        assertTrue("the number of resources has to be set correctly", modelObj.getResources().getBrick() == expectedModelObj.getResources().getBrick());


    }

}
