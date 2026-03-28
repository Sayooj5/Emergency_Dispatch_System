package classes;

import interfaces.I_Ambulance;



public class Ambulance implements I_Ambulance {

	private String ID;
	private Boolean hasDoctor;

	public Ambulance (int stationNum , int vehicleNum , boolean hasDoctor) {
		this.hasDoctor = hasDoctor;

		int vehicletype;
		if (hasDoctor) {
			vehicletype = 81; // Ambulance with doctor
		} else {
			vehicletype = 83; // Ambulance without doctor
		}
		this.ID = stationNum + "/" + vehicletype + "/" + vehicleNum;


	}


	@Override
	public String getID() {
		return this.ID;
	}

	@Override
	public void setID(String ID) {
		this.ID = ID;

	}

	@Override
	public boolean getHasDoctor() {
		return this.hasDoctor;
	}

	@Override
	public void setHasDoctor(boolean hasDoctor) {
		this.hasDoctor = hasDoctor;

	}

}
