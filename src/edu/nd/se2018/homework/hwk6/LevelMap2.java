package edu.nd.se2018.homework.hwk6;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelMap2 extends LevelMap														//Make this an interface, separate classes for each level
{
	int[][] oceanGrid;
	final int dimensions;
	int totalChips = 4;
	Enemy enemies[];
	public LevelMap2(int size)
	{
		super(size);
		oceanGrid = super.getOceanGrid();
		dimensions = 25;
	}
	
	
	public int[][] drawLevelMap2(ObservableList<Node> root, int scalingFactor, int [][]oceanGrid) 
	{
		this.oceanGrid = oceanGrid;
		drawWalls2(root, scalingFactor);
		drawSpecialSpaces2(root, scalingFactor);
		super.setTotalChips(totalChips);
		return this.oceanGrid;
	}
	
	
	public void drawWalls2(ObservableList<Node> root, int scalingFactor)
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
	}
	
	public void drawSpecialSpaces2(ObservableList<Node> root, int scalingFactor)
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
		blockImageView.setY(10 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][10] = 2;
		
		blockImageView = new ImageView(blueBlockImage);		//4 is a blue block
		blockImageView.setX(10 * scalingFactor);
		blockImageView.setY(17 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[10][17] = 4;
		
		blockImageView = new ImageView(greenBlockImage);	//3 is a green block
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(15 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][15] = 3;
	
		blockImageView = new ImageView(redBlockImage);		//5 is a red block
		blockImageView.setX(15 * scalingFactor);
		blockImageView.setY(7 * scalingFactor);
		root.add(blockImageView);
		oceanGrid[15][7] = 5;
		
		keyImageView = new ImageView(blueKeyImage);			//8 is a blue key
		keyImageView.setX(13 * scalingFactor);
		keyImageView.setY(9 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[13][9] = 8;
		
		keyImageView = new ImageView(greenKeyImage);		//7 is a green key
		keyImageView.setX(8 * scalingFactor);
		keyImageView.setY(17 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[8][17] = 7;
		
		keyImageView = new ImageView(yellowKeyImage);		//6 is a yellow key
		keyImageView.setX(19 * scalingFactor);
		keyImageView.setY(13 * scalingFactor);
		root.add(keyImageView);
		oceanGrid[19][13] = 6;
		
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
		
		super.setTotalChips(totalChips);
	}
}
