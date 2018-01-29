package physics;

import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

import render.GameScreen;

public abstract class JointUtility {
	
	public static final float ACCEPTABLE_MARGIN = 0.1f;

	
	/**
	 * calculates and returns the appropriate angular speed to maintain the given angle, must be called at every frames
	 * 
	 * @param the joint
	 * @param the angle to maintain in radian
	 * 
	 * @return the angular speed in radian/seconds that the joint 
	 */
	public static float maintainAngle(RevoluteJoint joint, float angle)
	{
		float angSpeed = 0f;
		//if current angle is greater than desired angle
		
		if(joint.getJointAngle() - ACCEPTABLE_MARGIN > angle)
		{
			float diff = joint.getJointAngle() - angle;
			angSpeed = -(GameScreen.PLAYER_MUSCLE_ANGULAR_VELOCITY *diff);
		}
		else if (joint.getJointAngle() + ACCEPTABLE_MARGIN < angle)
		{
			float diff = angle - joint.getJointAngle();
			angSpeed = (GameScreen.PLAYER_MUSCLE_ANGULAR_VELOCITY  *diff);
		}
		
		return angSpeed;
	}
	
	public static boolean isAngleSimilar(RevoluteJoint joint, float angle)
	{
		boolean ans = false;
		if(joint.getJointAngle() - ACCEPTABLE_MARGIN < angle && joint.getJointAngle() + ACCEPTABLE_MARGIN > angle)
		{
			ans = true;
		}
		return ans;
	}
}
