/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.model.ModelContainer;
import client.model.Populator;
import client.model.Serializer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * ServerPoller is used to periodically update the client model by requesting
 * the current JSON representation of the model from the server via the server
 * proxy. ServerPoller also determines if the client and server are out of sync
 * and if so serializes the JSON and delivers it to the model facade.
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class ServerPoller implements ActionListener {
    final private iServerProxy serverProxy;
    final private Serializer serializer;
    final private Populator modelHandle;
    private int version;
    
    /**
     * @param proxy The proxy object that the poller will use to update
     * the client model
     * @param serializer The serializer object that will perform the
     * serialization of the JSON.
     * @param facade The facade object that will receive the serialized model
     */
    public ServerPoller(iServerProxy proxy, Serializer serializer,
                        Populator facade, int version) {
        this.serverProxy = proxy;
        this.serializer = serializer;
        this.modelHandle = facade;
        this.version = version;
    }
    
    /**
     * This method is run periodically to request the updated model from the
     * server. It uses the server proxy to communicate with the server
     * instead of direct communication.
     * @return poll() returns a string that represents the JSON model received
     * from the server via the update model interface. If an error occurs then
     * poll() returns an empty string.
     */
    public String poll() {
        String newModel = "";
        try {
            newModel = serverProxy.retrieveCurrentState(version);
        } catch (IOException ex) {
            System.err.println("Error while polling server");
            System.err.println(ex.getLocalizedMessage());
        }
        return newModel;
    }
    
    /**
     * The isNew method parses the given JSON string to determine if the string
     * is a valid representation of the model and the version number contained
     * therein is greater than the version number stored with the Poller object.
     * Essentially this method determines whether the model needs to be 
     * re-populated with fresh data.
     * @post Updates the version number of the model that the client
     * has received.
     * @param JSON This is a string representation of the model. This should 
     * have been returned to the proxy by the server. 
     * @return isNew return true if the string is a valid representation of the
     * model and it contains a more recent version number. If either of these
     * conditions are not met then isNew() returns false.
     */
    public boolean isNew(String JSON) {
        return false;
    }
    
    /**
     * UpdateModel uses the API of the model facade to update the model. It
     * passes the ModelContainer object to the facade to be used for update.
     * This method should only be called with a container object that has
     * been produced by the serializer. 
     * @param container ModelContainer should be a valid representation of the
     * model (not null). This object will be given to the model facade to 
     * populate the model.
     * @return updateModel returns true if the model facade successfully updated
     * the model using the container. If the container is null or another error
     * occurs then this method returns false.
     */
    private boolean updateModel(ModelContainer container) {
        if (container == null) {
            return false;
        } else {
            return modelHandle.populateModel(container);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newModel = poll();
        if (isNew(newModel)) {
            ModelContainer container = serializer.deserializeModel(newModel);
            updateModel(container);
        }
    }
    
}
