package client.roll;

import client.base.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

    private IRollResultView resultView;
    private final String MESSAGE = "Rolling in %d seconds";
    private final int rollingCount = 3;
    private ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random random = new Random();
            int rollValue = random.nextInt(7);
            getResultView().setRollValue(rollValue);
        }    
    };

    /**
     * RollController constructor
     *
     * @param view Roll view
     * @param resultView Roll result view
     */
    public RollController(IRollView view, IRollResultView resultView) {

        super(view);

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

    @Override
    public void rollDice() {
        String formattedString = String.format(MESSAGE, rollingCount);
        getRollView().setMessage(formattedString);
        Timer rollingTimer = new Timer(rollingCount, timerListener);
        rollingTimer.setInitialDelay(0);
        rollingTimer.setRepeats(false);
        rollingTimer.start();     
        getResultView().showModal();
    }

}
