package edu.nd.se2018.homework.hwk6;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelMap1 extends LevelMap
{
	int[][] oceanGrid;
	final int dimensions;
	int totalChips = 4;
	public LevelMap1(int size)
	{
		super(size);
		oceanGrid = super.getOceanGrid();
		dimensions = 25;
	}
	
	
	public int[][] drawLevelMap1(ObservableList<Node> root, int scalingFactor, int [][]oceanGrid) 
	{
		this.oceanGrid = oceanGrid;
		drawWalls1(root, scalingFactor);
		drawSpecialSpaces1(root, scalingFactor);
		super.setTotalChips(totalChips);
		return this.oceanGrid;
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
		ImageView keyImageView;
		
		blockImageView = new ImageView(yellowBlockImage);	//2 is a yellow block
		blockImageView.setX(10 * scalingFactor);
		blockImageView.setY(6 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][6] = 2;
		
		blockImageView = new ImageView(blueBlockImage);		//4 is a blue block
		blockImageView.setX(10 * scalingFactor);
		blockImageView.setY(18 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][18] = 4;
		
		blockImageView = new ImageView(greenBlockImage);	//3 is a green block
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(12 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][12] = 3;
	
		blockImageView = new ImageView(redBlockImage);		//5 is a red block
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(7 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][7] = 5;
		
		keyImageView = new ImageView(blueKeyImage);			//8 is a blue key
		keyImageView.setX(12 * scalingFactor);
		keyImageView.setY(7 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[12][7] = 8;
		
		keyImageView = new ImageView(greenKeyImage);		//7 is a green key
		keyImageView.setX(8 * scalingFactor);
		keyImageView.setY(17 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[8][17] = 7;
		
		keyImageView = new ImageView(yellowKeyImage);		//6 is a yellow key
		keyImageView.setX(17 * scalingFactor);
		keyImageView.setY(13 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[17][13] = 6;
		
		keyImageView = new ImageView(redKeyImage);			//9 is a red key
		keyImageView.setX(8 * scalingFactor);
		keyImageView.setY(7 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[8][7] = 9;
		
		chipItemImageView = new ImageView(chipItemImage);	//10 is a chip
		chipItemImageView.setX(7 * scalingFactor);
		chipItemImageView.setY(9 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[7][9] = 10;
		
		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(6 * scalingFactor);
		chipItemImageView.setY(17 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[6][17] = 10;

		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(17 * scalingFactor);
		chipItemImageView.setY(8 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[17][8] = 10;

		chipItemImageView = new ImageView(chipItemImage);
		chipItemImageView.setX(17 * scalingFactor);
		chipItemImageView.setY(12 * scalingFactor);
		root.add(chipItemImageView);
		oceanGrid[17][12] = 10;

		ImageView targetImageView = new ImageView(targetImage);
		targetImageView.setX(14 * scalingFactor);
		targetImageView.setY(19 * scalingFactor);
		root.add(targetImageView);
		oceanGrid[14][19] = 11;
		
	}
	public int getTotalChips()
	{
		return totalChips;
	}
}
