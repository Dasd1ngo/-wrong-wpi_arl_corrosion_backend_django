package flights;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Leg {
	private String airplane;
	private int flightMinutes;
	private String flightNumber;
	private String departureAirport;
	private LocalDateTime departureTime;
	private String arrivalAirport;
	private LocalDateTime arrivalTime;
	private int numOfReservedSeats_FirstClassSeat;
	private String priceOfFirstClassSeat;
	private int numOfReservedSeats_CoachSeat;
	private String priceOfCoachSeat; 
	
	public Leg(){
		
	}
	
	public String getPriceOfCoachSeat() {
		return this.priceOfCoachSeat;
	}
	
	public String getFlightNumber() {
		return this.flightNumber;
	}
	
	public String getPriceOfFirstClassSeat() {
		return this.priceOfFirstClassSeat;
	}
	
	public LocalDateTime getArrivalTime() {
		return this.arrivalTime;
	}
	
	public LocalDateTime getDepartureTime() {
		return this.departureTime;
	}
	
	public void setFlightNumber (String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public void setCoachPrice (String priceOfCoachSeat) {
		this.priceOfCoachSeat = priceOfCoachSeat;
	}
	public void setFirstClassPrice (String priceOfFirstClassSeat) {
		this.priceOfFirstClassSeat = priceOfFirstClassSeat;
	}
	public void setDepartureTime(String Time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm z");
		LocalDateTime time = LocalDateTime.parse(Time, formatter);
		this.departureTime = time;
	}
	
	public void setArrivalTime(String Time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm z");
		LocalDateTime time = LocalDateTime.parse(Time, formatter);
		this.arrivalTime = time;
	}
	
	public LocalTime getDepartureTimeInLocalTime() {
		
		return departureTime.toLocalTime();
	}
	
	public LocalTime getArrivalTimeInLocalTime() {
		
		return arrivalTime.toLocalTime();
	}
}
