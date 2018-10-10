package edu.nd.se2018.homework.hwk6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapExplorer extends Application {
	Pane root = new AnchorPane();
	final int dimensions = 25;
	final int islandCount = 25;
	final int scalingFactor = 25;
	Image chipImage;
	ImageView chipImageView;
	public int map = 0;													//Used to keep track of what map we are on

	Enemy enemies[] = new Enemy[4];
	ImageView enemyImageView[] = new ImageView[4];

	LevelMap oceanMap = new LevelMap1(dimensions);
	Scene scene;
	Chip chip;

	@Override
	public void start(Stage oceanStage) throws Exception 
	{

		oceanMap.drawLevelMap1(root.getChildren(), scalingFactor);
		map++;

		scene = new Scene(root, 625, 625);
		oceanStage.setTitle("Chip's Challenge");
		oceanStage.setScene(scene);
		oceanStage.show();
		loadChipImage();
		startMoving();
	}

	public void loadChipImage() 
	{

		chipImage = new Image("images/chipDown.png", scalingFactor, scalingFactor, true, true);
		chipImageView = new ImageView(chipImage);

		chip = new Chip();
		chipImageView.setX(chip.getChipLocation().x * scalingFactor);
		chipImageView.setY(chip.getChipLocation().y * scalingFactor);

		root.getChildren().add(chipImageView);
	}

	public void startMoving() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent ke) 
			{
				switch (ke.getCode()) {
				case RIGHT:													
					chipImage = new Image("images/chipRight.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getChipLocation().x + 1 < dimensions)
					{
						int space = oceanMap.getSpecialSpace(chip.getChipLocation().x + 1, chip.getChipLocation().y);
						if(space != -1)								//If the space chip wants to move to is not a blank place
						{
							specialSpace(space, chip.getChipLocation().x + 1, chip.getChipLocation().y);
						}
						if (oceanMap.checkForIsland(chip.getChipLocation().x + 1, chip.getChipLocation().y)) 
						{
							chip.goEast();							//If he is not moving to an island move him
							if(map == 2)
							{
								moveEnemy();						//If we are on the second map, move the enemy images
							}						
						}
					}
					break;
				case LEFT:											//Each case is the same as above
					chipImage = new Image("images/chipLeft.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getChipLocation().x - 1 >= 0)
					{
						int space = oceanMap.getSpecialSpace(chip.getChipLocation().x - 1, chip.getChipLocation().y);
						if(space != -1)
						{
							specialSpace(space, chip.getChipLocation().x - 1, chip.getChipLocation().y);
						}
						if (oceanMap.checkForIsland(chip.getChipLocation().x - 1, chip.getChipLocation().y)) 
						{
							chip.goWest();
							if(map == 2)
							{
								moveEnemy();
							}
						}
					}
					break;
				case UP:
					chipImage = new Image("images/chipUp.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getChipLocation().y - 1 >= 0)
					{
						int space = oceanMap.getSpecialSpace(chip.getChipLocation().x, chip.getChipLocation().y -1);
						if(space != -1)
						{
							specialSpace(space, chip.getChipLocation().x, chip.getChipLocation().y -1);
						}
						if (oceanMap.checkForIsland(chip.getChipLocation().x, chip.getChipLocation().y - 1)) 
						{
							chip.goNorth();
							if(map == 2)
							{
								moveEnemy();
							}
						}
					}
					break;
				case DOWN:
					chipImage = new Image("images/chipDown.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getChipLocation().y + 1 < dimensions)
					{
						int space = oceanMap.getSpecialSpace(chip.getChipLocation().x, chip.getChipLocation().y + 1);
						if(space != -1)
						{
							specialSpace(space, chip.getChipLocation().x, chip.getChipLocation().y + 1);
						}
						if (oceanMap.checkForIsland(chip.getChipLocation().x, chip.getChipLocation().y + 1)) 
						{
							chip.goSouth();
							if(map == 2)
							{
								moveEnemy();
							}
						}
					}
					break;

				case ESCAPE:
					System.exit(0);

				default:
					break;
				}											//Move chip's image
				chipImageView.setX(chip.getChipLocation().x * scalingFactor);
				chipImageView.setY(chip.getChipLocation().y * scalingFactor);
				root.getChildren().remove(chipImageView);	//Have to remove and readd chip so he will display on top of new blank
				root.getChildren().add(chipImageView);
				for(int i = 0; i < 4; i ++)					//If any of the enemies are on his position, end the game
				{
					if(map == 2 && (enemies[i].getEnemyLocation().x == chip.getChipLocation().x && enemies[i].getEnemyLocation().y == chip.getChipLocation().y))
					{
						System.exit(0);
					}
				}
				
			}
			
			public void moveEnemy()
			{
				for(int i = 0; i < enemies.length; i ++)
				{
					int enemiesX = (int)enemies[i].getEnemyLocation().getX();		//Move the enemy, make the space he was on open and the new one set to 12, enemy value
					int enemiesY = (int)enemies[i].getEnemyLocation().getY();
					int enemiesPrevX = (int)enemies[i].getPrevLocation().getX();
					int enemiesPrevY = (int)enemies[i].getPrevLocation().getY();
					oceanMap.setPosition(12, enemiesX, enemiesY);
					oceanMap.setPosition(0, enemiesPrevX, enemiesPrevY);
					root.getChildren().remove(enemyImageView[i]);
					enemyImageView[i].setX(enemiesX * 25);
					enemyImageView[i].setY(enemiesY * 25);
					root.getChildren().add(enemyImageView[i]);
				}
			}

			private void specialSpace(int space, int x, int y) 
			{
				switch(space) 
				{
				case 2:	//yellow block
					if(chip.getYellowKeys() > 0)
					{
						chip.changeYellowKeys(-1);
						makeBlank(x, y);
					}
					break;
				case 3:	//green block
					if(chip.getGreenKeys() > 0)
					{
						chip.changeGreenKeys(-1);
						makeBlank(x, y);
					}
					break;
				case 4:	//blue block
					if(chip.getBlueKeys() > 0)
					{
						chip.changeBlueKeys(-1);
						makeBlank(x, y);
					}
					break;
				case 5:	//red block
					if(chip.getRedKeys() > 0)
					{
						chip.changeRedKeys(-1);
						makeBlank(x, y);
					}
					break;
				case 6:	//yellow key
					chip.changeYellowKeys(1);
					makeBlank(x, y);
					break;
				case 7:	//green key
					chip.changeGreenKeys(1);
					makeBlank(x, y);
					break;
				case 8:	//blue key
					chip.changeBlueKeys(1);
					makeBlank(x, y);
					break;
				case 9:	//red key
					chip.changeRedKeys(1);
					makeBlank(x, y);
					break;
				case 10://chip
					chip.addChip();
					makeBlank(x, y);
					break;
				case 11://target
					if(chip.getTotalChips() == oceanMap.getTotalChips())
					{
						if(map == 2)
						{
							System.exit(0);
						}
						else
						{
							makeBlank(x, y);				//Prepare for a level change, clear values and draw new map
							chip.reset();
							oceanMap = new LevelMap2(25);
							oceanMap.drawLevelMap2(root.getChildren(), scalingFactor);
							map++;
							for(int i = 1; i <= 2; i++)		//Create enemies for second level
							{
								enemies[i-1] = new Enemy(oceanMap.getOceanGrid(), 8, 8 * i);
								enemies[i+1] = new Enemy(oceanMap.getOceanGrid(), 16, 8 * i);
							}
							
							for(int i = 0; i < enemies.length; i++)	//Create enemy images for next level
							{
								int xEnemy = (int)enemies[i].getEnemyLocation().getX();
								int yEnemy = (int)enemies[i].getEnemyLocation().getY();
								Image enemyImage = new Image("images/bugUp.png",25,25,true,true); 
								enemyImageView[i] = new ImageView(enemyImage);
								enemyImageView[i].setX(xEnemy * 25);
								enemyImageView[i].setY(yEnemy * 25);
								root.getChildren().add(enemyImageView[i]);
								oceanMap.setPosition(12, xEnemy, yEnemy);
								chip.addObserver(enemies[i]);
							}
						}
					}
					break;
				default:
					break;
				}
			}
			
			private void makeBlank(int x, int y)
			{
				oceanMap.makeBlank(root.getChildren(),x,y);
			}
		});
	}
	
	public void addPosition(int x, int y, int value)
	{
		oceanMap.setPosition(value, x, y);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
