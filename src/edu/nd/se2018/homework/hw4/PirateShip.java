package edu.nd.se2018.homework.hw4;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.examples.observer.catmouse.Mouse;
import javafx.scene.image.ImageView;

public class PirateShip implements Observer
{
	Point pirateShipLocation = new Point();
	Point ship;	
	
	public PirateShip() 
	{
		pirateShipLocation.x = (int)((25)*Math.random());
		pirateShipLocation.y = (int)((25)*Math.random());
	}

	public void movePirateShip() 
	{
		if (pirateShipLocation.x - ship.x < 0 && OceanMap.checkForIsland(pirateShipLocation.x + 1, pirateShipLocation.y))
		{
			pirateShipLocation.x++;
		}
		else if(OceanMap.checkForIsland(pirateShipLocation.x - 1, pirateShipLocation.y))
			pirateShipLocation.x--;
		
		if (pirateShipLocation.y - ship.y < 0 && OceanMap.checkForIsland(pirateShipLocation.x, pirateShipLocation.y + 1))
		{
			pirateShipLocation.y++;
		}
		else if(OceanMap.checkForIsland(pirateShipLocation.x, pirateShipLocation.y - 1))
		{
			pirateShipLocation.y--;
		}
	}

	@Override
	public void update(Observable s, Object arg1) 
	{
		if (s instanceof Ship)
		{
			ship = ((Ship)s).getShipLocation();
			movePirateShip();			
		}	
	}

	public Point getPirateShipLocation() 
	{
		return pirateShipLocation;
	}
	
}
