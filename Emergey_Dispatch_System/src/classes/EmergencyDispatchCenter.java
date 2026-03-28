package classes;

import interfaces.I_EmergencyDispatchCenter;

public class EmergencyDispatchCenter implements I_EmergencyDispatchCenter {

    private List<FireStation> fireStations;
    private List<Emergency> emergencies;

    // Constructor
    public EmergencyDispatchCenter() {
        this.fireStations = new List<>();
        this.emergencies = new List<>();
    }
    
    @Override
    // Returns the list of fire stations
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    @Override
    // Sets the list of fire stations
    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    @Override
    // Returns the list of emergencies
    public List<Emergency> getEmergencies() {
        return emergencies;
    }

    @Override
    // Sets the list of emergencies
    public void setEmergencies(List<Emergency> emergencies) {
        this.emergencies = emergencies;
    }

    @Override
    // Adds a fire station to the dispatch center
    public boolean addFireStation(FireStation station) {
        return fireStations.append(new Node<>(station));
    }

    @Override
    // Adds an ambulance to the given station
    public boolean addVehicle(int station, boolean hasDoctor) {
        Node<FireStation> node = fireStations.getHead();
        while (node != null) {
            if (node.getContent().getNumber() == station) {
                return node.getContent().addVehicle(hasDoctor);
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    // Adds a fire truck of the given kind to the specified station
    public boolean addVehicle(int station, FireTruckKinds kind) {
        Node<FireStation> node = fireStations.getHead();
        while (node != null) {
            if (node.getContent().getNumber() == station) {
                return node.getContent().addVehicle(kind);
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    // Removes a vehicle with the given ID from the appropriate fire station
    public boolean removeVehicle(String ID) {
        String[] parts = ID.split("/");
        int fsNo = Integer.parseInt(parts[0]);
        Node<FireStation> node = fireStations.getHead();
        boolean ret = false;
        while (node != null) {
            if (node.getContent().getNumber() == fsNo) {
                ret = node.getContent().removeVehicle(ID);
            }
            node = node.getNext();
        }
        return ret;
    }

    @Override
    // Creates a new emergency call
    public Emergency newCall(String location, EmergencyKinds kind, int patients, int patientsNeedDoctor) {
        return new Emergency(location, kind, patients, patientsNeedDoctor);
    }

    @Override
    // Adds an emergency call to the list of emergencies
    public boolean addCalltoList(Emergency call) {
        return emergencies.append(new Node<>(call));
    }

    @Override
    // Sorts the emergency calls from large to small
    public void sortCalls() {
        int n = emergencies.getSize();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (emergencies.get(j).getContent().getKind().ordinal() > emergencies.get(j + 1).getContent().getKind().ordinal()) {
                    emergencies.swap(j, j + 1);
                }
            }
        }
    }

    @Override
    // Sorts the emergency vehicles in ascending order by ID
    public void sortVehicles() {
        Node<FireStation> stationNode = fireStations.getHead();
        while (stationNode != null) {
            FireStation station = stationNode.getContent();
            station.sortVehicles();
            stationNode = stationNode.getNext();
        }
    }

    @Override
    // Dispatches vehicles to handle emergencies
    public boolean dispatchVehicles() {
        for (Node<Emergency> emergencyNode = emergencies.getHead(); emergencyNode != null; emergencyNode = emergencyNode.getNext()) {
            Emergency emergency = emergencyNode.getContent();
            String[][] trucksRequired = dispatchForEmergency(emergency);
            List<FireStation> fs = this.getFireStations();
            Node<FireStation> fsNode = fs.getHead();
            while (fsNode != null) {
                if (fsNode.getContent().getDistrict().equals(emergency.getLocation())) {
                    for (int i = 0; i < trucksRequired.length; i++) {
                        String trk = trucksRequired[i][0];
                        int trkCount = Integer.parseInt(trucksRequired[i][1]);
                        int actualCount = 0;
                        List<FireTruck> ftList = fsNode.getContent().getFireTrucks();
                        Node<FireTruck> ftNode = ftList.getHead();
                        while (ftNode != null) {
                            if (ftNode.getContent().getKind().name().equals(trk)) {
                                actualCount++;
                            }
                            ftNode = ftNode.getNext();
                        }
                        if (actualCount < trkCount) {
                            return false;
                        }
                    }
                }
                fsNode = fsNode.getNext();
            }
        }
        return true;
    }

    @Override
    // Prints all vehicles responding to the given emergency
    public void printRespondingVehicles(Emergency call) {
        System.out.print("To " + call.getKind() + " in " + call.getLocation() + " is responding:\n");
        Node<FireStation> stationNode = fireStations.getHead();
        while (stationNode != null) {
            if (stationNode.getContent().getDistrict().equals(call.getLocation())) {
                Node<FireTruck> ftHead = stationNode.getContent().getFireTrucks().getHead();
                while (ftHead != null) {
                    System.out.print(ftHead.getContent().getID() + " - " + ftHead.getContent().getKind() + "\n");
                    ftHead = ftHead.getNext();
                }
            }
            stationNode = stationNode.getNext();
        }
    }


    private String[][] dispatchForEmergency(Emergency emergency) {
        // Array of vehicle type and count for each emergency type
        final String[][][] EMERGENCY_VEHICLES = {
            {{"RescueTruck", "1"}, {"FireEngine", "5"}, {"LadderTruck", "2"}, {"CommandVehicle", "1"}}, // FireLarge
            {{"RescueTruck", "2"}, {"FireEngine", "4"}, {"LadderTruck", "1"}, {"CommandVehicle", "1"}}, // TechnicalEmergencyLarge
            {{"HazmatTruck", "2"}, {"RescueTruck", "1"}, {"FireEngine", "4"}, {"LadderTruck", "1"}, {"CommandVehicle", "1"}}, // HazmatEmergencyLarge
            {{"FireEngine", "3"}, {"LadderTruck", "1"}, {"CommandVehicle", "1"}}, // FireMiddle
            {{"RescueTruck", "1"}, {"FireEngine", "2"}, {"CommandVehicle", "1"}}, // TechnicalEmergencyMiddle
            {{"HazmatTruck", "1"}, {"RescueTruck", "1"}, {"FireEngine", "2"}, {"CommandVehicle", "1"}}, // HazmatEmergencyMiddle
            {{"FireEngine", "1"}}, // FireSmall
            {{"FireEngine", "1"}, {"LadderTruck", "1"}}, // TechnicalEmergencySmall
            {{"FireEngine", "2"}}, // HazmatEmergencySmall
            {} // MedicalEmergency (no fire trucks)
        };

        // Base vehicles required
        String[][] baseVehicles = EMERGENCY_VEHICLES[emergency.getKind().ordinal()];
        String[][] dispatchedVehicles;
        int casualties = emergency.getCasualties();
        int casualtiesNeedsDoctor = emergency.getCasualtiesNeedsDoctor();

        // Calculate total vehicle count: Base vehicles + ambulances + ambulances with doctors
        dispatchedVehicles = new String[baseVehicles.length + (casualties > 0 ? 1 : 0) + (casualtiesNeedsDoctor > 0 ? 1 : 0)][2];

        // Copy base vehicles into the new array
        int i = 0;
        for (; i < baseVehicles.length; i++) {
            dispatchedVehicles[i][0] = baseVehicles[i][0];  // Vehicle name
            dispatchedVehicles[i][1] = baseVehicles[i][1];  // Count
        }

        // Add ambulances if needed
        if (casualties > 0) {
            dispatchedVehicles[i][0] = "ambulance";
            dispatchedVehicles[i][1] = String.valueOf(casualties);
            i++;
        }

        // Add ambulances with doctors if needed
        if (casualtiesNeedsDoctor > 0) {
            dispatchedVehicles[i][0] = "ambulance_with_doctor";
            dispatchedVehicles[i][1] = String.valueOf(casualtiesNeedsDoctor);
        }

        return dispatchedVehicles;
    }
}
