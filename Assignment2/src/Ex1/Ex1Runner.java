package Ex1;

public class Ex1Runner {

	public static void main(String[] args) {

		WehicleWasher w = new WehicleWasher(5);
		boolean can = true;
		
		int counter = 0;
		while (counter < 5 && can) {
			if (w.getStations().get(counter) == null) {
				System.out.println("free");
				w.getStations().add(new Car());
				counter++;
			} else {
				System.out.println("full");
				// wait 
				
			}
			if (counter == 4) {
				counter = 0;
			}
		}
	}

}
