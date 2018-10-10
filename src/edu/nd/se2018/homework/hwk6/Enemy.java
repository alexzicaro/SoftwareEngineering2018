package edu.nd.se2018.homework.hwk6;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy implements Observer
{
	Point enemyLocation = new Point();
	ObservableList<Node> root;
	int[][] oceanMap;
	Point prevLocation = new Point();
	
	public Enemy() 
	{
		enemyLocation.x = (int)((25)*Math.random());
		enemyLocation.y = (int)((25)*Math.random());
	}
	
	public Enemy(int [][] oceanMap, int x, int y) 
	{
		this.oceanMap = oceanMap;
		this.enemyLocation.x = x;
		this.enemyLocation.y = y;
	}

	public void moveEnemy()	//Give the enemy a 25% chance of moving in each direction
	{
		int num = (int)(Math.random() * 4);
		switch(num)
		{
		case 0:
			goNorth();
			break;
		case 1:
			goSouth();
			break;
		case 2:
			goEast();
			break;
		case 3:
			goWest();
			break;
			
		}
	}

	public void goNorth()
	{
		if(this.enemyLocation.y - 1 >= 0 && oceanMap[enemyLocation.x][enemyLocation.y -1] == 0)
		{
			this.prevLocation.y = enemyLocation.y; //Have to keep trac of previous location so we can remove that space from the grid and make it blank
			this.prevLocation.x = enemyLocation.x;

			this.enemyLocation.y -= 1;
		}
		else										//If it could not move in this direction, call moveEnemy again to find a valid move
		{
			moveEnemy();
		}
		
	}
	
	public void goSouth()							//All are same as above
	{
		if(this.enemyLocation.y + 1 <= 24 && oceanMap[enemyLocation.x][enemyLocation.y + 1] == 0)
		{
			this.prevLocation.y = enemyLocation.y;
			this.prevLocation.x = enemyLocation.x;

			this.enemyLocation.y += 1;
		}
		else
		{
			moveEnemy();
		}
	}
	
	public void goEast() 
	{
		if(this.enemyLocation.x + 1 <= 24 && oceanMap[enemyLocation.x + 1][enemyLocation.y] == 0)
		{
			this.prevLocation.x = enemyLocation.x;
			this.prevLocation.y = enemyLocation.y;

			this.enemyLocation.x += 1;
		}
		else
		{
			moveEnemy();
		}
	}
	
	public void goWest() 
	{
		if(this.enemyLocation.x - 1 >=  0 && oceanMap[enemyLocation.x - 1][enemyLocation.y] == 0)
		{
			this.prevLocation.x = enemyLocation.x;
			this.prevLocation.y = enemyLocation.y;
			this.enemyLocation.x -= 1;
		}
		else
		{
			moveEnemy();
		}
	}

	@Override
	public void update(Observable s, Object arg1) 
	{
		if (s instanceof Chip)
		{
			moveEnemy();			
		}	
	}
	
	public Point getPrevLocation()
	{
		return prevLocation;
	}

	public Point getEnemyLocation() 
	{
		return enemyLocation;
	}
	
}
