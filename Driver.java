//Name: Lance Cross
//Due Date: 2/26/23
//driver file for infix conversion and postfix evaluation
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Pick a Command: Infix Conversion(c), Postfix Evaluation(e), or Quit(q) ");
        String input = console.next();

        //asks for input until quit or q is typed
        while(!input.equalsIgnoreCase("q")) {
            switch (input) {
                //handles the case where the user wants to convert
                case "c":
                    System.out.print("Enter the Infix Expression to convert: ");
                    console.nextLine();
                    infixConvert(console.nextLine());
                    break;                
                //handles the case where the user wants to evaluate
                case "e":
                    System.out.print("Enter the Postfix Expression to evaluate: ");
                    console.nextLine();
                    postfixEvaluation(console.nextLine());
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
     * converts and infix expression to a postfix expression
     * @param input the infix expression that needs to be converted
     */
    public static void infixConvert(String input) {
        LinkedListStack<String> calc = new LinkedListStack<>();
        //splits string into iterable array
        String [] tokens = input.split(" ");
        //holds the converted expression
        String postfix = "";
        //iterates through each element of the given expression
        for(int i = 0; i < tokens.length; i++) {
            if(i > 0) {
                //adds addition or subtraction sign
                if(tokens[i].equals("+") || tokens[i].equals("-")) {
                    if(calc.size() == 0) {
                        calc.push(tokens[i]);
                    } else if (calc.top().equals("(")) {
                        calc.push(tokens[i]);
                    } else {
                        postfix += calc.pop() + " ";
                        calc.push(tokens[i]);
                    }
                } else if(tokens[i].equals("*") || tokens[i].equals("/")) { //adds multiplications or division sign
                    if(calc.size() == 0 || calc.top().equals("(")) {
                        calc.push(tokens[i]);    
                    } else if(calc.top().equals("*") || calc.top().equals("/")) {
                        postfix += calc.pop() + " ";
                        calc.push(tokens[i]);
                    } else {
                        calc.push(tokens[i]);
                    }
                } else if(tokens[i].equals("(")) { //adds left parenthesis
                    calc.push(tokens[i]);
                } else if(tokens[i].equals(")")) {
                    if(calc.top() != null) {
                        //empties the stack until it reaches a (
                        while(!calc.top().equals("(")) {
                            postfix += calc.pop() + " ";
                            //ends if it pops all elements of the stack and never reaches a (
                            if(calc.top() == null) {
                                System.out.println("Invalid Postfix Expression.");
                                return;
                            }
                        }
                        //pops the (
                        calc.pop();
                    } else {
                        System.out.println("Invalid Postfix Expression.");
                        return;
                    }                    
                }else {
                    //if next token is an integer it will push it to the stack otherwise it will return null
                    try {
                        postfix += Integer.parseInt(tokens[i]) + " ";
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Postfix Expression.");
                        return;
                    }
                }
            } else {
                //pushes ( if it is the first element of the expression and doesnt allow ) as a first element
                if(tokens[i].equals("(")) {
                    calc.push("(");
                } else if(tokens[i].equals(")")) {
                    System.out.println("Invalid Postfix Expression.");
                    return;
                } else {
                    //if next token is an integer it will add it to the string or say invalid expression
                    try {
                        postfix += Integer.parseInt(tokens[i]) + " ";
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Postfix Expression.");
                        return;
                    }
                }
            }        
        }
        //empties the stack if there are still operators in it but throws error if there are unclosed parentheses
        while(calc.size() != 0) {
            if(!calc.top().equals("(")) {
                postfix += calc.pop() + " ";
            } else {
                System.out.println("Invalid Postfix Expression.");
                return;
            }
        }
        System.out.println("The converted expression is " + postfix);
    }
    
    /**
     * takes in a string and evaluates it if it is a valide postfix expression
     * @param input the expression that the user inputs to get evaluated
     */
    public static void postfixEvaluation(String input) {
        LinkedListStack<Integer> calc = new LinkedListStack<>();
        //splits string into iterable array
        String [] tokens = input.split(" ");
        //stores popped element so it can still be used
        int temp;
        //iterates through every element of the postfix expression
        for(int i = 0; i < tokens.length; i++) {
            //adds top two elements of stack
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
            }  else { //adds numbers to stack
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