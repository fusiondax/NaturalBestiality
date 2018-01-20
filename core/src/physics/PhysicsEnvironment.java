package physics;

import java.util.ArrayList;

public class PhysicsEnvironment {
	
	private long xLength;
	private long yLength;
	
	private ArrayList<DynamicObject> dynamicList;
	private ArrayList<StaticObject> staticList;
	
	public PhysicsEnvironment()
	{
		xLength = 5000;
		yLength = 5000;
		
		dynamicList = new ArrayList<DynamicObject>();
		
		
		staticList = new ArrayList<StaticObject>();
		
	}
	
	public void update()
	{
		System.out.println("wow");
		
		
		
	}

	public long getxLength() {
		return xLength;
	}

	public long getyLength() {
		return yLength;
	}

	public ArrayList<DynamicObject> getDynamicList() {
		return dynamicList;
	}

	public ArrayList<StaticObject> getStaticList() {
		return staticList;
	}

}
