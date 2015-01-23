/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.IOException;
import shared.locations.*;
import shared.definitions.*;

/**
 *
 * @author Jillian Koontz <jpkoontz@gmail.com>
 */
public interface iClientProxy {
    
	void sendChat(String content) throws IOException;
	void acceptTrade(boolean willAccept) throws IOException;
	void discardCards(ResourceList discardedCards) throws IOException; //ResourceList
	int rollNumber();
	void buildRoad(boolean free, EdgeLocation roadLocation) throws IOException;
	void buildSettlement(boolean free, VertexLocation vertexLocation) throws IOException;
	void buildCity(VertexLocation vertexLocation) throws IOException;
	void offerTrade(ResourceList offer, int reciever) throws IOException;
	void maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException;
	void robPlayer(HexLocation location, int victimIndex) throws IOException; //
	void finishTurn() throws IOException;
	void buyDevCard() throws IOException;
	void playSoldier(HexLocation location, int victimIndex) throws IOException;
	void playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IOException;
	void playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IOException;
	void playMonopoly(ResourceType resource) throws IOException;
	void playMonument() throws IOException;
	
	
}
