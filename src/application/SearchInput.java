package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SearchInput {
	private boolean isRoundTrip;
	private String departureAirport;
	private String arrivalAirport;
	private LocalDate departureDate;
	private LocalDate returnDate;
	private boolean isSearchDateByArriving;
	private String preferredClass;
	
	SearchInput() {
		this.isRoundTrip = false;
		this.departureAirport = new String();
		this.arrivalAirport = new String();
		this.isSearchDateByArriving = false;
		this.preferredClass = new String();
	}
	
	public void setDepartureAirport(String Airport) {
		this.departureAirport = Airport;
	}
	
	public void setArrivalAirport(String Airport) {
		this.arrivalAirport = Airport;
	}
	
	public void setDepartureDate(LocalDate date) {
		this.departureDate = date;
	}
	
	public void setReturnDate(LocalDate localDate) {
		this.returnDate = localDate;
	}
	
	public void setIsRoundTrip(boolean bln) {
		this.isRoundTrip = bln;
	}
	
	public void setIsSearchDateByArriving(boolean bln) {
		this.isSearchDateByArriving = bln;
	}
	public void setPreferredClass(String preferredClass) {
		this.preferredClass = preferredClass;
	}
	
	public void dateValidation() {
		
	}
	
	public void getInfoFromUser() {
		
	}
	
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
}
