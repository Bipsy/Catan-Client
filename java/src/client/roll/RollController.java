package client.roll;

import client.base.*;
import client.model.ModelFacade;
import client.model.Populator;
import client.network.ServerProxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.Timer;

import shared.exceptions.NoCookieException;
import shared.models.DTO.params.RollNumber;

/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {
	
	private ModelFacade facade;
    private static ServerProxy proxy = ServerProxy.getInstance();

    private IRollResultView resultView;
    private final String MESSAGE = "Rolling in %d seconds";
    private int countDown = 3;
    private Timer rollingTimer;
    
    private ActionListener timerListener = new ActionListener() {
    	   	
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(getRollView().isModalShowing()) {
        		getRollView().setMessage(String.format(MESSAGE, --countDown));
        		getRollView().showModal();
        		if(countDown == 0) {
        			System.out.println("Roll from timer");
	        		rollDice();
        		}
        	}
        }    
    };
    
    private int rollDice(int sides, int times) {
    	
    	
		Random random = new Random();
    	
		int total = 0;
    	for (int i = 0; i < times; i++) {
			total += random.nextInt(sides) + 1;
		}
    	
    	return total;
    }

    /**
     * RollController constructor
     *
     * @param view Roll view
     * @param resultView Roll result view
     */
    public RollController(IRollView view, IRollResultView resultView) {

        super(view);
        
        Populator.getInstance().addObserver(this);

        setResultView(resultView);
    }

    public IRollResultView getResultView() {
        return resultView;
    }

    public void setResultView(IRollResultView resultView) {
        this.resultView = resultView;
    }

    public IRollView getRollView() {
        return (IRollView) getView();
    }
    
    protected void showRollModal() {
    	System.out.println("Showing roll modal");

		getRollView().setMessage(String.format(MESSAGE, countDown));
		getRollView().showModal();
		rollingTimer = new Timer(1000, timerListener);
		rollingTimer.setInitialDelay(1000);
		rollingTimer.setRepeats(true);
		rollingTimer.start();
    }

    @Override
    public void rollDice() {
    	System.out.println("rolling dice");
    	getRollView().closeModal();
    	System.out.println("close modal");
    	rollingTimer.stop();
    	System.out.println("stop timer");
		countDown = 3;
    	System.out.println("reinit count");
    	int rollValue = rollDice(6,2);
    	do {
    		rollValue = rollDice(6,2);
    	} while (rollValue == 7);
    	System.out.println("roll dice");
		getResultView().setRollValue(rollValue);
    	System.out.println("set roll value");
		getResultView().showModal();
    	System.out.println("show value");
		RollNumber request = new RollNumber(facade.getLocalPlayerIndex(), rollValue);
    	System.out.println("create request");
		try {
			Populator.getInstance().populateModel(proxy.rollNumber(request), proxy.getLocalPlayerName());
	    	System.out.println("sent to server");
		} catch (IOException | NoCookieException e) {
			System.err.println(e.toString());
		}
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Populator && arg instanceof ModelFacade) {
        	facade = (ModelFacade)arg;
        	if(facade.isLocalPlayerTurn() && facade.getState().toLowerCase().equals("rolling")) {
        		showRollModal();
        	}            
        }
    }
}
