package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AirportScreen{

	private List<Flight> flights;
	private List<String> airlines;
	private List<String> destinies;
	public AirportScreen() {
		flights= new ArrayList<Flight>();
		airlines = new ArrayList<String>();
		destinies= new ArrayList<String>();
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File("airlines.txt")));
			BufferedReader r2 = new BufferedReader(new FileReader(new File("destinies.txt")));
			String air="";
			while((air=r.readLine())!=null) {
				System.out.println(air);
				airlines.add(air);
			}
			String dest="";
			while((dest=r2.readLine())!=null) {
				System.out.println(dest);
				destinies.add(dest);
			}
		} catch (FileNotFoundException e) {
			File c = new File("airlines.txt");
			File c2 = new File("airlines.txt");
				try {
					if(!c.exists())
						c.createNewFile();
					if(!c2.exists())
						c2.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public void generateRandomFlights(int numberOfFlights) {
		//when a new list is generated, the current one is deleted
		flights.clear();
		for (int i=0;i<numberOfFlights;i++){
			int month = randomNum(0,12);
			int day = randomNum(0,31);
			int hour = randomNum(0,24);
			int minute = randomNum(0,60);
			GregorianCalendar date = new GregorianCalendar(2019,month,day,hour,minute,0);
			int airline = randomNum(0,airlines.size());
			int destiny = randomNum(0,destinies.size());
			int gate = randomNum(1,30);
			flights.add(new Flight(date,airlines.get(airline),i,destinies.get(destiny),gate));
		}
	}
	public int randomNum(int inferiorLimit, int superiorLimit) {
		return (int) Math.floor(Math.random()*superiorLimit + inferiorLimit);
	}
	
	public void sortByHour() {
		
        int n = flights.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (flights.get(j).getDepartureDate().get(Calendar.HOUR_OF_DAY)>flights.get(j+1).getDepartureDate().get(Calendar.HOUR_OF_DAY))  { 
                    Flight temp = flights.get(j); 
                    flights.set(j, flights.get(j+1)); 
                    flights.set(j+1, temp); 
                } 
    } 
	
	public void sortByGate() { 
        int n = flights.size(); 
  
        
        for (int i = 0; i < n-1; i++) 
        { 
            
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (flights.get(j).getGate() < flights.get(min_idx).getGate()) 
                    min_idx = j; 
  
            
            Flight temp = flights.get(min_idx);
            flights.set(min_idx, flights.get(i));
            flights.set(i, temp);
        } 
    }
	
	public void sortByFlightNum() { 
        int n = flights.size(); 
        for (int i = 1; i < n; ++i) { 
            Flight key = flights.get(i); 
            int j = i - 1; 
  
            
            while (j >= 0 && flights.get(j).getFlightNumber() > key.getFlightNumber()) { 
            	flights.set(j+1, flights.get(j));
                j = j - 1; 
            } 
            flights.set(j+1, key);
        } 
    }
	public void sortByAirline() { 
        int n = flights.size(); 
        for (int i = 1; i < n; ++i) { 
            Flight key = flights.get(i); 
            int j = i - 1; 
            while (j >= 0 && key.compareTo(flights.get(j))==-1) { 
            	flights.set(j+1, flights.get(j));
                j = j - 1; 
            } 
            flights.set(j+1, key);
        } 
    }
	public void sortByDestiny() { 
        int n = flights.size(); 
        for (int i = 1; i < n; ++i) { 
            Flight key = flights.get(i); 
            int j = i - 1; 
            while (j >= 0 && key.compare(key, flights.get(j))==-1) { 
            	flights.set(j+1, flights.get(j));
                j = j - 1; 
            } 
            flights.set(j+1, key);
        } 
    }
	public void sortByDate() { 
        int n = flights.size(); 
        for (int i = 1; i < n; ++i) { 
            Flight key = flights.get(i); 
            int j = i - 1; 
            
            while (j >= 0 && flights.get(j).getDepartureDate().getTime().after(key.getDepartureDate().getTime())) { 
            	flights.set(j+1, flights.get(j));
                j = j - 1; 
            } 
            flights.set(j+1, key);
        } 
    }
	
	public int searchFlightNum(int x) { 
		sortByFlightNum();
        int l = 0, r = flights.size() - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            
            if (flights.get(m).getFlightNumber() == x) 
                return m; 
  
            
            if (flights.get(m).getFlightNumber() < x) 
                l = m + 1; 
  
           
            else
                r = m - 1; 
        } 
  
         
        return -1; 
    }
	
	public int searchAirline(String air) {
		sortByAirline();
		int x=0;boolean loop=true;
		for (int i=0;i<flights.size()&&loop;i++) {
			if (flights.get(i).getAirline().equals(air)) {
				x=i;loop=false;
			}
		}
		return x;
	}
}
