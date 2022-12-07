package Ex1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VehicleWasher {

	private Queue<Vehicle> waitingLine;
	private LinkedList<Vehicle> washingMachine;
	private Semaphore sem;
	private LinkedList<SUV> suvList;
	private LinkedList<Car> carList;
	private LinkedList<Truck> truckList;
	private LinkedList<MiniBus> miniBusList;
	
	public VehicleWasher(int numOfStations) {
		this.waitingLine = new ConcurrentLinkedQueue<Vehicle>();
		this.washingMachine = new LinkedList<Vehicle>();
		this.sem = new Semaphore(numOfStations);
		this.suvList = new LinkedList<SUV>();
		this.carList = new LinkedList<Car>();
		this.truckList = new LinkedList<Truck>();
		this.miniBusList = new LinkedList<MiniBus>();
	}
	
	public Queue<Vehicle> getWaitingLine() {
		return waitingLine; 
	}
	
	public LinkedList<Vehicle> getWashingMachine() {
		return this.washingMachine;
	}
	
	public void addToWait(Vehicle v) {
		this.waitingLine.add(v);
		System.out.println(v.getID() + " is waiting.. ");
	}
	
	public synchronized void removeFromWait() {
		this.waitingLine.remove();
	}
	
	public void addToWash(Vehicle v) throws InterruptedException {
		washingMachine.add(v);
		System.out.println(v.getID() + " has entered the car wash");
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			System.out.println(e);
		}  
	}
	
	public void finishWash(Vehicle v) throws InterruptedException {
		washingMachine.remove();
		sem.release();
		System.out.println(v.getID() + " is clean and leaving the facility.");
	}
	
	public synchronized void sortByType(Vehicle v) {
		if (v instanceof SUV) {
			this.suvList.add((SUV)v); 
		} if (v instanceof Truck) {
			this.truckList.add((Truck)v);
		} if (v instanceof MiniBus) {
			this.miniBusList.add((MiniBus)v);
		} if (v instanceof Car) {
			this.carList.add((Car)v);
		}
	}
	
	public synchronized void printTypeLists() {
		if (suvList != null) {
			System.out.println();
			for (SUV s : suvList) {
				System.out.print("id: " + s.getID() +  ", " + s.getType() + " ");
			}
		}
		if (truckList != null) {
			System.out.println();
			for (Truck t : truckList) {
				System.out.print("id: " + t.getID() +  ", " + t.getType() + " ");
			}	
		}
		if (miniBusList != null) {
			System.out.println();
			for (MiniBus m : miniBusList) {
				System.out.print("id: " + m.getID() +  ", " + m.getType() + " ");
			}
		}
		if (carList != null) {
			System.out.println();
			for (Car c : carList) {
				System.out.print("id: " + c.getID() +  ", " + c.getType() + " ");
			}
		}	
	}
	
}
