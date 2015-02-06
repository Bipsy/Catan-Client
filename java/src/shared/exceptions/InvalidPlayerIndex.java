package shared.exceptions;

/**
 * This exception is thrown whenever a player index is outside the range 0 - 3
 *
 * @author Austin
 *
 */
@SuppressWarnings("serial")
public class InvalidPlayerIndex extends Exception {

    public InvalidPlayerIndex() {
        // TODO Auto-generated constructor stub
    }

    public InvalidPlayerIndex(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public InvalidPlayerIndex(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public InvalidPlayerIndex(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    public InvalidPlayerIndex(String arg0, Throwable arg1, boolean arg2,
            boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

}
