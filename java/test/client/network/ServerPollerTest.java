/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import client.model.Populator;
import client.model.Serializer;
import client.model.iPopulator;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import shared.models.DTO.ClientModelDTO;

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

    public ServerPollerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            RandomAccessFile f = new RandomAccessFile("assets/test-model-1.txt", "r");
            encoded = new byte[(int) f.length()];
            f.read(encoded);
            defaultModel = new String(encoded, Charset.defaultCharset());
            serializer = new Serializer();
            proxy = new MockServerProxy(serializer, defaultModel);
            populator = new Populator();
        } catch (IOException ex) {
            System.err.println("JSON file was not found");
            assertTrue(false);
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

    // TODO add test methods here.
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
