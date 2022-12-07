package Ex1;

public class SUV extends Vehicle implements Runnable {
	 
	private String type;
	
	public SUV(VehicleWasher carWash) {
		super(carWash);
		this.type = "SUV";
	}
	
	public String getType() {
		return this.type;
	}
	
}
