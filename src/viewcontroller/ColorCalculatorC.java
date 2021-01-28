package viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ColorCalculator;
import model.ColorCode;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ColorCalculatorC implements Initializable {
  private ColorCalculator model;
  
  @FXML
  private Button btnColor;
  
  @FXML
  private Label lblColor;
  
  @FXML
  private TextField txtRed;
  
  @FXML
  private TextField txtGreen;
  
  @FXML
  private TextField txtBlue;
  
  @FXML
  private void btn_RedPlus() {
    model.changeColorViaRelativeValue(ColorCode.RED, 10);
    this.update(true);
  }
  
  @FXML
  private void btn_RedMinus() {
    model.changeColorViaRelativeValue(ColorCode.RED, -10);
    this.update(true);
  }
  
  @FXML
  private void btn_GreenPlus() {
    model.changeColorViaRelativeValue(ColorCode.GREEN, 10);
    this.update(true);
  }
  
  @FXML
  private void btn_GreenMinus() {
    model.changeColorViaRelativeValue(ColorCode.GREEN, -10);
    this.update(true);
  }
  
  @FXML
  private void btn_BluePlus() {
    model.changeColorViaRelativeValue(ColorCode.BLUE, 10);
    this.update(true);
  }
  
  @FXML
  private void btn_BlueMinus() {
    model.changeColorViaRelativeValue(ColorCode.BLUE, -10);
    this.update(true);
  }
  
  @FXML
  private void txt_RedChanged() {
    model.changeColorViaAbsoluteValue(ColorCode.RED, this.txtRed.getText());
    this.update(false);
  }
  
  @FXML
  private void txt_GreenChanged() {
    model.changeColorViaAbsoluteValue(ColorCode.GREEN, this.txtGreen.getText());
    this.update(false);
  }
  
  @FXML
  private void txt_BlueChanged() {
    model.changeColorViaAbsoluteValue(ColorCode.BLUE, this.txtBlue.getText());
    this.update(false);
  }
  
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    model = new ColorCalculator();
    this.update(true);
  }
  
  public static void show(Stage stage) {
    try {
      Parent root = FXMLLoader.load(ColorCalculator.class.getResource("/viewcontroller/colorCalculatorV.fxml"));
      
      //setting scene settings
      stage.setTitle("Color Calculator");
      stage.setScene(new Scene(root));
      
      //load icon
      stage.getIcons().add(new Image("res/ico.png"));
      
      stage.show();
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
  
  private void update(boolean updateInputs) {
    int r = model.getRed();
    int g = model.getGreen();
    int b = model.getBlue();
    
    if (updateInputs) {
      this.txtRed.setText(Integer.toString(r));
      this.txtGreen.setText(Integer.toString(g));
      this.txtBlue.setText(Integer.toString(b));
    }
    
    this.btnColor.setStyle("-fx-background-color: rgb(" + r + "," + g + ", " + b + ");");
    this.lblColor.setText("Hex: " + model.getHex());
  }

  @FXML
  private void load() {
    String s;
    int count = 0;

    try {
      FileReader output = new FileReader("Data.txt");
      BufferedReader reader = new BufferedReader(output);
      while ((s = reader.readLine()) != null)
      {
        if(count == 1)
        {
          model.changeColorViaAbsoluteValue(ColorCode.RED,s);
        }
        if(count == 2)
        {
          model.changeColorViaAbsoluteValue(ColorCode.GREEN,s);
        }
        if(count == 3)
        {
          model.changeColorViaAbsoluteValue(ColorCode.BLUE,s);
          break;
        }
        count++;
      }

      reader.close();
      output.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    update(true);
  }

    @FXML
  private void save()
  {
    try
    {
      FileWriter input = new FileWriter("Data.txt");
      BufferedWriter writer = new BufferedWriter(input);
      writer.write("Color File Format 1.0");
      writer.newLine();
      writer.write(txtRed.getText());
      writer.newLine();
      writer.write(txtGreen.getText());
      writer.newLine();
      writer.write(txtBlue.getText());
      writer.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}


