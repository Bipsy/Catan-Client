/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.model.Populator;
import client.model.Serializer;
import client.model.iPopulator;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.models.DTO.ClientModelDTO;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class ServerPollerTest {

    private byte[] encoded;
    private String defaultModel;
    private Serializer serializer;
    private iServerProxy proxy;
    private iPopulator populator;

    @Before
    public void setUp() {
        try {
            @SuppressWarnings("resource")
            RandomAccessFile f = new RandomAccessFile("assets/test-model-1.txt", "r");
            encoded = new byte[(int) f.length()];
            f.read(encoded);
            defaultModel = new String(encoded, Charset.defaultCharset());
            serializer = new Serializer();
            proxy = new MockServerProxy(serializer, defaultModel);
            populator = Populator.getInstance();
        } catch (IOException ex) {
            System.err.println("JSON file was not found");
        }
    }

    @After
    public void tearDown() {
        encoded = null;
        defaultModel = null;
        serializer = null;
        proxy = null;
        populator = null;
    }

    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void pollNewModelTest() {
        ServerPoller poller = new ServerPoller(proxy, populator, 0);
        ClientModelDTO model = poller.poll();
        assertNotNull(model);
    }

    @Test
    public void pollOldModelTest() {
        ServerPoller poller = new ServerPoller(proxy, populator, 1);
        ClientModelDTO model = poller.poll();
        assertNull(model);
    }
}
