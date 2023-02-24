//Name: Lance Cross
//Due Date: 2/26/23
//driver file for postfix conversion and evaluation
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Pick a Command: Postfix Conversion(pc), Postfix Evaluation(pe), or Quit(q) ");
        String input = console.next();

        //asks for input until quit or q is typed
        while(!input.equalsIgnoreCase("q")) {
            switch (input) {
                //handles the case where the user wants to evaluate
                case "pe":
                    System.out.println("Enter the Postfix Expression to evaluate: ");
                    //postfixEvaluation();
                    break;
                //handles the case where the user wants to convert
                case "pc":
                    //postfixConverter();
                    break;
                default:
                    System.out.println("Invalid Command. Try Again. ");
                    break;
            }

            System.out.print("Pick a Command: Postfix Conversion(pc), Postfix Evaluation(pe), or Quit(q) ");
            input = console.next();
        }
        console.close();
    }
    /*
    public static int postfixEvaluation(String input) {
        LinkedListStack<>
    }
    */
}
