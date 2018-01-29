package physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.utils.Array;

/**
 * Container for Box2D Bodies and Joints representing an in-game entity. Such as the players and other creatures
 * @author David Janelle
 *
 */
public class PhysicsEntity {
	
	public Array<Body> bodyParts;
	public Array<Joint> joints;
	
	public PhysicsEntity(Array<Body> bodyParts, Array<Joint> joints) {
		super();
		this.bodyParts = bodyParts;
		this.joints = joints;
	}
	
	


}
