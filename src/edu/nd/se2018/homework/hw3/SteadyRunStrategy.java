package edu.nd.se2018.homework.hw3;

public class SteadyRunStrategy implements Strategy 
{
	public SteadyRunStrategy()									//Default Constructor
	{
		
	}
	public double move(double currentMile, double maxSpeed)		//Move method
	{
		currentMile += maxSpeed * .8 / 60;
		return currentMile;
	}
}
