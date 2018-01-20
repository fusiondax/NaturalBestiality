package physics;

import java.awt.Point;
import java.util.ArrayList;

public class PhysicsObject {
	
	private ArrayList<PhysicsObjectNode> nodes;
	
	/**
	 * the location of the object relative the the environment, its "top left" corner 
	 */
	private Point globalLocation;	
	
	public class PhysicsObjectNode
	{
		private Point coordinate;
	}
}
