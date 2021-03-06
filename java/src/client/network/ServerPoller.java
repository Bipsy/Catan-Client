/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.model.ModelFacade;
import client.model.iPopulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.SwingUtilities;

import shared.exceptions.NoCookieException;
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
    final private ModelFacade facade;

    /**
     * @param proxy The proxy object that the poller will use to update the
     * client model
     *
     * @param populator The facade object that will receive the serialized model
     * @param version The initial version of the client model.
     */
    public ServerPoller(iServerProxy proxy, iPopulator populator) {
        this.facade = new ModelFacade();
        this.serverProxy = proxy;
        this.modelHandle = populator;
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
        if (serverProxy == null) {
            return null;
        }
        try {
            int version = facade.getVersion();
            ClientModelDTO newModel = serverProxy.retrieveCurrentState(new Integer(version));
            return newModel;
        } catch (IOException ex) {
            System.err.println("Error while polling server");
            System.err.println(ex.getLocalizedMessage());
        }
        return null;
    }

    private boolean updateModel(ClientModelDTO model) {
        if (model == null) {
            return false;
        } else {
            String name = null;
            try {
                name = serverProxy.getLocalPlayerName();
            } catch (NoCookieException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return modelHandle.populateModel(model, name);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread pollingThread = new Thread() {
            public void run() {
                final ClientModelDTO newModel = poll();
                if(newModel != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            updateModel(newModel);
                        }
                    });
                }
            }
        };
        pollingThread.start();
    }

}
