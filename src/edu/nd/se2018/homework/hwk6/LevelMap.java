package edu.nd.se2018.homework.hwk6;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelMap 
{
	static int[][] oceanGrid = new int[25][25]; 
	final int dimensions = 25;
	
	public void drawLevel1Map(ObservableList<Node> root, int scalingFactor) 
	{
		drawOuterWalls(root, scalingFactor);
		drawWalls1(root, scalingFactor);
		drawSpecialSpaces1(root, scalingFactor);
	}
	
	public void drawOuterWalls(ObservableList<Node> root, int scalingFactor)
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
	
	public void drawWalls1(ObservableList<Node> root, int scalingFactor)
	{
		for(int i = 0; i < 3; i++) //Inner  Veritcal walls
		{
			for(int j = 0; j < 15; j++)
			{
				int wallX = (int)(i * 5 + 10);
				int wallY = (int)(j + 5);
				Image wallImage = new Image("images/wall.png",scalingFactor,scalingFactor,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * scalingFactor);
				wallImageView.setY(wallY * scalingFactor);
				root.add(wallImageView);
				oceanGrid[wallX][wallY] = 1;
			}
		}
		
		for(int i = 0; i < 3; i++) //Inner Left Horizontal walls
		{
			for(int j = 0; j < 5; j++)
			{
				int wallY = (int)(i * 5 + 10);
				int wallX = (int)(j + 5);
				Image wallImage = new Image("images/wall.png",scalingFactor,scalingFactor,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * scalingFactor);
				wallImageView.setY(wallY * scalingFactor);
				root.add(wallImageView);
				oceanGrid[wallX][wallY] = 1;
			}
		}
		
		for(int i = 0; i < 3; i++) //Inner Right Horizontal walls
		{
			for(int j = 10; j < 15; j++)
			{
				int wallY = (int)(i * 5 + 10);
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
	
	public void drawSpecialSpaces1(ObservableList<Node> root, int scalingFactor)
	{
		Image yellowBlockImage = new Image("images/yellowKeyWall.png",scalingFactor,scalingFactor,true,true); 
		Image greenBlockImage = new Image("images/greenKeyWall.png",scalingFactor,scalingFactor,true,true); 
		Image blueBlockImage = new Image("images/blueKeyWall.png",scalingFactor,scalingFactor,true,true); 
		Image redBlockImage = new Image("images/redKeyWall.png",scalingFactor,scalingFactor,true,true); 
		
		Image chipItemImage = new Image("images/chipItem.png",scalingFactor,scalingFactor,true,true); 

		Image yellowKeyImage = new Image("images/yellowKey.png",scalingFactor,scalingFactor,true,true); 
		Image greenKeyImage = new Image("images/greenKey.png",scalingFactor,scalingFactor,true,true); 
		Image blueKeyImage = new Image("images/blueKey.png",scalingFactor,scalingFactor,true,true); 
		Image redKeyImage = new Image("images/redKey.png",scalingFactor,scalingFactor,true,true); 
		
		Image targetImage = new Image("images/target.png",scalingFactor,scalingFactor,true,true); 

		
		ImageView chipItemImageView;
		ImageView blockImageView;
		
		blockImageView = new ImageView(yellowBlockImage);
		blockImageView.setX(10 * scalingFactor);
		blockImageView.setY(6 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][6] = 2;
		
		blockImageView = new ImageView(blueBlockImage);
		blockImageView.setX(10 * scalingFactor);
		blockImageView.setY(18 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][18] = 4;
		
		blockImageView = new ImageView(greenBlockImage);
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(12 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][12] = 3;
	
		blockImageView = new ImageView(redBlockImage);
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(7 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][7] = 5;
		
		ImageView keyImageView = new ImageView(blueKeyImage);
		keyImageView.setX(12 * scalingFactor);
		keyImageView.setY(7 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[12][7] = 8;
		
		keyImageView = new ImageView(greenKeyImage);
		keyImageView.setX(8 * scalingFactor);
		keyImageView.setY(17 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[8][17] = 7;
		
		keyImageView = new ImageView(yellowKeyImage);
		keyImageView.setX(17 * scalingFactor);
		keyImageView.setY(13 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[17][13] = 6;
		
		keyImageView = new ImageView(redKeyImage);
		keyImageView.setX(8 * scalingFactor);
		keyImageView.setY(7 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[8][7] = 9;
		
		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(7 * scalingFactor);
		chipItemImageView.setY(9 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[7][9] = -1;
		
		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(6 * scalingFactor);
		chipItemImageView.setY(17 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[5][17] = -1;
		
		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(17 * scalingFactor);
		chipItemImageView.setY(8 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[17][8] = -1;
		
		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(17 * scalingFactor);
		chipItemImageView.setY(12 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[17][12] = -1;
		
		ImageView targetImageView = new ImageView(targetImage);
		targetImageView.setX(14 * scalingFactor);
		targetImageView.setY(19 * scalingFactor);
		root.add(targetImageView);
		oceanGrid[17][12] = -1;
		
	}
	
	public static boolean checkForIsland(int x, int y) 
	{
		if(oceanGrid[x][y] == 1)	//Whenever this is called, I have to handle the case of if there is a door and check the key
		{
			return false;
		}
		return true;
	}


	
}
