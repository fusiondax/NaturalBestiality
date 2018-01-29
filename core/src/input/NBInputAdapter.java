package input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game.NaturalBestiality;

import render.GameScreen;
import render.GameScreen.PlayerStance;

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
			//camera movement
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
			//toggle auto-step
			case Keys.P:
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
			//step simulation (only when paused)
			case Keys.L:
			{	
				if(!screen.autoStep)
				{
					game.world.step(1/60f, 6, 2);
				}
				break;
			}
			
			//player standing
			case Keys.S:
			{	
				screen.playerStance = PlayerStance.Standing;
				break;
			}
			//player lying down
			case Keys.C:
			{	
				screen.playerStance = PlayerStance.LyingDown;
				break;
			}
			//player curl into ball
			case Keys.X:
			{	
				screen.playerStance = PlayerStance.CurlIntoBall;
				break;
			}
			//player play dead
			case Keys.Z:
			{	
				screen.playerStance = PlayerStance.PlayDead;
				break;
			}
			//player moving right
			case Keys.D:
			{	
				
				break;
			}
			//player moving left
			case Keys.A:
			{	
				
				break;
			}
			
		}
		return true;
	}

}
