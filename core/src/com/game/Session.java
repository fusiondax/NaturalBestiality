package com.game;

/**
 * A session is an instance of the software running. It mainly contains a Game but also contains the user's settings
 * @author David Janelle
 *
 */
public class Session {
	
	private LogicGame game;
	
	
	public Session()
	{
		game = new LogicGame();
	}


	public LogicGame getGame() {
		return game;
	}

}
