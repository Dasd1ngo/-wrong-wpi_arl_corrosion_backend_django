/**
 * 
 */
package utils;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import application.Leg;

/**
 * @author blake and Anqi
 * @version 1.2
 *
 */
public class QueryFactory {
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirports(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airports";
	}
	
	/**
	 * Lock the server database so updates can be written
	 * 
	 * @param teamName is the name of the team to acquire the lock
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String lock (String teamName) {
		return "team=" + teamName + "&action=lockDB";
	}
	
	/**
	 * Unlock the server database after updates are written
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return the String written to the HTTP POST to unlock server database
	 */
	public static String unlock (String teamName) {
		return "team=" + teamName + "&action=unlockDB";
	}
	
	public static String getDepartureFlightFromAirPort(String teamName, String airportCode, LocalDate date) {
		
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
	
		return "?team=" + teamName + "&action=list&list_type=departing&airport=" + airportCode + "&day=" + year + "_" + month + "_" + day;
	}
	
	public static String buyTicket(String teamName, Leg leg, String ticketClass ) {
		String flightsString = "<Flights><Flight number=" + "\"" + leg.getFlightNumber() + "\"" + " seating=" + "\"" + ticketClass +"\""+ "/></Flights>";
		String finalString = "team=" + teamName + "&action=buyTickets&flightData=" + flightsString;
		System.out.println(finalString);
		return finalString;
		
	}
}
