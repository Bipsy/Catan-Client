package client.login;

import client.base.*;
import client.misc.*;
import client.network.ServerProxy;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import shared.models.DTO.params.UserCredentials;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

    private IMessageView messageView;
    private IAction loginAction;
    private ServerProxy proxy = new ServerProxy();

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
    	String username = ((ILoginView) this.getView()).getLoginUsername();
    	String password = ((ILoginView) this.getView()).getLoginPassword();
    	
    	//TODO: change proxy to return boolean. true == success, false == failure
    	Boolean successful = proxy.login(new UserCredentials(username, password);
    	
		if (successful) {
	        getLoginView().closeModal();
	        loginAction.execute();
		} else {
			// tell view to show errors
			messageView.setTitle("Login Error");
			messageView.setMessage("Login failed - bad password or username");
			messageView.showModal();
		}
    }

    @Override
    public void register() {
    	String username = ((ILoginView) this.getView()).getLoginUsername();
    	String password1 = ((ILoginView) this.getView()).getLoginPassword();
    	String password2 = ((ILoginView) this.getView()).getLoginPassword();
    	
    	if (password1 != password2) {
    		//show errors
    	}
    	
    	//TODO: check for non approved characters
    	if (password1.length() < 3 || password1.length() > 7) {
    		//show errors
    	}
    	
    	Boolean successful = proxy.login(new UserCredentials(username, password);
    	
    	if (successful) {
	        getLoginView().closeModal();
	        loginAction.execute();
		} else {
			// tell view to show errors
			messageView.setTitle("Login Error");
			messageView.setMessage("Login failed - bad password or username");
			messageView.showModal();
		}

    }

}
