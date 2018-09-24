package edu.nd.se2018.homework.hwk5.model.vehicles;

import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.hwk5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.hwk5.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * @author jane
 *
 */
public class Car extends Observable implements IVehicle, Observer{
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarY = -1;  // Current Y position of car directly infront of this one
	private double speed = 0.5;
	private boolean moveWest = false;					//Boolean to keep track of if the car is moving between highways
	private Collection<CrossingGate> gates = null;
	private CrossingGate otherGate;						//Passed from road to have access to the other gate for when the car switches roads
		
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 * @param gates 
	 */
	public Car(int x, int y, Collection<CrossingGate> gates, CrossingGate otherGate){
		this.currentX = x;
		this.currentY = y;
		this.gates = gates;
		this.otherGate = otherGate;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	public double getVehicleY(){
		return currentY;
	}
	
	public void move(){
		boolean canMove = true; 
		if(getVehicleY() < 394 && getVehicleY() > 390 && getVehicleX() == 791)		//If the car is in range of the western road and is in the right road
		{
			if(Math.random() > .998)												//This gets hit extremely often when the car is stopped at a gate so the chance is very low
			{
				ivCar.setRotate(ivCar.getRotate() + 90);
				moveWest = true;													//The car is going to move west
				Object[] gatesArray = gates.toArray();								
				((CrossingGate)gatesArray[0]).deleteObserver(this);					//Remove the car from it's first gate
				otherGate.addObserver(this);										//Add it as an observer to the other gate
				
			}
		}
		if(moveWest)
		{
			ivCar.setX(ivCar.getX()-1);												//Move the image west
			this.currentX--;														//Move the car west
			if(ivCar.getX() < 400 && ivCar.getX() > 394)							//If it hits the western highway
			{
				this.leadCarY = -1;													//It no loger has a lead car and no longer moves west
				moveWest = false;
				ivCar.setRotate(ivCar.getRotate() - 90);
			}
			setChanged();															//Update observers so cars that were behind it knows it is moving west
			notifyObservers();
		}
		else
		{
			// First case.  Car is at the front of the stopping line.
			if (gateDown && getVehicleY() < 430 && getVehicleY()> 390)
				canMove = false;
			
			// Second case. Car is too close too other car.
			if (leadCarY != -1  && getDistanceToLeadCar() < 100)
				canMove = false;
			
			if (canMove){
				currentY+=speed;
				ivCar.setY(currentY);
				setChanged();
				notifyObservers();
			}
		}
		
		
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown){
		this.gateDown = gateDown;
	}
	
	public boolean offScreen(){
		if (currentY > 1020)
			return true;
		else
			return false;			
	}
		
	public void reset(){
		currentY = originalY;
	}
	
	public double getDistanceToLeadCar(){
		return Math.abs(leadCarY-getVehicleY());
	}
	
	public void removeLeadCar(){
		leadCarY = -1;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car){
			leadCarY = (((Car)o).getVehicleY());
		if (leadCarY > 1020 || (((Car)o).getMoveWest()))							//If the lead car is out of range or is moving west, set lead car to -1 
			{
				leadCarY = -1;
			}
		}
			
		if (o instanceof CrossingGate){
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand()=="STOP")			
				gateDown = true;
			else
				gateDown = false;
					
		}				
	}

	private boolean getMoveWest() {
		return this.moveWest;
	}	
}
