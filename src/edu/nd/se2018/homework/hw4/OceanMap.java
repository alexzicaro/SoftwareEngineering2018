package edu.nd.se2018.homework.hw4;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap 
{
	static int[][] oceanGrid = new int[25][25]; 
	final int dimensions = 25;
	
	public void drawMap(ObservableList<Node> root, int scalingFactor) 
	{
		for(int i = 0; i < dimensions; i++) 
		{
			for(int j = 0; j < dimensions; j++) 
			{
				Rectangle ocean = new Rectangle(i * scalingFactor, j * scalingFactor, scalingFactor, scalingFactor);
				ocean.setStroke(Color.BLACK);
				ocean.setFill(Color.TURQUOISE);
				root.add(ocean);
				oceanGrid[i][j] = 0;
			}
		}
		
		for(int i = 0; i < 50; i++) 
		{
			int islandX = (int)((25)*Math.random());
			int islandY = (int)((25)*Math.random());
			
			if(oceanGrid[islandX][islandY] == 0) 
			{
				Rectangle island = new Rectangle(islandX * scalingFactor, islandY * scalingFactor, scalingFactor, scalingFactor);
				island.setStroke(Color.BLACK);
				island.setFill(Color.GREEN);
				root.add(island);
				oceanGrid[islandX][islandY] = 1;
			}
		}
	}
	
	public static boolean checkForIsland(int x, int y) 
	{
		if(oceanGrid[x][y] == 1)
		{
			return false;
		}
		return true;
	}


	
}
