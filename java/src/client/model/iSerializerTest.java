/**
 *
 */
package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.models.DTO.*;
import client.mocks.ModelMock;
/**
 * @author Anna Sokolova
 *
 */
public class iSerializerTest {

    private iSerializer s = new Serializer();

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeModel(java.lang.String)}.
     */
    @Test
    public void testDeserializeModel() {
        ModelMock modelMock = new ModelMock();
        try {
            ClientModelDTO modelObj = s.deserializeModel(modelMock.getJSON());
            ClientModelDTO expectedModelObj = modelMock.getObj();
            assertTrue("winner field should be deserialized correctly", modelObj.getWinner() == expectedModelObj.getWinner());
            assertTrue("version field should be deserialized correctly", modelObj.getVersion() == expectedModelObj.getVersion());
            assertTrue("map radius field should be deserialized correctly", modelObj.getMap().getRadius() == expectedModelObj.getMap().getRadius());
        } catch (Exception e) {
            fail("JSON does not match the ClientModelDTO object");
        }
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeModel(shared.models.DTO.ClientModelDTO)}.
     */
    @Test
    public void testSerializeModel() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeUser(java.lang.String)}.
     */
    @Test
    public void testDeserializeUser() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeUser(shared.models.DTO.UserDTO)}.
     */
    @Test
    public void testSerializeUser() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeGameContainer(java.lang.String)}.
     */
    @Test
    public void testDeserializeGameContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeGameContainer(shared.models.DTO.GameContainerDTO)}.
     */
    @Test
    public void testSerializeGameContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeGame(java.lang.String)}.
     */
    @Test
    public void testDeserializeGame() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeGame(shared.models.DTO.GameDTO)}.
     */
    @Test
    public void testSerializeGame() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeCommandContainer(java.lang.String)}.
     */
    @Test
    public void testDeserializeCommandContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeCommandContainer(shared.models.DTO.CommandContainerDTO)}.
     */
    @Test
    public void testSerializeCommandContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeAITypesContainer(java.lang.String)}.
     */
    @Test
    public void testDeserializeAITypesContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeAITypesContainer(shared.models.DTO.AITypesContainerDTO)}.
     */
    @Test
    public void testSerializeAITypesContainer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#deserializeAIPlayer(java.lang.String)}.
     */
    @Test
    public void testDeserializeAIPlayer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link client.model.iSerializer#serializeAIPlayer(shared.models.DTO.AIPlayerDTO)}.
     */
    @Test
    public void testSerializeAIPlayer() {
        fail("Not yet implemented");
    }

}
