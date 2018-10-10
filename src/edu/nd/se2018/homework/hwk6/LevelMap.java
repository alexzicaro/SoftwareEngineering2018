package edu.nd.se2018.homework.hwk6;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class LevelMap
{
	int[][] oceanGrid;
	final int dimensions;
	int totalChips;
	Enemy [] enemies;
	LevelMap2 levelMap2;
	
	public LevelMap(int size)
	{
		oceanGrid = new int[size][size]; 
		dimensions = 25;
		totalChips = 0;
	}
	
	public void drawLevelMap1(ObservableList<Node> root, int scalingFactor) //Draw  first map by calling it and drawing outer walls
	{
		drawOuterWalls(root, scalingFactor);
		LevelMap1 levelMap1 = new LevelMap1(25);
		this.oceanGrid = levelMap1.drawLevelMap1(root, scalingFactor, oceanGrid);
		totalChips = levelMap1.getTotalChips();
	}
	public void drawLevelMap2(ObservableList<Node> root, int scalingFactor) //Same as above but for second map
	{
		drawOuterWalls(root, scalingFactor);
		levelMap2 = new LevelMap2(25);
		this.oceanGrid = levelMap2.drawLevelMap2(root, scalingFactor, oceanGrid);
		totalChips = levelMap2.getTotalChips();
	}
	
	public void drawOuterWalls(ObservableList<Node> root, int scalingFactor)//Draw the default outer walls
	{
		for(int i = 0; i < dimensions; i++) //Blank tiles
		{
			for(int j = 0; j < dimensions; j++) 
			{
				Image blankImage = new Image("images/blankTile.png",scalingFactor,scalingFactor,true,true); 
				ImageView blankImageView = new ImageView(blankImage);
				blankImageView.setX(i * scalingFactor);
				blankImageView.setY(j * scalingFactor);
				root.add(blankImageView);
				oceanGrid[i][j] = 0; 
			}
		}
		for(int i = 0; i < 2; i++) //Outer West and East walls
		{
			for(int j = 0; j < 15; j++)
			{
				int wallX = (int)(i * 15 + 5);
				int wallY = (int)(j + 5);
				Image wallImage = new Image("images/wall.png",scalingFactor,scalingFactor,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * scalingFactor);
				wallImageView.setY(wallY * scalingFactor);
				root.add(wallImageView);
				oceanGrid[wallX][wallY] = 1;
			}
		}
		
		for(int i = 0; i < 2; i++) //Outer North and South walls
		{
			for(int j = 0; j < 16; j++)
			{
				int wallY = (int)(i * 15 + 5);
				int wallX = (int)(j + 5);
				Image wallImage = new Image("images/wall.png",scalingFactor,scalingFactor,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * scalingFactor);
				wallImageView.setY(wallY * scalingFactor);
				root.add(wallImageView);
				oceanGrid[wallX][wallY] = 1;
			}
		}
	}
	
	
	public boolean checkForIsland(int x, int y) 
	{
		if(oceanGrid[x][y] != 0)	//Check to see if there is an island, aka the value is not a blank space
		{
			return false;
		}
		return true;
	}
	
	
	public void setPosition(int set, int x, int y)
	{
		this.oceanGrid[x][y] = set;
	}
	
	public int getSpecialSpace(int x, int y)	//Return the value of the space if it is not blank, -1 if blank
	{
		if(oceanGrid[x][y] != 0 && oceanGrid[x][y] != 1)
		{
			return oceanGrid[x][y];
		}
		else
		{
			return -1;
		}
	}
	
	public int [][] getOceanGrid()
	{
		return oceanGrid;
	}
	
	public void makeBlank(ObservableList<Node> root, int x, int y)	//Takes a position and makes it a blank space
	{
		Image blankImage = new Image("images/blankTile.png",dimensions,dimensions,true,true); 
		ImageView blankImageView = new ImageView(blankImage);
		blankImageView.setX(x * dimensions);
		blankImageView.setY(y * dimensions);
		root.add(blankImageView);
		oceanGrid[x][y] = 0;
	}
	
	public void setTotalChips(int totalChips)
	{
		this.totalChips = totalChips;
	}
	
	public int getTotalChips()
	{
		return totalChips;
	}
	
	public void setEnemies(Enemy [] enemies)
	{
		this.enemies = enemies;

	}
	
	public Enemy [] getEnemies()
	{
		return enemies;
	}


	
}
