package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;
import com.game.NaturalBestiality;

import input.NBInputAdapter;
import physics.JointUtility;
import physics.PhysicsEntity;

public class GameScreen implements Screen {
	
	public Array<PhysicsEntity> entities;
	
	final NaturalBestiality game;
	
	public OrthographicCamera camera;
	
	InputMultiplexer gameInputs;

	public boolean autoStep;
	
	public PlayerStance playerStance;
	
	public static final float PLAYER_MUSCLE_ANGULAR_VELOCITY = (float)Math.PI/5;
	public static final float PLAYER_MUSCLE_MAX_TORQUE = 10f;
	
	public enum PlayerStance
	{
		Standing, LyingDown, CurlIntoBall, PlayDead;
	}
	
	public GameScreen(final NaturalBestiality game)
	{
		this.game = game;
		
		autoStep = false;
		
		entities = new Array<PhysicsEntity>();
		
		camera = new OrthographicCamera(10, 10);
		
		camera.translate(new Vector2(5, 2));
		
		gameInputs = new InputMultiplexer();
		
		gameInputs.addProcessor(new NBInputAdapter(this, game));
		
		Gdx.input.setInputProcessor(gameInputs);
		
		playerStance = PlayerStance.PlayDead;
		
		createFloor();
		createPlayerBody();
//		createReferenceBody();
	}
	
	private void createFloor() {
		BodyDef bodyDef = new BodyDef();  
		PolygonShape box = new PolygonShape(); 
		//floor
		bodyDef.position.set(new Vector2(0f, 0));  
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
		
//		//0,3 landmark
//		bodyDef.position.set(new Vector2(0, 3));  
//		Body bodyLandMark03 = game.world.createBody(bodyDef); 
//		
//		box.setAsBox(0.01f, 0.01f);
//		bodyLandMark03.createFixture(box, 0.0f);
		
		
		box.dispose();
	}

	private void createPlayerBody() {
		//Bodies and Fixtures
		//generic setup
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; 
		Vector2[] vertices3 = new Vector2[3];
		Vector2[] vertices4 = new Vector2[4];
		Vector2[] vertices5 = new Vector2[5];
		Vector2[] vertices6 = new Vector2[6];
		Vector2[] vertices7 = new Vector2[7];
		
		Array<Body> bodies = new Array<Body>();
		MassData mData = new MassData();
		mData.mass = 0.5f;
		
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
		
		bodyTailTip.setMassData(mData);
		
		
		bodies.add(bodyTailTip);
		
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
		
		bodies.add(bodyTail8);
		
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
		
		bodies.add(bodyTail7);
		
		//tail 6
		//body
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
		
		bodies.add(bodyTail6);
		
		//tail 5
		//body
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
		
		bodies.add(bodyTail5);
		
		//tail 4
		//body
		bodyDef.position.set(3.73f, 3);
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
		
		bodies.add(bodyTail4);
		
		//tail 3
		//body
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
		
		bodies.add(bodyTail3);
		
		//tail 2
		//body
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
		
		bodies.add(bodyTail2);
		
		//tail 1
		//body
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
		
		bodies.add(bodyTail1);
		
		//Torso
		//body
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
		
		bodies.add(bodyTorso);
		
		//Neck
		//body
		bodyDef.position.set(6.83f, 2.97f);
		Body bodyNeck = game.world.createBody(bodyDef);
		
		//fixture
		vertices6[0] = new Vector2(0f, -0.15f);
		vertices6[1] = new Vector2(0.15f, -0.30f);
		vertices6[2] = new Vector2(0.54f, -0.28f);
		vertices6[3] = new Vector2(0.60f, -0.15f);
		vertices6[4] = new Vector2(0.54f, -0.02f);
		vertices6[5] = new Vector2(0.15f, 0f);
		shape.set(vertices6);
		fixtureDef.shape = shape;
		bodyNeck.createFixture(fixtureDef);
		
		bodies.add(bodyNeck);
		
		//Head
		//body
		bodyDef.position.set(7.45f, 3.03f);
		Body bodyHead = game.world.createBody(bodyDef);
		
		//backhead fixture
		vertices7[0] = new Vector2(0f, -0.21f);
		vertices7[1] = new Vector2(0.11f, -0.35f);
		vertices7[2] = new Vector2(0.25f, -0.41f);
		vertices7[3] = new Vector2(0.33f, -0.28f);
		vertices7[4] = new Vector2(0.37f, -0.18f);
		vertices7[5] = new Vector2(0.28f, 0f);
		vertices7[6] = new Vector2(0.11f, -0.05f);
		shape.set(vertices7);
		fixtureDef.shape = shape;
		bodyHead.createFixture(fixtureDef);
		
		//fronthead Fixture
		vertices5[0] = new Vector2(0.28f, 0f);
		vertices5[1] = new Vector2(0.37f, -0.18f);
		vertices5[2] = new Vector2(0.67f, -0.20f);
		vertices5[3] = new Vector2(0.63f, -0.08f);
		vertices5[4] = new Vector2(0.46f, -0.02f);
		shape.set(vertices5);
		fixtureDef.shape = shape;
		bodyHead.createFixture(fixtureDef);
		
		bodies.add(bodyHead);
		
		//Jaw
		//body
		bodyDef.position.set(7.78f, 2.73f);
		Body bodyJaw = game.world.createBody(bodyDef);
		
		//back jaw fixture 
		vertices3[0] = new Vector2(0f, 0f);
		vertices3[1] = new Vector2(0.07f, -0.13f);
		vertices3[2] = new Vector2(0.11f, -0.08f);
		shape.set(vertices3);
		fixtureDef.shape = shape;
		bodyJaw.createFixture(fixtureDef);
		
		//front jaw fixture
		vertices4[0] = new Vector2(0.07f, -0.13f);
		vertices4[1] = new Vector2(0.28f, -0.15f);
		vertices4[2] = new Vector2(0.37f, -0.07f);
		vertices4[3] = new Vector2(0.11f, -0.08f);
		shape.set(vertices4);
		fixtureDef.shape = shape;
		bodyJaw.createFixture(fixtureDef);
		
		bodies.add(bodyJaw);
		
		//Joints
		//generic setup
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.collideConnected = false;
		jointDef.maxMotorTorque = PLAYER_MUSCLE_MAX_TORQUE;
//		jointDef.enableLimit = true;
//		jointDef.lowerAngle = -1.00f;
//		jointDef.upperAngle = 1.00f;
		
		Array<Joint> joints = new Array<Joint>();
		
		//Tail Tip + Tail 8
		jointDef.initialize(bodyTailTip, bodyTail8, 
			new Vector2(bodyTailTip.getPosition().x + 0.24f, bodyTailTip.getPosition().y -0.09f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 8 + tail 7
		jointDef.initialize(bodyTail8, bodyTail7, 
			new Vector2(bodyTail8.getPosition().x + 0.60f, bodyTail8.getPosition().y -0.11f));
		
		joints.add(game.world.createJoint(jointDef));
		
		
		//tail 7 + tail 6
		jointDef.initialize(bodyTail7, bodyTail6, 
			new Vector2(bodyTail7.getPosition().x + 0.60f, bodyTail7.getPosition().y -0.15f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 6 + tail 5
		jointDef.initialize(bodyTail6, bodyTail5, 
			new Vector2(bodyTail6.getPosition().x + 0.60f, bodyTail6.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 5 + tail 4
		jointDef.initialize(bodyTail5, bodyTail4, 
			new Vector2(bodyTail5.getPosition().x + 0.60f, bodyTail5.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 4 + tail 3
		jointDef.initialize(bodyTail4, bodyTail3, 
			new Vector2(bodyTail4.getPosition().x + 0.60f, bodyTail4.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
				
		//tail 3 + tail 2
		jointDef.initialize(bodyTail3, bodyTail2, 
			new Vector2(bodyTail3.getPosition().x + 0.60f, bodyTail3.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 2 + tail 1
		jointDef.initialize(bodyTail2, bodyTail1,
			new Vector2(bodyTail2.getPosition().x + 0.60f, bodyTail2.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//tail 1 + Torso
		jointDef.initialize(bodyTail1, bodyTorso, 
			new Vector2(bodyTail1.getPosition().x + 0.60f, bodyTail1.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//torso + neck
		jointDef.initialize(bodyTorso, bodyNeck, 
			new Vector2(bodyTorso.getPosition().x + 0.60f, bodyTorso.getPosition().y -0.18f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//Neck + Head
		jointDef.initialize(bodyNeck, bodyHead, 
			new Vector2(bodyNeck.getPosition().x + 0.60f, bodyNeck.getPosition().y -0.15f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//Head + Jaw
		jointDef.initialize(bodyHead, bodyJaw, 
				new Vector2(bodyHead.getPosition().x + 0.32f, bodyHead.getPosition().y -0.30f));
		
		joints.add(game.world.createJoint(jointDef));
		
		//mouse joints
		
		//tail Tip
//		MouseJointDef mouseJointDef = new MouseJointDef();
//		mouseJointDef.bodyA = bodyTailTip; 
//		mouseJointDef.bodyB = bodyTail8; 
//
//		MouseJoint mJoint = (MouseJoint) game.world.createJoint(mouseJointDef);
//		mJoint.setTarget(new Vector2(5, 5));
		
		//Tail 8

		PhysicsEntity physicsEntity = new PhysicsEntity(bodies, joints);
		
		bodyTailTip.setUserData(physicsEntity);
		bodyTail8.setUserData(physicsEntity);
		bodyTail7.setUserData(physicsEntity);
		bodyTail6.setUserData(physicsEntity);
		bodyTail5.setUserData(physicsEntity);
		bodyTail4.setUserData(physicsEntity);
		bodyTail3.setUserData(physicsEntity);
		bodyTail2.setUserData(physicsEntity);
		bodyTail1.setUserData(physicsEntity);
		bodyTorso.setUserData(physicsEntity);
		bodyNeck.setUserData(physicsEntity);
		bodyHead.setUserData(physicsEntity);
		bodyJaw.setUserData(physicsEntity);
		
		entities.add(physicsEntity);
		
		//cleanup
		shape.dispose();
	}

	/**
	 * call this method to update the motor joints on the player to try to get into a "standing" posture
	 * 
	 * for now, we assume the player is looking right
	 */
	private void updatePlayerStanding()
	{
		Array<RevoluteJoint> localJoints = new Array<RevoluteJoint>();
		Array<Body> localBodies = new Array<Body>();
		//set all joints on body to be motorized
		for(Joint j : entities.get(0).joints)
		{
			RevoluteJoint rj = (RevoluteJoint)j;
			
			rj.enableMotor(true);
			localJoints.add(rj);
		}
		
		for(Body b : entities.get(0).bodyParts)
		{
			localBodies.add(b);
		}
		//macro step 1: tail
		//body parts tail tip, tail 8, tail 7 and tail 6 
		//should be resting on the ground
		
		//micro step 1: detection
		//are tail tip, tail 8, tail 7 and tail 6 in contact with a static surface?
		float ttJt8 = -(float)Math.PI/10, 
				t8Jt7 = (float)Math.PI/8, 
				t7Jt6 = 0f,
				t6Jt5 = (float)Math.PI/3,
				t5Jt4 = (float)Math.PI/3,
				t4Jt3 = (float)Math.PI/3,
				t3Jt2 = -(float)Math.PI/8,
				t2Jt1 = -(float)Math.PI/4,
				t1Jtor = -(float)Math.PI/4,
				torJnec = -(float)Math.PI/3,
				necJhea = -(float)Math.PI/8;
//		localJoints.get(0).enableLimit(true);
//		localJoints.get(0).setLimits(ttJt8, ttJt8);
//		
//		localJoints.get(1).enableLimit(true);
//		localJoints.get(1).setLimits(t8Jt7, t8Jt7);
//		
//		localJoints.get(2).enableLimit(true);
//		localJoints.get(2).setLimits(t7Jt6, t7Jt6);
//		
//		localJoints.get(3).enableLimit(true);
//		localJoints.get(3).setLimits(t6Jt5, t6Jt5);
//		
//		localJoints.get(4).enableLimit(true);
//		localJoints.get(4).setLimits(t5Jt4, t5Jt4);
//		
//		localJoints.get(5).enableLimit(true);
//		localJoints.get(5).setLimits(t4Jt3, t4Jt3);
//		
//		localJoints.get(6).enableLimit(true);
//		localJoints.get(6).setLimits(t3Jt2, t3Jt2);
		
		if(!JointUtility.isAngleSimilar(localJoints.get(0), ttJt8))
		{
			System.out.println("1");
			//setting tail tip / tail 8 angle
			localJoints.get(0).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(0), ttJt8));
		}
		else
		{
			localJoints.get(0).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(1), t8Jt7))
		{
			System.out.println("2");
			//setting tail 8 / tail 7 angle
			localJoints.get(1).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(1), t8Jt7));
		}
		else
		{
			localJoints.get(1).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(2), t7Jt6))
		{
			System.out.println("3");
			//setting tail 7 / tail 6 angle
			localJoints.get(2).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(2), t7Jt6));
		}
		else
		{
			localJoints.get(2).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(3), t6Jt5))
		{
			System.out.println("4");
			//setting tail 6 / tail 5 angle
			localJoints.get(3).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(3), t6Jt5));
		}
		else
		{
			localJoints.get(3).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(4), t5Jt4))
		{
			System.out.println("5");
			//setting tail 5 / tail 4 angle
			localJoints.get(4).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(4), t5Jt4));
		}
		else
		{
			localJoints.get(4).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(5), t4Jt3))
		{
			System.out.println("6");
			//setting tail 4 / tail 3 angle
			localJoints.get(5).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(5), t4Jt3));
		}
		else
		{
			localJoints.get(5).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(6), t3Jt2))
		{
			System.out.println("7");
			//setting tail 3 / tail 2 angle
			localJoints.get(6).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(6), t3Jt2));
		}
		else
		{
			localJoints.get(6).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(7), t2Jt1))
		{
			System.out.println("8");
			//setting tail 2 / tail 1 angle
			localJoints.get(7).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(7), t2Jt1));
		}
		else
		{
			localJoints.get(7).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(8), t1Jtor))
		{
			System.out.println("9");
			//setting tail 1 / torso angle
			localJoints.get(8).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(8),t1Jtor));
		}
		else
		{
			localJoints.get(8).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(9), torJnec))
		{
			System.out.println("10");
			//setting torso / neck angle
			localJoints.get(9).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(9),torJnec ));
		}
		else
		{
			localJoints.get(9).setMotorSpeed(0f);
		}
		if(!JointUtility.isAngleSimilar(localJoints.get(10), necJhea))
		{
			System.out.println("11");
			//setting neck / head angle
			localJoints.get(10).setMotorSpeed(JointUtility.maintainAngle(localJoints.get(10),necJhea));
		}
		else
		{
			localJoints.get(10).setMotorSpeed(0f);
		}
		

		
		
		//macro step 2: lower body
		//body parts tail 5, tail 4 and tail 3
		//should curve backward to elevate the rest of the body
		
		
		
		
		//macro step 3: upper body
		//body parts tail 2, tail 1 and torso
		//should curve forward to bring the torso and head up in front
		
		
		
		
		//macro step 4: neck and head
		//body parts Neck and Head
		//should stand still to keep the head centered
		
		
		
		
	}
	
	/**
	 * call this method to update the motor joints on the player to try to get into a "lyingDown" posture
	 */
	private void updatePlayerLyingDown()
	{
		for(Joint j : entities.get(0).joints)
		{
			RevoluteJoint rj = (RevoluteJoint)j;
			rj.enableMotor(true);
			rj.setMotorSpeed(0f);
		}
	}
	
	/**
	 * call this method to update the motor joints on the player to try to get into a "curlIntoBall" posture
	 */
	private void updatePlayerCurlIntoBall()
	{
		for(Joint j : entities.get(0).joints)
		{
			RevoluteJoint rj = (RevoluteJoint)j;
			rj.enableMotor(true);
			rj.setMotorSpeed(-PLAYER_MUSCLE_ANGULAR_VELOCITY);
		}
		
	}
	
	/**
	 * call this method to update the motor joints on the player to try to get into a "playDead" posture
	 */
	private void updatePlayerPlayDead()
	{
		for(Joint j : entities.get(0).joints)
		{
			RevoluteJoint rj = (RevoluteJoint)j;
			rj.enableMotor(false);
			rj.setMotorSpeed(0f);
		}
		
	}

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
		
		switch(playerStance)
		{
		case Standing:
			updatePlayerStanding();
			break;
		case LyingDown:
			updatePlayerLyingDown();
			break;
		case CurlIntoBall:
			updatePlayerCurlIntoBall();
			break;
		case PlayDead:
			updatePlayerPlayDead();
			break;
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
