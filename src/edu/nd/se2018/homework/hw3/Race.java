package edu.nd.se2018.homework.hw3;

public class Race 
{
	int printAmount = 0;																		//Counter to know where we are for printing during race
	int length;																					//Length of race
	Horse horses[] = new Horse [10];															//Array to hold enrolled horses
	int numOfHorses = 0;																		//Counter for number of horses in case they are less than 10
	public Race ()																				//Default constructor
	{
		length = 10;
	}
	
	public Race(int length)																		//Construct with length
	{
		this.length = length;
	}
	public void enrollHorse(String name, int horseNum, int maxSpeed, Strategy s)				//Method to enroll horses
	{
		horses[numOfHorses] = new Horse(name, horseNum, maxSpeed, s);
		numOfHorses++;																			//Increment total number of horses
	}
	public String race()																		//Main race method
	{
		boolean raceFinished = false;															//Boolean to track if race is complete
		while(!raceFinished)
		{
			for(int i = 0; i < numOfHorses; i++)												//Have each horse race for this time interval
			{
				horses[i].setCurrentMile(horses[i].move());
			}
			for(int j = 0; j < numOfHorses; j++)												//Check to see if any horse has won
			{
				if(horses[j].getCurrentMile() >= this.length)
				{
					raceFinished = true;
					j = 100;
				}
			}
			if(printAmount % 5 == 0 && !raceFinished)											//Print horses in intervals of 5 loops as long as the race is not finished
			{
				printHorses();
			}
			System.out.println();
			printAmount++;
		}
		return winner();																		//Call winner method once the race is finished
	}

	
	public void printHorses()																	//Print horses method
	{
		for(int i = 0; i < numOfHorses; i++)
		{
			System.out.println(horses[i].getName() + ": " + horses[i].getCurrentMile());
		}
	}

	public String winner()																		//Print winner method
	{
		double furthest = 0;																	//Keep track of farthest mileage
		int furthestIndex = 0;																	//Index of farthest mile
		printHorses();
		for(int i = 0; i < numOfHorses; i++)
		{
			if(furthest < horses[i].getCurrentMile())											//Find the winner
			{
				furthest = horses[i].getCurrentMile();
				furthestIndex = i;
			}
		}
		return "We have a winner! Horse " + horses[furthestIndex].getName() + " won!";
	}
	
}
