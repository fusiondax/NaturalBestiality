package com.game;

import physics.PhysicsEnvironment;

/**
 * an instance of the currently played game. it is the root of all of the data structures required for game logics and algorythms.
 * @author David Janelle
 *
 */
public class LogicGame {
	
	private PhysicsEnvironment enviro;
	
	public LogicGame()
	{
		enviro = new PhysicsEnvironment();
	}

	public PhysicsEnvironment getEnviro() {
		return enviro;
	}

}
