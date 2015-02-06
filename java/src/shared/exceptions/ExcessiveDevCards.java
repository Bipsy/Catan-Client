package shared.exceptions;

/**
 * This exception is thrown whenever an excessive number of development cards is
 * added to a DevCardListDTO
 *
 * @author Austin
 *
 */
@SuppressWarnings("serial")
public class ExcessiveDevCards extends Exception {

    public ExcessiveDevCards() {
        // TODO Auto-generated constructor stub
    }

    public ExcessiveDevCards(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ExcessiveDevCards(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public ExcessiveDevCards(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ExcessiveDevCards(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
