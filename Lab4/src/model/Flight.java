package model;

import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Flight implements Comparable<Flight>, Comparator<Flight>{

	private GregorianCalendar departureDate;
	private String airline;
	private int flightNumber;
	private String destiny;
	private int gate;
	public Flight(GregorianCalendar departureDate, String airline, int flightNumber, String destiny,
			int gate) {
		this.departureDate = departureDate;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destiny = destiny;
		this.gate = gate;
	}
	public GregorianCalendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(GregorianCalendar departureDate) {
		this.departureDate = departureDate;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDestiny() {
		return destiny;
	}
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}
	public int getGate() {
		return gate;
	}
	public void setGate(int gate) {
		this.gate = gate;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	@Override
	public int compare(Flight fl1, Flight fl2) {
		int fl1d = (int) fl1.getDestiny().charAt(0);
		int fl2d = (int) fl2.getDestiny().charAt(0);
		return (fl1d<fl2d)?-1:(fl1d>fl2d)?1:0;
	}
	
	@Override
	public int compareTo(Flight fl) {
		return ((int)airline.charAt(0)<(int)fl.getAirline().charAt(0))?-1:((int)airline.charAt(0)>(int)fl.getAirline().charAt(0))?1:0;
	}

	
}
