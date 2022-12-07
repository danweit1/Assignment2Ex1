package Ex1;

public class Truck extends Vehicle implements Runnable {

	private String type;
	
	public Truck(VehicleWasher carWash) {
		super(carWash);
		this.type = "Truck";
	}
	
	public String getType() {
		return this.type;
	}
	

}
