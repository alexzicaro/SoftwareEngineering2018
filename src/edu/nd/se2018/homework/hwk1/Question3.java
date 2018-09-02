package edu.nd.se2018.homework.hwk1;
public class Question3 {
	
	public Question3(){}	
	
    public int getMirrorCount(int[] numbers)
    {
    	int countOfSequence = 0;						//Total count for the length of the sequence
    	int endOfArray = numbers.length - 1;			//Easy variable to keep track of the end of the array
    	boolean isSequence = false;						//Boolean to keep track of if we are in a sequence
    	for(int i = 0; i < numbers.length; i++)
    	{
    		if(numbers[i] == numbers[endOfArray - i])	//If the number on the left equals the corresponding number on the right
    		{
    			if(isSequence || countOfSequence == 0)	//If we are in a sequence or we have not found one yet
    			{
    				countOfSequence++;
        			isSequence = true;
    			}
    		}
    		else
    		{
    			isSequence = false;
    		}
    	}
		return countOfSequence;	
	}
}
