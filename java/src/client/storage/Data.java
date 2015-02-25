package client.storage;

import shared.models.*;

public class Data {

    private static ClientModel currentModelinstance;

    public static ClientModel getCurentModelInstance() {
        if (currentModelinstance == null) {
            currentModelinstance = new ClientModel();
        }
        return currentModelinstance;
    }
}
