package ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import model.AirportScreen;
import model.Flight;

public class ScreenController {
	
	private AirportScreen as;
	private ObservableList<String> items;
	private String titles;
	private SimpleDateFormat date;
	private SimpleDateFormat hour;
	
	public void initialize() {
		as = new AirportScreen();
		items = FXCollections.observableArrayList();
		date = new SimpleDateFormat("yyyy-MM-dd");
		hour = new SimpleDateFormat("HH:mm a");
		titles = "Airline \t\t FL# \t\t Hour \t\t Date \t\t Destiny \t\t Gate";
		as.generateRandomFlights(20);
		as.sortByHour();
		updateItems();
	}
    @FXML
    private ListView<String> flightListView;
    @FXML
    void findFlight(ActionEvent event) {
    	List<String> choices = new ArrayList<String>();
    	choices.add("Flight Number");
    	choices.add("Airline");
    	ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a method", choices);
    	dialog.setTitle("Find Flight");
    	dialog.setHeaderText("Find Flight Dialog");
    	dialog.setContentText("Choose a parameter to search:");
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		TextInputDialog input = new TextInputDialog("");
        	input.setTitle("Find Flights");
        	input.setHeaderText("Search by parameter");
        	input.setContentText("Input information: ");
    	    switch(result.get()) {
    	    case "Flight Number":
    	    	Optional<String> result2 = input.showAndWait();
    	    	if (result2.isPresent()) {
    	    		Alert alert = new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("Information Dialog");
    	    		alert.setHeaderText(null);
    	    		alert.setContentText(generateFlightInfo(as.searchFlightNum(Integer.parseInt(result2.get()))));
    	    		alert.showAndWait();
            	}
    	    	break;
    	    case "Airline":
    	    	Optional<String> result3 = input.showAndWait();
    	    	if (result3.isPresent()) {
    	    		Alert alert = new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("Information Dialog");
    	    		alert.setHeaderText(null);
    	    		alert.setContentText(generateFlightInfo(as.searchAirline(result3.get())));
    	    		alert.showAndWait();
            	}
    	    	break;
    	    }
    	}
    }
    @FXML
    void generate(ActionEvent event) {
    	TextInputDialog input = new TextInputDialog("Number of flights to generate");
    	input.setTitle("Generate Flights");
    	input.setHeaderText("How many flights do you want to generate?");
    	input.setContentText("Number: ");
    	Optional<String> result = input.showAndWait();
    	if (result.isPresent()) {
    		if(Integer.parseInt(result.get())>0)
    			as.generateRandomFlights(Integer.parseInt(result.get()));updateItems();
    	}
    }
    @FXML
    void sortByAirline(ActionEvent event) {
    	as.sortByAirline();
    	updateItems();
    }
    @FXML
    void sortByDate(ActionEvent event) {
    	as.sortByDate();
    	updateItems();
    }
    @FXML
    void sortByDestiny(ActionEvent event) {
    	as.sortByDestiny();
    	updateItems();
    }
    @FXML
    void sortByFlightNumber(ActionEvent event) {
    	as.sortByFlightNum();
    	updateItems();
    }
    @FXML
    void sortByGate(ActionEvent event) {
    	as.sortByFlightNum();
    	updateItems();
    }
    @FXML
    void sortByHour(ActionEvent event) {
    	as.sortByHour();
    	updateItems();
    }
    public void updateItems() {
    	items.clear();
    	items.add(titles);
    	for (int i=0;i<as.getFlights().size();i++) {
    		items.add(generateFlightInfo(i));
    	}
    	flightListView.setItems(items);
    }
    public String generateFlightInfo(int index) {
    	Flight fl = as.getFlights().get(index);
    	return fl.getAirline()+" \t\t "+fl.getFlightNumber()+" \t\t "+hour.format(fl.getDepartureDate().getTime())
		+" \t\t "+date.format(fl.getDepartureDate().getTime())+" \t\t "+fl.getDestiny()+" \t\t "+fl.getGate();
    }

}

