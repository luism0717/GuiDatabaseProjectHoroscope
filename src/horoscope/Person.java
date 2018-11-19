package horoscope;


import java.awt.Label;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Person {

  private SimpleStringProperty firstName;
  private SimpleStringProperty lastName;
  private SimpleStringProperty zodiacName;
  private Image zodiacImage;


  public Person(String firstName, String lastName, String zodiacName) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.zodiacName = new SimpleStringProperty(zodiacName);
    zodiacImage = new Image("images/aquarius.png");
  }

  public Person(String firstName, String lastName, String zodiacName, Image zodiacImage) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.zodiacName = new SimpleStringProperty(zodiacName);
    this.zodiacImage = zodiacImage;
  }


  public String getFirstName() {
    return firstName.get();
  }

  public void setFirstName(String firstName) {
    this.firstName.set(firstName);
  }

  public String getLastName() {
    return lastName.get();
  }

  public void setLastName(String lastName) {
    this.lastName.set(lastName);
  }

  public String getZodiacName() {
    return zodiacName.get();
  }

  public void setZodiacName(String zodiacName) {
    this.zodiacName.set(zodiacName);
  }

  public Image getImage() {
    return zodiacImage;
  }

  public void setImage(Image newAquariusImage) {
    this.zodiacImage = newAquariusImage;
  }
}
