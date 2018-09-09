package edu.nd.se2018.homework.hw3;

public class Horse {

	String name;															//Variables for horse
	double horseNum;
	double maxSpeed;
	Strategy strategy;
	double currentMile;
	public Horse()															//Default constructor
	{
		this.name = "Lucky";
		this.horseNum = 0;
		this.maxSpeed = 25;
		this.strategy = new SteadyRunStrategy();
		this.currentMile = 0;
	}
	public Horse(String name, int horseNum, int maxSpeed, Strategy s) 
	{
		this.name = name;
		this.horseNum = horseNum;
		this.maxSpeed = maxSpeed;
		this.strategy = s;
		this.currentMile = 0;												//Current mile always set to 0
	}

	public double move() 													//Call the horses' respective move method
	{
		return strategy.move(currentMile, maxSpeed);
	}

	public double getCurrentMile() 											//Getters and setters
	{
		return currentMile;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setStrategy(Strategy s)
	{
		this.strategy = s;
	}
	public void setCurrentMile(double currentMile) 
	{
		this.currentMile = currentMile;
	}

}
