package edu.nd.se2018.homework.hwk1;

public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords)
	{
		String inputArray[] = input.split(" ");															//Convert the input strings into easier to manage arrays split at their spaces
		String stopWordsArray[] = stopwords.split(" ");
		int stringFrequency[] = new int[inputArray.length];												//Create an array to keep track of the frequencies
		for(int i = 0; i < inputArray.length; i++)														//Outer loop
		{
			String currentString = inputArray[i];														//Stores current string
			boolean isStopWord = false;																	//Boolean to track if it is a stop word
			for(int j = 0; j < stopWordsArray.length; j++)												//Loop to check for stop words
			{
				if(currentString.equals(stopWordsArray[j]))												//Checks to see if it is a stop word
				{
					isStopWord = true;
					j = stopWordsArray.length;															//Stop the loop
				}
			}
			if(!isStopWord)
			{
				for(int j = 0; j < inputArray.length; j++)
				{
					if(currentString.equals(inputArray[j]))												//If it is not a stop word, find the first occurrence of the word and increase the frequency value for it
					{
						stringFrequency[j]++;
						j = inputArray.length;
					}
				}
			}
		}
		int mostFrequentIndex = 0;
		for(int i = 0; i < stringFrequency.length; i++)
		{
			if(stringFrequency[i] > stringFrequency[mostFrequentIndex])									//Find the most frequent index in the array
			{
				mostFrequentIndex = i;
			}
		}
		boolean isDuplicate = false;
		for(int i = 0; i < stringFrequency.length; i++)
		{
			if((stringFrequency[i] == stringFrequency[mostFrequentIndex]) && (i != mostFrequentIndex))	//Checks to see if the most frequent has a tie that is not itself
			{
				isDuplicate = true;
				i = stringFrequency.length;
			}
		}
		
		if(isDuplicate)																					//If duplicate return null, otherwise return the word
		{
			return null;
		}
		else
		{
			return inputArray[mostFrequentIndex];
		}
	}
}
