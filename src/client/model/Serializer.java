/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 * Serializer interface describes the API for converting JSON to Java objects. 
 * This interface serializes the JSON if it is a valid representation of the 
 * model and produces a valid Java representation and vice versa.
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public interface Serializer {
    /**
     * Converts a JSON string into <code>Model Container</code> Object. 
     * @pre Serialize requires the JSON string to be a valid representation as 
     * described in the Catan JSON API.
     * @param JSON A valid representation of the model in JSON.
     * @return A <code>ModelContainer</code> object that represents the client 
     * model using Java objects. This method will return null if the the JSON 
     * parameter was not valid (null, missing fields, etc).
     */
    ModelContainer deserialize(String JSON);
    
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
    String serialize(ModelContainer container);
}
