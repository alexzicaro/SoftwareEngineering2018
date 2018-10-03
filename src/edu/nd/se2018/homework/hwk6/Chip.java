package edu.nd.se2018.homework.hwk6;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Chip extends Observable
{
	int redKey = 0;
	int blueKey = 0;
	int yellowKey = 0;
	int greenKey = 0;

	List<Observer> observers = new LinkedList<Observer>();
	Point shipLocation = new Point();
	
	public Chip()
	{
		shipLocation.x = 12; 
		shipLocation.y = 12;
	}
	
	public Point getShipLocation() 
	{
		return shipLocation;
	}
	
	public void goNorth()
	{
		if(this.shipLocation.y - 1 >= 0)
		{
			this.shipLocation.y -= 1; 
//			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
//			notifyObservers();  // Now notify observers.
		}
		
	}
	
	public void goSouth()
	{
		if(this.shipLocation.y + 1 <= 24)
		{
			this.shipLocation.y += 1;
//			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
//			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goEast() 
	{
		if(this.shipLocation.x + 1 <= 24)
		{
			this.shipLocation.x += 1;
//			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
//			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goWest() 
	{
		if(this.shipLocation.x - 1 >= 0)
		{
			this.shipLocation.x -= 1;
//			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
//			notifyObservers();  // Now notify observers.
		}
	}
	
	public int getRedKeys()
	{
		return redKey;
	}
	
	public int getGreenKeys()
	{
		return greenKey;
	}
	
	public int getYellowKeys()
	{
		return yellowKey;
	}
	
	public int getBlueKeys()
	{
		return blueKey;
	}
}
