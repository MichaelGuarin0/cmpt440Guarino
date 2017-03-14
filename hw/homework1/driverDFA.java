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
    ManWolf test = new ManWolf();
    try{
      test.openCSV();
    }
    catch(IOException e){
        System.out.println(e.getMessage());
    }
  }
}
