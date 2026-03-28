package classes;

import interfaces.I_Emergency;

public class Emergency implements I_Emergency {
	
	private String location;
	private int casualties;            
    private int casualtiesNeedsDoctor; 

	private EmergencyKinds kind;
	
	public Emergency (String location , EmergencyKinds kind, int patients , int
			patientsDoc) { 
		this.location = location;
		this.kind = kind;
        this.casualties = patients;
        this.setCasualtiesNeedsDoctor(patientsDoc);
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public EmergencyKinds getKind() {
		return kind;
	}

	@Override
	public void setKind(EmergencyKinds kind) {
		this.kind = kind;		
	}

	@Override
	public int getCasualties() {
		return casualties;
	}

	@Override
	public void setCasualties(int casualties) {
        this.casualties = casualties;
	}

	@Override
	public int getCasualtiesNeedsDoctor() {
        return casualtiesNeedsDoctor;
	}

	@Override
	public void setCasualtiesNeedsDoctor(int casualtiesNeedsDoctor) {
	    this.casualtiesNeedsDoctor = casualtiesNeedsDoctor;
		
	}

}
