package client.domestic;

import client.model.ModelFacade;
import client.network.ServerProxy;

public abstract class DomesticTradeState {

	ServerProxy proxy = ServerProxy.getInstance();
	ModelFacade facade = new ModelFacade();
	
	//default methods
	void startTrade() {}
	
	void sendTradeOffer() {}
	
	void acceptTrade() {
		
	}
	
	public static class Playing extends DomesticTradeState {
		void startTrade() {
        	System.out.println("BAYBAYBAYBAYBAYBAYBAYBAY");
		}
		
		void sendTradeOffer() {
			
		}
		
		void acceptTrade() {}
	}
}


