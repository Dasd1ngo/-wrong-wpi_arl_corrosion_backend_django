package flights;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight {
	private ArrayList<Leg> legs;
	private int totalPrice;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private String typeOfClass;
	
	
	Flight(){
		totalPrice = 0;
		typeOfClass = "Coach";
		
	}

	
	
	
	public ArrayList<Leg> getLegs() {
		return legs;
	}
	public int getTotalPrice() {
		return calculateTotalPrice(legs, typeOfClass);
		
	}
	public int calculateTotalPrice(ArrayList<Leg> legs, String typeOfClass) {

		if(typeOfClass.equals("Coach")) {
			for(int i =0; i<legs.size(); i++) {
				totalPrice = totalPrice + legs.get(i).getPriceOfCoachSeat();
			}
		}
		else {
			for(int i =0; i<legs.size(); i++) {
				totalPrice = totalPrice + legs.get(i).getPriceOfFirstClassSeat();
			}
			}
		return totalPrice;
	}
	
	public LocalDateTime getDepartureTime() {
		return legs.get(0).getDepartureTime();
	}

	public LocalDateTime getArrivalTime() {
		return legs.get(legs.size()-1).getArrivalTime();
	}
	
	
	
}