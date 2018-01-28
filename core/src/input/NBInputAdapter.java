package input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game.NaturalBestiality;

import render.GameScreen;

public class NBInputAdapter extends InputAdapter {
	
	final GameScreen screen;
	final NaturalBestiality game;
	
	final OrthographicCamera camera;
	
	public NBInputAdapter(GameScreen screen, NaturalBestiality game) {
		
		this.screen = screen;
		
		this.camera = screen.camera;
		
		this.game = game;
		
	}
	
	@Override
	public boolean scrolled(int amount)
	{
		if(amount == 1 && camera.zoom < 6)
		{
			camera.zoom += 0.2;
		}
		else if(amount == -1 && camera.zoom > 0.4)
		{
			camera.zoom -= 0.2;
		}
		
		return true;
	}
	
	@Override
	public boolean keyDown(int key)
	{
		switch(key)
		{
			case Keys.LEFT:
			{
				camera.translate(-0.2f, 0);
				break;
			}
			case Keys.RIGHT:
			{
				camera.translate(0.2f, 0);
				break;
			}
			case Keys.UP:
			{
				camera.translate(0, 0.2f);
				break;
			}
			case Keys.DOWN:
			{
				camera.translate(0, -0.2f);
				break;
			}
			case Keys.S:
			{
				if(screen.autoStep)
				{
					screen.autoStep = false;
				}
				else
				{
					screen.autoStep = true;
				}
				break;
			}
			case Keys.D:
			{	
				if(!screen.autoStep)
				{
					game.world.step(1/60f, 6, 2);
				}
				break;
			}
		}
		return true;
	}

}
