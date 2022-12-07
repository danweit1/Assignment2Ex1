package Ex1;

public class Car extends Vehicle implements Runnable {

	private String type;
	
	public Car(VehicleWasher carWash) {
		super(carWash);
		this.type = "Car";
	}
	
	public String getType() {
		return this.type;
	}

}
