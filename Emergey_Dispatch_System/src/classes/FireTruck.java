package classes;

import interfaces.I_FireTruck;



public class FireTruck implements I_FireTruck {
	
	  private String ID;                 
	  private FireTruckKinds kind;    
	 
	  //Constructor
	  public FireTruck (int stationNum , int vehicleNum , FireTruckKinds kind) {
	        this.kind = kind;
            //Type of firetruck
	        int vehicleType = 0; 
	        switch (kind) {
	        case CommandVehicle:
	            vehicleType = 11;
	            break;
	        case LadderTruck:
	            vehicleType = 33;
	            break;
	        case FireEngine:
	            vehicleType = 49;
	            break;
	        case RescueTruck:
	            vehicleType = 52;
	            break;
	        case HazmatTruck:
	            vehicleType = 78;
	            break;
	        default:
	        	System.out.print("Unknown FireTruckKind: " + kind);
	    }
	        this.ID = stationNum + "/" + vehicleType + "/" + vehicleNum;

	  }

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

	@Override
	public void setID(String ID) {
		// TODO Auto-generated method stub
		this.ID = ID;
	}

	@Override
	public FireTruckKinds getKind() {
		// TODO Auto-generated method stub
		return this.kind;
	}

	@Override
	public void setKind(FireTruckKinds kind) {
		// TODO Auto-generated method stub
		this.kind = kind;
        
	}

}
