package edu.nd.se2018.homework.hwk1;

public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		int sum = 0;											//Running total of the summed numbers
		int uniqueNumbersRead = 0;								//Running total of the numbers read that are not duplicates
		int readNumbers[] = new int[numbers.length];			//Create an array to hold read unique numbers, with max length equal to the passed array
		
		for(int i = 0; i < numbers.length; i++) 				//Outer for loop that runs through the array that was inputed by the method
		{
			int currentNumber = numbers[i];						//Variable to easily reference the current number
			boolean alreadyRead = false;						//Boolean to keep track of if the number was already read
			for(int j = 0; j < readNumbers.length; j++)			//Inner loop that will go through the already read numbers to see if the current number was read already
			{
				if(currentNumber == readNumbers[j])				//Check to see if the number has already been read
				{
					alreadyRead = true;							//If it has been read, set boolean to true
					j = numbers.length;							//End the loop to save time
				}
			}
			if(!alreadyRead)									//If the current number has not already been read
			{
				sum += currentNumber;							//Add the current number to the sum
				readNumbers[uniqueNumbersRead] = currentNumber;	//Add the current number to the array of read numbers
				uniqueNumbersRead++;							//Increase the amount of uniqueReadNumbers
				
			}
		}
		return sum;	
	}
}
