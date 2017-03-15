/*
@author: Michael Guarino
professor: Pablo Rivas
class: cmpt_440
desc: file containing the implementation of the class above
notes:
*/

//contains only the part that reads from standard input, calls the functions of manwolf class
//prints the result to standard output
import java.io.IOException;

public class driverDFA{
  public static void main(String[] args){
    //is command line input valid
    if(args.length == 0){
      System.out.println("Please enter input.");
    }
    else if(! ManWolf.validateInput(args[0])){
      System.out.println("This is not valid input.");
    }
    else{
      ManWolf test = new ManWolf();
      if(test.process(args[0])){
        System.out.println("This is a solution.");
      }
      else{
        System.out.println("This is not a solution.");
      }
    }
  }
}
