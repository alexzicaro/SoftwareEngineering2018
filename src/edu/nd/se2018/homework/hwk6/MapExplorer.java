package edu.nd.se2018.homework.hwk6;

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

public class MapExplorer extends Application {
	Pane root = new AnchorPane();
	final int dimensions = 25;
	final int islandCount = 25;
	final int scalingFactor = 25;
	Image chipImage;
	ImageView chipImageView;

	Image piratechipImage;

	LevelMap oceanMap = new LevelMap();
	Scene scene;
	Chip chip;
	Enemies pirateShips[] = new Enemies[3];
	ImageView piratechipImageView[] = new ImageView[3];

	@Override
	public void start(Stage oceanStage) throws Exception {

		oceanMap.drawLevel1Map(root.getChildren(), scalingFactor);

		scene = new Scene(root, 625, 625);
		oceanStage.setTitle("Chip's Challenge");
		oceanStage.setScene(scene);
		oceanStage.show();
		loadChipImage();
		//loadEnemyImages();
		startSailing();
	}

	public void loadChipImage() {

		chipImage = new Image("images/chipDown.png", scalingFactor, scalingFactor, true, true);
		chipImageView = new ImageView(chipImage);

		chip = new Chip();
		chipImageView.setX(chip.getShipLocation().x * scalingFactor);
		chipImageView.setY(chip.getShipLocation().y * scalingFactor);

		root.getChildren().add(chipImageView);
	}

	public void loadEnemyImages() {
		piratechipImage = new Image("images/bugUp.png", scalingFactor, scalingFactor, true, true);
		for (int i = 0; i < 3; i++) {
			piratechipImageView[i] = new ImageView(piratechipImage);

			do {
				pirateShips[i] = new Enemies();
				piratechipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor);
				piratechipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);

			} while (LevelMap.oceanGrid[pirateShips[i].getPirateShipLocation().x][pirateShips[i]
					.getPirateShipLocation().y] != 0);
			root.getChildren().add(piratechipImageView[i]);
			chip.addObserver(pirateShips[i]);
		}
	}

	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
				case RIGHT:
					chipImage = new Image("images/chipRight.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getShipLocation().x + 1 < dimensions)
						if (LevelMap.checkForIsland(chip.getShipLocation().x + 1, chip.getShipLocation().y)) {

							chip.goEast();
//							this.chaseShip();
						}
					break;
				case LEFT:
					chipImage = new Image("images/chipLeft.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getShipLocation().x - 1 >= 0)
						if (LevelMap.checkForIsland(chip.getShipLocation().x - 1, chip.getShipLocation().y)) {

							chip.goWest();
//							this.chaseShip();

						}
					break;
				case UP:
					chipImage = new Image("images/chipUp.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getShipLocation().y - 1 >= 0)
						if (LevelMap.checkForIsland(chip.getShipLocation().x, chip.getShipLocation().y - 1)) {

							chip.goNorth();
//							this.chaseShip();
						}
					break;
				case DOWN:
					chipImage = new Image("images/chipDown.png", scalingFactor, scalingFactor, true, true);
					chipImageView.setImage(chipImage);
					if (chip.getShipLocation().y + 1 < dimensions)
						if (LevelMap.checkForIsland(chip.getShipLocation().x, chip.getShipLocation().y + 1)) {

							chip.goSouth();
//							this.chaseShip();
						}
					break;

				case ESCAPE:
					System.exit(0);

				default:
					break;
				}
				chipImageView.setX(chip.getShipLocation().x * scalingFactor);
				chipImageView.setY(chip.getShipLocation().y * scalingFactor);
			}

			private void chaseShip() {
				for (int i = 0; i < 3; i++) {
					piratechipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);
					piratechipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor);
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
