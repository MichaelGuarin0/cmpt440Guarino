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

public class ManWolf {
//takes a string from the command line and reports whether or not it represents a solution to the man-wolf-goat-cabbage problem
  public void openCSV() throws IOException{
    //open file input stream
    BufferedReader reader = new BufferedReader(new FileReader("stateTransitions.csv"));
    String line = null;
    Scanner scanner = null;
    int rowIndex = 0;
    int colIndex = 0;
    String[][] tranTable = new String[12][5];
    //read file line by line
    while((line = reader.readLine()) != null){
        scanner = new Scanner(line);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            String data = scanner.next();
            System.out.println(data);
            System.out.println("next string");
            /*
            if(colIndex == 0)
                tranTable[rowIndex][colIndex] = data;
            else if(colIndex == 1)
                tranTable[rowIndex][colIndex] = data;
            else if(colIndex == 2)
                tranTable[rowIndex][colIndex] = data;
            else if(colIndex == 3)
                tranTable[rowIndex][colIndex] = data;
            else if(colIndex == 4)
                tranTable[rowIndex][colIndex] = data;
            colIndex ++;*/
        }
        //colIndex = 0;
       //rowIndex ++;
    }
    reader.close();
  }
}