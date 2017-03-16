/**
  * file: driverDFA.java
  * author: Michael Guarino
  * course: CMPT 440
  * assignment: homework 1
  * due date: March 20, 2017
  * version: completed
  *
  * This file contains declaration and implementation
  * the driverDFA class.
  */

import java.io.IOException;

/**
  * driverDFA
  * 
  * This class implements the abstract class ManWolf.
  * It takes input from the command line and uses methods
  * from the ManWolf class implemented in the driverDFA class
  * to determine if the command line input is a valid solution
  * to the man-wolf-goat-cabbage problem.
  */
public class driverDFA extends ManWolf{

  /**
  * main
  *
  * The main method takes command line arguments
  * and determines if they are valid input and 
  * then calls methods of the driverDFA class
  * to determine if command line input is a valid
  * solution to man-wolf-goat-cabbage problem.
  *
  * Parameters:
  *   args: command line argument tested as a valid solution
  *   to man-wolf-goat-cabbage problem
  *
  * Return value: none
  */
  public static void main(String[] args){
    //determine if command line input is valid input
    if(args.length == 0){
      System.out.println("Please enter input.");
    }
    else if(! validateInput(args[0].toLowerCase())){
      System.out.println("This is not valid input.");
    }
    //determine if command line input is valid solution
    else{
      driverDFA tester = new driverDFA();
      if(tester.process(args[0].toLowerCase())){
        System.out.println("This is a solution.");
      }
      else{
        System.out.println("This is not a solution.");
      }
    }
  }
}
