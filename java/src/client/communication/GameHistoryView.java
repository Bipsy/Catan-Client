<<<<<<< HEAD:java/src/client/communication/GameHistoryView.java
package client.communication;

import java.util.List;
import java.awt.*;

import javax.swing.*;

import client.base.*;

/**
 * Game history view implementation
 */
@SuppressWarnings("serial")
public class GameHistoryView extends PanelView implements IGameHistoryView {

    private LogComponent logPanel;
    private JScrollPane logScroll;

    public GameHistoryView() {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        logPanel = new LogComponent();

        logScroll = new JScrollPane(logPanel);

        this.add(logScroll, BorderLayout.CENTER);
    }

    @Override
    public IGameHistoryController getController() {
        return (IGameHistoryController) super.getController();
    }

    @Override
    public void setEntries(final List<LogEntry> entries) {

        // Can't set entries immediately, because logPanel doesn't
        // have a width or height yet, which messes up the word wrap
        // calculations in LogComponent. Therefore, we call
        // invokeLater.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logPanel.setEntries(entries);
                JScrollBar vertical = logScroll.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            }
        });
    }

}
=======
package client.communication;

import java.util.List;
import java.awt.*;

import javax.swing.*;

import client.base.*;

/**
 * Game history view implementation
 */
@SuppressWarnings("serial")
public class GameHistoryView extends PanelView implements IGameHistoryView {

    private LogComponent logPanel;
    private JScrollPane logScroll;

    public GameHistoryView() {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        logPanel = new LogComponent();

        logScroll = new JScrollPane(logPanel);

        this.add(logScroll, BorderLayout.CENTER);
    }

    @Override
    public IGameHistoryController getController() {
        return (IGameHistoryController) super.getController();
    }

    @Override
    public void setEntries(final List<LogEntry> entries) {

		// Can't set entries immediately, because logPanel doesn't
        // have a width or height yet, which messes up the word wrap
        // calculations in LogComponent. Therefore, we call
        // invokeLater.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logPanel.setEntries(entries);
                JScrollBar vertical = logScroll.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            }
        });
    }

}
>>>>>>> d7ac12c8aed2ad9d15af7283b9f48b2bd6e7f9e2:java/client/communication/GameHistoryView.java
