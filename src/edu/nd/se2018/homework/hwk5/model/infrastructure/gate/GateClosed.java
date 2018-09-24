package edu.nd.se2018.homework.hwk5.model.infrastructure.gate;

/**
 * Gate in CLOSED state
 * @author jane
 *
 */
public class GateClosed implements IGateState {
	
	private CrossingGate gate;
	private int trainsInStation;
	
	protected GateClosed(CrossingGate gate){
		this.gate = gate;
		trainsInStation = 1;
	}

	@Override
	public void approachStation() {
		trainsInStation++;
		if(trainsInStation >= 1)
		{
			gate.setGateState(gate.getGateClosingState());
		}
		// Do nothing.  Gate is already closed.
		// IF there were two tracks we would have to keep track of how many trains were in the station!	
	}

	@Override
	public void leaveStation() {
		trainsInStation--;
		gate.setGateState(gate.getGateOpeningState());		
	}

	@Override
	public void gateFinishedOpening() {
		// not applicable		
	}

	@Override
	public void gateFinishedClosing() {
		// not applicable.  Gate is already closed.	
	}

	@Override
	public void operate() {
		// Flash lights
		
	}

	@Override
	public String getTrafficAction() {
		return "STOP";
	}
	
	
}
