package Ex1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Ex1Runner {

	public static void main(String[] args) {

		 
		
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
				c.start();
				break;
			case 2:
				Truck t = new Truck(carWash);
				t.addId();
				t.start();
				break;
			case 3:
				MiniBus m = new MiniBus(carWash);
				m.addId();
				m.start();
				break;
			case 4:
				SUV s = new SUV(carWash);
				s.addId();
				s.start();
				break;
			}
			randNum = rand.nextInt(4) + 1;
		}

		int count = 0;
		
		
		carWash.printTypeLists();

		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println();
		System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
		input.close();
	}

}
