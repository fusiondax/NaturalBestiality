package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.game.NaturalBestiality;

import input.NBInputAdapter;

public class GameScreen implements Screen {
	
	final NaturalBestiality game;
	
	public OrthographicCamera camera;
	
	InputMultiplexer gameInputs;

	public boolean autoStep;
	
	public GameScreen(final NaturalBestiality game)
	{
		this.game = game;
		
		autoStep = false;
		
		camera = new OrthographicCamera(10, 10);
		
		gameInputs = new InputMultiplexer();
		
		gameInputs.addProcessor(new NBInputAdapter(this, game));
		
		Gdx.input.setInputProcessor(gameInputs);
		
		createFloor();
		createPlayerBody();
//		createReferenceBody();
	}
	
	private void createFloor() {
		BodyDef bodyDef = new BodyDef();  
		PolygonShape box = new PolygonShape(); 
		//floor
		bodyDef.position.set(new Vector2(-4.5f, 0));  
		Body groundBody = game.world.createBody(bodyDef); 
		
		box.setAsBox(10, 2f);
		groundBody.createFixture(box, 0.0f); 
		
		
		//0,0 landmark
		bodyDef.position.set(new Vector2(0, 0));  
		Body bodyLandMark00 = game.world.createBody(bodyDef); 
		
		box.setAsBox(0.01f, 0.01f);
		bodyLandMark00.createFixture(box, 0.0f);
		
		//1,1 landmark
		bodyDef.position.set(new Vector2(1, 1));  
		Body bodyLandMark11 = game.world.createBody(bodyDef); 
		
		box.setAsBox(0.01f, 0.01f);
		bodyLandMark11.createFixture(box, 0.0f);
				
		//5,1 landmark
		bodyDef.position.set(new Vector2(5, 1));  
		Body bodyLandMark51 = game.world.createBody(bodyDef); 
		
		box.setAsBox(0.01f, 0.01f);
		bodyLandMark51.createFixture(box, 0.0f);
		
		//0,3 landmark
		bodyDef.position.set(new Vector2(0, 3));  
		Body bodyLandMark03 = game.world.createBody(bodyDef); 
		
		box.setAsBox(0.01f, 0.01f);
		bodyLandMark03.createFixture(box, 0.0f);
		
		
		box.dispose();
	}

	private void createPlayerBody() {
		//generic setup
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; 
		Vector2[] vertices3 = new Vector2[3];
		Vector2[] vertices6 = new Vector2[6];
		
		//Bodies and Fixtures
		
		//tailTip
		//body
		bodyDef.position.set(1f, 2.91f);
		Body bodyTailTip = game.world.createBody(bodyDef);
		
		//lower fixture
		vertices3[0] = new Vector2(0f, -0.09f);
		vertices3[1] = new Vector2(0.30f, -0.18f);
		vertices3[2] = new Vector2(0.24f, -0.09f);
		shape.set(vertices3);
		fixtureDef.shape = shape;
		bodyTailTip.createFixture(fixtureDef);
		
		//upper fixture
		vertices3[0] = new Vector2(0f, -0.09f);
		vertices3[1] = new Vector2(0.24f, -0.09f);
		vertices3[2] = new Vector2(0.30f, 0f);
		shape.set(vertices3);
		fixtureDef.shape = shape;
		bodyTailTip.createFixture(fixtureDef);
		
		//tail 8
		//body
		bodyDef.position.set(1.25f, 2.94f);
		Body bodyTail8 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.11f);
		vertices6[1] = new Vector2(0.14f, -0.22f);
		vertices6[2] = new Vector2(0.53f, -0.22f);
		vertices6[3] = new Vector2(0.6f, -0.11f);
		vertices6[4] = new Vector2(0.53f, 0f);
		vertices6[5] = new Vector2(0.14f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail8.createFixture(fixtureDef);
		
		//tail 7
		//body
		bodyDef.position.set(1.87f, 2.97f);
		Body bodyTail7 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.15f);
		vertices6[1] = new Vector2(0.14f, -0.3f);
		vertices6[2] = new Vector2(0.54f, -0.3f);
		vertices6[3] = new Vector2(0.60f, -0.15f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.14f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail7.createFixture(fixtureDef);
		
		//tail 6
		bodyDef.position.set(2.49f, 3);
		Body bodyTail6 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail6.createFixture(fixtureDef);
		
		//tail 5
		bodyDef.position.set(3.11f, 3);
		Body bodyTail5 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail5.createFixture(fixtureDef);
		
		//tail 4
		bodyDef.position.set(3.77f, 3);
		Body bodyTail4 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail4.createFixture(fixtureDef);
		
		//tail 3
		bodyDef.position.set(4.35f, 3);
		Body bodyTail3 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail3.createFixture(fixtureDef);
		
		//tail 2
		bodyDef.position.set(4.97f, 3);
		Body bodyTail2 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail2.createFixture(fixtureDef);
		
		//tail 1
		bodyDef.position.set(5.59f, 3);
		Body bodyTail1 = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTail1.createFixture(fixtureDef);
		
		//Torso
		bodyDef.position.set(6.21f, 3);
		Body bodyTorso = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.18f);
		vertices6[1] = new Vector2(0.15f, -0.36f);
		vertices6[2] = new Vector2(0.54f, -0.36f);
		vertices6[3] = new Vector2(0.60f, -0.18f);
		vertices6[4] = new Vector2(0.54f, 0f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyTorso.createFixture(fixtureDef);
		
		//Neck
		
		//Head
		
		
		//Joints
		//setup
		RevoluteJointDef jointDef = new RevoluteJointDef();
		
		jointDef.collideConnected = true;
		
		//Tail Tip + Tail 8
		jointDef.initialize(bodyTailTip, bodyTail8, 
			new Vector2(bodyTailTip.getPosition().x + 0.24f, bodyTailTip.getPosition().y -0.09f));
		
		game.world.createJoint(jointDef);
		
		//tail 8 + tail 7
		jointDef.initialize(bodyTail8, bodyTail7, 
			new Vector2(bodyTail8.getPosition().x + 0.60f, bodyTail8.getPosition().y -0.11f));
		
		game.world.createJoint(jointDef);
		
		
		//tail 7 + tail 6
		jointDef.initialize(bodyTail7, bodyTail6, 
			new Vector2(bodyTail7.getPosition().x + 0.60f, bodyTail7.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		//tail 6 + tail 5
		jointDef.initialize(bodyTail6, bodyTail5, 
			new Vector2(bodyTail6.getPosition().x + 0.60f, bodyTail6.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		//tail 5 + tail 4
		jointDef.initialize(bodyTail5, bodyTail4, 
			new Vector2(bodyTail5.getPosition().x + 0.60f, bodyTail5.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		//tail 4 + tail 3
		jointDef.initialize(bodyTail4, bodyTail3, 
			new Vector2(bodyTail4.getPosition().x + 0.60f, bodyTail4.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
				
		//tail 3 + tail 2
		jointDef.initialize(bodyTail3, bodyTail2, 
			new Vector2(bodyTail3.getPosition().x + 0.60f, bodyTail3.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		//tail 2 + tail 1
		jointDef.initialize(bodyTail2, bodyTail1,
			new Vector2(bodyTail2.getPosition().x + 0.60f, bodyTail2.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		//tail 1 + Torso
		jointDef.initialize(bodyTail1, bodyTorso, 
			new Vector2(bodyTail1.getPosition().x + 0.60f, bodyTail1.getPosition().y -0.15f));
		
		game.world.createJoint(jointDef);
		
		
		
		
		
		//mouse joints
		
		//tail Tip
//		MouseJointDef mouseJointDef = new MouseJointDef();
//		mouseJointDef.bodyA = bodyTailTip; 
//		mouseJointDef.bodyB = bodyTail8; 
//
//		MouseJoint mJoint = (MouseJoint) game.world.createJoint(mouseJointDef);
//		mJoint.setTarget(new Vector2(5, 5));
		
		//Tail 8
		
		
		
		//cleanup
		shape.dispose();
	}

//	private void createReferenceBody() {
//		
//		// tail tip
//		BodyDef square = new BodyDef();
//		square.type = BodyType.DynamicBody;
//		square.position.set(2, 5);
//		Body body = game.world.createBody(square);
//		
//		CircleShape shape = new CircleShape();
//
//		shape.setPosition(new Vector2(0, 0));
//		shape.setRadius(6);
//		
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = shape;
//		fixtureDef.density = 0.5f; 
//		fixtureDef.friction = 0.4f;
//		fixtureDef.restitution = 0.6f; 
//	
//		body.createFixture(fixtureDef);
//		
//		shape.dispose();
//	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.debug.render(game.world, camera.combined);
		if(autoStep)
		{
			game.world.step(1/60f, 6, 2);
		}
			

		game.batch.begin();
		
		//batch.draw(img, 0, 0);
		game.batch.end();
	}

	@Override
	public void show() 
	{
		
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
