# Emergency Dispatch Center Simulator (Java)

Java simulation of an emergency dispatch center that registers calls, prioritizes them by severity, and dispatches fire trucks and ambulances accordingly.

---

## What it does

- Models emergencies, fire stations, fire trucks, and ambulances as Java classes. 
- Assigns vehicle requirements based on `EmergencyKinds` (e.g. FireLarge vs MedicalEmergency). 
- Calculates and dispatches the correct mix of fire trucks and ambulances (with/without doctor) per call. 
- Uses a custom generic doubly linked list (`List<T>` and `Node<T>`) instead of Java collections.

---

## How it is built

- Packages: `interfaces`, `classes`, `tests` as required by the assignment.
- Core classes in `classes`: 
  - `Emergency`, `Ambulance`, `FireTruck`, `FireStation`, `EmergencyDispatchCenter`  
  - `List<T>`, `Node<T>`, enums `FireTruckKinds`, `EmergencyKinds`  
- `EmergencyDispatchCenter`:
  - Manages fire stations and a waiting list of emergencies.
  - `sortCalls()` orders emergencies from large to small.
  - `dispatchVehicles()` checks availability and allocates vehicles; returns whether all calls can be fully served.

---

## Why it is relevant

- Shows ability to implement **data structures** (linked list, node) from scratch.  
- Demonstrates **OO design under strict interface and library constraints**. 
- Encodes non-trivial **business rules**: priority handling, vehicle combinations, ambulance/doctors per casualty.  
- Written to pass a predefined JUnit test suite, emphasizing correctness and testability.

---

## Tech & constraints

- Language: Java  
- Testing: JUnit 5  
- Allowed standard classes only: `Object`, `String`, `StringBuilder`, `Math`, `Arrays` (no collections, no third-party libs).
