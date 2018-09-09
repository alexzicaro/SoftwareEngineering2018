package edu.nd.se2018.homework.hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void test() {
		Race race = new Race(15);
		race.enrollHorse("Shoeless",0,20,new EarlySprintStrategy());
		assert(race.race()).equals("We have a winner! Horse Shoeless won!");
		
		Race race2 = new Race();
		race2.enrollHorse("Moe",0,21,new EarlySprintStrategy());
		race2.enrollHorse("Loe",1,19,new SlowStartStrategy());
		assert(race2.race()).equals("We have a winner! Horse Moe won!");
		
		Race race3 = new Race(40);
		race3.enrollHorse("Donkey",0, 19,new EarlySprintStrategy());
		race3.enrollHorse("Buddy",1,20,new SlowStartStrategy());
		race3.enrollHorse("Winney",2,18,new EarlySprintStrategy());
		race3.enrollHorse("Dopey",3,21,new EarlySprintStrategy());
		race3.enrollHorse("Mopey",4,22,new EarlySprintStrategy());
		assert(race3.race()).equals("We have a winner! Horse Buddy won!");
		
	}

}
