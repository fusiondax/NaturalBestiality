package physics;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;

public class DynamicObject extends PhysicsObject {
	
	/**
	 * the center of mass of this dynamic object relative to it's globalLocation
	 */
	private Point centerOfMass;
	
	/**
	 * the point where rotation of is currently happening
	 */
	private Point centerOfRotation;
	
	/**
	 * the direction and speed of the the translation of the Dynamic object
	 */
	private Vector2 translationVector;
	
	/**
	 * the speed at which rotation is happening
	 */
	private int rotationSpeed;

}
