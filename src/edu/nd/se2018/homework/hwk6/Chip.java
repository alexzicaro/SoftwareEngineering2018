package edu.nd.se2018.homework.hwk6;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Chip extends Observable
{
	int redKey = 0;	//Keep track of how many keys and chips chip has
	int blueKey = 0;
	int yellowKey = 0;
	int greenKey = 0;
	int chips = 0;

	List<Observer> observers = new LinkedList<Observer>();
	Point chipLocation = new Point();
	
	public Chip()
	{
		chipLocation.x = 12; 
		chipLocation.y = 12;
	}
	
	public Point getChipLocation() 
	{
		return chipLocation;
	}
	
	public void goNorth()
	{
		if(this.chipLocation.y - 1 >= 0)
		{
			this.chipLocation.y -= 1; 
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
		
	}
	
	public void goSouth()
	{
		if(this.chipLocation.y + 1 <= 24)
		{
			this.chipLocation.y += 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goEast() 
	{
		if(this.chipLocation.x + 1 <= 24)
		{
			this.chipLocation.x += 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goWest() 
	{
		if(this.chipLocation.x - 1 >= 0)
		{
			this.chipLocation.x -= 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
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
	
	public void changeRedKeys(int change)
	{
		redKey += change;
	}
	
	public void changeGreenKeys(int change)
	{
		greenKey += change;
	}
	public void changeYellowKeys(int change)
	{
		yellowKey += change;
	}
	public void changeBlueKeys(int change)
	{
		blueKey += change;
	}
	public void addChip()
	{
		chips++;
	}
	public int getTotalChips()
	{
		return chips;
	}
	public void reset() 
	{
		blueKey = redKey= yellowKey = greenKey = chips = 0;
	}
	
}
