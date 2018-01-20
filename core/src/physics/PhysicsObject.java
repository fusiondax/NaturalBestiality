package physics;

import java.awt.Point;
import java.util.ArrayList;

public abstract class PhysicsObject {
	
	/**
	 * a list of all nodes that this Object has
	 */
	private ArrayList<PhysicsObjectNode> nodes;
	/**
	 * the location of the object relative the the environment, its "top left" corner 
	 */
	private Point globalLocation;	
	
	public class PhysicsObjectNode
	{
		/**
		 * coordinates relative the the object's location
		 */
		private Point coordinate;
		
		/**
		 * the node to whom this node is linked, which created a solid "line"
		 */
		private PhysicsObjectNode linkedNode;
	}
}
