package Ex1;

import java.io.IOException;
import java.util.Random;

public abstract class Vehicle extends Thread implements Runnable {

	private VehicleWasher carWash;
	private int id;
	private String type;
	
	public Vehicle(VehicleWasher carWash) {
		this.carWash = carWash;
		Random rand = new Random();
		int randNum = rand.nextInt(9999999) + 1;
		this.id = randNum;
	}
	
	public int getID() {
		return this.id;
	}
	
	public synchronized void addId() {
		this.id++;
	}
	
	public void run() {
		double arrivalTime = (((-Math.log(Math.random())) / 1.5) * 1000);
		try {
			Thread.sleep((long)arrivalTime);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			carWash.addToWait(this);
			carWash.addToWash(this);
			carWash.removeFromWait();
			double washTime = (((-Math.log(Math.random())) / 3) * 1000);
			Thread.sleep((long)washTime);
			carWash.finishWash(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		carWash.sortByType(this);		
	}
}
