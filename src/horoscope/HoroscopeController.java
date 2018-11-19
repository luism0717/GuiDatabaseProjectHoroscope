package horoscope;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class HoroscopeController implements Initializable {

  // Instance variable used to create new person objects
  @FXML
  private TextField nameFieldFirst;

  // Instance variable used to create new person objects
  @FXML
  private TextField nameFieldLast;

  @FXML
  private TableView<Person> tableViewPersonInformation;

  @FXML
  private TableColumn<Person, String> firstNameColumn;

  @FXML
  private TableColumn<Person, String> lastNameColumn;

  @FXML
  private TableColumn<Person, String> zodiacColumn;

  @FXML
  private Button buttonViewDetail;

  @FXML
  private ComboBox zodiacComboBox;

  //Method will enable detailed view button
  public void userClickedTable() {
    this.buttonViewDetail.setDisable(false);
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //These items are configuring combo box
    zodiacComboBox.getItems().add("Capricorn");
    zodiacComboBox.getItems().add("Aquarius");
    zodiacComboBox.getItems().add("Pisces");
    zodiacComboBox.getItems().add("Aries");
    zodiacComboBox.getItems().add("Taurus");
    zodiacComboBox.getItems().add("Gemini");
    zodiacComboBox.getItems().add("Cancer");
    zodiacComboBox.getItems().add("Leo");
    zodiacComboBox.getItems().add("Virgo");
    zodiacComboBox.getItems().add("Libra");
    zodiacComboBox.getItems().add("Scorpio");
    zodiacComboBox.getItems().add("Sagittarius");
    zodiacComboBox.setVisibleRowCount(4);
    zodiacComboBox.setEditable(true);
    zodiacComboBox.setPromptText("Zodiac Sign");

    // Set up the columns in table
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
    zodiacColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("zodiacName"));

    //Load dummy data
    tableViewPersonInformation.setItems(getPeople());

    // Allow abler to select multiple rows at once
    tableViewPersonInformation.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    //Disable view detail button until is selected
    this.buttonViewDetail.setDisable(true);
  }

  //Method will return an ObservableList of People objects
  public ObservableList<Person> getPeople() {
    ObservableList<Person> people = FXCollections.observableArrayList();
    people.add(new Person("Jaquelyn", "Pena", "Sagittarius", new Image("/images/sagittarius.png")));
    people.add(new Person("Luis", "Mendez", "Cancer"));
    people.add(new Person("Jose", "Moreno", "Capricorn"));

    return people;
  }

  //Method will create a new user and add to table
  @FXML
  public void addButtonClicked() {

    Person newPerson = new Person(nameFieldFirst.getText(), nameFieldLast.getText(),
        zodiacComboBox.getValue().toString());

    // Get all items from table as list, then add new person to list
    tableViewPersonInformation.getItems().addAll(newPerson);
  }

  //Method will remove a user from the table
  @FXML
  public void removeButtonClicked() {
    ObservableList<Person> selectedRows, allPeople;
    allPeople = tableViewPersonInformation.getItems();

    //Gives us the row we selected
    selectedRows = tableViewPersonInformation.getSelectionModel().getSelectedItems();

    // Loop over the selected rows and remove person object
    for (Person person : selectedRows) {
      allPeople.remove(person);
    }
  }

  //Method will have person object change scene to detail person
  public void changeSceneToPersonView(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PersonView.fxml"));
    Parent tableViewPersonInformationParent = loader.load();

    Scene tableViewPersonInformationScene = new Scene(tableViewPersonInformationParent);

    //Access controller and call method
    PersonViewController controller = loader.getController();
    controller.initData(tableViewPersonInformation.getSelectionModel().getSelectedItem());

    //This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(tableViewPersonInformationScene);
    window.show();
  }

}

