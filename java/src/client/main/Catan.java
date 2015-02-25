package client.main;

import javax.swing.*;

import client.catan.*;
import client.login.*;
import client.join.*;
import client.misc.*;
import client.base.*;
import client.model.Populator;
import client.network.ProxyAlreadyInstantiated;
import client.network.ServerPoller;
import client.network.ServerProxy;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame {

    private CatanPanel catanPanel;
    private static Timer pollerTimer;

    public Catan(String hostname, String port) {

        client.base.OverlayView.setWindow(this);

        this.setTitle("Settlers of Catan");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        catanPanel = new CatanPanel();
        this.setContentPane(catanPanel);
        initializeNetwork(hostname, port);

        display();
    }
    
    // TODO initialize pr
    private void initializeNetwork(String hostname, String port) {
        try {
            ServerProxy.init(hostname, port);
        } catch (ProxyAlreadyInstantiated ex) {
            System.err.println("Server Proxy was already instantiated");
        }
    }
    
    public static void startPoller(int millis) {
        ServerProxy proxy = ServerProxy.getInstance();
        Populator populator = new Populator();
        ServerPoller poller = new ServerPoller(proxy, populator, 0);
        pollerTimer = new Timer(millis, poller);
    }

    private void display() {
        pack();
        setVisible(true);
    }

    //
    // Main
    //
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (args.length > 1) {
                    new Catan(args[0], args[1]);
                } else {
                    new Catan(null, null);
                }
                
                PlayerWaitingView playerWaitingView = new PlayerWaitingView();
                final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
                        playerWaitingView);
                playerWaitingView.setController(playerWaitingController);

                JoinGameView joinView = new JoinGameView();
                NewGameView newGameView = new NewGameView();
                SelectColorView selectColorView = new SelectColorView();
                MessageView joinMessageView = new MessageView();
                final JoinGameController joinController = new JoinGameController(
                        joinView,
                        newGameView,
                        selectColorView,
                        joinMessageView);
                joinController.setJoinAction(new IAction() {
                    @Override
                    public void execute() {
                        playerWaitingController.start();
                    }
                });
                joinView.setController(joinController);
                newGameView.setController(joinController);
                selectColorView.setController(joinController);
                joinMessageView.setController(joinController);

                LoginView loginView = new LoginView();
                MessageView loginMessageView = new MessageView();
                LoginController loginController = new LoginController(
                        loginView,
                        loginMessageView);
                loginController.setLoginAction(new IAction() {
                    @Override
                    public void execute() {
                        joinController.start();
                    }
                });
                loginView.setController(loginController);
                loginView.setController(loginController);

                
                loginController.start();
            }
        });
    }

}
