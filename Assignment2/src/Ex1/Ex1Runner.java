

package Ex1;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedList;

public class Ex1Runner {

	public static void main(String[] args) throws InterruptedException, IOException {

		 LinkedList<Vehicle> threadsList = new LinkedList<Vehicle>();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of washing stations: ");
		int numStations = input.nextInt();
		System.out.println("Enter a number of cars: ");
		int num = input.nextInt();
		Random rand = new Random();
		int randNum = rand.nextInt(4) + 1;

		VehicleWasher carWash = new VehicleWasher(numStations);

		long start = System.currentTimeMillis();
		
		for (int i = 0; i < num; i++) {
			switch (randNum) {
			case 1:
				Car c = new Car(carWash);
				c.addId();
				threadsList.add(c);
				c.start();
				break;
			case 2:
				Truck t = new Truck(carWash);
				t.addId();
				threadsList.add(t);
				t.start();
				break;
			case 3:
				MiniBus m = new MiniBus(carWash);
				m.addId();
				threadsList.add(m);
				m.start();
				break;
			case 4:
				SUV s = new SUV(carWash);
				s.addId();
				threadsList.add(s);
				s.start();
				break;
			}
			randNum = rand.nextInt(4) + 1;
		}
		
		try {
			for (int i = 0; i < num; i++) {
				threadsList.get(i).join();
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		carWash.printTypeLists();
		
		carWash.getLog().closeLog();
		input.close();
	}

}
