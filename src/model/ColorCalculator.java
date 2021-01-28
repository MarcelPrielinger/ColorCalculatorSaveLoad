package model;

import java.util.Scanner;

public class ColorCalculator {
  private ModularCounter red;
  private ModularCounter green;
  private ModularCounter blue;
  
  public ColorCalculator() {
    this.red = new ModularCounter(0, 256);
    this.green = new ModularCounter(0, 256);
    this.blue = new ModularCounter(0, 256);
  }
  
  public void changeColorViaAbsoluteValue(ColorCode cc, String value) {
    try {
      int i = Integer.parseInt(value);
      changeColorViaAbsoluteValue(cc, i);
    }
    catch (Exception e) {
      System.out.println("Invalid color Value!");
    }
  }
  
  //Implement method called by controller (or by main) 
  public void changeColorViaAbsoluteValue(ColorCode cc, int value) {
    //Compute the real value for the color
    //Possible exceptions if: not a number or not in range [0..255] 
    try {
      if (cc == ColorCode.RED) {
        red = new ModularCounter(value, 256);
      }
      if (cc == ColorCode.GREEN) {
        green = new ModularCounter(value, 256);
      }
      if (cc == ColorCode.BLUE) {
        blue = new ModularCounter(value, 256);
      }
    }
    catch (Exception e) {
      System.out.println("Invalid color Value!");
    }
    
    //Always display state in the console and update GUI
    System.out.println("State: " + this + System.lineSeparator());
  }
  
  public void changeColorViaRelativeValue(ColorCode cc, String value) {
    try {
      int i = Integer.parseInt(value);
      changeColorViaRelativeValue(cc, i);
    }
    catch (Exception e) {
      System.out.println("Invalid color Value!");
    }
  }
  
  //Implement method called by controller (or by main)
  public void changeColorViaRelativeValue(ColorCode cc, int value) {
    //Increment correct color, only if it has a correct value
    if (cc == ColorCode.RED && red != null) {
      red.update(value);
    }
    else {
      if (cc == ColorCode.GREEN && green != null) {
        green.update(value);
      }
      else {
        if (cc == ColorCode.BLUE && blue != null) {
          blue.update(value);
        }
        else {
          return;  //Not a good color!
        }
      }
    }
    
    //Always display state in the console and update GUI
    System.out.println("State: " + this + "\n");
  }
  
  //The view calls these accessors
  public int getRed() {
    return (red == null ? -1 : red.getValue());
  }
  
  public int getGreen() {
    return (green == null ? -1 : green.getValue());
  }
  
  public int getBlue() {
    return (blue == null ? -1 : blue.getValue());
  }
  
  public String getHex() {
    int r = getRed();
    int g = getGreen();
    int b = getBlue();
    
    return gh(r) + gh(g) + gh(b);
  }
  
  //Primarily for debugging purposes
  public String toString() {
    return "Model[red=" + red + ", green=" + green + ", blue=" + blue + "]";
  }
  
  //Helper method
  private String gh(int i) {
    String hexDigits = "0123456789ABCDEF";
    return "" + hexDigits.charAt(i / 16) + hexDigits.charAt(i % 16);
  }
  
  /////////////////////////////////////////////////////////////
  //
  //Driver Program
  //
  //For testing Model independently from View and Controller
  //Inside Java Target set the Main Class to colorMachine.Model
  //
  /////////////////////////////////////////////////////////////
  public static void main(String[] args) {
    //Prompt for construction arguments; get object to test; Don't use constructor directly
    ColorCalculator m = new ColorCalculator();
    System.out.println("State: " + m + "\n");
    
    Scanner sc = new Scanner(System.in);
    
    for (; ; ) {
      try {
        System.out.println("Menu");
        System.out.println("  a - changeColorViaAbsoluteValue");
        System.out.println("  r - changeColorViaRelativeValue");
        System.out.println("  ? - view all accessors");
        System.out.println("  q - quit");
        System.out.println("Enter Command");
        
        String selection = sc.next();
  
        if (selection.equals("a") || selection.equals("r")) {
          System.out.println("  Enter color    ");
          String colorSelection = sc.next();
          ColorCode color = ColorCode.RED;
    
          if (colorSelection.equalsIgnoreCase("red")) {
            color = ColorCode.RED;
          }
          if (colorSelection.equalsIgnoreCase("green")) {
            color = ColorCode.GREEN;
          }
          if (colorSelection.equalsIgnoreCase("blue")) {
            color = ColorCode.BLUE;
          }
    
          System.out.println("  Enter value");
          String value = sc.next();
    
          if (selection.equals("a")) {
            m.changeColorViaAbsoluteValue(color, value);
          }
          else {
            m.changeColorViaRelativeValue(color, value);
          }
        }
        else {
          if (selection.equals("?")) {
            System.out.println("  getRed   = " + m.getRed());
            System.out.println("  getGreen = " + m.getGreen());
            System.out.println("  getBlue  = " + m.getBlue());
            if (m.getRed() == -1 || m.getGreen() == -1 || m.getBlue() == -1) {
              System.out.println("  No getHex because some colors missing");
            }
            else {
              System.out.println("  getHex   = " + m.getHex());
            }
            System.out.println();
          }
          else {
            if (selection.equals("q")) {
              break;
            }
            else {
              System.out.println("\"" + selection + "\" is unknown command");
            }
          }
        }
      }
      catch (Exception e) {
        System.out.println("  Exception Caught/Handled: " + e.getMessage());
      }
    }
  }
}