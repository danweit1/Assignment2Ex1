package Ex1;

public class MiniBus extends Vehicle implements Runnable {
	
	private String type;
	
	public MiniBus(VehicleWasher carWash) {
		super(carWash);
		this.type = "Mini Bus";
	}
	
	public String getType() {
		return this.type;
	}



}
