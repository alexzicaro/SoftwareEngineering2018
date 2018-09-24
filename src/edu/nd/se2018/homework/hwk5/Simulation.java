package edu.nd.se2018.homework.hwk5;

import java.util.ArrayList;
import java.util.Collection;

import edu.nd.se2018.homework.hwk5.model.infrastructure.MapBuilder;
import edu.nd.se2018.homework.hwk5.model.infrastructure.RailwayTracks;
import edu.nd.se2018.homework.hwk5.model.infrastructure.Road;
import edu.nd.se2018.homework.hwk5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.hwk5.model.vehicles.Car;
import edu.nd.se2018.homework.hwk5.model.vehicles.Train;
import edu.nd.se2018.homework.hwk5.view.MapDisplay;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Simulation extends Application{
	
	private Pane root;
	private Scene scene;
	private MapBuilder mapBuilder;
	private MapDisplay mapDisplay;
	
	@Override  
	public void start(Stage stage) throws Exception {
		
		root = new Pane();
		
		// Build infrastructure
		mapBuilder = new MapBuilder();
		mapDisplay = new MapDisplay(root, mapBuilder.getRoads(), mapBuilder.getTracks(),mapBuilder.getAllGates());					
		mapDisplay.drawTracks();		
		mapDisplay.drawRoad();
		mapDisplay.drawGate();
		
		scene = new Scene(root,1200,1000);
		stage.setTitle("Railways");
		stage.setScene(scene);
		stage.show();
				
		// Train
		RailwayTracks trackNorth = mapBuilder.getTrack("Royal");
		Train trainNorth = new Train(trackNorth.getEndX()+100,trackNorth.getEndY()-25);
		root.getChildren().add(trainNorth.getImageView());
		RailwayTracks trackSouth = mapBuilder.getTrack("Blue");
		Train trainSouth = new Train(trackSouth.getStartX()-25,trackSouth.getStartY()+25);
		root.getChildren().add(trainSouth.getImageView());
		
		for(CrossingGate gate: mapBuilder.getAllGates())
		{
			//Narrowed down issue to when gate is observing both trains at once
			trainNorth.addObserver(gate);
			trainSouth.addObserver(gate);
		}
				
		// Sets up a repetitive loop i.e., in handle that runs the actual simulation
		new AnimationTimer(){

			@Override
			public void handle(long now) {
			
				createCar();
				trainNorth.moveRight();
				trainSouth.moveLeft();
				
				for(CrossingGate gate: mapBuilder.getAllGates())
					gate.operateGate();
				
				if (trainNorth.offScreen())
					trainNorth.reset();
				if (trainSouth.offScreen())
					trainSouth.reset();
						
				clearCars();				
			}
		}.start();
	}
	
	// Clears cars as they leave the simulation
	private void clearCars(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){			
			if (road.getCarFactory()!= null){
				ArrayList<Car> junkCars = road.getCarFactory().removeOffScreenCars();	
				mapDisplay.removeCarImages(junkCars);
			}
		}
	}
	
	private void createCar(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){
			if (road.getCarFactory() != null){
				if ((int)(Math.random() * 100) == 15){
					Car car = road.getCarFactory().buildCar();
					if (car != null){
						root.getChildren().add(car.getImageView());
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}
}