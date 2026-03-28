package classes;

import interfaces.I_FireStation;

public class FireStation implements I_FireStation {
	
	private int number;
	private String district;
	private List<FireTruck> fireTrucks;
	private List<Ambulance> ambulances;


	

	
	//Constructor
	public FireStation (int number , String name) {
		this.number = number;
		this.district = name;
		this.fireTrucks = new List<>();
		this.ambulances = new List<>();
		

	}

	@Override
	//Returns the number of the fire station
	public int getNumber() {
		return this.number;
	}

	@Override
	//Sets the number of the fire station.
	public void setNumber(int number) {
		 this.number = number;
		
	}

	@Override
	//Returns the district of the fire station.
	public String getDistrict() {
		return this.district;
	}

	@Override
	//Sets the district of the fire station.
	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	//Returns a list of the fire trucks of the fire station.
	public List<FireTruck> getFireTrucks() {
		return this.fireTrucks;
	}

	@Override
	//Sets a list of the fire trucks of the fire station.
	public void setFireTrucks(List<FireTruck> fireTrucks) {
		this.fireTrucks = fireTrucks;
		
	}

	@Override
	//Returns a list of the ambulances of the fire station.
	public List<Ambulance> getAmbulances() {
		return this.ambulances;
	}

	@Override
	//Sets a list of the ambulances of the fire station.
	public void setAmbulances(List<Ambulance> ambulances) {
		this.ambulances = ambulances;
	}

	@Override
	//Adds the given FireTruck object to the list of fire trucks 
	public boolean addVehicle (FireTruck truck) {

        if (truck == null) {
            return false;
        }
        
        // Create a new Node with the FireTruck
        Node<FireTruck> Truck = new Node<>(truck);

        return fireTrucks.append(Truck);
		    }

	
	
	@Override
	//Adds a new fire truck of the given kind to the list 
	public boolean addVehicle(FireTruckKinds kind) {
			
		boolean ret = false;
		    if (kind == null) {
		        return ret; 
		    }
		    int count=1;
		    List<FireTruck> ftlist=this.getFireTrucks();
		    Node<FireTruck> ft = ftlist.getHead();
		    while(ft!=null)
		    {
		    	if(ft.getContent().getKind().equals(kind))
		    	{
		    		count++;
		    	}
		    	ft=ft.getNext();
		    }
		    
		    FireTruck fireTruck = new FireTruck(this.getNumber(),count,kind);
		    Node<FireTruck> node = new Node<FireTruck>(fireTruck);
		    ret = fireTrucks.append(node);

		    return ret;
	}
		    
	@Override
	//Adds the given Ambulance object to the list of ambulances 
	public boolean addVehicle(Ambulance ambulance) {

        if (ambulance == null) {
            return false;
        }
        // Create a new Node with the FireTruck
        Node<Ambulance> ambunode = new Node<>(ambulance);

        return ambulances.append(ambunode);
		    }


	@Override
	//Adds a new ambulance to the list of ambulances
	public boolean addVehicle(boolean hasDoctor) {
	
		boolean ret = false;
		int count = 1;
	    List<Ambulance> ambulances = this.getAmbulances();
	    Node<Ambulance> ambuNode = ambulances.getHead(); 
	    while (ambuNode != null) {
	        if (ambuNode.getContent().getHasDoctor() == hasDoctor) {
	            count++;
	        }
	        ambuNode = ambuNode.getNext();
	    }
	    Ambulance ambulance = new Ambulance(this.getNumber(), count, hasDoctor);
	    Node<Ambulance> ambunode = new Node<>(ambulance);
	    ret = ambulances.append(ambunode);

	    return ret;
	    }
	

	@Override
	//Removes the given fire truck from the list of fire trucks
	public boolean removeVehicle(FireTruck truck) {
		    boolean removed = false;
		    int index = 0;
		    Node<FireTruck> ft = fireTrucks.getHead();

		    while (ft != null) {
		        if (ft.getContent().equals(truck)) {
		            removed = fireTrucks.remove(index);
		            break; 
		        }
		        ft = ft.getNext();
		        index++;
		    }

		    if (removed) {
		        Node<FireTruck> current = fireTrucks.getHead();
		        int newId = 1; 

		        while (current != null) {
		            current.getContent().setID(String.valueOf(newId)); 
		            current = current.getNext();
		            newId++;
		        }
		    }

		    return removed;
		}
	@Override
	//Removes the given ambulance from the list of ambulances
	public boolean removeVehicle(Ambulance ambulance) {
		    int i = 0;
		    boolean removed = false;
		    Node<Ambulance> ambuNode = ambulances.getHead();

		    // Find the ambulance to remove
		    while (ambuNode != null) {
		        if (ambuNode.getContent().equals(ambulance)) {
		            removed = ambulances.remove(i);
		            break; 
		        }
		        ambuNode = ambuNode.getNext();
		        i++;
		    }

		    //  update the IDs of the remaining ambulances 
		    if (removed) {
		        Node<Ambulance> current = ambulances.getHead();
		        int newId = 1; 

		        while (current != null) {
		            current.getContent().setID(String.valueOf(newId)); // Update the ID
		            current = current.getNext();
		            newId++;
		        }
		    }

		    return removed;
		}
	
	@Override
	//Removes the vehicle with the given ID
	public boolean removeVehicle(String ID) {
		    // Search for the vehicle in the fireTrucks list
		    Node<FireTruck> ftNode = fireTrucks.getHead();
		    while (ftNode != null) {
		        if (ftNode.getContent().getID().equals(ID)) {
		            return removeVehicle(ftNode.getContent());
		        }
		        ftNode = ftNode.getNext();
		    }
		    
		    // Search for the vehicle in the ambulances list
		    Node<Ambulance> ambuNode = ambulances.getHead();
		    while (ambuNode != null) {
		        if (ambuNode.getContent().getID().equals(ID)) {
		            return removeVehicle(ambuNode.getContent());
		        }
		        ambuNode = ambuNode.getNext();
		    }
	
		    return false;
		}
	@Override
	public void printVehicles() {
	    sortVehicles();
	    //System.out.println("station " + this.getNumber() + ":");

	  //print firetrucks
	    for (int i = 0; i < fireTrucks.getSize(); i++) {
	        FireTruck truck = fireTrucks.get(i).getContent();
	        System.out.println(truck.getID() + " - FireTruck");
	    }
	    
	    //  print ambulances.
	    for (int i = 0; i < ambulances.getSize(); i++) {
	        Ambulance ambulance = ambulances.get(i).getContent();
	        System.out.println(ambulance.getID() + " - Ambulance");
	    }
	}


	@Override
	public void sortVehicles() {
	    //  fireTrucks list
	    int n = fireTrucks.getSize();
	    if (n >= 2) {
	        // compare adjacent fire trucks
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                String i_id = fireTrucks.get(j).getContent().getID();
	                String j_id = fireTrucks.get(j + 1).getContent().getID();
	                // Split the IDs
	                String[] split1 = i_id.split("/");
	                String[] split2 = j_id.split("/");
	                // Compare vehicle types 
	                int first = Integer.parseInt(split1[1]);
	                int second = Integer.parseInt(split2[1]);
	                if (first > second) {
	                    fireTrucks.swap(j, j + 1);
	                } else if (first == second) {
	                    // If vehicle types are equal, compare the vehicle numbers
	                    int num1 = Integer.parseInt(split1[2]);
	                    int num2 = Integer.parseInt(split2[2]);
	                    if (num1 > num2) {
	                        fireTrucks.swap(j, j + 1);
	                    }
	                }
	            }
	        }
	    }
	    
	    //  ambulances list 
	    int m = ambulances.getSize();
	    if (m >= 2) {
	        for (int i = 0; i < m - 1; i++) {
	            for (int j = i + 1; j < m; j++) {
	                String i_id = ambulances.get(i).getContent().getID();
	                String h_id = ambulances.get(j).getContent().getID();
	                String[] split1 = i_id.split("/");
	                String[] split2 = h_id.split("/");
	                int first = Integer.parseInt(split1[1]);
	                int second = Integer.parseInt(split2[1]);
	                if (first > second) {
	                    ambulances.swap(i, j);
	                } else if (first == second) {
	                    int x = Integer.parseInt(split1[2]);
	                    int y = Integer.parseInt(split2[2]);
	                    if (x > y) {
	                        ambulances.swap(i, j);
	                    }
	                }
	            }
	        }
	    }
	}
}