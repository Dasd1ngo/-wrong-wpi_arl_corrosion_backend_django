package application;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.w3c.dom.NodeList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import xmlParser.ServerInterface;
import xmlParser.xmlParserFlight;

public class mainController {
	
	ObservableList<String> cBoxPrefClassList = FXCollections.observableArrayList("Coach", "Business");
	ObservableList<String> cBoxTypeOfTripList = FXCollections.observableArrayList("Oneway", "Round Trip");
	ArrayList<Leg> results;
	Leg selectedLeg;
	
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblDepCity;
	@FXML
	private Label lblArvCity;
	@FXML
	private Label lblPrefClass;
	@FXML
	private Label lblRtrnDate;
	@FXML
	private Label lblDepDate;
	@FXML
	private Label lblTypeOfTrip;
	@FXML
	private ComboBox<String> cBoxPrefClass;
	@FXML
	private ComboBox<String> cBoxTypeOfTrip;
	@FXML
	private DatePicker dtpkRtrnDate;
	@FXML
	private DatePicker dtpkDepDate;
	@FXML
	private TextField txtDepCity;
	@FXML
	private TextField txtArvCity;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnReserveTicket;
	@FXML
	private TableView<Leg> resultTable;
	@FXML
	private TableColumn<Leg, String> flightNumberCol;
	@FXML
	private TableColumn<Leg, String> coachPriceCol;
	@FXML
	private TableColumn<Leg, LocalTime> colDepartureTime;
	@FXML
	private TableColumn<Leg, LocalTime> colArrivalTime;
	@FXML
	private TableColumn<Leg, String> colFirstClassPrice;
	@FXML
	private TableColumn<Leg, String> colTixAvailableFirstClass;
	@FXML
	private TableColumn<Leg, String> colTixAvailableCoach;
	
	
	@FXML
	private void initialize() {
		//LocalDate presetDate = LocalDate.of(2018, 5, 20);
		//dtpkDepDate.setValue(presetDate);
		cBoxTypeOfTrip.setValue("Oneway");
		cBoxTypeOfTrip.setItems(cBoxTypeOfTripList);
		
		cBoxPrefClass.setValue("Coach");
		cBoxPrefClass.setItems(cBoxPrefClassList);
		
		// default is oneway, hide returning DatePicker and its Label
		
		dtpkRtrnDate.setVisible(false);
		lblRtrnDate.setVisible(false);
		
		cBoxTypeOfTrip.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				lblStatus.setText("type of trip just changed");
				if(cBoxTypeOfTrip.getValue().equals("Oneway")) {
					dtpkRtrnDate.setVisible(false);
					lblRtrnDate.setVisible(false);
					
				}
				else if(cBoxTypeOfTrip.getValue().equals("Round Trip")) {
					dtpkRtrnDate.setVisible(true);
					lblRtrnDate.setVisible(true);
				}
				
			}
			
		}
				);
		
		resultTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		     selectedLeg =newSelection;
		    }
		});
		
	}
	
	private ObservableList<Leg> getFlights() {
		// TODO Auto-generated method stub
		
		ObservableList<Leg> flights = FXCollections.observableArrayList();
		for(int i =0; i< results.size();i++) {
			flights.add(results.get(i));
		}
        
        return flights;
		
	}

	public SearchInput createUserInput() {
		SearchInput userInput = new SearchInput();
		
		if(cBoxTypeOfTrip.getValue() == "Oneway") {
			userInput.setIsRoundTrip(false);
		}else {
			userInput.setIsRoundTrip(true);
			userInput.setReturnDate(dtpkRtrnDate.getValue());
		}
		
		userInput.setDepartureAirport(txtDepCity.getText());
		userInput.setArrivalAirport(txtArvCity.getText());
		userInput.setDepartureDate(dtpkDepDate.getValue());
		userInput.setPreferredClass(cBoxPrefClass.getValue());
		
			    System.out.println("Trip Type: "+ cBoxTypeOfTrip.getValue());
				System.out.println("Departing City: "+ txtDepCity.getText());
				System.out.println("Arriving City: "+ txtArvCity.getText());
				System.out.println("Departing Date: "+ dtpkDepDate.getValue());
				System.out.println("Returning Date: "+ dtpkRtrnDate.getValue());
				System.out.println("Preferred Class: "+ cBoxPrefClass.getValue());
		
		return userInput;
		
	}
	
	public void SearchFlight (ActionEvent event) {
		//type of trip
		SearchInput userInput = this.createUserInput();

		//test cases using xmlParser
		String xml = ServerInterface.INSTANCE.getDepartureFlightFromAirPort("BombSquad", userInput.getDepartureAirport(), userInput.getDepartureDate());
		xmlParserFlight parser = new xmlParserFlight(xml, userInput.getArrivalAirport());
		results = parser.getResultAsLegList();
		
		//set up the columns in the table
        flightNumberCol.setCellValueFactory(new PropertyValueFactory<Leg, String>("flightNumber"));
        coachPriceCol.setCellValueFactory(new PropertyValueFactory<Leg, String>("priceOfCoachSeat"));
        colFirstClassPrice.setCellValueFactory(new PropertyValueFactory<Leg, String>("priceOfFirstClassSeat"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<Leg, LocalTime>("departureTimeInLocalTime"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<Leg, LocalTime>("arrivalTimeInLocalTime"));
        
        //load dummy data
        resultTable.setItems(getFlights());
	
	}
	
	public void bookFlight (ActionEvent event) {
		//type of trip
		
		System.out.println(selectedLeg);
		if(selectedLeg != null) {
			ServerInterface.INSTANCE.lock("BombSquad");
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Do you wanna proceed and book this selected tickets");
			alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				ServerInterface.INSTANCE.TicketDeduction("BombSquad", selectedLeg, "FirstClass" );
				ServerInterface.INSTANCE.unlock("BombSquad");
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
			
			
			
		}
		
	
	}
	
	
	
}
