package edu.nd.se2018.homework.hw3;

public class Main 
{
	public static void main(String args[])
	{
		Race race = new Race(20);
		race.enrollHorse("Donkey",0, 19,new EarlySprintStrategy());			//Main method
		race.enrollHorse("Buddy",1,20,new SlowStartStrategy());
		race.enrollHorse("Winney",2,18,new EarlySprintStrategy());
		race.enrollHorse("Dopey",3,21,new EarlySprintStrategy());
		race.enrollHorse("Mopey",4,22,new EarlySprintStrategy());
		System.out.println(race.race());
	}
	
}
