package client.login;

import client.base.*;
import client.misc.*;
import client.network.ServerProxy;

import java.io.*;
import shared.models.DTO.params.UserCredentials;

import org.javatuples.Pair;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

    private final IMessageView messageView;
    private IAction loginAction;
    private static final ServerProxy proxy = ServerProxy.getInstance();

    /**
     * LoginController constructor
     *
     * @param view Login view
     * @param messageView Message view (used to display error messages that
     * occur during the login process)
     */
    public LoginController(ILoginView view, IMessageView messageView) {

        super(view);

        this.messageView = messageView;
    }

    public ILoginView getLoginView() {

        return (ILoginView) super.getView();
    }

    public IMessageView getMessageView() {

        return messageView;
    }

    /**
     * Sets the action to be executed when the user logs in
     *
     * @param value The action to be executed when the user logs in
     */
    public void setLoginAction(IAction value) {

        loginAction = value;
    }

    /**
     * Returns the action to be executed when the user logs in
     *
     * @return The action to be executed when the user logs in
     */
    public IAction getLoginAction() {

        return loginAction;
    }

    @Override
    public void start() {
        getLoginView().showModal();
    }

    @Override
    public void signIn() {
        try {
            String username = ((ILoginView) this.getView()).getLoginUsername();
            String password = ((ILoginView) this.getView()).getLoginPassword();

            Pair<Boolean, Integer> pair = proxy.login(new UserCredentials(username, password));
            boolean successful = pair.getValue0();

            if (successful) {
                getLoginView().closeModal();
                loginAction.execute();
            } else {
                messageView.setTitle("Login Error");
                messageView.setMessage("Login failed - bad password or username");
                messageView.showModal();
            }
        } catch (IOException ex) {
            System.err.println("Server error");         
        }
    }

    @Override
    public void register() {
        try {
            String username = ((ILoginView) this.getView()).getRegisterUsername();
            String password1 = ((ILoginView) this.getView()).getRegisterPassword();
            String password2 = ((ILoginView) this.getView()).getRegisterPasswordRepeat();
            
            if (username.length() < 3 || username.length() > 7) {
                messageView.setTitle("Register Error");
                messageView.setMessage("Username must be between 3 and "
                        + "7 characters long");
                messageView.showModal();
                return;
            }

            if (!password1.equals(password2)) {
                messageView.setTitle("Register Error");
                messageView.setMessage("Passwords didn't match");
                messageView.showModal();
                return;
            }

            if (password1.length() < 5) {
                messageView.setTitle("Register Error");
                messageView.setMessage("Password must be greater than "
                        + "5 characters long");
                messageView.showModal();
                return;
            }
            
            for (int i = 0; i < password1.length(); i++) {
                char c = password1.charAt(i);
                if (!(Character.isLetterOrDigit(c) || c == '_' || c == '-')) {
                    messageView.setTitle("Register Error");
                    messageView.setMessage("Password can only contain 0-9, a-z, A-Z, _, and -");
                    messageView.showModal();
                    return;
                }
            }

            Pair<Boolean, Integer> pair = proxy.registerNewUser(new UserCredentials(username, password1));
            boolean successful = pair.getValue0();

            if (successful) {
                getLoginView().closeModal();
                loginAction.execute();
            } else {
                // tell view to show errors
                messageView.setTitle("Login Error");
                messageView.setMessage("Login failed - bad password or username");
                messageView.showModal();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
