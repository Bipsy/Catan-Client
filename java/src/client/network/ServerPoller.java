/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.model.iPopulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import shared.models.DTO.ClientModelDTO;

/**
 * ServerPoller is used to periodically update the client model by requesting
 * the current JSON representation of the model from the server via the server
 * proxy. ServerPoller also determines if the client and server are out of sync
 * and if so serializes the JSON and delivers it to the model facade.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class ServerPoller implements ActionListener {

    final private iServerProxy serverProxy;

    final private iPopulator modelHandle;
    private int version;

    /**
     * @param proxy The proxy object that the poller will use to update the
     * client model
     *
     * @param facade The facade object that will receive the serialized model
     * @param version The initial version of the client model.
     */
    public ServerPoller(iServerProxy proxy, iPopulator facade, int version) {

        this.serverProxy = proxy;
        this.modelHandle = facade;
        this.version = version;
    }

    /**
     * This method is run periodically to request the updated model from the
     * server. It uses the server proxy to communicate with the server instead
     * of direct communication.
     *
     * @return poll() returns a string that represents the JSON model received
     * from the server via the update model interface. If an error occurs then
     * poll() returns an empty string.
     */
    public ClientModelDTO poll() {
        try {
            ClientModelDTO newModel = serverProxy.retrieveCurrentState(new Integer(version));
            return newModel;
        } catch (IOException ex) {
            System.err.println("Error while polling server");
            System.err.println(ex.getLocalizedMessage());
        }
        return null;
    }

    private boolean isNew(ClientModelDTO model) {
        if (model == null) {
            return false;
        } else if (model.getVersion() > version) {
            version = model.getVersion();
            return true;
        } else {
            return false;
        }
    }

    private boolean updateModel(ClientModelDTO model) {
        if (model == null) {
            return false;
        } else {
            return modelHandle.populateModel(model);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientModelDTO newModel = poll();
        if (isNew(newModel)) {
            updateModel(newModel);
        }
    }

}
