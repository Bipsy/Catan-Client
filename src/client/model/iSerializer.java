/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.IOException;

import shared.models.DTO.*;

/**
 * Serializer interface describes the API for converting JSON to Java objects. 
 * This interface serializes the JSON if it is a valid representation of the 
 * model and produces a valid Java representation and vice versa.
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public interface iSerializer {
    /**
     * Converts a JSON string into <code>Model Container</code> Object. 
     * @pre Serialize requires the JSON string to be a valid representation as 
     * described in the Catan JSON API.
     * @param JSON A valid representation of the model in JSON.
     * @return A <code>ModelContainer</code> object that represents the client 
     * model using Java objects. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     * @throws IOException 
     */
	ClientModelDTO deserializeModel(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the model into a JSON 
     * representation. 
     * @pre Serialize requires the Java object to be a valid 
     * representation as described in the Catan JSON API.
     * @param container A valid representation of the model in Java.
     * @return A JSON string that represents the client model using 
     * JSON. This method will return null if the the container parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeModel(ClientModelDTO container);
    
    /**
     * Converts a JSON string into <code>User</code> Object. 
     * @pre requires the JSON string to be a valid representation of the User object
     * @param JSON A valid representation of the User Object in JSON.
     * @return A <code>User</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    UserDTO deserializeUser(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the User object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the User object
     * @param user A valid representation of the User object in Java.
     * @return A JSON string that represents the User object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeUser(UserDTO user);
    
    /**
     * Converts a JSON string into <code>GameContainer</code> Object. 
     * @pre requires the JSON string to be a valid representation of the GameContainer object
     * @param JSON A valid representation of the GameContainer object in JSON.
     * @return A <code>GameContainer</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    GameContainerDTO deserializeGameContainer(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the GameContainer object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the GameContainer object
     * @param user A valid representation of the GameContainer object in Java.
     * @return A JSON string that represents the GameContainer object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeGameContainer(GameContainerDTO games);
    
    /**
     * Converts a JSON string into <code>Game</code> Object. 
     * @pre requires the JSON string to be a valid representation of the Game object
     * @param JSON A valid representation of the Game object in JSON.
     * @return A <code>Game</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    GameDTO deserializeGame(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the Game object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the Game object
     * @param user A valid representation of the Game object in Java.
     * @return A JSON string that represents the Game object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeGame(GameDTO game);
    
    /**
     * Converts a JSON string into <code>CommandContainer</code> Object. 
     * @pre requires the JSON string to be a valid representation of the CommandContainer object
     * @param JSON A valid representation of the CommandContainer object in JSON.
     * @return A <code>CommandContainer</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    CommandContainerDTO deserializeCommandContainer(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the CommandContainer object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the CommandContainer object
     * @param user A valid representation of the CommandContainer object in Java.
     * @return A JSON string that represents the CommandContainer object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeCommandContainer(CommandContainerDTO commands);
    
    /**
     * Converts a JSON string into <code>AITypesContainer</code> Object. 
     * @pre requires the JSON string to be a valid representation of the AITypesContainer object
     * @param JSON A valid representation of the AITypesContainer object in JSON.
     * @return An <code>AITypesContainer</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    AITypesContainerDTO deserializeAITypesContainer(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the AITypesContainer object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the AITypesContainer object
     * @param user A valid representation of the AITypesContainer object in Java.
     * @return A JSON string that represents the AITypesContainer object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeAITypesContainer(AITypesContainerDTO aIplayers);
    
    /**
     * Converts a JSON string into <code>AIPlayer</code> Object. 
     * @pre requires the JSON string to be a valid representation of the AIPlayer object
     * @param JSON A valid representation of the AIPlayer object in JSON.
     * @return An <code>AIPlayer</code> object. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    AIPlayerDTO deserializeAIPlayer(String JSON) throws IOException;
    
    /**
     * Converts a Java object representation of the AIPlayer object into a JSON 
     * representation. 
     * @pre requires the Java object to be a valid representation of the AIPlayer object
     * @param user A valid representation of the AIPlayer object in Java.
     * @return A JSON string that represents the AIPlayer object using 
     * JSON. This method will return null if the the parameter was
     * not valid (null, missing fields, etc).
     */
    String serializeAIPlayer(AIPlayerDTO aIplayer);
    
    GameToCreateDTO deserializeGameToCreate(String JSON) throws IOException;

	String serializeGameToCreate(GameToCreateDTO game);
    
}
