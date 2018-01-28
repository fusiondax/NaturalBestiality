package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import render.MainMenuScreen;

public class NaturalBestiality extends Game {
	
	public World world;
	public SpriteBatch batch;
	public BitmapFont font;
	
	public Box2DDebugRenderer debug;
	
	
	@Override
	public void create () {
		
		initWorld();
		
		debug = new Box2DDebugRenderer();
		
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		
		this.setScreen(new MainMenuScreen(this));
	}
	

	private void initWorld() {
		
		world = new World(new Vector2(0, -10), true);
	}


	@Override
	public void render () {
		
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
