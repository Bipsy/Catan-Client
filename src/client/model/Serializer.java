package client.model;

import java.io.IOException;

import shared.models.DTO.*;

import com.google.gson.*;

public class Serializer implements iSerializer  {
	
	private Gson gson;

	public Serializer(){
		gson = new Gson();
	}

	public ClientModelDTO deserializeModel(String JSON) throws IOException {
		return gson.fromJson(JSON, ClientModelDTO.class);  
	}

	public String serializeModel(ClientModelDTO container) {
		return gson.toJson(container);
	}

	public UserDTO deserializeUser(String JSON) throws IOException {
		return gson.fromJson(JSON, UserDTO.class);
	}

	public String serializeUser(UserDTO user) {
		return gson.toJson(user);
	}

	public GameContainerDTO deserializeGameContainer(String JSON) throws IOException {
		return gson.fromJson(JSON, GameContainerDTO.class);
	}

	public String serializeGameContainer(GameContainerDTO games) {
		return gson.toJson(games);
	}

	public GameDTO deserializeGame(String JSON) throws IOException {
		return gson.fromJson(JSON, GameDTO.class);
	}

	public String serializeGame(GameDTO game) {
		return gson.toJson(game);
	}

	public CommandContainerDTO deserializeCommandContainer(String JSON) throws IOException {
		return gson.fromJson(JSON, CommandContainerDTO.class);
	}

	public String serializeCommandContainer(CommandContainerDTO commands) {
		return gson.toJson(commands);
	}

	public AITypesContainerDTO deserializeAITypesContainer(String JSON) throws IOException {
		return gson.fromJson(JSON, AITypesContainerDTO.class);
	}

	public String serializeAITypesContainer(AITypesContainerDTO aIplayers) {
		return gson.toJson(aIplayers);
	}

	public AIPlayerDTO deserializeAIPlayer(String JSON) throws IOException {
		return gson.fromJson(JSON, AIPlayerDTO.class);
	}

	public String serializeAIPlayer(AIPlayerDTO aIplayer) {
		return gson.toJson(aIplayer);
	}
	
	public GameToCreateDTO deserializeGameToCreate(String JSON) throws IOException {
		return gson.fromJson(JSON, GameToCreateDTO.class);
	}

	public String serializeGameToCreate(GameToCreateDTO game) {
		return gson.toJson(game);
	}
	

}
