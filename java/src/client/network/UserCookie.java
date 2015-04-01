package client.network;

public class UserCookie {

	private String name = "";
	private String password = "";
	private int playerID = -1;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Cookie Name: " + name + "\n");
            builder.append("Cookie Password: " + password + "\n");
            builder.append("Cookie Player ID: " + playerID + "\n");
            return builder.toString();
        }
	
}
