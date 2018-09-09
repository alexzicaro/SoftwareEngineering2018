package edu.nd.se2018.homework.hw3;

public class SlowStartStrategy implements Strategy 
{
	public SlowStartStrategy()									//Default constructor
	{
		
	}
	public double move(double currentMile, double maxSpeed)		//Move method
	{
		if(currentMile < 6)
		{
			currentMile += maxSpeed *.75 / 60;
		}
		else if(currentMile < 9)
		{
			currentMile += maxSpeed * .90 / 60;
		}
		else
		{
			currentMile += maxSpeed / 60;
		}
		return currentMile;
	}

}
