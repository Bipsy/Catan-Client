package client.model;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import shared.models.DTO.*;
import client.data.GameInfo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * @author Anna Sokolova
 *
 */
public class Serializer implements iSerializer {

    private Gson gson;

    public Serializer() {
        gson = new Gson();
    }

    public String serialize(Object object) {
        return gson.toJson(object);
    }

    public Object deserialize(String JSON) {
        return gson.fromJson(JSON, Object.class);
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
    
    public GameInfo deserializeGameInfo(String JSON) {
    	return gson.fromJson(JSON, GameInfo.class);
    }

	public List<GameInfo> deserializeGameInfoList(String JSON) {
		System.out.println(JSON);
		Type listType = new TypeToken<ArrayList<GameInfo>>() {
        }.getType();
        return gson.fromJson(JSON, listType);
	}

}
