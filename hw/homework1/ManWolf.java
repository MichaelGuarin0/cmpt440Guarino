/**
  * file: ManWolf.java
  * author: Michael Guarino
  * course: CMPT 440
  * assignment: homework 1
  * due date: March 20, 2017
  * version: completed
  *
  * This file contains declaration of the
  * abstract class ManWolf.
  */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
  * ManWolf
  * 
  * The abstract class ManWolf contains all necessary methods
  * to recieve an input string and determines if that string is
  * a valid solution to the man-wolf-goat-cabbage problem.
  */
public abstract class ManWolf{

  /**
  * validateInput
  *
  * This static method takes a string from the command line
  * and determines if the characters in that string
  * are valid input commands (nwgc) in the
  * man-wolf-goat-cabbage problem.
  *
  * Parameters:
  *   args: a string of inputs taken from the command line
  *
  * Return value: boolean value indicating that the input string
  * is valid/invalid input (not determining if input is a valid/invalid solution)
  */
  public static boolean validateInput(String args){
    return args.matches("[nwgc]+");
  }

  /**
  * createTransTable
  *
  * This helper method to process creates a 2D table from the stateTransitions csv file
  * that defines all possible state transitions for given input.
  *
  * Parameters: none
  *
  * Return value: transTable that defines all possible state transitions.
  */
  private int[][] createTransTable() throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("stateTransitions.csv"));
    Scanner scanner = null;
    String line = null;
    int rowIndex = 0;
    int colIndex = 0;
    int[][] transTable = new int[11][4];
    //read file line by line
    while((line = reader.readLine()) != null){
      scanner = new Scanner(line);
      scanner.useDelimiter(",");
      while(scanner.hasNext()){
        //insert data from csv into transTable
        int data = scanner.nextInt();
        if(colIndex == 0){
          transTable[rowIndex][colIndex] = data;
        }
        else if(colIndex == 1){
          transTable[rowIndex][colIndex] = data;
        }
        else if(colIndex == 2){
          transTable[rowIndex][colIndex] = data;
        }
        else if(colIndex == 3){
          transTable[rowIndex][colIndex] = data;
        }
        colIndex ++;
      }
      colIndex = 0;
      rowIndex ++;
    }
    reader.close();
    return transTable;
  }

  /**
  * translateInput
  *
  * This helper method to process takes an input string, converts the character
  * of the input string defined as commands in the man-wolf-goat-cabbage
  * and converts them to numeric string values used later to index into
  * the transTable.
  *
  * Parameters:
  *   input: string of input values that correspond to state transitions
  *
  * Return value: string[] numeric string values translated from corresponding character values from input string
  */
  private String[] translateInput(String input){
    String[] splitInput = input.replaceAll("n", Integer.toString(0)).replaceAll("w", Integer.toString(1)).replaceAll("g", Integer.toString(2)).replaceAll("c", Integer.toString(3)).split("");
    return splitInput;
  }

  /**
  * accepted
  *
  * This helper method to process determines if the
  * input string is a valid solution to the man-wolf-goat-cabbage
  * problem.
  *
  * Parameters:
  *   state: integer corresponding to the final state after all
  *   state transition have been performed as defined by the input string
  *
  * Return value: boolean value that determines if acceptance state has
  * been reached
  */
  private boolean accepted(int state){
    int acceptanceState = 9;
    return state == acceptanceState;
  }

  /**
  * process
  *
  * This function makes recieves an input string and
  * makes one state transition for every for each
  * character in the input string. When all state
  * transitions are completed this function uses the
  * accepted helper function to determine if the input
  * string was valid input.
  *
  * Parameters:
  *   input: string of input values that correspond to state transitions
  *
  * Return value: boolean value that flags input value as a valid/invalid solution.
  */
  public boolean process(String input){
    //create transition table from csv
    int[][] transTable = null;
    try{
      transTable = createTransTable();
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
    //translate input into numeric string array
    String[] transInput = translateInput(input);
    //reset state to initial state
    int initialState = 0;
    int state = initialState;
    //perform state transitions
    for(int count = 0; count < transInput.length; count++){
      state = transTable[state][Integer.parseInt(transInput[count])];
    }
    return accepted(state);
  }
}