package client.storage;

import shared.models.*;

public class Data {
	
	private ClientModel currentModel;
	
	
	public static ClientModel getCurrentModel() {
		Data data = new Data();
		return data.currentModel;
	}
	
	public static void updateCurrentModel(ClientModel model) {
		Data data = new Data();
		data.currentModel = model;
	}
}
