package edu.nd.se2018.homework.hwk5.model.infrastructure.gate;

import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.hwk5.model.vehicles.Train;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Context class for Crossing Gate
 * @author jane
 *
 */
public class CrossingGate extends Observable implements Observer{
	
	// Crossing Gate location and its trigger & exit points
	private int anchorX;
	private int anchorY;
	private int movingX;
	private int movingY;
	private int triggerPoint;
	private int exitPointLeft;
	private int exitPointRight;

	private IGateState gateClosed;
	private IGateState gateOpen;
	private IGateState gateClosing;
	private IGateState gateOpening;
	private IGateState currentGateState;
	private Line line; 
	private boolean gateDown = false; 
	
	String gateName;
	
	public CrossingGate(){}
	
	public CrossingGate(int xPosition, int yPosition, String crossingGate){		
		anchorX = xPosition;
		anchorY = yPosition;
		movingX = anchorX;
		movingY = anchorY-60;
		triggerPoint = anchorX+300;
		exitPointLeft = anchorX-300;
		exitPointRight = triggerPoint;
		
		// Gate elements
		line = new Line(anchorX, anchorY,movingX,movingY);
		line.setStroke(Color.RED);
	    line.setStrokeWidth(10);
		
		// Gate States
		gateClosed = new GateClosed(this);
		gateOpen = new GateOpen(this);
		gateOpening = new GateOpening(this);
		gateClosing = new GateClosing(this);
		currentGateState = gateOpen;
		gateName = crossingGate;
	}
	
	public Line getGateLine(){
		return line;
	}
	
	public void operateGate(){
		currentGateState.operate();
	}
	
	public void close(){
		if (movingY<anchorY){		
		    movingX+=1;
		    movingY+=1;
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		} else {
			currentGateState.gateFinishedOpening();
		}
	}
	
	public void open(){
		if (movingX>anchorX){
			movingX-=1;
			movingY-=1;		
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		}  else {
			currentGateState.gateFinishedOpening();
		}
	}
	
	// State getters and setters
	public IGateState getGateClosedState(){
		return gateClosed;
	}
	public IGateState getGateOpenState(){
		return gateOpen;
	}
	public IGateState getGateClosingState(){
		return gateClosing;
	}
	public IGateState getGateOpeningState(){
		return gateOpening;
	}
	
	public void setGateState(IGateState newState){
		currentGateState = newState;
		setChanged();
		notifyObservers();
	}
	
	public int getAnchor()
	{
		return anchorX;
	}
	
	public String getTrafficCommand(){
		return currentGateState.getTrafficAction();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Train){
			Train train = (Train)o;

			
			if(train.getVehicleX() < exitPointRight && train.getVehicleX() > exitPointLeft && train.getVehicleY() == 475)
			{
				currentGateState.approachStation();		//If the train is the top train and is in range of the gate, approach
			}
			
			if(train.getVehicleX() < exitPointLeft && train.getVehicleX() > exitPointLeft - 10 && train.getVehicleY() == 475)
			{
				currentGateState.leaveStation();		//If the train is the top trains and is to the left of the gate, leave
			}
			
			if(train.getVehicleX() > exitPointRight && train.getVehicleX() < exitPointRight + 10 && train.getVehicleY() == 525)
			{
				currentGateState.leaveStation();		//If the train is the bottom train and is in range of the gate, approach
			}
			
			if(train.getVehicleX() > exitPointLeft && train.getVehicleX() < exitPointRight && train.getVehicleY() == 525)
			{
				currentGateState.approachStation();		//If the train is the bottom trains and is to the right of the gate, leave
			}
			
		}	
	}
}
