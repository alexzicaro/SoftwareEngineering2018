package edu.nd.se2018.homework.hw4;

import java.awt.Point;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OceanExplorer extends Application
{
	Pane root = new AnchorPane();
	final int dimensions = 25;
	final int islandCount = 25;
	final int scalingFactor = 25;
	Image shipImage;
	ImageView shipImageView;

	Image pirateShipImage;
	
	OceanMap oceanMap = new OceanMap();
	Scene scene;
	Ship ship;
	PirateShip pirateShips[] = new PirateShip [3];
	ImageView pirateShipImageView [] = new ImageView[3];

	@Override
	public void start(Stage oceanStage) throws Exception
	{
				
		oceanMap.drawMap(root.getChildren(), scalingFactor);
		
		scene = new Scene(root, 625, 625);
		oceanStage.setTitle("The Ocean Blue!");
		oceanStage.setScene(scene);
		oceanStage.show();
		loadShipImage();
		loadPirateShipImages();
		startSailing();
	}
	
	public void loadShipImage() 
	{

		shipImage = new Image("images/ColumbusShip.png",scalingFactor,scalingFactor,true,true); 
		shipImageView = new ImageView(shipImage);
		
		do
		{			
			ship = new Ship();
			shipImageView.setX(ship.getShipLocation().x * scalingFactor); 
			shipImageView.setY(ship.getShipLocation().y * scalingFactor);
			
		}while(OceanMap.oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] != 0);	
		root.getChildren().add(shipImageView);
	}
	
	public void loadPirateShipImages() 
	{
		pirateShipImage = new Image("images/pirateship.png",scalingFactor,scalingFactor,true,true);
		for(int i = 0; i < 3; i++)
		{
			pirateShipImageView[i] = new ImageView(pirateShipImage);
			
			do
			{			
				pirateShips[i] = new PirateShip();
				pirateShipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor); 
				pirateShipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);
				
			}while(OceanMap.oceanGrid[pirateShips[i].getPirateShipLocation().x][pirateShips[i].getPirateShipLocation().y] != 0);	
			root.getChildren().add(pirateShipImageView[i]);
			ship.addObserver(pirateShips[i]);
		}
	}
	
	public void startSailing() 
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent ke)
			{
				switch(ke.getCode())
				{
					case RIGHT: 
						if(ship.getShipLocation().x + 1 < dimensions)
							if(OceanMap.checkForIsland(ship.getShipLocation().x + 1, ship.getShipLocation().y))
							{
								ship.goEast();
								this.chaseShip();
							}
							break; 
					case LEFT:
						if(ship.getShipLocation().x - 1 >= 0)
							if(OceanMap.checkForIsland(ship.getShipLocation().x - 1, ship.getShipLocation().y))
							{
								ship.goWest();
								this.chaseShip();

							}
							break; 
					case UP:
						if(ship.getShipLocation().y - 1 >= 0)
							if(OceanMap.checkForIsland(ship.getShipLocation().x, ship.getShipLocation().y - 1))
							{
								ship.goNorth();
								this.chaseShip();
							}
							break; 
					case DOWN:
						if(ship.getShipLocation().y + 1 < dimensions)
							if(OceanMap.checkForIsland(ship.getShipLocation().x, ship.getShipLocation().y + 1))
							{
								ship.goSouth();
								this.chaseShip();
							}
							break; 
					default:
						break;
				}
				shipImageView.setX(ship.getShipLocation().x*scalingFactor); 
				shipImageView.setY(ship.getShipLocation().y*scalingFactor);
			}

			private void chaseShip() 
			{
				for(int i = 0; i < 3; i++)
				{
					pirateShipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);
					pirateShipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor);
				}
			}
		});
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
