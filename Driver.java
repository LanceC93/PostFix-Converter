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
                    System.out.print("Enter the Postfix Expression to evaluate: ");
                    System.out.println(console.nextLine());
                    postfixEvaluation(console.nextLine());
                    break;
                //handles the case where the user wants to convert
                case "pc":
                    //postfixConverter();
                    break;
                default:
                    System.out.println("Invalid Command. Try Again. ");
                    break;
            }

            System.out.print("Pick a Command: ");
            input = console.next();
        }
        console.close();
    }
    
    /**
     * takes ina string and evaluates it if it is a valide postfix expression
     * @param input the expression that the user inputs to get evaluated
     */
    public static void postfixEvaluation(String input) {
        LinkedListStack<Integer> calc = new LinkedListStack<>();
        String [] tokens = input.split(" ");
        //stores popped element so it can still be used
        int temp;
        for(int i = 0; i < tokens.length; i++) {
            //adds top two elements of stack
            if(calc.size() > 1) {
                if(tokens[i].equals("+")) {
                    temp = calc.pop();
                    temp = calc.pop() + temp;
                    calc.push(temp);
                } else if(tokens[i].equals("-")) { //subtracts top two elements of stack
                    temp = calc.pop();
                    temp = calc.pop() - temp;
                    calc.push(temp);
                } else if(tokens[i].equals("*")) { //multiplies top two elements of stack
                    temp = calc.pop();
                    temp = calc.pop() * temp;
                    calc.push(temp);
                } else if(tokens[i].equals("/")) { //divides top two elements of stack
                    temp = calc.pop();
                    temp = calc.pop() / temp;
                    calc.push(temp);
                }  
            } else {
                //if next token is an integer it will push it to the stack otherwise it will return null
                try {
                    temp = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Postfix Expression.");
                    return;
                }
                calc.push(temp);
            }
        }

        //checks that the process ended correctly
        if(calc.size() != 1) {
            System.out.println("Invalid Postfix Expression.");
        } else {
            System.out.println("The evaluation of this expression is " + calc.top() + ". ");
        }
    }
    
}
