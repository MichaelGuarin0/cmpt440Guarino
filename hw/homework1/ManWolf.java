/*
@author: Michael Guarino
professor: Pablo Rivas
class: cmpt_440
desc: file containing the abstract class of the man-wolf-goat-cabbage problem, i.e., class definition, all its attributes, all its methods
notes:
Instructions: Using a table-driven DFA approach, write a Java class ManWolf that takes a string from the command line and reports whether or not it represents a solution to the man-wolf-goat-cabbage problem of Chapter 2 in the textbook. You will implement your ManWolf class (ManWolf.java) in a driver java file named driverDFA.java that contains only the part that reads from standard input, calls the functions of the ManWolf class, and prints the result to standard output. For example, it should have this exact same behavior:
   > java driverDFA gncgwng
   That is a solution.
   > java driverDFA ggggggggg
   That is not a solution.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//takes a string from the command line and reports whether or not it represents a solution to the man-wolf-goat-cabbage problem
public class ManWolf {
  //reads in csv and creates grade translation table
  private int[][] createTransTable() throws IOException{
    //open file input stream
    BufferedReader reader = new BufferedReader(new FileReader("stateTransitions.csv"));
    String line = null;
    Scanner scanner = null;
    int rowIndex = 0;
    int colIndex = 0;
    int[][] transTable = new int[11][4];
    //read file line by line
    while((line = reader.readLine()) != null){
        scanner = new Scanner(line);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            int data = scanner.nextInt();
            if(colIndex == 0)
                transTable[rowIndex][colIndex] = data;
            else if(colIndex == 1)
                transTable[rowIndex][colIndex] = data;
            else if(colIndex == 2)
                transTable[rowIndex][colIndex] = data;
            else if(colIndex == 3)
                transTable[rowIndex][colIndex] = data;
            colIndex ++;
        }
        colIndex = 0;
        rowIndex ++;
    }
    reader.close();
    return transTable;
  }

  //makes one transition for each character recieved in a given input string
  public boolean process(String input){
    //create transition table from csv
    int[][] transTable = null;
    try{
      transTable = createTransTable();
    }
    catch(IOException e){
        System.out.println(e.getMessage());
    }
    String[] transInput = translateInput(input);
    int state = 0;
    for(int count = 0; count < transInput.length; count++){
      state = transTable[state][Integer.parseInt(transInput[count])];
    }
    return accepted(state);
  }

  //helper function process to tranlate input string into format accepted by grade translation table
  private String[] translateInput(String input){
    //translate to correct column from char
    String[] splitInput = input.replaceAll("n", Integer.toString(0)).replaceAll("w", Integer.toString(1)).replaceAll("g", Integer.toString(2)).replaceAll("c", Integer.toString(3)).split("");
    return splitInput;
  }

  //check if string was accepted
  private boolean accepted(int state){
    //takes in state then determines if ends in an accepting state
    return state == 9;
  }

  //validate input from command line args
  public static boolean validateInput(String args){
        return args.matches("[nwgc]+");
  }
}