package main;//Application to build/connect components in the colorCalculator package

import javafx.application.Application;
import javafx.stage.Stage;
import viewcontroller.ColorCalculatorC;

public class TheMain extends Application {
  @Override
  public void start(Stage primaryStage) {
    ColorCalculatorC.show(primaryStage);
  }
  
  public static void main(String[] args) {
    //launch the JavaFX Application
    launch(args);
  }
}