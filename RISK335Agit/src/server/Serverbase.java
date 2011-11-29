package server;

public interface Serverbase {

	// make creates a server and starts its processes (listening and adding
	// players to the chat).
	public void make(int port);

	// start game should create the game with the specified number of players
	// starting with clients and filling the rest with AI.
	public void startGame(int players);
	

}
