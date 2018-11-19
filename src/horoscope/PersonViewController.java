package horoscope;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class PersonViewController implements Initializable {

  // Arrays to hold url of each image
  private String[] imgArray = {"aquarius.png", "aries.png", "cancer.png", "capricornus.png",
      "gemini.png", "leo.png", "libra.png", "pisces.png", "sagittarius.png", "scorpio.png",
      "taurus.png", "virgo.png"};

  private Person selectedPerson;

  @FXML
  private Label firstNameLabel;

  @FXML
  private Label lastNameLabel;

  @FXML
  private Label zodiacSignLabel;

  @FXML
  private ImageView zodiacSignImage;

  private FileChooser fileChooser;
  private File filePath;


  //Method will allow user to change image
  public void chooseImageButtonPushed(ActionEvent event){
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

    fileChooser = new FileChooser();
    fileChooser.setTitle("Open Image");

    //set to user directory
    String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
    File userDirectory = new File(userDirectoryString);

    if (!userDirectory.canRead())
      userDirectory = new File("c:/");

    fileChooser.setInitialDirectory(userDirectory);

    this.filePath = fileChooser.showOpenDialog(stage);

    //Try to update image by loading new image
    try{
      BufferedImage bufferedImage = ImageIO.read(filePath);
      Image image = SwingFXUtils.toFXImage(bufferedImage, null);
      selectedPerson.setImage(image);
      zodiacSignImage.setImage(selectedPerson.getImage());

    }catch (IOException e){
System.err.println(e.getMessage());
    }
  }

  //Method accepts person to initialize the view
  public void initData(Person person) {
    selectedPerson = person;
    firstNameLabel.setText(selectedPerson.getFirstName());
    lastNameLabel.setText(selectedPerson.getLastName());
    zodiacSignLabel.setText(selectedPerson.getZodiacName());
    zodiacSignImage.setImage(selectedPerson.getImage());
  }

  public void changeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableViewParent = FXMLLoader.load(getClass().getResource("Horoscope.fxml"));
    Scene tableViewScene = new Scene(tableViewParent);

    //This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

}
