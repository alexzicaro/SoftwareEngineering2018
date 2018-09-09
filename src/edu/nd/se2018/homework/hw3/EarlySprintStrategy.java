package edu.nd.se2018.homework.hw3;

public class EarlySprintStrategy implements Strategy
{
	public EarlySprintStrategy()								//Default Constructor
	{
		
	}
	public double move(double currentMile, double maxSpeed)		//Move method
	{
		if(currentMile < 2)
		{
			currentMile += maxSpeed / 60;
		}
		else
		{
			currentMile += maxSpeed * .75 / 60;
		}
		return currentMile;
	}
}
