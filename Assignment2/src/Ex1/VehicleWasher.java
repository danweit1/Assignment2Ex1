package Ex1;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	private VehicleLogger log;
	private double sumCar, sumTruck, sumSUV, sumMiniBus;
	private boolean first = true;
	private long start, end;
	private boolean firstSuv = true, firstTruck = true, firstMiniBus = true, firstCar = true;
	
	public VehicleWasher(int numOfStations) throws IOException {
		this.waitingLine = new ConcurrentLinkedQueue<Vehicle>();
		this.washingMachine = new LinkedList<Vehicle>();
		this.sem = new Semaphore(numOfStations);
		this.log = new VehicleLogger();
	}
	
	public Queue<Vehicle> getWaitingLine() {
		return waitingLine; 
	}
	
	public LinkedList<Vehicle> getWashingMachine() {
		return this.washingMachine;
	}
	
	public synchronized void addToWait(Vehicle v) throws IOException {
		if (this.first) {
			start = System.currentTimeMillis();
			this.first = false;
		}
		String str = "\n";
		this.waitingLine.add(v);
		if (v instanceof SUV) {
			this.sumSUV++;
			System.out.println("An " + ((SUV)v).getType() + ", id: " + v.getID() + " is waiting.. ");
			str += "An " + ((SUV)v).getType() + ", id: " + v.getID() + " is waiting.. ";
		} if (v instanceof Truck) {
			this.sumTruck++;
			System.out.println("A " + ((Truck)v).getType() + ", id: " + v.getID() + " is waiting.. ");
			str += "A " + ((Truck)v).getType() + ", id: " + v.getID() + " is waiting.. ";
		} if (v instanceof MiniBus) {
			this.sumMiniBus++;
			System.out.println("A " + ((MiniBus)v).getType() + ", id: " + v.getID() + " is waiting.. ");
			str += "A " + ((MiniBus)v).getType() + ", id: " + v.getID() + " is waiting.. ";
		} if (v instanceof Car) {
			this.sumCar++;
			System.out.println("A " + ((Car)v).getType() + ", id: " + v.getID() + " is waiting.. ");
			str += "A " + ((Car)v).getType() + ", id: " + v.getID() + " is waiting.. ";
		}
		this.log.write(str);
	}
	
	public synchronized void removeFromWait() {
		if (!this.waitingLine.isEmpty()) {
			this.waitingLine.remove();
		} 
	}
	
	public void addToWash(Vehicle v) throws InterruptedException, IOException {
		String str = "\n";
		washingMachine.add(v);
		if (v instanceof SUV) {
			System.out.println("The " + ((SUV)v).getType() + ", id: " + v.getID() + " has entered the car wash");
			str += "The " + ((SUV)v).getType() + ", id: " + v.getID() + " has entered the car wash";
		} if (v instanceof Truck) {
			System.out.println("The " + ((Truck)v).getType() + ", id: " + v.getID() + " has entered the car wash");
			str += "The " + ((Truck)v).getType() + ", id: " + v.getID() + " has entered the car wash";
		} if (v instanceof MiniBus) {
			System.out.println("The " + ((MiniBus)v).getType() + ", id: " + v.getID() + " has entered the car wash");
			str += "The " + ((MiniBus)v).getType() + ", id: " + v.getID() + " has entered the car wash";
		} if (v instanceof Car) {
			System.out.println("The " + ((Car)v).getType() + ", id: " + v.getID() + " has entered the car wash ");
			str += "The " + ((Car)v).getType() + ", id: " + v.getID() + " has entered the car wash";
		}
		this.log.write(str);
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			System.out.println(e);
		}  
	}
	
	public void finishWash(Vehicle v) throws InterruptedException, IOException  {
		String str = "\n";
		washingMachine.remove();
		sem.release();
		if (v instanceof SUV) {
			System.out.println("The " + ((SUV)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.");
			str += "The " + ((SUV)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.";
		} if (v instanceof Truck) {
			System.out.println("The " + ((Truck)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.");
			str += "The " + ((Truck)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.";
		} if (v instanceof MiniBus) {
			System.out.println("The " + ((MiniBus)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.");
			str += "The " + ((MiniBus)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.";
		} if (v instanceof Car) {
			System.out.println("The " + ((Car)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.");
			str += "The " + ((Car)v).getType() + ", id: " + v.getID() + " is clean and leaving the facility.";
		}
		this.log.write(str);
	}
	
	
	public synchronized void sortByType(Vehicle v) {
		if (v instanceof SUV) {
			if (firstSuv) {
				this.suvList = new LinkedList<SUV>();
				firstSuv = false;
			}
			this.suvList.add((SUV)v); 
		} if (v instanceof Truck) {
			if (firstTruck) {
				this.truckList = new LinkedList<Truck>();
				firstTruck = false;
			}
			this.truckList.add((Truck)v);
		} if (v instanceof MiniBus) {
			if (firstMiniBus) {
				this.miniBusList = new LinkedList<MiniBus>();
				firstMiniBus = false;
			}
			this.miniBusList.add((MiniBus)v);
		} if (v instanceof Car) {
			if (firstCar) {
				this.carList = new LinkedList<Car>();
				firstCar = false;
			}
			this.carList.add((Car)v);
		}
	}
	
	public VehicleLogger getLog() {
		return this.log;
	}
	
	public synchronized void printTypeLists() throws IOException {
		String str = "\n";
		System.out.println();
		if (suvList != null) {
			System.out.println("\nSUV list: ");
			str += "\nSUV list: ";
			for (SUV s : suvList) {
				System.out.print("| id: " + s.getID() +  ", " + s.getType() + " |");
				str += "| id: " + s.getID() +  ", " + s.getType() + " |";
			}
			this.end = System.currentTimeMillis();
			double time = (double)end - (double)start;
			System.out.println();
			System.out.printf("Average waiting time for mini SUV's: %.2f", 
					(time / this.sumSUV) / 1000, " seconds");
			str += String.format("\nAverage waiting time for SUV's: %.2f", 
					(time / this.sumSUV) / 1000, " seconds");
		}
		if (truckList != null) {
			System.out.println("\nTruck list: ");
			str += "\nTruck list: ";
			for (Truck t : truckList) {
				System.out.print("| id: " + t.getID() +  ", " + t.getType() + " |");
				str += "\n| id: " + t.getID() +  ", " + t.getType() + " |";
			}
			this.end = System.currentTimeMillis();
			double time = (double)end - (double)start;
			System.out.println();
			System.out.printf("Average waiting time for mini trucks: %.2f", 
					(time / this.sumTruck) / 1000, " seconds");
			str += String.format("\nAverage waiting time for trucks: %.2f", 
					(time / this.sumTruck) / 1000, " seconds");
		}
		if (miniBusList != null) {
			System.out.println("\nMini Bus list: ");
			str += "\nMini Bus list: ";
			for (MiniBus m : miniBusList) {
				System.out.print("| id: " + m.getID() +  ", " + m.getType() + " |");
				str += "\n| id: " + m.getID() +  ", " + m.getType() + " |";
			}
			this.end = System.currentTimeMillis();
			double time = (double)end - (double)start;
			System.out.println();
			System.out.printf("Average waiting time for mini buses: %.2f", 
					(time / this.sumMiniBus) / 1000, " seconds");
			str += String.format("\nAverage waiting time for mini buses: %.2f", 
					(time / this.sumMiniBus) / 1000, " seconds");
		}
		if (carList != null) {
			System.out.println("\nCar list: ");
			str += "\nCar list: ";
			for (Car c : carList) {
				System.out.print("| id: " + c.getID() +  ", " + c.getType() + " |");
				str += "\n| id: " + c.getID() +  ", " + c.getType() + " |";
			}
			this.end = System.currentTimeMillis();
			double time = (double)end - (double)start;
			System.out.println();
			System.out.printf("Average waiting time for cars: %.2f", 
					(time / this.sumCar) / 1000, " seconds");
			str += String.format("\nAverage waiting time for cars: %.2f", 
					(time / this.sumCar) / 1000, " seconds");
		}	
		this.end = System.currentTimeMillis();
		double time = (double)this.end - (double)this.start;
		System.out.println();
		System.out.printf("Total time for the facility: %.2f" , time / 1000d, "seconds");
		str += String.format("\nTotal time for the facility: %.2f" , time / 1000d, "seconds");
		this.log.write(str);	
	}
	
}
