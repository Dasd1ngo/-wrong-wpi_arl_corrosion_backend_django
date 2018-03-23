package xmlParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import application.Leg;


public class xmlParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LocalDate date = LocalDate.of(2018, 05, 20);
		System.out.println(date);
		String xml = ServerInterface.INSTANCE.getDepartureFlightFromAirPort("BombSquad", "BOS", date);
		//System.out.println(xml);
		
	    xmlParserFlight parser = new xmlParserFlight(xml, "CVG");
	    ArrayList<Leg> result = parser.getResultAsLegList();
	    for(Leg l: result) {
	    	System.out.println(l.getFlightNumber());
	    }

	}

}
