package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.game.NaturalBestiality;

public class GameScreen implements Screen {
	
	final NaturalBestiality game;
	
	OrthographicCamera camera;
	
	
	public GameScreen(final NaturalBestiality game)
	{
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		createStaticBody();
		createDynamicBody();
		createKinematicBody();
		
	}
	

	private void createKinematicBody() {
		// TODO Auto-generated method stub
		
	}


	private void createDynamicBody() {
		// TODO Auto-generated method stub
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(100, 300);

		// Create our body in the world using our body definition
		Body body = game.world.createBody(bodyDef);

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
	}


	private void createStaticBody() {
		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();  
		// Set its world position
		groundBodyDef.position.set(new Vector2(0, 10));  

		// Create a body from the defintion and add it to the world
		Body groundBody = game.world.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(camera.viewportWidth, 10.0f);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 
		// Clean up after ourselves
		groundBox.dispose();
	}


	@Override
	public void show() 
	{
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.debug.render(game.world, camera.combined);
		game.world.step(1/60f, 6, 2);
		game.batch.begin();
		
		//batch.draw(img, 0, 0);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
